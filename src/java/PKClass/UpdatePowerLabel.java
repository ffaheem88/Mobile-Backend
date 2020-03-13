/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Super
 */
public class UpdatePowerLabel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            /* TODO output your page here. You may use following sample code. */
             AirDB airdb = new AirDB();
            
            
            String AC_LABEL = request.getParameter("LABEL");
//            String node_id = airdb.getNodeType(AC_LABEL);
            String thermoval = request.getParameter("POWER");
//            if (node_id.startsWith("A")) {

AC_LABEL = airdb.getIDFromLabel(AC_LABEL);

String prevval = airdb.getPowerStateAC(AC_LABEL);

                airdb.UpdatePowerAC(AC_LABEL, thermoval);
                
                 String vals[];
             try {
                  String raw="";
             vals = airdb.getState(AC_LABEL);
            String Speed = vals[0];
            String Thermo = vals[1];
            String operation = vals[2];
            String mode = vals[3];
            String swing = vals[4];
            String brand = vals[5];
            if(brand.equals("13") && operation.equals("ON") && prevval.equals("OFF")){
                  raw =  airdb.getCodePower14(Thermo, Speed, operation, brand, mode, swing);  
             }else{
                  raw =  airdb.getCode(Thermo, Speed, operation, brand, mode, swing);     
            }
            airdb.UpdateIrCode(AC_LABEL, raw);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
                
                JsonObject ac = new JsonObject();
                 ac.addProperty("ID", AC_LABEL);
               Gson gsonBuilder = new GsonBuilder().create();
           String acliststr = gsonBuilder.toJson(ac);
           
           out.println(acliststr);
            
           
            
                

        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
