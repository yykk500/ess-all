package com.ess.framework.boot.gloabl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.ess.framework.commons.utils.NetUtils;

/**
 * 启动类，获取随机端口
 * @author Luozelin
 *
 */
public class StartCommand {
	/** Logger used by this class. Available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public StartCommand(String[] args) {
		boolean isServerPort = false;
		String serverPort = "";
		if(args != null) {
			for(String arg : args) {
				// 判断是否启动命令指定了端口
				if(StringUtils.hasText(arg) && arg.startsWith("--server.port") ) {
					isServerPort = true;
					serverPort = arg;
				}
			}
		}
		// 没有指定端口,则随机生成一个。
		if(!isServerPort) {
			int  port = getAvailablePort();
			logger.info("current server.port={}"+port);
			System.setProperty("server.port", String.valueOf(port));
		}else {
			logger.info("current server.port={}"+serverPort.split("=")[1]);
			System.setProperty("server.port", serverPort.split("=")[1]);
		}
	}
	
	/**
	 * 获取可用端口
	 * @return
	 */
	public static int getAvailablePort() {
		int max = 65535;
		int min =2000;
		Random random = new Random();
		int port = random.nextInt(max) % (max-min+1)+min;
		boolean using = NetUtils.isLoclePortUsing(port);
		if(using) {
			return getAvailablePort();
		}
		return  port;
	}
}
