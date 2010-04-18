
<%@taglib prefix="n" uri="/WEB-INF/coolie-tags.tld" %>
<html>
<head>
    <title>


    </title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        ul {
            border: 1px solid #cfdae8;
            border-left: none;
            border-bottom: none;
            overflow: hidden;
            background: url(images/linex.gif);
            zoom: 1;
        }

        li {
            width: 20%;
            *width: 19.9%; /*IE6&IE7的宽度有点囧*/
            float: left;
        }

        span {
            display: block;
            border-left: 1px solid #cfdae8;
            height: 48px;
            font-size: 12px;
            line-height: 24px;
            padding: 0 4px;
        }
    </style>
</head>
<body>
<h2>Hello World!</h2>

<h3>HAHA! This is our time!!!!</h3>
<n:layoutpanel>

    <li><span><div>名称</div><div><input type="text"/></div></span></li>
    <li><span><div>密码</div><div><input type="text"/></div></span></li>
    <li><span><div>现住地址</div><div><input type=""/></div></span></li>
    <li><span><div>性别</div><div><label for="sex1">男</label>
        <input type="radio" value="1" id="sex1" name="sex1"/>
        <label for="sex2">女</label>
        <input type="radio" value="2" id="sex2" name="sex2"/>
        <label for="sex3">保密</label>
        <input type="radio" value="3" id="sex3" name="sex3"/>
        <input type="hidden" value="3"/>
    </div></span></li>

    <li><span><div>名称</div><div><input type="text"/><input type="hidden" value="3"/></div></span></li>
    <li><span><div>密码</div><div><input type="text"/></div></span></li>
    <li><span><div>现住地址</div><div><input type=""/></div></span></li>
    <li><span><div>性别</div><div><label for="sex1">男</label>
        <input type="radio" value="1" id="sex4" name="sex"/>
        <label for="sex2">女</label>
        <input type="radio" value="2" id="sex5" name="sex"/>
        <label for="sex3">保密</label>
        <input type="radio" value="3" id="sex6" name="sex"/>
    </div></span></li>
    <li><span><div>现住地址</div><div><input type=""/></div></span></li>
    <li><span></span></li>
    <li><span><div>摘要</div><div><input type="text" size="53" maxlength="53"/></div></span></li>
</n:layoutpanel>
</body>
</html>
