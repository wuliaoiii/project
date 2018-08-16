package ${packageName}.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：${tableAnnotation}模型
 * @author ${author}
 * @date ${date}
 */
@Data
@NoArgsConstructor
@TableName("${tableNameSmall}")
public class ${tableName} implements Serializable {

<#if column?exists>
    <#list column as model>
        <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
    /**
     *${model.columnComment!}
     */
    @TableField("${model.columnName}")
    private String ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'BIGINT' && model.columnName = '${pk}')>
    /**
     *${model.columnComment!}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ${model.changeColumnName?uncap_first};

        <#elseif (model.columnType = 'BIGINT' && model.columnName != '${pk}')>
    /**
     *${model.columnComment!}
     */
    @TableField("${model.columnName}")
    private Long ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'INT')>
    /**
     *${model.columnComment!}
     */
    @TableField("${model.columnName}")
    private Integer ${model.changeColumnName?uncap_first};

        </#if>
        <#if (model.columnType = 'DECIMAL')>
    /**
     *${model.columnComment!}
     */
    @TableField("${model.columnName}")
    private BigDecimal ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'TIMESTAMP'>
    /**
     *${model.columnComment!}
     */
    @TableField("${model.columnName}")
    private Date ${model.changeColumnName?uncap_first};

        </#if>
    </#list>
</#if>

}