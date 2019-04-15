package com.java.src.lululu.config;


import com.zaxxer.hikari.HikariDataSource;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.ExecuteListenerProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.conf.SettingsTools;
import org.jooq.conf.StatementType;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.simpleflatmapper.jooq.SfmRecordMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * Created by jimmy on 2016/11/23.
 */

@Configuration
public class JooqSpringBootConfiguration {

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driverClassName}")
    private String dataSourceClassName;

    @Value("${spring.datasource.hikari.idleTimeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.connectionTimeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.maxLifetimeMs}")
    private int maxLifetimeMs;

    @Value("${spring.datasource.hikari.leakDetectionThresholdMs}")
    private int leakDetectionThresholdMs;

    @Value("${spring.datasource.hikari.connectionTestQuery}")
    private String connectionTestQuery;

    @Autowired
    private Environment env;

    @Bean("dataSource")
    @Primary
    public DataSource primaryDataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(maximumPoolSize);
        ds.setMaxLifetime(maxLifetimeMs);
        ds.setIdleTimeout(idleTimeout);
        ds.setDriverClassName(dataSourceClassName);
        ds.setJdbcUrl(dataSourceUrl);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setConnectionTestQuery(connectionTestQuery);
        ds.setLeakDetectionThreshold(leakDetectionThresholdMs);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
        ds.addDataSourceProperty("prepStmtCacheSize", "250");
        ds.addDataSourceProperty("useServerPrepStmts", "true");
        ds.addDataSourceProperty("cachePrepStmts", "true");
        return ds;
    }

    @Bean
    @DependsOn("dataSource")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
//    @DependsOn("dataSource")
//    public ConnectionProvider connectionProvider(DataSource dataSource) {
//        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
//    }

    //    @Bean
//    public TransactionProvider transactionProvider() {
//        return new SpringTransactionProvider();
//    }
    @Bean
    @DependsOn("dataSource")
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }


    @Bean
    public ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }

    @Bean
    public ExecuteListenerProvider executeListenerProvider(ExceptionTranslator exceptionTranslator) {
        return new DefaultExecuteListenerProvider(exceptionTranslator);
    }

    @Bean
    public org.jooq.Configuration jooqConfig(@Autowired ConnectionProvider connectionProvider, @Autowired
            ExecuteListenerProvider executeListenerProvider) {
        Settings settings = SettingsTools.defaultSettings();
        settings.setStatementType(StatementType.PREPARED_STATEMENT);
        settings.withRenderSchema(false);
        //格式化sql
//        settings.withRenderFormatted(true);
        org.jooq.Configuration derive = new DefaultConfiguration()
                .derive(connectionProvider)
                .derive(settings)
                .derive(executeListenerProvider)
                .derive(SQLDialect.MYSQL)
                .set(DefaultExecuteListenerProvider.providers(new SlowQueryListener()));
        derive.set(new SfmRecordMapperProvider());
        return derive;
    }

    @Bean
    public DSLContext dsl(org.jooq.Configuration jooqConfig) {
        return new DefaultDSLContext(jooqConfig);
    }

//
//    @Bean
//    @DependsOn("dataSource")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
//        sfb.setDataSource(dataSource);
//        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        sfb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
//        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
//        sfb.setPlugins(new Interceptor[]{new PageInterceptor()});
//        return sfb.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
//        modelMapper.getConfiguration().addValueReader(new RecordValueReader());
//        return modelMapper;
//    }


}
