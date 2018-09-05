<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.dao.${tableName}Mapper">
    <#if column?exists>
    <resultMap id="BaseResultMap" type="${basePackageName}.common.entity.${tableName}">
        <#list column as model>
            <#if (model.columnType = 'INT')>
        <result column="${model.columnName}" property="${model.changeColumnName?uncap_first}" jdbcType="INTEGER"/>
            <#else>
        <result column="${model.columnName}" property="${model.changeColumnName?uncap_first}"
                jdbcType="${model.columnType}"/>
            </#if>
        </#list>
    </resultMap>
    </#if>

    <sql id="Base_Column_List">
    <#if column?exists>
        <#list column as model>
            <#if (model_has_next)>
                ${model.columnName},
            <#else>
                ${model.columnName}
            </#if>
        </#list>
    </#if>
    </sql>

    <!--根据id查询${tableAnnotation}-->
    <select id="findById" resultMap="BaseResultMap" parameterType="java.lang.String">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableNameSmall}
        WHERE ${pk} = ${r"#{recordId}"}
    </select>


    <!--根据条件查询${tableAnnotation}-->
    <select id="findByParam" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableNameSmall}
        <where>
            <#if column?exists>
                <#list column as model>
                    <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null and ${tableName?uncap_first}.${model.changeColumnName?uncap_first} !='' ">
                AND ${model.columnName} like CONCAT('%',${r"#{"}${tableName?uncap_first}
                .${model.changeColumnName?uncap_first}},'%')
            </if>
                    <#else>
            <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null">
                AND ${model.columnName} = ${r"#{"}${tableName?uncap_first}.${model.changeColumnName?uncap_first}}
            </if>
                    </#if>
                </#list>
            </#if>
        </where>
    </select>

    <!--根据条件查询${tableAnnotation}-->
    <select id="findFirst" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableNameSmall}
        <where>
            <#if column?exists>
                <#list column as model>
                    <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null and ${tableName?uncap_first}.${model.changeColumnName?uncap_first} !='' ">
                AND ${model.columnName} like CONCAT('%',${r"#{"}${tableName?uncap_first}
                .${model.changeColumnName?uncap_first}},'%')
            </if>
                    <#else>
            <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null">
                AND ${model.columnName} = ${r"#{"}${tableName?uncap_first}.${model.changeColumnName?uncap_first}}
            </if>
                    </#if>
                </#list>
            </#if>
        </where>
        LIMIT 0,1
    </select>


    <!--根据id集合查询${tableAnnotation}-->
    <select id="findByPage" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableNameSmall}
        <if test="${tableName?uncap_first} !=null">
            <where>
                <#if column?exists>
                    <#list column as model>
                        <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
                <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null and ${tableName?uncap_first}.${model.changeColumnName?uncap_first} !='' ">
                    AND ${model.columnName} like CONCAT('%',${r"#{"}${tableName?uncap_first}
                    .${model.changeColumnName?uncap_first}},'%')
                </if>
                        <#else>
                <if test="${tableName?uncap_first}.${model.changeColumnName?uncap_first} != null">
                    AND ${model.columnName} = ${r"#{"}${tableName?uncap_first}.${model.changeColumnName?uncap_first}}
                </if>
                        </#if>
                    </#list>
                </#if>
            </where>
        </if>
        <if test="pageInfo != null">
            order by ${pk} desc
            <if test="pageInfo.offset != null and pageInfo.pageSize != null">
                LIMIT ${r"#{pageInfo.offset}, #{pageInfo.pageSize}"}
            </if>
        </if>
    </select>


    <!--根据参数分页查询${tableAnnotation}-->
    <select id="findByIdList" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableNameSmall}
        WHERE id IN
        <foreach collection="idList" open="(" close=")" item="recordId" separator=",">
        ${r"#{recordId}"}
        </foreach>
    </select>


    <!--根据条件计数-->
    <select id="count" resultType="int">
        SELECT
        COUNT(id)
        FROM ${tableNameSmall}
        <where>
            <#if column?exists>
                <#list column as model>
                    <#if (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} !='' ">
                AND ${model.columnName} like CONCAT('%',${r"#{"}${model.changeColumnName?uncap_first}},'%')
            </if>
                    <#else>
            <if test="${model.changeColumnName?uncap_first} != null">AND ${model.columnName}
                = ${r"#{"}${model.changeColumnName?uncap_first}}
            </if>
                    </#if>
                </#list>
            </#if>
        </where>
    </select>


    <!--添加${tableAnnotation}-->
    <insert id="save" parameterType="${basePackageName}.common.entity.${tableName}" useGeneratedKeys="true"
            keyProperty="id">
        INSERT INTO ${tableNameSmall}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#if column?exists>
            <#list column as model>
                <#if (model.columnName = '${pk}')>
                <#elseif (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} !=''">
                ${model.columnName},
            </if>
                <#else>
            <if test="${model.changeColumnName?uncap_first} != null">
                ${model.columnName},
            </if>
                </#if>
            </#list>
        </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#if column?exists>
            <#list column as model>
                <#if (model.columnName = '${pk}')>
                <#elseif (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} !=''">
                ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
            </if>
                <#elseif (model.columnType = 'INT')>
            <if test="${model.changeColumnName?uncap_first} != null">
                ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=INTEGER},
            </if>
                <#else>
            <if test="${model.changeColumnName?uncap_first} != null">
                ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
            </if>
                </#if>
            </#list>
        </#if>
        </trim>
    </insert>


    <!--添加${tableAnnotation}-->
    <insert id="saveList" parameterType="${basePackageName}.common.entity.${tableName}" useGeneratedKeys="true"
            keyProperty="id">
        INSERT INTO ${tableNameSmall}
        <#if column?exists>
            <#list column as model>
                <#if (model.columnName = '${pk}')>
                <#elseif (model_has_next)>
                    ${model.columnName},
                <#else>
                    ${model.columnName}
                </#if>
            </#list>
        </#if>
        <#if column?exists>
            <#list column as model>
                <#if (model.columnName = '${pk}')>
                <#elseif (model_has_next)>
                    ${r"#{"}${model.changeColumnName?uncap_first}},
                <#else>
                    ${r"#{"}${model.changeColumnName?uncap_first}}
                </#if>
            </#list>
        </#if>
    </insert>

    <!--修改${tableAnnotation}-->
    <update id="update" parameterType="${basePackageName}.common.entity.${tableName}">
        UPDATE ${tableNameSmall}
        <#if column?exists>
        <set>
            <#list column as model>
                <#if (model.columnName = '${pk}')>
                <#elseif (model.columnType = 'VARCHAR' || model.columnType = 'text')>
            <if test="${model.changeColumnName?uncap_first} != null and ${model.changeColumnName?uncap_first} !=''">
                ${model.columnName} = ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
            </if>
                <#elseif (model.columnType = 'INT')>
            <if test="${model.changeColumnName?uncap_first} != null">
                ${model.columnName} = ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=INTEGER},
            </if>
                <#else>
            <if test="${model.changeColumnName?uncap_first} != null">
                ${model.columnName} = ${r"#{"}${model.changeColumnName?uncap_first},jdbcType=${model.columnType}},
            </if>
                </#if>
            </#list>
        </set>
        </#if>
        WHERE ${pk} = ${r"#{id}"}
    </update>

    <!--根据id删除${tableAnnotation}-->
    <delete id="delById" parameterType="java.lang.Long">
        DELETE FROM ${tableNameSmall}
        WHERE ${pk} = ${r"#{id}"}
    </delete>

    <!--根据条件删除${tableAnnotation}-->
    <delete id="delBy${tableName}" parameterType="${basePackageName}.common.entity.${tableName}">
        DELETE FROM ${tableNameSmall}
        WHERE ${pk} = ${r"#{recordId}"}
    </delete>


    <!--根据id集合删除${tableAnnotation}-->
    <delete id="delByIdList">
        DELETE FROM ${tableNameSmall}
        WHERE ${pk} IN
        <foreach collection="idArr" open="(" close=")" item="recordId" separator=",">
        ${r"#{recordId}"}
        </foreach>
    </delete>

</mapper>