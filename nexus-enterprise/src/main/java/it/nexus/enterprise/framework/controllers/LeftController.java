package it.nexus.enterprise.framework.controllers;

import com.opensymphony.xwork2.ActionContext;
import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.enterprise.annotations.Access;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.menu.Menu;
import it.nexus.core.tools.RoleUtils;
import it.nexus.core.web.PluginManager;
import it.nexus.enterprise.system.user.model.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author dcriori 主要是生成树型菜单
 */
@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
public class LeftController extends BaseAction {
  private static final long serialVersionUID = -1313764364459365272L;
  private final String page_left = "/left.ftl";
  private Menu leftMenu = new Menu();

  @Access("访问")
  @Action(value = "/left", results = {@Result(type = "freemarker", location = page_left, name = "success")})
  public String left() throws Exception {
    Map<String, BasePlugin> plugins = PluginManager.getPlugins();
    ActionContext context = getContext();
    Iterator it = plugins.keySet().iterator();
    //创建一个菜单根结点
    Menu rootMenu = new Menu();
    for (Object o : plugins.keySet()) {
      BasePlugin bp = (BasePlugin) plugins.get(o);
      rootMenu.add(bp.getMenus().getChilds());
    }

    //用Menu的ID属性将菜单排序
    Menu[] array = rootMenu.getChilds().toArray(new Menu[]{});
    Arrays.sort(array);
    rootMenu.setChilds(Arrays.asList(array));

    //rootMenu排序完成
    //得到权限
    Map<String, Object> session = ActionContext.getContext().getSession();
    Map<String, Map<String, Long>> roles = (Map<String, Map<String, Long>>) session.get("roles");
    User user = (User) session.get("userinfo");
    StringBuilder menu_info = new StringBuilder();
    menu_info.append("<ul id=\"menu\" class=\"filetree treeview-famfamfam\">");
    //检查权限,内置用户admin除外
    if(!user.getUsername().equalsIgnoreCase("admin"))
        checkMenuRole(leftMenu,rootMenu, roles);
    else
      leftMenu.add(rootMenu.getChilds());
    
    
    createMenu(menu_info, leftMenu);
    menu_info.append("</ul>");
    getSession().put("output", menu_info.toString());
    return super.execute();
  }

  private void checkMenuRole(Menu resultMenu,Menu rootMenu, Map<String, Map<String, Long>> roles) throws NexusException {
    final Map<String, Map<String, Map<Long, String>>> PLUGIN_ACCESS_MAP = PluginManager.getPluginMap();

    for (Menu menu : rootMenu.getChilds()) {
      if (menu.isFolder()) {
        Menu curr_menu = createNewMenu(menu,true);
        checkMenuRole(curr_menu,menu, roles);
        if(curr_menu.getChilds().size()>0)
          resultMenu.add(curr_menu);
        continue;
      }

      String[] role_arr = menu.getRole().split("[.]");
      long userPurview = findUserPurview(role_arr, roles);
      long optPurview = findOptPurview(role_arr, PLUGIN_ACCESS_MAP);
      if (RoleUtils.checkRole(userPurview, optPurview)) {
        resultMenu.add(createNewMenu(menu,false));
      }
    }
  }

  private Menu createNewMenu(Menu menu,Boolean isFolder) throws NexusException {
    return new Menu(menu.getId(),menu.getName(),menu.getUrl(),menu.getUrl(),isFolder);
  }

  private long findUserPurview(String[] role_arr,
                               Map<String, Map<String, Long>> authorisedRoles) {
    String plugin_name = role_arr[0];      //plugin名称，如：system,psi
    String module_name = role_arr[1];      //功能模块名称 如：部门，员工，存货
    if (authorisedRoles == null)
      return 0;
    if (authorisedRoles.containsKey(plugin_name)) {
      Map<String, Long> optionMap = authorisedRoles.get(plugin_name);
      if (optionMap.containsKey(module_name)) {
        Long result = optionMap.get(module_name);
        if (result == null)
          return 0;
        else
          return result.longValue();
      }
    }
    return 0;
  }

  private long findOptPurview(String[] roleArr,
                              Map<String, Map<String, Map<Long, String>>> pluginAccessMap) {
    String plugin_name = roleArr[0];      //plugin名称，如：system,psi
    String module_name = roleArr[1];      //功能模块名称 如：部门，员工，存货
    String option_name = roleArr[2];      //功能名称     如：新建，审核，删除
    if (pluginAccessMap == null)
      return 0;
    if (pluginAccessMap.containsKey(plugin_name)) {
      Map<String, Map<Long, String>> moduleMap = pluginAccessMap.get(plugin_name);
      if (moduleMap.containsKey(module_name)) {
        Map<Long, String> optionMap = moduleMap.get(module_name);
        if (optionMap.containsValue(option_name)) {
          Iterator<?> iterator = optionMap.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue().equals(option_name)) {
              Long result = (Long) entry.getKey();
              if (result == null)
                return 0;
              else
                return result.longValue();
            }
          }
        }
      }
    }
    return 0;
  }


  private void createMenu(StringBuilder menu_info, Menu rootMenu) {
    //无URL
    for (Menu menu : rootMenu.getChilds()) {
      if (menu.isFolder()) {
        menu_info.append("<li class=\"closed\"><span class=\"folder\">"
            + menu.getName()
            + "</span><ul>");
        if (menu.hasChilds()) {
          createMenu(menu_info, menu);
        }
        menu_info.append("</ul></li>");
      } else {
        if (!menu.isRemove()) {
          menu_info.append("<li><span class=\"file\"><a href=\""
              + menu.getUrl()
              + "\" target=\"contener\">"
              + menu.getName() + "</a></span></li>");
        }
      }
    }
  }
}
