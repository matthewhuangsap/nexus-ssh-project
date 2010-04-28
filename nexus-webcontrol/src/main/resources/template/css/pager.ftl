<#assign isValid = true/>
<#assign total = "${total}"/>
<#assign cpage = "${cpage}"/>
<#assign url = "${url}"/>

<#if  isValid>
    <#if total?if_exists == "">
        <#assign isValid=false/>
    <#else>
        <#assign isValid = true/>
    </#if >
</#if>
<#if  isValid>
    <#if cpage?if_exists == "">
        <#assign isValid=false/>
    <#else>
        <#assign isValid = true/>
    </#if >
</#if>
<#if  isValid>
    <#if url?if_exists == "">
        <#assign isValid=false/>
    <#else>
        <#assign isValid = true/>
    </#if >
</#if>
<div class="pagerClass">
共${total}页/第${cpage}页<#rt>
<#if isValid>
<a href="${url}?cpage=${1}&total=${total}&url=${url}">［首页］</a><#rt >
<#if cpage=="1">
［上一页］
<#else >
<a href="${url}?cpage=${cpage?number-1}&total=${total}&url=${url}">［上一页］</a><#rt >
</#if>
<#if cpage == total>
［下一页］
<#else >
<a href="${url}?cpage=${cpage?number + 1}&total=${total}&url=${url}">［下一页］</a><#rt >
</#if>
<a href="${url}?cpage=${total}&total=${total}&url=${url}">［末页］</a><#rt >
<#else >
<a href="#">［首页］</a><#rt >
<a href="#">［上一页］</a><#rt >
<a href="#">［下一页］</a><#rt >
<a href="#">［末页］</a><#rt >
</#if>
</div>