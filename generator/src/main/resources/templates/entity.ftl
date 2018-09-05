package ${packageName}.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：${tableAnnotation}模型
 * @author ${author}
 * @date ${date}
 */
@Data
@NoArgsConstructor
public class ${tableName} implements Serializable {

<#if column?exists>
    <#list column as model>
        <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
    /**
     *${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'BIGINT' && model.columnName = '${pk}')>
    /**
     *${model.columnComment!}
     */
    private Long ${model.changeColumnName?uncap_first};

        <#elseif (model.columnType = 'BIGINT' && model.columnName != '${pk}')>
    /**
     *${model.columnComment!}
     */
    private Long ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'INT')>
    /**
     *${model.columnComment!}
     */
    private Integer ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'DECIMAL')>
    /**
     *${model.columnComment!}
     */
    private BigDecimal ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'TIMESTAMP'>
    /**
     *${model.columnComment!}
     */
    private Date ${model.changeColumnName?uncap_first};

        </#if>
    </#list>
</#if>

}