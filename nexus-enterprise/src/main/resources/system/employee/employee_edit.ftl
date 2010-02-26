<#global c=JspTaglibs["/WEB-INF/coolie-tags.tld"]/> 
<html>
	<head>
		<title>Edit User Page</title>
		<@c.head compressed="false" locale="zh-CN"></@c.head>
		<style> 
			.btn { 
			BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7b9ebd 1px solid 
			} 
			.ButtonCss{
			    font-family: "Tahoma", "宋体";
			    font-size:9pt; color: #003399;
			    border: 1px #003399 solid;
			    color:006699;
			    BORDER-BOTTOM: #93bee2 1px solid; 
			    BORDER-LEFT: #93bee2 1px solid; 
			    BORDER-RIGHT: #93bee2 1px solid; 
			    BORDER-TOP: #93bee2 1px solid;
			    background-image:url(../Images/bluebuttonbg.gif);
			    background-color: #e8f4ff;
			    CURSOR: hand;
			    font-style: normal ;
			    width:60px;
			    height:22px;
			}
			.blueButtonCss{
			    font-family: "Tahoma", "宋体";
			    font-size: 9pt; color: #003366;
			    border: 0px #93bee2 solid;
			    BORDER-BOTTOM: #93bee2 1px solid; 
			    BORDER-LEFT: #93bee2 1px solid; 
			    BORDER-RIGHT: #93bee2 1px solid; 
			    BORDER-TOP: #93bee2 1px solid;
			    background-image:url(../Images/blue_button_bg.gif);
			    background-color: #ffffff;
			    CURSOR: hand;
			    font-style: normal ;
			}
			
			.btn1_mouseout { 
			BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid 
			} 
			.btn1_mouseover { 
			BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid 
			} 
			.btn2 {padding: 2 4 0 4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;} 
			.btn3_mouseout { 
			BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid 
			} 
			.btn3_mouseover { 
			BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid 
			} 
			.btn3_mousedown 
			{ 
			BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #FFE400 1px solid 
			} 
			.btn3_mouseup { 
			BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid 
			} 
			.btn_2k3 { 
			BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #002D96 1px solid 
			} 
		</style> 
	</head>

	<body topmargin="0" onkeydown="if(event.keyCode==13){return false;}">
		<#include "/components/buttongroup.ftl" />
		<hr>
	
		<@s.bean name="java.util.HashMap" id="qTableLayout">
			<@s.param name="tablecolspan" value="%{4}" />
		</@s.bean>
		<@s.form action="save" theme="nexus">
			<@s.hidden name="dmo.id"></@s.hidden>
			<@s.hidden name="dmo.available"></@s.hidden>
			<@s.hidden name="dmo.spell"></@s.hidden>
			<@s.hidden name="dmo.isLock"></@s.hidden>
			<@s.hidden name="dmo.lockReason"></@s.hidden>
			<@s.hidden name="dmo.createDate"></@s.hidden>
			<@s.hidden name="dmo.modifyDate"></@s.hidden>
			<@s.hidden name="dmo.version"></@s.hidden>

			<@s.textfield name="dmo.name" label="名称">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.textfield>
			<@s.textfield name="dmo.code" label="编号">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.textfield>

			<@c.choicebox id="ci" name="dmo.dept" action="../../data/datakind"
				label="部门" datakind="部门" displayValue="dmo.dept.name"
				value="dmo.dept.id">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@c.choicebox>
			<@s.textfield name="dmo.gender" label="性别">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{1}" />
			</@s.textfield>
			<@c.dateinput id="di" name="dmo.birthday" label="出生日期"></@c.dateinput>
			<@s.textfield name="dmo.post" label="职位"></@s.textfield>
			<@s.textfield name="dmo.idCardNo" label="身份证"></@s.textfield>
			<@s.textfield name="dmo.residenceAddress" label="户籍地址"></@s.textfield>
			<@s.textfield name="dmo.address" label="现居住地"></@s.textfield>
			<@s.textfield name="dmo.educationalLevel" label="教育程度"></@s.textfield>
			<@s.textfield name="dmo.contact" label="联系方式"></@s.textfield>
			<@s.textfield name="dmo.graduatedFrom" label="毕业院校"></@s.textfield>
			<@s.textfield name="dmo.major" label="专业"></@s.textfield>
			<@s.textfield name="dmo.postCode" label="邮编"></@s.textfield>
			<@s.textarea name="dmo.remark" label="摘要" wrap="true">
				<@s.param name="labelcolspan" value="%{1}" />
				<@s.param name="inputcolspan" value="%{3}" />
			</@s.textarea>
			<@s.submit value="提交" />

			
		</@s.form>
	</body>
</html>
