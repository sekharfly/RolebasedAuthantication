
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.pdf.parser.clipper.Paths;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String s1=request.getParameter("m1");
		
		if("male".equals(s1))
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://10.10.0.141:3306/ajr", "itadmin",
					"ajr@123");
			PreparedStatement pstm = null;
			Part filePart = request.getPart("fileName");
			String string = java.nio.file.Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream inputStream = filePart.getInputStream();
			
			

			//FileInputStream input = new FileInputStream(string);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);

				double id = row.getCell(0).getNumericCellValue();
				String password = row.getCell(1).getStringCellValue();
				String email = row.getCell(2).getStringCellValue();
				String sql = "INSERT INTO ajr.user_roles (id,user_id,user_role)VALUES('" + id + "','" + password + "','"
						+ email + "')";
				pstm = (PreparedStatement) con.prepareStatement(sql);
				pstm.executeUpdate();
				System.out.println("Import rows " + i);
			}
			pstm.close();
			con.close();
			System.out.println("Success import excel to mysql table");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

}
