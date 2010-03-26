package it.nexus.enterprise.system.data.controller;

import it.nexus.core.datakind.Argument;
import it.nexus.core.datakind.ChoiceBoxSettings;
import it.nexus.core.datakind.IChoiceBoxCallback;
import it.nexus.core.datakind.WordPair;
import it.nexus.core.tools.xml.XmlUtils;
import it.nexus.enterprise.system.data.dao.DatakindDAO;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage(value = "json-default")
@Namespace("/data")
public class DatakindController extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<WordPair> lists = new ArrayList<WordPair>();
	
	@Resource
	DatakindDAO datakindDAO;

    @Resource
    JdbcTemplate jdbcTemplate;

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
		String query = query_string;
		String datakind = dk_string;

        IChoiceBoxCallback callback = ChoiceBoxSettings.getCallbacks().get(datakind);
        
        List<WordPair> wpList = callback.getData(jdbcTemplate,new Argument("","",""));
//
//        for(WordPair wp : wpList){
//            System.out.println("KEY:"+wp.getKey() + "   VALUE:"+wp.getValue());
//        }
        
		
		String templetDire = request.getSession().getServletContext()
		 .getRealPath("/config/datakind/datakinds.xml");
		Document document = XmlUtils.loadDocument(templetDire);
		lists.add(new WordPair("",""));
		List<?> list = datakindDAO.getDataKind(document, datakind);
		for (Object object : list) {
			Object[] dept = (Object[]) object;
			lists.add(new WordPair(dept[0].toString(), dept[1].toString()));
		}
		System.out.println("out put:"+query+":"+datakind);    
		return super.execute();
	}
	public List<WordPair> getLists() {
		return lists;
	}
	public void setLists(List<WordPair> lists) {
		this.lists = lists;
	}
}
