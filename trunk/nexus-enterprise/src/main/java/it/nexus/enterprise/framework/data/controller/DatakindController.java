package it.nexus.enterprise.framework.data.controller;

import it.nexus.enterprise.framework.data.Argument;
import it.nexus.enterprise.framework.data.ChoiceBoxSettings;
import it.nexus.enterprise.framework.data.IChoiceBoxCallback;
import it.nexus.enterprise.framework.data.WordPair;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
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
    JdbcTemplate jdbcTemplate;

	@Action(value = "/data/datakind", results = { @Result(type="json", name = "success")})
	public String execute() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String input_str = request.getParameter("query");
		//获取DataKind的值
		String kind_str =request.getParameter("dk");
		//获取其它控件的值，用来在下拉时做过滤数据使用
		String code_str = request.getParameter("beforDrop");
		
		if(input_str==null)input_str = "";
		if(kind_str == null)kind_str = "";
        if(code_str == null)code_str = "";

        IChoiceBoxCallback callback = ChoiceBoxSettings.getCallbacks().get(kind_str);
        
       lists = callback.getData(jdbcTemplate,new Argument(code_str,input_str));
		return super.execute();
	}
	public List<WordPair> getLists() {
		return lists;
	}
	public void setLists(List<WordPair> lists) {
		this.lists = lists;
	}
}
