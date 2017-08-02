import java.io.FileOutputStream;
import java.io.*;
import java.util.*;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class jdbc_pdf_report {  
        public static void main(String[] args) throws Exception{
                
                /* Create Connection objects */
                Class.forName ("com.mysql.jdbc.Driver"); 
                Connection conn = DriverManager.getConnection("jdbc:mysql://10.10.0.141:3306/ajr","itadmin","ajr@123");
                Statement stmt = conn.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("select id,user_id,user_role from ajr.user_roles");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("/home/sekharvaana/pdf_report_from_sql_using_java.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(3);
                //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String dept_id = query_set.getString("id");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=query_set.getString("user_id");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                String manager_id=query_set.getString("user_role");
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                              
                                /* String location_id=query_set.getString("LOCATION_ID");
                                table_cell=new PdfPCell(new Phrase(location_id));
                                my_report_table.addCell(table_cell);*/
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                conn.close();               
                
        }
}