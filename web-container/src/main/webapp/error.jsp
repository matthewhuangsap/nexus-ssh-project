<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dcriori
  Date: 2010-4-27
  Time: 13:33:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Error Page </title></head>
  <body>
    <h1>发生了错误！</h1>
    <s:property value="exception.message"/>
  </body>

</html>