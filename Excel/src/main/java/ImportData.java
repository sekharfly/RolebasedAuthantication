import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ImportData {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://10.10.0.141:3306/ajr","itadmin","ajr@123");
			PreparedStatement pstm = null;
			//D://file.xlsx
			FileInputStream input = new FileInputStream("/home/sekharvaana/sekhar1.xlsx");

			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				
				int id = (int) row.getCell(0).getNumericCellValue();
				String password = row.getCell(1).getStringCellValue();
				String email = row.getCell(2).getStringCellValue();
				String sql = "INSERT INTO ajr.user_roles (id,user_id,user_role)VALUES('"+id+"','"+password+"','"+email+"')";
				pstm = (PreparedStatement) con.prepareStatement(sql);
				pstm.executeUpdate();
				System.out.println("Import rows " + i);
			}
			//con.commit();
			pstm.close();
			con.close();
			input.close();
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
