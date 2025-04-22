package main.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.db.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = request.getParameter("table");
        System.out.println("Table: "+table);
        if (table == null || table.isEmpty()) {
            request.setAttribute("error", "Please select a table.");
            request.getRequestDispatcher("/views/search.jsp").forward(request, response);
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            StringBuilder query = new StringBuilder("SELECT * FROM " + table + " WHERE 1=1");
            List<String> params = new ArrayList<>();
            List<String> strParams = Arrays.asList("Name","Reason","Status","Appeal_Level","Conditions","Court_Name",
            		"Location",
            		"Jurisdiction_Level","Room_Number","Availability_Status","Role","Type","Description","Address",
            		"Case_Number","Case_Type","Status_Update","Notes","Outcome","Terms");

            // Dynamically build query based on provided parameters
            System.out.println(request.getParameterMap().keySet().toString());
            for (String param : request.getParameterMap().keySet()) {
                if (!param.equals("table") && request.getParameter(param) != null && !request.getParameter(param).isEmpty()) {
                	if(strParams.contains(param)) { //parameter is a string
                		query.append(" AND ").append(param).append(" LIKE ?");
                		params.add("%" + request.getParameter(param) + "%");
                	}
                	else {
                		query.append(" AND ").append(param).append(" = ?");
                		params.add(request.getParameter(param));
                	}
                }
            }

            PreparedStatement stmt = conn.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Get column names
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // Results
            List<Map<String, Object>> results = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (String col : columnNames) {
                    row.put(col, rs.getObject(col));
                }
                results.add(row);
            }

            // attributes forwarded to JSP
            request.setAttribute("results", results);
            request.setAttribute("columnNames", columnNames);
            request.getRequestDispatcher("/views/search.jsp").forward(request, response);

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing your request.");
            request.getRequestDispatcher("/views/search.jsp").forward(request, response);
        }
    }
}