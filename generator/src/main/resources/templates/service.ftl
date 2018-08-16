package ${packageName}.service;

import ${basePackageName}.common.model.PageInfo;
import ${basePackageName}.common.entity.${tableName};

import java.util.List;
<#global entityName=tableName?uncap_first/>

/**
 * 描述：${tableAnnotation}接口
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${tableName}Service{

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    long save(${tableName} record);

    /**
     * 批量添加记录
     *
     * @param ${entityName}List
     * @return
     */
    int saveList(List<${tableName}> ${entityName}List);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int del(Long id);

    /**
     * 根据条件删除记录
     *
     * @param record
     * @return
     */
    int del(${tableName} record);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    int delByIdList(List
<Long> idList);

    /**
    * 修改记录
    *
    * @param record
    * @return
    */
    long update(${tableName} record);

    /**
    * 根据主键查询记录
    *
    * @param id
    * @return
    */
${tableName} findById(Long id);

    /**
    * 查询满足条件的第一条记录
    *
    * @param record
    * @return
    */
${tableName} findFirst(${tableName} record);

    /**
    * 根据id集合查询记录集合
    *
    * @param idList
    * @return
    */
    List<${tableName}> findByIdList(List
    <Long> idList);

        /**
        * 条件查询记录集合
        *
        * @param record
        * @return
        */
        List<${tableName}> findByParam(${tableName} record);

        /**
        * 分页查询
        *
        * @param pageInfo
        * @return
        */
        PageInfo findByPage(PageInfo<${tableName}> pageInfo);

        /**
        * 条件统计
        *
        * @param record
        * @return
        */
        int count(${tableName} record);
        }
