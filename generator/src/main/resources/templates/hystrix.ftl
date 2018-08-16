package ${packageName}.api.hystrix;

import ${packageName}.api.${tableName}Api;
import ${basePackageName}.common.entity.${tableName};
import ${basePackageName}.common.enums.ErrorCode;
import ${basePackageName}.common.model.PageInfo;
import ${basePackageName}.common.model.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述：${tableAnnotation} 熔断
 *
 * @author ${author}
 * @date ${date}
 */
@Component
public class ${tableName}Hystrix implements ${tableName}Api {

    @Override
    public Result save(${tableName} ${tableName?uncap_first}){
        return new Response
<Object>().fail(ErrorCode.NETWORK_FAIL);
    }

    @Override
    public Result deleteById(String recordId){
    return new Response
    <Object>().fail(ErrorCode.NETWORK_FAIL);
        }

        @Override
        public Result deleteByIdArr(Long[] recordIdArr){
        return new Response
        <Object>().fail(ErrorCode.NETWORK_FAIL);
            }

            @Override
            public Result update${tableName}(${tableName} ${tableName?uncap_first}){
            return new Response
            <Object>().fail(ErrorCode.NETWORK_FAIL);
                }

                @Override
                public Result find${tableName}ById(String recordId){
                return new Response
                <Object>().fail(ErrorCode.NETWORK_FAIL);
                    }

                    @Override
                    public Result find${tableName}ByIdList(Long[] recordIdArr){
                    return new Response
                    <Object>().fail(ErrorCode.NETWORK_FAIL);
                        }

                        @Override
                        public Result find${tableName}ByParams(PageInfo<${tableName}> pageInfo){
                        return new Response
                        <Object>().fail(ErrorCode.NETWORK_FAIL);
                            }

                            }
