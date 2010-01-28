<#if parameters.compressed?default(true)>
  <#assign jqueryFile="jquery-1.3.2.min.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.min.js">
  <#assign jqueryAutoFile="jquery.choicebox-0.1.js">
<#else>
  <#assign jqueryFile="jquery-1.3.2.js">
  <#assign jqueryUIFile="jquery-ui-1.7.2.js">
  <#assign jqueryAutoFile="jquery.choicebox-0.1.js">
</#if>
<#assign coolieFile="jquery.coolie.js">

<#if parameters.baseRelativePath?if_exists != "">
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryUIFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${jqueryAutoFile}' includeParams='none' encode='false'/>"></script>
  <script language="JavaScript" type="text/javascript" src="<@s.url value='${parameters.baseRelativePath}/${coolieFile}' includeParams='none' encode='false'/>"></script>
<#else>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryUIFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${jqueryAutoFile}"></script>
  <script language="JavaScript" type="text/javascript" src="${base}/struts/jquery/${coolieFile}"></script>
</#if>  

<#if parameters.locale?if_exists != "">
  <#if parameters.locale?if_exists != "en">
	<script type="text/javascript" src="${base}/struts/i18n/ui.datepicker-${parameters.locale?string}.js"></script>
  </#if>
</#if>

<#if parameters.uiTheme?? && parameters.uiTheme != "">
	<#if parameters.uiTheme?contains("/") || parameters.uiTheme?contains("\\")>
		<link rel="stylesheet" href="${base}/${parameters.uiTheme?string}" type="text/css"/>
    <#else>
		<link rel="stylesheet" href="${base}/struts/jquery/theme/${parameters.uiTheme?string}/jquery-ui.css" type="text/css"/>
    </#if>
	<link rel="stylesheet" href="${base}/struts/jquery/theme/choicebox/jquery.choicebox.css" type="text/css"/>
<#else>
	<link rel="stylesheet" href="${base}/struts/jquery/theme/base/jquery-ui.css" type="text/css"/>
	<link rel="stylesheet" href="${base}/struts/jquery/theme/choicebox/jquery.choicebox.css" type="text/css"/>
</#if>
   