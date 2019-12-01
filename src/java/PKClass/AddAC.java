/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faisal
 */
public class AddAC extends HttpServlet {

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
              try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AddAC.class.getName()).log(Level.SEVERE, null, ex);
            }
            /* TODO output your page here. You may use following sample code. */
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
            Calendar now = Calendar.getInstance(); // in your case now will be the server time after getting from DB
            now.add(Calendar.HOUR, +5);  
            
            
            
            String dev_id = request.getParameter("i");
            String brand = request.getParameter("b");
            String userid = request.getParameter("g");
            String label = request.getParameter("l");
            String power = request.getParameter("p");
//            String camera = request.getParameter("c");
//            String motion = request.getParameter("m");
            String group_id = "0";
            String subgroup_id = "0";
            String time = sdf.format(now.getTime());
            String POWER = "ON";
            String THERMO ="24";
            String FAN_SPEED = "HIGH";
            String MODE ="COOL";
            String Swing = "AUTO";
            String Inherited = "0";
            String remote = "3";
            String ip="0";
            String temp = "0";
            String humid = "0";
            String lastupdate = sdf.format(now.getTime());
            AirDB airdb = new AirDB(); 
            
            
//            
//       
//            airdb.CreateUser(dev_id);
//           airdb.GrantPriv(dev_id);
            
            airdb.deleteAC(dev_id);
            airdb.deletePower(dev_id);
            airdb.clearSchedule(dev_id);
            airdb.NewDevice(dev_id, group_id, subgroup_id, label, brand, Inherited, THERMO, FAN_SPEED, POWER, Swing, MODE,remote,ip,time,userid,lastupdate,temp,humid);
            airdb.AddPower(dev_id,power);
          
//            if(motion.trim().equals("1")){
//            airdb.AddMotion(dev_id);
//            }
//            if(camera.trim().equals("1")){
//                airdb.AddCam(dev_id);
//            }

 JsonObject ac = new JsonObject();
             
                 ac.addProperty("ID", dev_id);
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
