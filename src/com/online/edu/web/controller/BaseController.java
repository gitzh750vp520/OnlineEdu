package com.online.edu.web.controller;

import com.online.edu.web.logger.Logger;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());  
     
    public static void logBefore(Logger logger, String interfaceName){  
        logger.info("");  
        logger.info("start");  
        logger.info(interfaceName);  
    }  
      
    public static void logAfter(Logger logger){  
        logger.info("end");  
        logger.info("");  
    }

}
