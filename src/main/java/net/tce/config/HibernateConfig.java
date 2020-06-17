package net.tce.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
//@ComponentScan({ "net.tce.config" })
public class HibernateConfig {
	Logger log4j = Logger.getLogger( this.getClass());

		@Value("${c3p0.initial_pool_size}")
	    private int initialPoolSize;
	
	 	@Value("${c3p0.max_size}")
	    private int maxSize;
	 
	    @Value("${c3p0.min_size}")
	    private int minSize;
	 
	    @Value("${c3p0.acquire_increment}")
	    private int acquireIncrement;
	 
	    @Value("${c3p0.idle_test_period}")
	    private int idleTestPeriod;
	 
	    @Value("${c3p0.max_statements}")
	    private int maxStatements;
	 
	    @Value("${c3p0.max_idle_time}")
	    private int maxIdleTime;
	 
	    @Value("${jdbc.url}")
	    private String url;
	 
	    @Value("${jdbc.username}")
	    private String username;
	 
	    @Value("${jdbc.password}")
	    private String password;
	 
	    @Value("${jdbc.driverClassName}")
	    private String driverClassName;
	
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        try {
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan(new String[] { "net.tce.model" });
	        sessionFactory.setHibernateProperties(hibernateProperties());
		} catch (PropertyVetoException e) {
			log4j.fatal("### ERROR AL CREAR DATA_SOURCE ->"+e);
			e.printStackTrace();
		}
        
        return sessionFactory;
     }
	
   /* @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }*/
    
    /**
     * Se crea un datasource con el pool de conexiones C3PO
     * @return
     * @throws PropertyVetoException	
     */
    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setMaxPoolSize(maxSize);
        dataSource.setMinPoolSize(minSize);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setUser(username);
        dataSource.setDriverClass(driverClassName);
        return dataSource;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.default_schema", environment.getRequiredProperty("hibernate.default_schema"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}


