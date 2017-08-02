import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Exporter {
	private static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://10.10.0.141:3306/ajr";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "itadmin", "ajr@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver class not found. Please add MySQL connector jar in classpath");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured while getting Database connection");
		}
		return con;
	}

	public ArrayList<Object[]> getTableData() {
		ArrayList<Object[]> tableDataList = null;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement("select * from ajr.employee_list");
				ResultSet result = ps.executeQuery();
				tableDataList = new ArrayList<Object[]>();
				while (result.next()) {
					Object[] objArray = new Object[15];
					objArray[0] = result.getString(1);
					objArray[1] = result.getString(2);
					objArray[2] = result.getString(3);
					objArray[3] = result.getString(4);
					objArray[4] = result.getString(5);
					objArray[5] = result.getString(6);
					objArray[6] = result.getString(7);
					objArray[7] = result.getString(8);
					objArray[8] = result.getString(9);
					objArray[9] = result.getString(10);
					objArray[10] = result.getString(11);
					objArray[11] = result.getString(12);
					objArray[12] = result.getString(13);
					objArray[13] = result.getString(14);
					objArray[14] = result.getString(15);
					tableDataList.add(objArray);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to create PreparedStatement");
			}
		}
		return tableDataList;
	}

	public void doExport(ArrayList<Object[]> dataList) {
		if (dataList != null && !dataList.isEmpty()) {
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet sheet = workBook.createSheet();
			HSSFRow headingRow = sheet.createRow(0);
			headingRow.createCell((short) 0).setCellValue("id");
			headingRow.createCell((short) 1).setCellValue("employee_code");
			headingRow.createCell((short) 2).setCellValue("date_of_joing");
			headingRow.createCell((short) 3).setCellValue("first_name");
			headingRow.createCell((short) 4).setCellValue("last_name");
			headingRow.createCell((short) 5).setCellValue("designation");
			headingRow.createCell((short) 6).setCellValue("date_birth");
			headingRow.createCell((short) 7).setCellValue("gender");
			headingRow.createCell((short) 8).setCellValue("email_id");
			headingRow.createCell((short) 9).setCellValue("marital_status");
			headingRow.createCell((short) 10).setCellValue("marrige_date");
			headingRow.createCell((short) 11).setCellValue("spouse_name");
			headingRow.createCell((short) 12).setCellValue("number_of_childrens");
			headingRow.createCell((short) 12).setCellValue("childrens_name");
			headingRow.createCell((short) 14).setCellValue("childrens_dob");
			short rowNo = 1;
			for (Object[] objects : dataList) {
				if (objects != null) {
					HSSFRow row = sheet.createRow(rowNo);
					if (objects[0] != null) {
						row.createCell((short) 0).setCellValue(objects[0].toString());
					} else {
						row.createCell(0);
					}
					if (objects[1] != null) {
						row.createCell((short) 1).setCellValue(objects[1].toString());
					} else {
						row.createCell(1);
					}
					if (objects[2] != null) {
						row.createCell((short) 2).setCellValue(objects[2].toString());
					} else {
						row.createCell(2);
					}
					if (objects[3] != null) {
						row.createCell((short) 3).setCellValue(objects[3].toString());
					} else {
						row.createCell(3);
					}
					if (objects[4] != null) {
						row.createCell((short) 4).setCellValue(objects[4].toString());
					} else {
						row.createCell(4);
					}
					if (objects[5] != null) {
						row.createCell((short) 5).setCellValue(objects[5].toString());
					} else {
						row.createCell(5);
					}
					if (objects[6] != null) {
						row.createCell((short) 6).setCellValue(objects[6].toString());
					} else {
						row.createCell(6);
					}
					if (objects[7] != null) {
						row.createCell((short) 7).setCellValue(objects[7].toString());
					} else {
						row.createCell(7);
					}
					if (objects[8] != null) {
						row.createCell((short) 8).setCellValue(objects[8].toString());
					} else {
						row.createCell(8);
					}
					if (objects[9] != null) {
						row.createCell((short) 9).setCellValue(objects[9].toString());
					} else {
						row.createCell(9);
					}
					if (objects[10] != null) {
						row.createCell((short) 10).setCellValue(objects[10].toString());
					} else {
						row.createCell(10);
					}
					if (objects[11] != null) {
						row.createCell((short) 11).setCellValue(objects[11].toString());
					} else {
						row.createCell(11);
					}
					if (objects[12] != null) {
						row.createCell((short) 12).setCellValue(objects[12].toString());
					} else {
						row.createCell(12);
					}
					if (objects[13] != null) {
						row.createCell((short) 13).setCellValue(objects[13].toString());
					} else {
						row.createCell(13);
					}
					if (objects[14] != null) {
						row.createCell((short) 14).setCellValue(objects[14].toString());
					} else {
						row.createCell(14);
					}
				}
				rowNo++;
			}

			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/sekharr.xlsx");
			try {
				FileOutputStream fos = new FileOutputStream(file);
				workBook.write(fos);
				fos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Invalid directory or file not found");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occurred while writting excel file to directory");
			}
		}
	}

	/*
	 * public static void main(String[] args) { Exporter exporter = new
	 * Exporter(); ArrayList<Object[]> dataList = exporter.getTableData(); if
	 * (dataList != null && dataList.size() > 0) { exporter.doExport(dataList);
	 * } else { System.out.println(
	 * "There is no data available in the table to export"); } }
	 */
}