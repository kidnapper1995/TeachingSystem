package com.example.Study.controller;

import com.example.Study.bean.SUser;
import com.example.Study.service.SecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求页面分发，注意和WebMvcConfig的对比，功能类似
 * @author Veiking
 */
@Controller
public class PageController {
	@Autowired
	SecurityDataService securityDataService;



	@RequestMapping("/index")
	public String index(Model model, String tt) {
		return "index";
	}




	@RequestMapping("/login")
	public String login(Model model, String tt) {
		return "login";
	}

	@RequestMapping("/register")
	public String re(Model m) {
		return "register";
	}





	@RequestMapping("/register/user")
	public String register(Model model, String userName, String password,String realName) {
		SUser sUser1=securityDataService.findSUserByName(userName);
		if (sUser1!=null){
			model.addAttribute("error","用户名已被占用，请重新注册!三秒钟自动跳转注册页面");
			return "register/error";
		}


			securityDataService.addUser(userName,realName,password);
			model.addAttribute("error","注册成功！三秒钟自动跳转登陆页面");
			return "register/success";



	}





}
