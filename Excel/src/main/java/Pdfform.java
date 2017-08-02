
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class Pdfform
 */
public class Pdfform extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		 	Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://10.10.0.141:3306/ajr", "itadmin", "ajr@123");
			Statement stmt = conn.createStatement();
			/* Define the SQL query */
			ResultSet query_set = stmt.executeQuery("select id,user_id,user_role from ajr.user_roles");
			/* Step-2: Initialize PDF documents - logical objects */
			Document my_pdf_report = new Document();
			PdfWriter.getInstance(my_pdf_report,
					new FileOutputStream("/home/sekharvaana/pdf_report_from_sql_using_java2.pdf"));
			my_pdf_report.open();
			// we have four columns in our table
			PdfPTable my_report_table = new PdfPTable(3);
			// create a cell object
			PdfPCell table_cell;

			while (query_set.next()) {
				String dept_id = query_set.getString("id");
				table_cell = new PdfPCell(new Phrase(dept_id));
				my_report_table.addCell(table_cell);
				String dept_name = query_set.getString("user_id");
				table_cell = new PdfPCell(new Phrase(dept_name));
				my_report_table.addCell(table_cell);
				String manager_id = query_set.getString("user_role");
				table_cell = new PdfPCell(new Phrase(manager_id));
				my_report_table.addCell(table_cell);

				/*
				 * String location_id=query_set.getString("LOCATION_ID");
				 * table_cell=new PdfPCell(new Phrase(location_id));
				 * my_report_table.addCell(table_cell);
				 */
			}
			/* Attach report table to PDF */
			my_pdf_report.add(my_report_table);
			my_pdf_report.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
