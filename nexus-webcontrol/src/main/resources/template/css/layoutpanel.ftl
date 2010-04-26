<div <#rt>
<#if parameters.id?if_exists !="" >
 id="lp_${parameters.id}"<#rt>
</#if>
<#if parameters.name?if_exists !="">
 name = "lp_name_${parameters.name}" <#rt>
</#if>
<#if parameters.height?if_exists !="">
 height = "${parameters.height}" <#rt>
</#if>

><#rt>

<span class="titleClass"><strong><#rt>
<#if parameters.title?if_exists !="">
    ${parameters.title?html}  <#rt>
</#if>
</strong></span><ul><#rt>