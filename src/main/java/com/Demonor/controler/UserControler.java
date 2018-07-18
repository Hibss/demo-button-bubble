package com.Demonor.controler;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.Demonor.model.User;
import com.Demonor.service.UserService;

@RestController
public class UserControler {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	String home(){
		return "Hello World!!!!!!";
	}
	
	@RequestMapping("/userlist")
	public ModelAndView list(Model model){
	
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return new ModelAndView("user/userList");
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest req,Model model){
		String userName = req.getParameter("userName");
		String url = "user/userList";
		if(userService.remove(userName)){
			model.addAttribute("remove", "移除成功");
			List<User> users = userService.getAll();
			if(users.isEmpty()){
				model.addAttribute("remove", "");
			}
			model.addAttribute("users", users);
			url = "user/userList";
		}
		return new ModelAndView(url);
	}
	
	

}
