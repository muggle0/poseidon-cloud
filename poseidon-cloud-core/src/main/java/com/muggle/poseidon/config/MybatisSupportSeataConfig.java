package com.muggle.poseidon.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @program: poseidon-cloud
 * @description:
 * @author: muggle
 * @create: 2020-03-18 14:58
 */


@Configuration
//@SuppressWarnings("all")
@ConditionalOnProperty(prefix = "poseidon.mybatis",name = "support",havingValue = "seata")
public class MybatisSupportSeataConfig {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(MybatisSupportSeataConfig.class);

    @Autowired
    private DataSource dataSource;

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis-plus.type-aliases-package}")
    private String typeAliasesPackage;

    @Bean(name = "dataSourceProxy")
    public DataSourceProxy dataSourceProxy() {
        return new DataSourceProxy(dataSource);
    }

/*
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return sqlSessionFactoryBean.getObject();
    }*/

    @Bean(name = "globalConfig")
    public GlobalConfig globalConfiguration() {
        log.info("初始化GlobalConfiguration");
        GlobalConfig config = new GlobalConfig();
        // 控制台打印 plus banner
        config.setBanner(false);
        return config;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean(@Qualifier(value = "globalConfig") GlobalConfig configuration,@Qualifier(value = "dataSourceProxy")DataSourceProxy dataSourceProxy) throws Exception {
        log.info("初始化SqlSessionFactory");
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptor);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new MybatisDataHandler()});
        try {
            sqlSessionFactoryBean.setGlobalConfig(configuration);
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "dataSourceProxy")DataSourceProxy dataSourceProxy) {
        log.info("初始化transactionManager");
        return new DataSourceTransactionManager(dataSourceProxy);
    }


}
