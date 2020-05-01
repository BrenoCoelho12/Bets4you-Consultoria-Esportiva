package com.desenvolvimento.pibetting.config.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import com.desenvolvimento.pibetting.config.JPAConfig;
import com.desenvolvimento.pibetting.config.MailConfig;
import com.desenvolvimento.pibetting.config.SecurityConfig;
import com.desenvolvimento.pibetting.config.ServiceConfig;
import com.desenvolvimento.pibetting.config.WebConfig;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.MultipartConfigElement;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {WebConfig.class, MailConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		
        return new Filter[] {  };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

	
}
