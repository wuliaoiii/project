//package com.yangy.common.database;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author yang yang
// * @create 2018/8/24
// * @since 1.0.0
// */
//@Slf4j
//public class DynamicDataSourceContextHolder {
//
//    /**
//     * 用于在切换数据源时保证不会被其他线程修改
//     */
//    private static Lock lock = new ReentrantLock();
//
//    /**
//     * 用于轮循的计数器
//     */
//    private static int counter = 0;
//
//    /**
//     * Maintain variable for every thread, to avoid effect other thread
//     */
//    private static final ThreadLocal<Object> CONTEXT_HOLDER = new ThreadLocal<>();
//
//
//    /**
//     * All DataSource List
//     */
//    public static List<Object> dataSourceKeys = new ArrayList<>();
//
//    /**
//     * The constant slaveDataSourceKeys.
//     */
//    public static List<Object> slaveDataSourceKeys = new ArrayList<>();
//
//    /**
//     * To switch DataSource
//     *
//     * @param key the key
//     */
//    public static void setDataSourceKey(String key) {
//        CONTEXT_HOLDER.set(key);
//    }
//
//    /**
//     * Use master data source.
//     */
//    public static void useMasterDataSource() {
//        CONTEXT_HOLDER.set(DataSourceKey.master);
//    }
//
//    /**
//     * 当使用只读数据源时通过轮循方式选择要使用的数据源
//     */
//    public static void useSlaveDataSource() {
//        lock.lock();
//
//        try {
//            int datasourceKeyIndex = counter % slaveDataSourceKeys.size();
//            CONTEXT_HOLDER.set(String.valueOf(slaveDataSourceKeys.get(datasourceKeyIndex)));
//            counter++;
//        } catch (Exception e) {
//            log.error("Switch slave datasource failed, error message is {}", e.getMessage());
//            useMasterDataSource();
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//
//    public static String getDataSourceKey() {
//        return (String) CONTEXT_HOLDER.get();
//    }
//
//
//    public static void clearDataSourceKey() {
//        CONTEXT_HOLDER.remove();
//    }
//
//
//    public static boolean containDataSourceKey(String key) {
//        return dataSourceKeys.contains(key);
//    }
//
//
//}