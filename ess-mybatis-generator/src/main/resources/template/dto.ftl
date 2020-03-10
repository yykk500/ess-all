package ${package?substring(0,package?last_index_of('$'))}${tableClass.lowerCaseName};

import lombok.Getter;
import lombok.Setter;

<#if tableClass.allFields??>
    <#list tableClass.allFields as field>
<#if field != "java.lang">
import ${field.fullTypeName};
</#if>
    </#list>
</#if>

/**
* ${tableClass.shortClassName}Dto
*
* @author dto-generator
*/
@Setter
@Getter
public class ${tableClass.shortClassName}Dto {

<#if tableClass.allFields??>
    <#list tableClass.allFields as field>
    /**
    * ${field.remarks}
    */
    private ${field.shortTypeName} ${field.fieldName};

    </#list>
</#if>

}




