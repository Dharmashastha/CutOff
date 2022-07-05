package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CustomException;
import common.HelperUtility;
import cutoff.Connected;
import cutoff.StudentInfo;

public class CalculateCutOff extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CalculateCutOff() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void init(ServletConfig config) {
		cutoff.CalculateCutOff calculateCutOff = new cutoff.CalculateCutOff();
		try {
			super.init(config);
			config.getServletContext().setAttribute("calculateCutOff", calculateCutOff);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		RequestDispatcher req = request.getRequestDispatcher("GetData.jsp");
		req.forward(request, response);

		cutoff.CalculateCutOff calculateCutOff = (cutoff.CalculateCutOff) request.getServletContext()
				.getAttribute("calculateCutOff");
		if (request.getParameter("submit") != null) {
			String rollNumber = request.getParameter("rollno");
			String name = request.getParameter("name");
			int maths = Integer.parseInt(request.getParameter("maths"));
			int chemistry = Integer.parseInt(request.getParameter("chemistry"));
			int physics = Integer.parseInt(request.getParameter("physics"));

			try {
				HelperUtility.checkString(name);
				HelperUtility.checkString(rollNumber);
			} catch (CustomException e) {
				//e1.printStackTrace();
				System.out.println(e.getMessage());
			}

			StudentInfo studentInfo = new StudentInfo();

			studentInfo.setRollNumber(rollNumber);
			studentInfo.setName(name);
			studentInfo.setMaths(maths);
			studentInfo.setChemistry(chemistry);
			studentInfo.setPhysics(physics);

			double cutoff = calculateCutOff.engineeringCutOff(maths, chemistry, physics);

			studentInfo.setCutOffMark(cutoff);
			Connected connect = calculateCutOff.connect();

			try {
				System.out.println(connect.insertEngineeringCustOff(studentInfo));
			} catch (CustomException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

}
