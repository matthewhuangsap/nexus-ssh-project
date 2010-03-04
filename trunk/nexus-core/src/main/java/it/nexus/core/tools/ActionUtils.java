package it.nexus.core.tools;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 *
 */
public class ActionUtils {
    /**
     * 在Struts2的Action中获取 request对象 
     * @param context
     * @return
     */
	public static HttpServletRequest getRequest(ActionContext context) {
		return (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
	}

    /**
     * 在Struts2的Action中获取 RealPath 信息 
     * @param context
     * @return
     */
	public static String getRealPath(ActionContext context,String path) {
		HttpServletRequest request = ActionUtils.getRequest(context);
		return request.getSession().getServletContext().getRealPath(path);
	}

    
	public static String getConfigPath(ActionContext context){
		return getRealPath(context,"/")+"config";
	}
	
	public static String getPluginPath(ActionContext context){
		return getConfigPath(context)+"\\plugin";
	}
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static String getClassesPath(ActionContext context){
		HttpServletRequest request = ActionUtils.getRequest(context);
		return request.getSession().getServletContext().getRealPath("/WEB-INF/classes");
	}
	
	public static String getLibPath(ActionContext context){
		HttpServletRequest request = ActionUtils.getRequest(context);
		return request.getSession().getServletContext().getRealPath("/WEB-INF/lib");
	}
	
	/**
	 * 
	 * @param context Struts2 Action Context
	 * @return HttpSession 
	 */
	public static HttpSession getSession(ActionContext context) {
		HttpServletRequest request = ActionUtils.getRequest(context);
		return request.getSession();
	}
	
	/**
	 * 
	 * @param context Struts2 Action Context
	 * @param name Session Attribute Name
	 * @param object Session Attribute value
	 */
	public static void setSessionAttribute(ActionContext context ,String name, Object object){
		ActionUtils.getSession(context).setAttribute(name, object);
	}
	
	/**
	 * @param context
	 * @param name
	 * @return 
	 */
	public static Object getSessionAttribute(ActionContext context ,String name){
		return ActionUtils.getSession(context).getAttribute(name);
	}
	
    public static ServletContext getApplication(ActionContext context){
        return ActionUtils.getSession(context).getServletContext();
    }

    public static Object getApplictionAttribute(ActionContext context, String name){
        return ActionUtils.getApplication(context).getAttribute(name);
    }

    public static void setApplicationAttribute(ActionContext context,String name, Object value){
        ActionUtils.getApplication(context).setAttribute(name,value);
    }
}
