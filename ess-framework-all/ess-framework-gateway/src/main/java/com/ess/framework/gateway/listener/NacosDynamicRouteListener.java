package com.ess.framework.gateway.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties(prefix = "route.nacos")
public class NacosDynamicRouteListener implements ApplicationEventPublisherAware {
	
	private String dataId = "ess-gateway-router";

	private String group = "DEFAULT_GROUP";

	private String namespace = null;

	@Value("${spring.cloud.nacos.config.server-addr}")
	private String serverAddr;

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	private ApplicationEventPublisher applicationEventPublisher;

	private static final List<String> ROUTE_LIST = new ArrayList<String>();

	@PostConstruct
	public void dynamicRouteByNacosListener() {
		try {
			Properties properties = new Properties();
			properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
			if (namespace != null && !namespace.isEmpty()) {
				properties.put(PropertyKeyConst.NAMESPACE, namespace);
			}
			ConfigService configService = NacosFactory.createConfigService(properties);
			String json = configService.getConfig(dataId, group, 5000);
			System.out.println(json);
			// 启动加载初始化如有配置
			updateRouter(json);
			// 监听配置变化事件
			configService.addListener(dataId, group, new Listener() {

				public void receiveConfigInfo(String configInfo) {
					// 配置变化后更新路由器
					updateRouter(configInfo);
				}

				public Executor getExecutor() {
					return null;
				}
			});
		} catch (NacosException e) {
			e.printStackTrace();
		}
	}

	private void updateRouter(String configInfo) {
		if (configInfo == null) {
			return;
		}
		clearRoute();
		try {
			List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
			for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
				addRoute(routeDefinition);
			}
			publish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearRoute() {
		for (String id : ROUTE_LIST) {
			this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
		}
		ROUTE_LIST.clear();
	}

	private void addRoute(RouteDefinition definition) {
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			ROUTE_LIST.add(definition.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void publish() {
		this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
