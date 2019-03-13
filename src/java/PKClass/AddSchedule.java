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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ffahe
 */
public class AddSchedule extends HttpServlet {

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
         AirDB airdb = new AirDB();
            
            String userid = request.getParameter("uid");
            String label = request.getParameter("label");
            String power = request.getParameter("power");
            String temp = request.getParameter("thermo");
            String fansp = request.getParameter("fan");
            String mode = "COOL";
            String swing = "AUTO";
             String mon= request.getParameter("mon");
             String tue= request.getParameter("tue");
             String wed= request.getParameter("wed");
             String thu= request.getParameter("thu");
             String fri= request.getParameter("fri");
             String sat= request.getParameter("sat");
             String sun= request.getParameter("sun");
             
              String hour = request.getParameter("hour");
               String minute = request.getParameter("minute");
            
            
            
            
            airdb.setnewSchedule(userid,label,"1/1/2000","1/1/2100","0");
            
            
            
            String lastid = airdb.getLastScheduleID(userid);
            
            if(mon.equals("1")){
            airdb.setnewScheduleDetail(power,"2",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(tue.equals("1")){
            airdb.setnewScheduleDetail(power,"3",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(wed.equals("1")){
            airdb.setnewScheduleDetail(power,"4",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(thu.equals("1")){
            airdb.setnewScheduleDetail(power,"5",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(fri.equals("1")){
            airdb.setnewScheduleDetail(power,"6",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(sat.equals("1")){
            airdb.setnewScheduleDetail(power,"7",lastid,hour,minute,temp,fansp,mode,swing);
            }
            if(sun.equals("1")){
            airdb.setnewScheduleDetail(power,"1",lastid,hour,minute,temp,fansp,mode,swing);
            }
            
           JsonObject ac = new JsonObject();
                 ac.addProperty("ID", lastid);
               Gson gsonBuilder = new GsonBuilder().create();
           String acliststr = gsonBuilder.toJson(ac);
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
