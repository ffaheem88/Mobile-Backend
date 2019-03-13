/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;

import static PKClass.AirDB.dbadress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Faisal
 */
public class UpdateFanState extends HttpServlet {

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
         response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        PrintWriter out = response.getWriter();
        
        try {
          
            String AC_ID = request.getParameter("AC_ID");

            String FAN = request.getParameter("FAN");

            AirDBMobile airdb  = new AirDBMobile();
            
            airdb.UpdateFanAC(AC_ID, FAN);
            
             String vals[];
             try {
             vals = airdb.getState(AC_ID);
            String Speed = vals[0];
            String Thermo = vals[1];
            String operation = vals[2];
            String mode = vals[3];
            String swing = vals[4];
            String brand = vals[5];
            String raw =  airdb.getCode(Thermo, Speed, operation, brand, mode, swing);     
            airdb.UpdateIrCode(AC_ID, raw);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            
            JsonObject ac = new JsonObject();
                 ac.addProperty("ID", AC_ID);
               Gson gsonBuilder = new GsonBuilder().create();
           String acliststr = gsonBuilder.toJson(ac);
           out.println(acliststr);
            
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
