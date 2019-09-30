package com.zs.apsg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zs.apsg.dto.LoginDto;
import com.zs.apsg.dto.UserDto;
import com.zs.apsg.services.ApsgService;

@RestController
@RequestMapping(value = "/apsg")
@CrossOrigin(origins = "*")
public class ApsgController {

	@Autowired
	private ApsgService service;

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String saveUserDateInDb(@RequestBody UserDto user) {

		System.out.println("Inside controller saveUserDataInDb method");
		System.out.println("---------------------------------------------");
		System.out.println("Entered User Data is :-");
		System.out.println(user);
		System.out.println("---------------------------------------------");
		UserDto dbResponse = service.saveUserData(user);
		if (dbResponse != null)
			return "Data Successfully Saved in Db";
		else
			return "Data Couldn't be saved in Db.Please fill all the required fields.";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<LoginDto> fetchLoginCredentialsFromDb(@RequestBody LoginDto loginDto, HttpServletRequest request) {
		System.out.println("Inside controller fetchLoginCredentialsFromDb method");
		System.out.println("------------------------------------------------");
		System.out.println("Login credentials Entered :-" + loginDto.getUserName() + "," + loginDto.getPassword());
		System.out.println("------------------------------------------------");
		UserDto user = new UserDto();

		HttpSession session = request.getSession();
		session.setAttribute("userName", loginDto.getUserName());
		session.setAttribute("password", loginDto.getPassword());

		if (loginDto.getUserName() != null && loginDto.getPassword() != null) {
			UserDto responseFromDb = service.fetchUserData(loginDto);
			if (loginDto.getUserName().equals(responseFromDb.getUserName())
					&& loginDto.getPassword().equals(responseFromDb.getPassword())) {
				System.out.println("Logged In Successfully.");
				//return "Welcome " + loginDto.getUserName();
				return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
			} else {
				System.err.println("Invalid Login Credentials Entered");
				return new ResponseEntity<LoginDto>(loginDto, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		//return null;
	}

	@RequestMapping(value = "/logout")
	public void logoutUser(HttpServletRequest request) {
		System.out.println("Inside logoutUser method...");

		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userName"));
		System.out.println(session.getAttribute("password"));

		if (session.getAttribute("userName") != null && session.getAttribute("password") != null) {
			session.invalidate();
			System.err.println("Logged out Successfully...");
		}

		else
			System.err.println("Please Login First");

	}

}
