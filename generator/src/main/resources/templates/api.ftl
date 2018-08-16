package ${packageName}.api;

import ${packageName}.api.hystrix.${tableName}Hystrix;
import ${basePackageName}.common.entity.${tableName};
import ${basePackageName}.common.model.PageInfo;
import ${basePackageName}.common.model.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
<#global entityName=tableName?uncap_first/>
/**
 * 描述：${tableAnnotation} Api
 *
 * @author ${author}
 * @date ${date}
 */
@FeignClient(value = "${entityName}", fallback = ${tableName}Hystrix.class)
public interface ${tableName}Api {
     /**
    * @des 创建${tableAnnotation}
    * @param ${entityName}
    */
    @PostMapping("/${entityName}/save")
    @ResponseBody
    Result save(@RequestBody ${tableName} ${entityName});

    /**
    * @des 根据id删除${tableAnnotation}
    * @param recordId ${tableAnnotation}id
    */
    @PostMapping(value = "/${entityName}/del")
    @ResponseBody
    Result deleteById(@RequestParam("recordId") String recordId);

    /**
    * @des 根据id集合删除${tableAnnotation}
    * @param recordIdArr ${tableAnnotation}id
    */
    @PostMapping(value = "/${entityName}/del/list/{recordIdArr}")
    @ResponseBody
    Result deleteByIdArr(@RequestParam("recordIdArr") Long[] recordIdArr);

    /**
    * @des 修改${tableAnnotation}
    * @param ${entityName}
    */
    @PostMapping("/${entityName}/update")
    @ResponseBody
    Result update${tableName}(@RequestBody ${tableName} ${entityName});

    /**
    * @des 根据id查询${tableAnnotation}
    * @param recordId
    */
    @PostMapping(value = "/${entityName}/find")
    @ResponseBody
    Result find${tableName}ById(@RequestParam("recordId") String recordId);

    /**
    * @des 根据id集合查询${tableAnnotation}
    * @param recordIdArr
    */
    @PostMapping(value = "/${entityName}/find/list")
    @ResponseBody
    Result find${tableName}ByIdList(@RequestParam("recordIdArr") Long[] recordIdArr);

    /**
    * @des 分页条件${tableAnnotation}
    * @param pageRequest
    */
    @PostMapping(value = "/${entityName}/find/list/params")
    @ResponseBody
    Result find${tableName}ByPage(@RequestBody PageRequestParams<${tableName}> pageRequest);
}