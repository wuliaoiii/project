package ${packageName}.controller;

import ${basePackageName}.common.model.PageInfo;
import ${basePackageName}.common.model.Result;
import ${basePackageName}.common.entity.${tableName};
import ${packageName}.service.${tableName}Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
<#global entityName=tableName?uncap_first/>

/**
 * 描述：${tableAnnotation}控制层
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("/${entityName}")
public class ${tableName}Controller {

    @Resource
    private ${tableName}Service ${entityName}Service;

    /**
     * 创建${tableAnnotation}
     *
     * @param ${entityName}
     * @result
     */
    @PostMapping("/save/${entityName}")
    public Result save(@RequestBody ${tableName} ${entityName}) {
        return new Result<Long>().ok(${entityName}Service.save(${entityName}));
    }

    /**
    * 创建${tableAnnotation}
    *
    * @param ${entityName}
    * @result
    */
    @PostMapping("/save/return/${entityName}")
    public Result saveAndReturn(@RequestBody ${tableName} ${entityName}) {
    long saveResult = ${entityName}Service.save(${entityName});
    return new Result<${tableName}>().ok(${entityName}Service.findById(saveResult));
    }

    /**
     * 批量创建${tableAnnotation}
     *
     * @param ${entityName}List
     * @result
     */
    @PostMapping("/save/list")
    public Result save(@RequestBody List<${tableName}> ${entityName}List) {
        return new Result<Integer>().ok(${entityName}Service.saveList(${entityName}List));
    }

    /**
     * 根据id删除${tableAnnotation}
     *
     * @param recordId ${tableAnnotation}id
     * @result
     */
    @PostMapping(value = "/del/${entityName}/id")
    public Result deleteById(@RequestParam("recordId") Long recordId) {
        return new Result<Integer>().ok(${entityName}Service.del(recordId));
    }

    /**
     * 根据id集合删除${tableAnnotation}
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/del/list/ids")
    public Result deleteById(@RequestParam("idList") List<Long> idList) {
        return new Result<Integer>().ok(${entityName}Service.delByIdList(idList));
    }

    /**
     * 修改${tableAnnotation}
     *
     * @param ${entityName}
     * @result
     */
    @PostMapping(value = "/update/${entityName}")
    public Result update(@RequestBody ${tableName} ${entityName}) {
        return new Result<Long>().ok(${entityName}Service.update(${entityName}));
    }

    /**
     * 修改${tableAnnotation}
     *
     * @param ${entityName}
     * @result
     */
    @PostMapping(value = "/update/return/${entityName}")
    public Result updateAndReturn(@RequestBody ${tableName} ${entityName}) {
        long updateResult = ${entityName}Service.update(${entityName});
        return new Result<${tableName}>().ok(${entityName}Service.findById(updateResult));
    }

    /**
    * 根据id查询${tableAnnotation}
    *
    * @param recordId
    * @result
    */
    @PostMapping(value = "/find/${entityName}/id")
    public Result findById(@RequestParam("recordId") Long recordId) {
        return new Result<${tableName}>().ok(${entityName}Service.findById(recordId));
    }

    /**
     * 根据id集合查询${tableAnnotation}
     *
     * @param idList
     * @result
     */
    @PostMapping(value = "/find/list/ids")
    public Result findByIdList(@RequestParam("idList") List<Long> idList) {
        return new Result<List<${tableName}>>().ok(${entityName}Service.findByIdList(idList));
    }

    /**
     * 条件查询${tableAnnotation}
     *
     * @param ${entityName}
     * @result
     */
    @PostMapping(value = "/find/list/param")
    public Result findByParam(@RequestBody ${tableName} ${entityName}) {
        return new Result<List<${tableName}>>().ok(${entityName}Service.findByParam(${entityName}));
    }

    /**
     * 分页查询${tableAnnotation}
     *
     * @param pageInfo
     * @result
     */
    @PostMapping(value = "/find/list/page")
    public Result findByPage(@RequestBody PageInfo<${tableName}> pageInfo) {
        return new Result<PageInfo>().ok(${entityName}Service.findByPage(pageInfo));
    }
}