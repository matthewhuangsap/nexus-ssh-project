<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/coolie-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head><title>Simple jsp page</title>
        <n:head/>
    </head>
    <body>
        flag:<s:property value="#parameters.flag"/>
        <s:bean name="java.util.HashMap" id="qTableLayout">
            <s:param name="tablecolspan" value="%{4}"/>
        </s:bean>
        <br>
        tablecolspan:<s:property value="#qTableLayout.tablecolspan"/><br/>
    </body>
    <n:layoutpanel cols="5" >
        <s:textfield id="tf">
            <s:param name="inputcolspan" value="2"/>
        </s:textfield>
    </n:layoutpanel>
</html>