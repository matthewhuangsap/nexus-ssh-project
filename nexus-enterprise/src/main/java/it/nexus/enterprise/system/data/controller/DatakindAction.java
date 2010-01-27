package it.nexus.enterprise.system.data.controller;

import it.nexus.core.tools.xml.XmlUtils;
import it.nexus.enterprise.system.data.dao.DatakindDAO;
import it.nexus.enterprise.system.data.model.Datakind;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Document;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace("/data")
public class DatakindAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<Datakind> lists = new ArrayList<Datakind>();
	
	@Resource(name="dkDAO")
	DatakindDAO datakindDAO;
	
	@Action(value = "/data/datakind", results = { @Result(type="json", name = "success")})
	public String execute() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String query_string= request.getParameter("query");
		//获取DataKind的值
		String dk_string=request.getParameter("dk");
		//获取其它控件的值，用来在下拉时做过滤数据使用
		String bd_String = request.getParameter("beforDrop");
		
		if(query_string==null)
			query_string = "";
		if(dk_string == null)
			dk_string = "";
		//得到查询字符串和datakind名称，比如query=部门1 d 
//			String query = new String(query_string.getBytes("ISO-8859-1"),"UTF-8");
//			String datakind =new String(dk_string.getBytes("ISO-8859-1"),"UTF-8");
		String query = query_string;
		String datakind = dk_string;
		
		String templetDire = request.getSession().getServletContext()
		 .getRealPath("/config/datakind/datakinds.xml");
//			System.out.println("AAAAAAAA"+templetDire);
		Document document = XmlUtils.loadDocument(templetDire);
		lists.add(new Datakind("",""));
		List<?> list = datakindDAO.getDataKind(document, datakind);
		for (Object object : list) {
			Object[]dept = (Object[]) object;
			lists.add(new Datakind(dept[0].toString(), dept[1].toString()));
		}
		System.out.println("out put:"+query+":"+datakind);    
		return super.execute();
	}
	public List<Datakind> getLists() {
		return lists;
	}
	public void setLists(List<Datakind> lists) {
		this.lists = lists;
	}
}
