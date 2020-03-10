package com.ess.framework.boot.gloabl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller 基础类。所有项目中的Controller 必须继承此类
 * @author Luozelin
 *
 */
public abstract class AbstractController {

	/** Logger used by this class. Available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}

