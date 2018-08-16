package com.yangy.generator.utils;

import com.yangy.generator.config.GenerateConfig;
import com.yangy.generator.model.Column;
import com.yangy.generator.model.TableClass;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author yangy
 * @email java_yangy@126.com
 * @create 2018/6/13
 * @since 1.0.0
 */
public class CodeGenerateUtils {

    public static void main(String[] args) throws Exception {
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generateAll();
    }

    public static Connection getConnection() throws Exception {
        Class.forName(GenerateConfig.getDRIVER());
        Connection connection = DriverManager.getConnection(GenerateConfig.getURL(), GenerateConfig.getUSER(), GenerateConfig.getPASSWORD());
        return connection;
    }

    public void generateAll() {
        try {
            List<TableClass> tables = getTables();
            for (TableClass table : tables) {
                generate(table);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    public static void generate(TableClass tableClass) {
        try {
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableClass.getTableName(), "%");
            //生成Api文件
            generateApiFile(resultSet, tableClass);
            //生成Hystrix文件
            generateHystrixFile(resultSet, tableClass);
            //生成Mybatis文件
            generateMybatisFile(resultSet, tableClass);
            //生成Mapper文件
            generateMapperFile(resultSet, tableClass);
            //生成服务实现层文件
            generateServiceImplFile(resultSet, tableClass);
            //生成服务层接口文件
            generateServiceInterfaceFile(resultSet, tableClass);
            //生成Controller层文件
            generateControllerFile(resultSet, tableClass);

            ResultSet dataColumns = databaseMetaData.getColumns(null, "%", tableClass.getTableName(), "%");

            //生成Model文件
            generateEntityFile(dataColumns, tableClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    /**
     * 生成API
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateApiFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "Api.java";
        String dicPath = GenerateConfig.getDISKPATH() + "api/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "api.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 生成API hystrix
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateHystrixFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "Hystrix.java";
        String dicPath = GenerateConfig.getDISKPATH() + "api/hystrix/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "hystrix.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 生成控制器
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateControllerFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "Controller.java";
        String dicPath = GenerateConfig.getDISKPATH() + "controller/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "controller.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 生成实体类
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateEntityFile(ResultSet resultSet, TableClass tableClass) {

//        changeTableName = replaceUnderLineAndUpperCase(tableClass.tableName);

        final String suffix = ".java";
        String dicPath = GenerateConfig.getDISKPATH() + "entity/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "entity.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = getEntityMap(resultSet, tableClass);
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);

    }

    /**
     * 生成 mapper 文件
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateMapperFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "Mapper.java";
        String dicPath = GenerateConfig.getDISKPATH() + "dao/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "mapper.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);

    }

    /**
     * 生成 mybatis xml
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateMybatisFile(ResultSet resultSet, TableClass tableClass) {
        String dicPath = GenerateConfig.getRESOURCEPATH() + "mybatis/";
        final String suffix = "Mapper.xml";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "mybatis.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = getEntityMap(resultSet, tableClass);
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 生成业务实现类
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateServiceImplFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "ServiceImpl.java";
        String dicPath = GenerateConfig.getDISKPATH() + "service/impl/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "serviceImpl.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 生成业务接口
     *
     * @param resultSet
     * @param tableClass
     */
    private static void generateServiceInterfaceFile(ResultSet resultSet, TableClass tableClass) {
        final String suffix = "Service.java";
        String dicPath = GenerateConfig.getDISKPATH() + "service/";
        final String path = dicPath + tableClass.getChangeTableName() + suffix;
        final String templateName = "service.ftl";
        makeFile(dicPath, suffix);
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap, tableClass);
    }

    /**
     * 根据模板和文件生成文件
     *
     * @param templateName
     * @param file
     * @param dataMap
     * @param tableClass
     */
    private static void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap, TableClass tableClass) {
        try {
            Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
            FileOutputStream fos = new FileOutputStream(file);
            dataMap.put("pk", tableClass.getPk());
            dataMap.put("changePk", tableClass.getChangePk());
            dataMap.put("tableNameSmall", tableClass.getTableName());
            dataMap.put("tableName", tableClass.getChangeTableName());
            dataMap.put("author", GenerateConfig.getAUTHOR());
            dataMap.put("date", GenerateConfig.getDATE());
            dataMap.put("packageName", GenerateConfig.getPACKAGENAME());
            dataMap.put("basePackageName", GenerateConfig.getBASEPACKAGENAME());
            dataMap.put("tableAnnotation", tableClass.getTableAnnotation());
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
            template.process(dataMap, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件夹
     *
     * @param dicPath
     * @param filePath
     */
    private static void makeFile(String dicPath, String filePath) {
        File file = new File(dicPath);
        boolean exists = file.exists();
        if (!exists) {
            try {
                boolean mkdirs = file.mkdirs();
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转驼峰命名
     *
     * @param str
     * @param tableClass
     * @return
     */
    public static String replaceUnderLineAndUpperCase(String str, TableClass tableClass) {
        String replace = str.replace(tableClass.getPrefix(), "");
        StringBuffer sb = new StringBuffer();
        sb.append(replace);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

    /**
     * 获取结果集
     *
     * @param resultSet
     * @param tableClass
     * @return
     */
    private static Map<String, Object> getEntityMap(ResultSet resultSet, TableClass tableClass) {
        List<Column> columnClassList = new ArrayList<>();
        Column columnClass = null;
        try {
            while (resultSet.next()) {
                columnClass = new Column();
                //字段名称
                columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
                //字段类型
                columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
                //转换字段名称，如 sys_name 变成 SysName
                columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME"), tableClass));
                //字段在数据库的注释
                columnClass.setColumnComment(resultSet.getString("REMARKS"));
                columnClassList.add(columnClass);
            }
        } catch (Exception e) {

        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("column", columnClassList);
        return dataMap;
    }

    /**
     * 获取表
     *
     * @return
     * @throws Exception
     */
    private static List<TableClass> getTables() throws Exception {
        List<TableClass> tableClassList = new ArrayList<>();
        Connection connection = getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(connection.getCatalog(), "root", null, new String[]{"TABLE"});
        TableClass tableClass = null;
        while (resultSet.next()) {
            tableClass = new TableClass();
            //获取字段名称
            tableClass.setTableName(resultSet.getString("TABLE_NAME"));
            //字段在数据库的注释
            tableClass.setTableAnnotation(resultSet.getString("REMARKS"));
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableClass.getTableName());
            while (primaryKeys.next()) {
                String pk = primaryKeys.getString("COLUMN_NAME");
                tableClass.setPk(pk);
            }
            tableClassList.add(tableClass);
        }
        return tableClassList;
    }

}