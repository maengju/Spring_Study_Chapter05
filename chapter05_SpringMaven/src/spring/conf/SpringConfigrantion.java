package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.dao.UserDAOMybatis;
import user.main.HelloSpring;
import user.service.UserDeleteService;
import user.service.UserInsertService;
import user.service.UserSearchService;
import user.service.UserSelectService;
import user.service.UserUpdateService;


@Configuration
@PropertySource("classpath:spring/db.properties")
public class SpringConfigrantion {
	
	
	@Value("${jdbc.driver}")
    private String driver;
	@Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    
    //connection pool
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setMaxTotal(20);
		basicDataSource.setMaxIdle(3);
		
		return basicDataSource;
	}
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("user/dao/userMapper.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public DataSourceTransactionManager  transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager =
										new DataSourceTransactionManager(dataSource());
		return dataSourceTransactionManager;
	}
//---------------------------------------------------------------------------------------------------

	@Bean
	public HelloSpring helloSpring() {
		return new HelloSpring();
	}
	
	@Bean
	public UserInsertService userInsertService() {
		return new UserInsertService();
	}
	
	@Bean
	public UserSelectService userSelectService() {
		return new UserSelectService();
	}
	
	@Bean
	public UserUpdateService userUpdateService() {
		return new UserUpdateService();
	}
	
	@Bean
	public UserDeleteService userDeleteService() {
		return new UserDeleteService();
	}
	
	@Bean
	public UserSearchService userSearchService() {
		return new UserSearchService();
	}
	
	@Bean
	public UserDTO userDTO() {
		return new UserDTO();
	}
	
	@Bean UserDAO userDAO()  {
		
		return new UserDAOMybatis();
		
	}
	
	
}
