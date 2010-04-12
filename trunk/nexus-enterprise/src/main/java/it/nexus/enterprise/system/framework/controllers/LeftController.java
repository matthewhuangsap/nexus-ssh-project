package it.nexus.enterprise.system.framework.controllers;

import it.nexus.core.BasePlugin;
import it.nexus.core.SettingClass;
import it.nexus.core.annotation.Access;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.menu.Menu;
import it.nexus.core.tools.ActionUtils;
import it.nexus.core.tools.ClassUtils;
import it.nexus.core.tools.RoleUtils;
import it.nexus.core.tools.xml.XmlUtils;

import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.annotation.Resource;

import it.nexus.core.web.PluginManager;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author dcriori 主要是生成树型菜单
 */
@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
public class LeftController extends BaseAction {
	private static final long	serialVersionUID	= -1313764364459365272L;
	private final String		page_left			= "/left.ftl";
	@Resource
	SettingClass				settingClass;

	@Access("访问")
	@Action(value = "/left", results = { @Result(type = "freemarker", location = page_left, name = "success") })
	public String left() throws Exception {
        Map<String, BasePlugin> plugins =  PluginManager.getPlugins();
        ActionContext context = getContext();
        Iterator it = plugins.keySet().iterator();
        //创建一个菜单根结点
        Menu rootMenu = new Menu();
        for   (Object   o   :   plugins.keySet())   {
             BasePlugin bp =(BasePlugin) plugins.get(o);  
             rootMenu.add(bp.getMenus().getChilds());
        }

        //用Menu的ID属性将菜单排序
        Menu[] array = rootMenu.getChilds().toArray(new Menu[] {});
        Arrays.sort(array);
        rootMenu.setChilds(Arrays.asList(array));

        //rootMenu排序完成
        //得到权限
        Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, Map<String,Long>> roles = (Map<String, Map<String,Long>>) session.get("roles");
        //检查权限
        checkMenuRole(rootMenu,roles);

		StringBuilder menu_info = new StringBuilder();
		menu_info.append("<ul id=\"menu\" class=\"filetree treeview-famfamfam\">");
        createMenu2(menu_info,rootMenu);
		menu_info.append("</ul>");
		getSession().put("output", menu_info.toString());
		return super.execute();
	}

    private void checkMenuRole(Menu rootMenu,Map<String, Map<String,Long>> roles) {
        final Map<String, Map<String, Map<Long, String>>> plugin_map = (Map<String, Map<String, Map<Long, String>>>) ActionContext
				.getContext().getSession().get("access_group_map");
		final Map<String, String> plugin_info = (Map<String, String>) ActionContext
				.getContext().getSession().get("plugin_info");
		System.out.println(plugin_map);
		System.out.println(plugin_info);
        for(Menu menu : rootMenu.getChilds()){
            if(menu.isFolder()){
                checkMenuRole(menu,roles);
                continue;
            }
            String[] role_arr = menu.getRole().split("[.]");
            String plugin_name = role_arr[0];
			String access_group_name = role_arr[1];
			String access_name = role_arr[2];
            
        }
    }

    private void createMenu2(StringBuilder menu_info, Menu rootMenu) {
        //无URL
        for(Menu menu: rootMenu.getChilds()){
            if(menu.isFolder()){
                menu_info.append("<li class=\"closed\"><span class=\"folder\">"
                            + menu.getName()
                            + "</span><ul>");
                if(menu.hasChilds())
                {
                    createMenu2(menu_info,menu);
                }
                menu_info.append("</ul></li>");
            }else{
                menu_info.append("<li><span class=\"file\"><a href=\""
						+ menu.getUrl()
						+ "\" target=\"contener\">"
						+ menu.getName() + "</a></span></li>"); 
            }
        }
    }

    //早期版本
    private void temp(ActionContext context,StringBuilder menu_info)throws Exception{
        // 创建一个新的document存放合并后的XML
		Document merge_doc = DocumentHelper.createDocument();
		Element root = merge_doc.addElement("Plugin");
		Element menus = root.addElement("Menus");

		if (settingClass.getDebugModule()) {
			String plugin_path = ActionUtils.getPluginPath(context);
			System.out.println("Plugin Path:" + plugin_path);
			List<String> plugin_file = ClassUtils.searchFileFromFolder(
					plugin_path, ".*\\.plugin.xml");
			for (String pf : plugin_file) {
				Document document = XmlUtils.loadDocument(pf);
				List<Element> list = document.selectNodes("//Plugin//Menus");
				for (Element element : list) {
					menus.appendContent(element);
				}
			}
		} else {
			// 得到Plugin里的菜单配置文件
			List<String> plugin_matching = settingClass.getPluginMatching();
			// 存放jar的路径及名称
			List<String> jar_list = new ArrayList<String>();

			String lib_path = ActionUtils.getLibPath(context);
			for (String string : plugin_matching) {
				// System.out.println("打印配置的插件正则匹配：" + string);
				List<String> plugin_list = ClassUtils.searchFileFromFolder(
						lib_path, string);
				for (String jar_path : plugin_list) {
					jar_list.add(jar_path);
				}
			}
			// 处理读取的jar中的xml
			for (String jar_path : jar_list) {
				JarFile jarFile = new JarFile(jar_path);
				Enumeration<?> enu = jarFile.entries();
				while (enu.hasMoreElements()) {
					JarEntry entry = (JarEntry) enu.nextElement();
					String name = entry.getName();
					if (name.equals("plugin.xml")) {
						InputStream input = jarFile.getInputStream(entry);
						Document document = XmlUtils.loadDocument(input);
						List<Element> list = document
								.selectNodes("//Plugin//Menus");
						for (Element element : list) {
							menus.appendContent(element);
						}
					}
				}
				jarFile.close();
			}
		}

		// 过滤权限

		List<Element> menu_url = merge_doc
				.selectNodes("//Plugin//Menus//Menu[@url]");

		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, Long> roles = (Map<String, Long>) session.get("roles");
		checkRole(menu_url, roles);

		List<?> merge_menu = XmlUtils.getElementList(merge_doc,
				"/Plugin/Menus/Menu", "@id");
		createMenu(merge_menu, menu_info, merge_doc);
    }

	private void removeElement(Element element) {
		Element parent = element.getParent();
		parent.remove(element);
	}

	@SuppressWarnings("unchecked")
	private void checkRole(List<Element> menu_url_list, Map<String, Long> roles) {
		final Map<String, Map<String, Map<Long, String>>> plugin_map = (Map<String, Map<String, Map<Long, String>>>) ActionContext
				.getContext().getSession().get("access_group_map");
		final Map<String, String> plugin_info = (Map<String, String>) ActionContext
				.getContext().getSession().get("plugin_info");
		System.out.println(plugin_map);
		System.out.println(plugin_info);

		for (Element element : menu_url_list) {
			String role_name = element.attributeValue("roles");
			Long role_key = null;
			String[] arrary = null;
			if (!role_name.equals(""))
				arrary = role_name.split("[.]"); // 此处.为分割符
			String plugin_name = arrary[0];
			String access_group_name = arrary[1];
			String access_name = arrary[2];

			System.out.println(plugin_name + "。" + access_group_name + "。"
					+ access_name + "成功");
			if (plugin_map == null) {
				// removeElement(element);
				continue;
			}
			Map<String, Map<Long, String>> access_group_map = null;
			if (plugin_map.containsKey(plugin_name)) {
				access_group_map = plugin_map.get(plugin_name);
			}

			if (access_group_map == null) {
				// removeElement(element);
				continue;
			}
			Map<Long, String> access_map = null;
			if (access_group_map.containsKey(access_group_name)) {
				access_map = access_group_map.get(access_group_name);
			}

			if (access_map == null) {
				// removeElement(element);
				continue;
			}
			if (access_map.containsValue(access_name)) {
				Iterator<?> iterator = access_map.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					if (entry.getValue().equals(access_name)) {
						role_key = (Long) entry.getKey();
					}
				}
			}

			Long authorised_role = null;
			if (roles.containsKey(access_group_name)) {
				authorised_role = roles.get(access_group_name);
			}
			if (role_key != null && authorised_role != null) {
//				System.out
//						.println("asdf:" + role_key + "   " + authorised_role);
				if (!RoleUtils.checkRole(authorised_role, role_key)) {
					removeElement(element);
				} else if (authorised_role == -1) {
					removeElement(element);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void createMenu(List<?> list, StringBuilder sBuilder,
			Document document) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, Long> roles = (Map<String, Long>) session.get("roles");
		System.out.println(roles);
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (XmlUtils.elementIsExistChild(element)) {
				if (!XmlUtils.elementIsExistAttribute(element, "url")) {
					sBuilder
							.append("<li class=\"closed\"><span class=\"folder\">"
									+ element.attributeValue("name")
									+ "</span><ul>");
				}
				List<?> childList = element.elements();
				createMenu(childList, sBuilder, document);
				sBuilder.append("</ul></li>");
			} else {
				if (!XmlUtils.elementIsExistAttribute(element, "url")) {
					continue;
				}

				sBuilder.append("<li><span class=\"file\"><a href=\""
						+ element.attributeValue("url")
						+ "\" target=\"contener\">"
						+ element.attributeValue("name") + "</a></span></li>");
			}
		}
	}

	@SuppressWarnings("unused")
	private Long getAccessIndex(final String access_group) {

		return null;
	}
}