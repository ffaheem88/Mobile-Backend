/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ffahe
 */
public class GetSubGroupList extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            AirDB conn = new AirDB();

            
            String userId = request.getParameter("GROUP_ID");
//            String node_id = airdb.getNodeType(AC_LABEL);
//            
//             if (node_id.startsWith("A")) {
                 ArrayList<String[]> idlist = conn.GetSubGroupList(userId);
                 
                  JsonArray aclist = new JsonArray();
                  
            for(int x = 0;x<idlist.size();x++){
                 JsonObject ac = new JsonObject();
                 String[] list = idlist.get(x);
                 
                 
                 
                 ac.addProperty("ID", list[0]);
                 
                 
                  String getPowerOn = conn.getPowerStateACSubGroup(list[0]);
                 boolean allON = false;
                 int total = 0;
                 int poweron =0;
                 try{
                 total = Integer.parseInt(list[2]);
                 poweron = Integer.parseInt(getPowerOn);
                 }catch(Exception e){
                     total = 0;
                     poweron =0;
                 }
                 if(total - poweron == 0){
                     allON = true;
                 }
                 ac.addProperty("ALLON", allON);
                 ac.addProperty("LABEL", list[1]);
                 ac.addProperty("DEV", list[2]);
                 ac.addProperty("OFFLINEDEV", list[3]);
              
           
              
                aclist.add(ac);
            }
            Gson gsonBuilder = new GsonBuilder().create();
            String acliststr = gsonBuilder.toJson(aclist);
           out.println(acliststr);
                 
                 
                 
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
