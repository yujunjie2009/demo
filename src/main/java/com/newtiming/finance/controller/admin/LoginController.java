package com.newtiming.finance.controller.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.newtiming.finance.controller.common.BaseController;

/**
 * 登录
 * @author yujunjie
 *
 */
@Controller
//@RequestMapping("/admin")
public class LoginController extends BaseController {


	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/reqlogin.htm", method = RequestMethod.GET)
	public String reqlogin(HttpServletRequest request) throws Exception {
		return adminViewsRoot + "/login";
	}
	
	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception {
		return adminViewsRoot + "/index/index";
	}
	

}