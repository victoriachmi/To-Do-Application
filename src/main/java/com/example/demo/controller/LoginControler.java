package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginControler {

	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, ModelMap model) {
		String errorMessage = null;
		if (error != null) {
			errorMessage = "Username or Password is incorrect!!!";
		}

		if (logout != null) {
			errorMessage = "You have successfully logged out";

		}
		model.addAttribute("errorMessage", errorMessage);
		return "loginS";

	}

	@RequestMapping(value = "/forbiden", method = RequestMethod.GET)
	public String forbiden() {
		return "forbiden";
	}

}
