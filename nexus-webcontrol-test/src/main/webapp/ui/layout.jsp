<%@ taglib prefix="n" uri="/coolie-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dcriori
  Date: 2010-4-22
  Time: 10:10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }
    </style>
    <n:head/>
</head>
<body>
<h2>Hello World!</h2>

<h3>HAHA! This is our time!!!!</h3>
<n:title dmoID="23" id="tt" name="tt" label="XXX单据"/>
<s:form action="layout" theme="css">
    <n:layoutpanel id="main" name="main" title="单据头信息" cols="5"  fieldHeight="48px">

        <li><span><div>Name:</div><div><input type="text"/></div></span></li>
        <li><span><div>PSW:</div><div><input type="text"/></div></span></li>
        <li><span><div>Address:</div><div><input type=""/></div></span></li>
        <li><span><div>Sex:</div><div><label for="sex1">Female</label>
            <input type="radio" value="1" id="sex1" name="sex1"/>
            <label for="sex2">Male</label>
            <input type="radio" value="2" id="sex2" name="sex2"/>
            <label for="sex3">Secrecy</label>
            <input type="radio" value="3" id="sex3" name="sex3"/>
            <input type="hidden" value="3"/>
        </div></span></li>

        <li><span><div>Name:</div><div><input type="text"/></div></span></li>
        <li><span><div>PSW:</div><div><input type="text"/></div></span></li>
        <li><span><div>Address:</div><div><input type=""/></div></span></li>
        <li><span><div>Sex:</div><div><label for="sex1">Female</label>
            <input type="radio" value="1" id="sex4" name="sex1"/>
            <label for="sex2">Male</label>
            <input type="radio" value="2" id="sex5" name="sex2"/>
            <label for="sex3">Secrecy</label>
            <input type="radio" value="3" id="sex6" name="sex3"/>
            <input type="hidden" value="3"/>
        </div></span></li>
        <n:dateinput />

    </n:layoutpanel>
    <n:layoutpanel id="main2" fieldHeight="230px" name="main2" cols="1" title="其它信息" height="100%">
        <s:textarea id="ta" value="XXXXXXXX" rows="10" cols="70"/>
        <s:textarea id="ta2" value="XXXXXXXX" rows="10" cols="70"/>
    </n:layoutpanel>
</s:form>

</body>
</html>