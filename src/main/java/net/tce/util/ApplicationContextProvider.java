package net.tce.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Esta clase se ocupa de proporcionar una instancia del contexto (Spring) corriente
 * @author Goyo
 *
 *
 *	Por ejemplo asi se aplica:
				ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
				PaisDao paisDaotc=(PaisDao) ctx.getBean("paisDao");
 */
public class ApplicationContextProvider implements ApplicationContextAware{
	 private static ApplicationContext ctx = null;
	 public static ApplicationContext getApplicationContext() {
		 return ctx;
	 }
	 
	 @SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		 this.ctx = ctx;
	 }
	}
