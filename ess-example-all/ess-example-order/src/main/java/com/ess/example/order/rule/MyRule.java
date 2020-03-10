package com.ess.example.order.rule;

import java.util.List;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * 自定义路由规则
 * @author Administrator
 *
 */
public class MyRule implements IRule {

	private ILoadBalancer loadBalancer;
	
	@Override
	public Server choose(Object arg0) {
		List<Server> servers = loadBalancer.getAllServers();
		for(Server server : servers) {
			System.out.println(server.getHost()+":"+server.getPort());
		}
		return servers.get(0);
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return  this.loadBalancer;
	}

	@Override
	public void setLoadBalancer(ILoadBalancer loadBalancer) {
		this.loadBalancer = loadBalancer;
	}

}
