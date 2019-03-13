/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;

import static PKClass.AirDB.dbadress;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Faisal
 */
public class GetFanState extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            AirDB airdb = new AirDB();
            
            
            String AC_LABEL = request.getParameter("AC_ID");
 String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
           connection = DriverManager.getConnection(dbadress, "ffaheem_move", "device@123");
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM AC_MAIN WHERE ID='"+AC_LABEL+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
            String fanspeed = data;
            out.println(fanspeed);
//            }else if(node_id.startsWith("S")){
//            String fanspeed = airdb.getFanStateSubGrp(node_id.substring(1));
//            out.println(fanspeed);
//            }else if(node_id.startsWith("G")){
//            String fanspeed = airdb.getFanStateGrp(node_id.substring(1));
//            out.println(fanspeed);
//            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
