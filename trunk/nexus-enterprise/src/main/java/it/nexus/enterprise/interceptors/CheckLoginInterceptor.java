package it.nexus.enterprise.interceptors;

import it.nexus.enterprise.system.user.model.User;

import java.lang.reflect.Method;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1683769230647168110L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		Object object = invocation.getAction();
		System.out.println("拦截的Action为：" + object.getClass().getName());
		ActionProxy aProxy = invocation.getProxy(); // 此处得到的为 struts 的 action
													// 函数的名称

		System.out.println("Proxy is :" + aProxy.getActionName());
		
		Method method = null;
		try {
			method = object.getClass().getMethod(aProxy.getActionName());
		} catch (NoSuchMethodException e) {
			if(method==null)
				return Action.ERROR;
		}
			
		User user = (User) session.get("userinfo");
		if (null != user) {
			return invocation.invoke();
		} else {
			return Action.LOGIN;
		}

	}
}
