package ${packageName}.service.impl;

import com.alibaba.fastjson.JSON;
import ${basePackageName}.common.enums.ResultCode;
import ${basePackageName}.common.exception.MyException;
import ${basePackageName}.common.model.PageInfo;
import ${packageName}.dao.${tableName}Mapper;
import ${basePackageName}.common.entity.${tableName};
import ${packageName}.service.${tableName}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
<#global entityName=tableName?uncap_first/>
/**
 * 描述：${tableAnnotation} 服务实现层
 *
 * @author ${author}
 * @date ${date}
 */
@Service
@Slf4j
public class ${tableName}ServiceImpl implements ${tableName}Service {

    @Resource
    private ${tableName}Mapper ${entityName}Mapper;

    /**
     * 添加记录
     *
     * @param record
     */
    @Override
    @Transactional
    public long save(${tableName} record){
        log.info("添加${entityName} -> ${entityName}={}",JSON.toJSONString(record));
        try {
            ${entityName}Mapper.save(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return record.get${changePk}();
    }

    /**
     * 批量添加记录
     *
     * @param ${entityName}List
     * @return
     */
    @Override
    @Transactional
    public int saveList(List<${tableName}> ${entityName}List){
        log.info("批量添加 ${entityName} -> ${entityName}List={}",JSON.toJSONString(${entityName}List));
        int saveResult = 0;
        try {
            saveResult = ${entityName}Mapper.saveList(${entityName}List);
        } catch (MyException e) {
            throw new MyException(ResultCode.SAVE_FAIL);
        }
        return saveResult;
    }

    /**
     * 修改记录
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public long update(${tableName} record){
        log.info("修改${entityName}-> ${entityName}={}",JSON.toJSONString(record));
        if(null == record.get${changePk}()){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        try {
            ${entityName}Mapper.update(record);
        } catch (MyException e) {
            throw new MyException(ResultCode.UPDATE_FAIL);
        }
        return record.get${changePk}();
    }

    /**
     *  根据主键删除信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int del(Long id){
        log.info("删除${entityName} -> id={}",id);
        if(null == id){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = ${entityName}Mapper.delById(id);
        } catch (Exception e) {
            throw new MyException(ResultCode.DEL_FAIL);
        }
        return delResult;
    }

    /**
     * 根据条件删除信息
     *
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int del(${tableName} record){
        log.info("根据条件删除${entityName} -> ${entityName}={}",JSON.toJSONString(record));
        if(null == record){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = ${entityName}Mapper.delBy${tableName}(record);
        } catch (Exception e) {
            throw new MyException(ResultCode.DEL_FAIL);
        }
        return delResult;
    }

    /**
     * 根据主键集合删除信息
     *
     * @param idList
     * @return
     */
    @Override
    @Transactional
    public int delByIdList(List<Long> idList){
        log.info("批量删除${entityName} -> idList={}",JSON.toJSONString(idList));
        if(CollectionUtils.isEmpty(idList)){
            throw new MyException(ResultCode.PARAM_ERROR);
        }
        int delResult = 0;
        try {
            delResult = ${entityName}Mapper.delByIdList(idList);
        } catch (Exception e) {
            throw new MyException(ResultCode.DEL_FAIL);
        }
        return delResult;
    }

    /**
    * 根据id 查询信息
    *
    * @param id
    * @return
    */
    @Override
    @Transactional
    public ${tableName} findById(Long id){
        log.info("根据主键查询${entityName} -> id={}",id);
        return null == id ? null : ${entityName}Mapper.findById(id);
    }

    /**
    * 查询满足条件的第一条记录
    *
    * @param record
    * @return
    */
    @Override
    @Transactional
    public ${tableName} findFirst(${tableName} record){
        log.info("根据条件查询${entityName}的第一条记录 -> ${entityName}={}",JSON.toJSONString(record));
        return ${entityName}Mapper.findFirst(record);
    }

    /**
    * 根据 id 集合查询信息
    *
    * @param idList
    * @return
    */
    @Override
    public List<${tableName}> findByIdList(List<Long> idList){
        log.info("根据主键集合查询${entityName} -> idList={}",JSON.toJSONString(idList));
        return ${entityName}Mapper.findByIdList(idList);
    }

    /**
    * 根据条件查询信息
    *
    * @param record
    * @return
    */
    @Override
    public List<${tableName}> findByParam(${tableName} record) {
    log.info("根据条件查询${entityName} -> ${entityName}={}",JSON.toJSONString(record));
        return ${entityName}Mapper.findByParam(record);
    }

    /**
    * 根据条件分页查询信息
    *
    * @param pageInfo
    * @return
    */
    @Override
    public PageInfo findByPage(PageInfo<${tableName}> pageInfo) {
        log.info("分页查询${entityName} -> pageInfo={}",JSON.toJSONString(pageInfo));
        ${tableName} ${entityName} = pageInfo.getData();
        List<${tableName}> ${entityName}List = ${entityName}Mapper.findByPage(${entityName}, pageInfo);
        int count = count(${entityName});

        PageInfo<List<${tableName}>> pageReturn = new PageInfo<List<${tableName}>>();
        pageReturn.setCount(count);
        pageReturn.setPageIndex(pageInfo.getPageIndex());
        pageReturn.setPageSize(pageInfo.getPageSize());
        pageReturn.setData(${entityName}List);

        return pageReturn;
    }

    /**
    * 根据条件统计信息
    *
    * @param record
    * @return
    */
    @Override
    public int count(${tableName} record) {
    log.info("根据条件计数 -> ${entityName}={}", JSON.toJSONString(record));
        return ${entityName}Mapper.count(record);
    }
}