

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExportServlet
 */
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Exporter exporter = new Exporter();
		ArrayList<Object[]> dataList = exporter.getTableData();
		if (dataList != null && dataList.size() > 0) {
			exporter.doExport(dataList);
			out.println("data inseted successfully");
		} else {
			out.println("There is no data available in the table to export");
		}
		
	}

}
