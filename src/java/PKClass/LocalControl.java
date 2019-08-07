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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ffahe
 */
public class LocalControl extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          String tracker_id = request.getParameter("id");
            String temp = request.getParameter("t");
            String signal = request.getParameter("w");
            AirDBMobile conn = new AirDBMobile();
            conn.setSignal(tracker_id, signal);
            //connector.setWifi(tracker_id, wifi);
            
            if(!temp.trim().equals("nan")){
            Double t = Double.parseDouble(temp);
            
              
              try {
                  conn.setTempNow(tracker_id, String.format("%.2f", t));
              } catch (SQLException ex) {
                  Logger.getLogger(LocalControl.class.getName()).log(Level.SEVERE, null, ex);
              }
              
            
            }else{
            
            }
            
           String[] state = conn.GetState(tracker_id);
           
           
           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
           Calendar now = Calendar.getInstance(); // in your case now will be the server time after getting from DB
           now.add(Calendar.HOUR, +5);  
           String time = sdf.format(now.getTime());
            try {
                conn.setUpdateTime(tracker_id, time);
            } catch (SQLException ex) {
                Logger.getLogger(LocalControl.class.getName()).log(Level.SEVERE, null, ex);
            }
      		Date d1 = null;
                Date d2 = null;
           
            JsonObject ac = new JsonObject();
             
                 ac.addProperty("ID", state[0]);
                 ac.addProperty("Label", state[1]);
                 ac.addProperty("Power", state[2]);
                 ac.addProperty("Thermostat", state[3]);
                 ac.addProperty("Fan_Speed", state[4]);
                 ac.addProperty("Mode", state[5]);
                 ac.addProperty("Swing", state[6]);
                 ac.addProperty("Temp", state[7]);
                 ac.addProperty("Humid", state[8]);
                 ac.addProperty("Signal", state[9]);
                 ac.addProperty("Remote", state[10]);
                 String lasttime = state[11];
           String status = "OFFLINE";
                 try {
                                 d1 = sdf.parse(lasttime);
                                 d2 = sdf.parse(time);
                             
		
                    long diff = ((d2.getTime() - d1.getTime())/1000);
		 status = "ONLINE";
	
                        if((diff)>35){
              
                   status = "OFFLINE";
              
                        }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                             }
               ac.addProperty("Status", status);
               ac.addProperty("Brand", state[12]);
               
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
