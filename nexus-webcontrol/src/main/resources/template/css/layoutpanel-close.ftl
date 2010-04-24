</div>
<#if parameters.id?if_exists != "">
    <#assign iden="#lp_${parameters.id?html}"/>
</#if>
<#if  parameters.cols?if_exists !="">
    <#assign cols = "${parameters.cols?html}"/>
<#else >
    <#assign cols = "1"/>
</#if>
<#if parameters.fieldHeight?if_exists !="">
    <#assign fieldHeight = "${parameters.fieldHeight?html}"/>
<#else >
    <#assign fieldHeight = "48px"/>
</#if>
<script type="text/javascript">
        $(document).ready(function(){
           addLayoutPanel($("${iden}")
                   ,$("${iden}>ul")
                   ,$("${iden}>ul>li")
                   ,$("${iden}>ul>li>span"),${cols},"${fieldHeight}");
        });
</script>