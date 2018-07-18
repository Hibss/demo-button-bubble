package com.Demonor.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.Demonor.model.User;
import com.Demonor.service.UserService;

@RestController
public class LoginControler {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(Model model){
	
		
		return "sdsdsdsd";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView tologin(Model model){
		model.addAttribute("user", new User());
		return new ModelAndView("service/login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute(value="user") User user,Model model){
		String url = "service/index";
		if(user.getUsername().equals("")||user.getPassword().equals("")){
			model.addAttribute("error", "用户名密码不能为空");
			url = "service/login";
		}
		else if(!userService.isUser(user)){
			model.addAttribute("error", "用户名或密码错误");
			url = "service/login";
		}else{
			model.addAttribute("login_success", "登录成功");
			url = "service/index";
		}
		return new ModelAndView(url);
		
	}

}
