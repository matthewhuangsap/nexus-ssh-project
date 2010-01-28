<div <#if parameters.id?if_exists != "">id="${parameters.id?html}"</#if>><#rt>
<@s.if test="${parameters.dmoValue}== 0"> ${parameters.nameValue?default("")}新建</@s.if><#rt>
<@s.else>${parameters.nameValue?default("")}No.${parameters.dmoValue?default("")}编辑</@s.else><#rt>
</div>