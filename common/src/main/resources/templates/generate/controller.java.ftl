package ${package.Controller};

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${table.comment} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("save")
    public Result save(@RequestBody ${entity} ${entity?uncap_first}) {
        boolean insert = ${entity?uncap_first}Service.insert(${entity?uncap_first});
        return new Result<${entity}>().ok(${entity?uncap_first}.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<${entity}> ${entity?uncap_first}List) {
        boolean insert = ${entity?uncap_first}Service.insertBatch(${entity?uncap_first}List);
        return new Result
    <Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody ${entity} ${entity?uncap_first}) {
        boolean update = ${entity?uncap_first}Service.updateById(${entity?uncap_first});
        return new Result<${entity}>().ok(${entity?uncap_first}.selectById());
    }

     @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        ${entity} selectOne = ${entity?uncap_first}Service.selectById(recordId);
        return new Result<${entity}>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody ${entity} ${entity?uncap_first}) {
        EntityWrapper<${entity}> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(${entity?uncap_first});
        ${entity} selectOne = ${entity?uncap_first}Service.selectOne(entityWrapper);
        return new Result<${entity}>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List
    <Long> idList) {
        List<${entity}> userList = ${entity?uncap_first}Service.selectBatchIds(idList);
        return new Result
        <List<${entity}>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody ${entity} ${entity?uncap_first}) {
        Page<${entity}> ${entity?uncap_first}Page = new Page<>();
        EntityWrapper<${entity}> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(${entity?uncap_first});
        Page<${entity}> ${entity?uncap_first}PageDB = ${entity?uncap_first}Service.selectPage(${entity?uncap_first}Page,
        entityWrapper);
        return new Result
        <Page<${entity}>>().ok(${entity?uncap_first}PageDB);
    }

}
    </#if>
</#if>
