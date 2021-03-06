package com.student.portal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.portal.entity.Student;
import com.student.portal.service.StudentService;
import com.student.portal.serviceimpl.StudentServiceImpl;

/**
 * Servlet implementation class RegisterationController
 */
public class RegisterationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterationController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		throw new IOException("GET Method is not allowed");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Hits the registeration controller success");
		StudentService service = null;
		int registeredId = 0;
		Student student = null;
		try {
			String password = request.getParameter("singlePassword");
			String confirmPassword = request.getParameter("doublePassword");
			if (!confirmPassword.equals(password)) {
				throw new Exception("Password 1 and 2 does not match");
			}
			student = new Student();
			student.setPassword(password);
			student.setEmail(request.getParameter("email"));
			student.setMobile(request.getParameter("mobile"));
			student.setName(request.getParameter("name"));
			service = new StudentServiceImpl();
			registeredId = service.registerStudent(student);
			if (registeredId <= 0) {
				throw new Exception("Failed to register try again later");
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		} finally {
			request.setAttribute("generatedId", registeredId);
			request.getRequestDispatcher("PostRegisterationContorller").forward(request, response);
		}

	}

}
