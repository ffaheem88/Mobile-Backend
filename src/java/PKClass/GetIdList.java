/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
/**
 *
 * @author Faisal
 */
public class GetIdList extends HttpServlet {

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
           
            String userId = request.getParameter("userid");
            
            AirDBMobile conn = new AirDBMobile();
           ArrayList<String[]> idlist = conn.GetidList(userId);
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
           Calendar now = Calendar.getInstance(); // in your case now will be the server time after getting from DB
           now.add(Calendar.HOUR, +5);  
           String time = sdf.format(now.getTime());
          
      		Date d1 = null;
                Date d2 = null;
            Date d3 = null;
            JsonArray aclist = new JsonArray();
            for(int x = 0;x<idlist.size();x++){
                 JsonObject ac = new JsonObject();
                 String[] list = idlist.get(x);
                 ac.addProperty("ID", list[0]);
                 ac.addProperty("Label", list[1]);
                 ac.addProperty("Power", list[2]);
                String lasttime = list[3];
                String installtime = list[5];
                 try {
                                 d1 = sdf.parse(lasttime);
                                 d2 = sdf.parse(time);
                                 d3 = sdf.parse(installtime);
                             } catch (ParseException ex) {
                             }
		
                    long diff = ((d2.getTime() - d1.getTime())/1000);
		String status = "ONLINE";
	
                        if((diff)>35){
              
                   status = "OFFLINE";
              
                        }
                
               ac.addProperty("Status", status);
               try{
               ac.addProperty("Temp", list[4].substring(0, 2));
               }catch(Exception e){
                       e.printStackTrace();
                       }
               SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String updatetime = sdf2.format(d1);
                installtime = sdf2.format(d3);
              ac.addProperty("LastTime", updatetime);
              ac.addProperty("InstallTime", installtime);
                aclist.add(ac);
            }
            Gson gsonBuilder = new GsonBuilder().create();
            String acliststr = gsonBuilder.toJson(aclist);
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
