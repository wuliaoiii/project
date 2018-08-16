package com.yangy.generator.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 代码生成器对象
 *
 * @author yangy
 * @email java_yangy@126.com
 * @create 2018/6/13
 * @since 1.0.0
 */
public class TableClass {
    /**
     * 表前缀
     */
    private String prefix;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableAnnotation;

    /**
     * 实体类名称
     */
    private String changeTableName;
    private String pk;
    private String changePk;

    public String getPrefix() {
        String[] split = tableName.split("_");
        return split[0] + "_";
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAnnotation() {
        return tableAnnotation;
    }

    public void setTableAnnotation(String tableAnnotation) {
        this.tableAnnotation = tableAnnotation;
    }

    public String getChangeTableName() {
        return replaceUnderLineAndUpperCase(tableName);
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
        this.changePk = replaceUnderLineAndUpperCase(pk);
    }

    public String getChangePk() {
        return replaceUnderLineAndUpperCase(pk);
    }

    public void setChangePk(String changePk) {
        this.changePk = pk;
    }

    /**
     * 去掉下划线
     *
     * @param str
     * @return
     */
    public String replaceUnderLineAndUpperCase(String str) {
        String replace = str.replace(getPrefix(), "");
        StringBuffer sb = new StringBuffer();
        sb.append(replace);
        int count = sb.indexOf("_");

        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                sb.replace(count, count + 1, (char) (ss - 32) + "");
            }
        }
        return StringUtils.capitalize(sb.toString().replaceAll("_", ""));
    }

}