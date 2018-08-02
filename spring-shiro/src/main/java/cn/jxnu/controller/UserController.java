package cn.jxnu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jxnu.domain.User;
import cn.jxnu.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String showIndex() {
		User user = userService.findByUsername("aaa");
		System.out.println("name="+user.getUsername());
		return "index";
	}
	
	@RequestMapping("/succ")
	public String showSucc() {
		return "succ";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "add";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@RequestMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
	}
	
	@RequestMapping("/login")
	public String login(String username,String password,Model model) {	
		/**
		 * 使用shiro编写认证操作
		 */
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		/**
		 * 不出现异常就登录成功，出现异常则登录失败
		 */
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			//e.printStackTrace();
			model.addAttribute("msg", "用户名不存在");
			System.out.println("用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//e.printStackTrace();
			model.addAttribute("msg", "密码错误");
			System.out.println("密码错误");
			return "login";
		}
		
		
		return "redirect:/succ.action";
	}
	
}
