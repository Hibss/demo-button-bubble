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
public class RegisterControler {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView toRegister(Model model){
		model.addAttribute("user", new User());
		return new ModelAndView("service/register");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute(value="user") User user,Model model){
		String url = "service/login";
		userService.save(user);
		if(userService.get(user.getUsername())!=null){
			model.addAttribute("register_success", "注册成功");
			url = "service/login";
		}else{
			url = "service/register";
		}	
		return new ModelAndView(url);	
	}

}
