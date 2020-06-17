package net.tce.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration 
public class MailConfig {
	    @Value("${email.host}")
	    private String host;

	    @Value("${email.port}")
	    private Integer port;
	    
	    @Value("${email.username}")
	    private String username;

	    @Value("${email.password}")
	    private String password;
	    
	    @Value("${email.protocol}")
	    private String protocol;
	    
	    @Value("${email.username.clave}")
		private boolean isUsuarioclave;
	    
	    @Value("${email.remite}")
		 private String emailRemitente;

	    @Bean
	    public JavaMailSender javaMailService() {
	        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	        javaMailSender.setHost(host);
	        javaMailSender.setPort(port);
	        javaMailSender.setUsername(isUsuarioclave ? username:emailRemitente);
	        javaMailSender.setPassword(password);
	        javaMailSender.setJavaMailProperties(getMailProperties());

	        return javaMailSender;
	    }

	    private Properties getMailProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("mail.transport.protocol", protocol);
	        properties.setProperty("mail.smtp.auth", "true");
	        properties.setProperty("mail.smtp.starttls.enable", "true");
	        properties.setProperty("mail.debug", "true");
	        return properties;
	    }
}
