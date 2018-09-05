//package com.yangy.common.database;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class DataSourceConfigurer {
//
//    /**
//     * master DataSource
//     *
//     * @return data source
//     * @Primary 注解用于标识默认使用的 DataSource Bean，因为有5个 DataSource Bean，该注解可用于 master
//     * 或 slave DataSource Bean, 但不能用于 dynamicDataSource Bean, 否则会产生循环调用
//     * @ConfigurationProperties 注解用于从 application.yml 文件中读取配置，为 Bean 设置属性
//     */
//    @Bean("master")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
//    public DataSource master() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave alpha data source.
//     *
//     * @return the data source
//     */
//    @Bean("slaveAlpha")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-alpha")
//    public DataSource slaveAlpha() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave beta data source.
//     *
//     * @return the data source
//     */
//    @Bean("slaveBeta")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-beta")
//    public DataSource slaveBeta() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave gamma data source.
//     *
//     * @return the data source
//     */
//    @Bean("slaveGamma")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave-gamma")
//    public DataSource slaveGamma() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Dynamic data source.
//     *
//     * @return the data source
//     */
//    @Bean("dynamicDataSource")
//    public DataSource dynamicDataSource() {
//        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new HashMap<>(4);
//        dataSourceMap.put(DataSourceKey.master.name(), master());
//        dataSourceMap.put(DataSourceKey.slaveAlpha.name(), slaveAlpha());
//        dataSourceMap.put(DataSourceKey.slaveBeta.name(), slaveBeta());
//        dataSourceMap.put(DataSourceKey.slaveGamma.name(), slaveGamma());
//
//        // 将 master 数据源作为默认指定的数据源
//        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
//        // 将 master 和 slave 数据源作为指定的数据源
//        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
//
//        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
//        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
//
//        // 将 Slave 数据源的 key 放在集合中，用于轮循
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
//        return dynamicRoutingDataSource;
//    }
//
//    /**
//     * 配置 SqlSessionFactoryBean
//     *
//     * @return the sql session factory bean
//     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
//     * Bean 的属性中，如果没有使用 *.mybatis 则可以不用该配置，否则将会产生 invalid bond statement 异常
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * 注入 DataSourceTransactionManager 用于事务管理
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//
//}