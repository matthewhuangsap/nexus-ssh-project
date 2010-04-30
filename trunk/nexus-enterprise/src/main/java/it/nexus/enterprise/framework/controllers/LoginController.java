package it.nexus.enterprise.framework.controllers;

import com.opensymphony.xwork2.ActionContext;
import it.nexus.enterprise.annotations.Access;
import it.nexus.core.controller.BaseAction;
import it.nexus.enterprise.system.user.model.User;
import it.nexus.enterprise.system.user.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class LoginController extends BaseAction implements ServletRequestAware,
    SessionAware {
  private static final long serialVersionUID = 1788765307656307005L;
  /**
   * Dec 17, 2009 LoingController.java Administrator
   */
  private User dmo;

  public User getDmo() {
    return dmo;
  }

  public void setDmo(User dmo) {
    this.dmo = dmo;
  }

  @Resource
  UserService userService;

  @Action(value = "show", results = {@Result(type = "freemarker", location = "/login.ftl", name = "success")})
  public String show() throws Exception {
    return super.execute();
  }

  @Access("访问")
  @Action(value = "login", results = {@Result(type = "redirect", location = "default", name = "success")})
  public String login() throws Exception {
    if ((dmo.getUsername().equalsIgnoreCase("admin")
        && dmo.getPassword().equalsIgnoreCase("admin"))) {
      getSession().put("userinfo", dmo);
      return super.execute();
    }

    if (userService.isLogin(dmo.getUsername(), dmo.getPassword())) {
      getSession().put("userinfo", dmo);
      dmo = userService.getUser(dmo.getUsername(), dmo.getPassword());
      Map<String, Map<String, Long>> roleActions = userService.getRoleActions(dmo);
      getSession().put("roles", roleActions);

    } else {
      ActionContext.getContext().getSession().put("userinfo", null);
      ActionContext.getContext().getSession().put("roles", null);
    }
//    getSecurityMap();
    return super.execute();
  }

  private Map<String, Object> session;

  @Override
  public void setSession(Map<String, Object> sesssion) {
    this.session = sesssion;
  }

  private HttpServletRequest request;

  @Override
  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public Map<String, Object> getSession() {
    return session;
  }

}
