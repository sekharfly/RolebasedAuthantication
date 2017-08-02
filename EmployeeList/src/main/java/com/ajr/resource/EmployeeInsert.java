package com.ajr.resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajr.model.Methods;

public class EmployeeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Methods instance = Methods.getInstance();
		instance.name();
		Connection connection = instance.getConnection();
		String emp_code = request.getParameter("empCode");
		String doj = request.getParameter("DOJ");
		String lastname = request.getParameter("first_name");
		String name = request.getParameter("last_name");
		String designation = request.getParameter("designation");
		String dateofbirth = request.getParameter("dateofbirth");
		String gender = request.getParameter("gender");
		String emailId = request.getParameter("emailId");
		String marriedStatus = request.getParameter("marriedstatus");
		if ("married".equals(marriedStatus)) {
			String marrigedate = request.getParameter("mdate");
			String spousename = request.getParameter("spousename");
			int noOfChild = Integer.parseInt(request.getParameter("noofchild"));
			String chname = request.getParameter("childrenname");
			String chdob = request.getParameter("childrennamedob");
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(
						"insert into employee_list(employee_code,date_of_joing,first_name,last_name,designation,date_birth,gender,email_id,marital_status,marrige_date,spouse_name,number_of_childrens,childrens_name,children_dob) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				prepareStatement.setString(1, emp_code);
				prepareStatement.setString(2, doj);
				prepareStatement.setString(3, name);
				prepareStatement.setString(4, lastname);
				prepareStatement.setString(5, designation);
				prepareStatement.setString(6, dateofbirth);
				prepareStatement.setString(7, gender);
				prepareStatement.setString(8, emailId);
				prepareStatement.setString(9, marriedStatus);
				prepareStatement.setString(10, marrigedate);
				prepareStatement.setString(11, spousename);
				prepareStatement.setInt(12, noOfChild);
				prepareStatement.setString(13, chname);
				prepareStatement.setString(14, chdob);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			PreparedStatement prepareStatement;
			try {
				prepareStatement = connection.prepareStatement(
						"insert into employee_list(employee_code,date_of_joing,first_name,last_name,designation,date_birth,gender,email_id,marital_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				prepareStatement.setString(1, emp_code);
				prepareStatement.setString(2, doj);
				prepareStatement.setString(3, name);
				prepareStatement.setString(4, lastname);
				prepareStatement.setString(5, designation);
				prepareStatement.setString(6, dateofbirth);
				prepareStatement.setString(7, gender);
				prepareStatement.setString(8, emailId);
				prepareStatement.setString(9, marriedStatus);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
