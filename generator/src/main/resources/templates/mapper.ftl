package ${packageName}.dao;

import ${basePackageName}.common.entity.${tableName};
import ${basePackageName}.common.model.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
<#global entityName=tableName?uncap_first/>
/**
* 描述：${tableAnnotation}
* @author ${author}
* @date ${date}
*/
@Mapper
public interface ${tableName}Mapper {


    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int save(@Param("${entityName}")${tableName} record);

    /**
     * 批量添加记录
     *
     * @param ${entityName}List
     * @return
     */
    int saveList(@Param("${entityName}List")List<${tableName}> ${entityName}List);

    /**
     * 根据主键删除记录
     *
     * @param id
     * @return
     */
    int delById(@Param("id")Long id);

    /**
     * 根据条件删除记录
     *
     * @param record
     * @return
     */
    int delBy${tableName}(@Param("${entityName}")${tableName} record);

    /**
     * 根据主键集合删除记录
     *
     * @param idList
     * @return
     */
    int delByIdList(@Param("idList")List
<Long> idList);

    /**
    * 修改记录
    *
    * @param record
    * @return
    */
    int update(@Param("${entityName}")${tableName} record);

    /**
    * 根据主键查询记录
    *
    * @param id
    * @return
    */
${tableName} findById(@Param("id") Long id);

    /**
    * 查询满足条件的第一条记录
    *
    * @param record
    * @return
    */
${tableName} findFirst(@Param("${entityName}")${tableName} record);

    /**
    * 根据id集合查询记录集合
    *
    * @param idList
    * @return
    */
    List<${tableName}> findByIdList(@Param("idList")List
    <Long> idList);

        /**
        * 条件查询记录集合
        *
        * @param record
        * @return
        */
        List<${tableName}> findByParam(@Param("${entityName}")${tableName} record);

        /**
        * 分页查询
        *
        * @param pageInfo
        * @return
        */
        List<${tableName}> findByPage(@Param("${entityName}")${tableName} ${entityName},@Param("pageInfo")PageInfo
        pageInfo);

        /**
        * 条件统计
        *
        * @param record
        * @return
        */
        int count(@Param("${entityName}")${tableName} record);

        }