<div <#if parameters.id?if_exists != "">id="${parameters.id?html}"</#if>><#rt>
<@s.if test="${parameters.dmoValue}== 0"> ${parameters.nameValue?default("")}新建</@s.if><#rt>
<@s.else><@s.property value="title" /><#assign id = stack.findValue(parameters.dmoID)/><#rt>
No.${id}编辑</@s.else>
</div>