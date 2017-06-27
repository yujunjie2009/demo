package com.newtiming.finance.listener;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;


/**
 * @author yujunjie
 * @created 2017-05-22
 * @describe 
 */
public class InitListener implements ServletContextListener{

    public void contextDestroyed(ServletContextEvent sce) {}
    
    /*static {
        String devConf = "dev";  
        String profile = "dev";  
        if (profile == null) {  
          profile = devConf;  
        }
      
        String log4jPropertiesPath = InitListener.class.getResource("/conf/") + profile  + "/log4j2.xml";  
        //动态配置log4j.properties的路径  
        try {  
          URL url = new URL(log4jPropertiesPath);  
           
           * System.setProperty("log4j.configurationFile", url.toString()); 
           * LoggerContext context = (LoggerContext) LogManager.getContext(false); 
           * context.reconfigure(); 
           * 这种方法也行，但是代码中修改系统属性，不太好维护 
             
          ConfigurationSource source = new ConfigurationSource(url.openStream(), url);  
          LoggerContext context = Configurator.initialize(null, source);  
          XmlConfiguration xmlConfig = new XmlConfiguration(context, source);  
          context.start(xmlConfig);  
        } catch (Exception e) {  
          e.printStackTrace();  
        }  
        System.out.println("----------------------------------------------");  
        System.out.println("You are using the log4j properties: " + log4jPropertiesPath);  
        System.out.println("----------------------------------------------");  
      
      } */ 
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
		try {
			InputStream in = InitListener.class.getClassLoader().getResourceAsStream("properties/init_params.properties");
			Properties initProperties = new Properties();
			initProperties.load(in);
			
			for(String propName : initProperties.stringPropertyNames()){
				if(System.getProperty(propName) == null){
					System.setProperty(propName, initProperties.getProperty(propName));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//        System.out.println(sce.getServletContext().getInitParameter("USERNAME"));
    }
}
