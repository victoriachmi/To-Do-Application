package com.example.demo.service;

public class LoginService {

		public Boolean isValidUser(String userName, String password) {
			if(userName.equals("") && password.equals(""))
				return true;
			
			return false;
		}
}
