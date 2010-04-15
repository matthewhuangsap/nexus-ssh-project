<input <#rt>
<#if parameters.id?if_exists != "">id="df_dateinput_${parameters.id?html}"</#if><#rt>
<#if parameters.name?if_exists != "">name="${parameters.name?html}"</#if><#rt/>
  class="dfDateInput" type="text" <#rt>
  <#if parameters.nameValue??>
	 value="<@s.property value="parameters.nameValue"/>"<#rt/>
	</#if>
  readonly="readonly"/></td>
<script type="text/javascript"> $(document).ready(function() {<#rt>   
    addDateInput($("#df_dateinput_${parameters.id?html}")); <#rt>
});  </script>