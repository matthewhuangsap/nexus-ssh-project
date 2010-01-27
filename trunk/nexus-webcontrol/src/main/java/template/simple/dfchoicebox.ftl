<td><#if parameters.label?if_exists != ""><span>${parameters.label?html}:</span></#if><#rt/></td>
<td><span><#rt>
<input<#rt> 
 <#if parameters.id?if_exists != "">id="df_cb_${parameters.id?html}"</#if><#rt>
 <#if parameters.nameValue??>value="<@s.property value="parameters.displayValue"/>"</#if><#rt>
  class="dfChoiceBox" type="text" /><#rt>
<input <#rt>
<#if parameters.id?if_exists != "">id="df_cb_hidden_${parameters.id?html}"</#if><#rt>
 type="hidden" <#rt>
  <#if parameters.nameValue??>
	 value="<@s.property value="parameters.value"/>"<#rt/>
	</#if>
  <#if parameters.name?if_exists != "">name="${parameters.name?html}"</#if><#rt/>
/><#rt>
</span></td>
<script type="text/javascript"> $(document).ready(function() {<#rt>   
    addChoiceBox($("#df_cb_${parameters.id?html}"),"df_cb_hidden_${parameters.id?html}",<#rt>
<#if parameters.action?if_exists != "">"${parameters.action?html}"</#if>,"${parameters.datakind?html}"); <#rt>
});  </script>