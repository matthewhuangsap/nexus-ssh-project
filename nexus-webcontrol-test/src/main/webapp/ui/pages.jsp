<%@ taglib prefix="n" uri="/coolie-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2010-4-27
  Time: 15:33:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  共${total}页 第${cpage}页<br>
  <n:pages cpage="${cpage}" total="${total}" url="${url}"/>

</html>