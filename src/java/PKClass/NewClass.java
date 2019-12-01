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
import com.google.gson.JsonParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ffahe
 */


public class NewClass {
    static String dbadress = "jdbc:mysql://pkujala01-cluster.cluster-c66yg152cqdw.us-west-2.rds.amazonaws.com:3306/ffaheem_wiir";
    static String user="ffaheem";
    static String pass="fai12345";
    public static void main(String args[]){
        
        ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE PROFILE_ID=22");  
            while (resultSet.next()) { 
                String[] list = new String[5];
                list[0] = resultSet.getString("ID");
                list[1] = resultSet.getString("LABEL");
                list[2] = resultSet.getString("POWER");
                list[3] = resultSet.getString("LAST_UPDATE");
                list[4] = resultSet.getString("TEMP");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
           Calendar now = Calendar.getInstance(); // in your case now will be the server time after getting from DB
           now.add(Calendar.HOUR, +5);  
           String time = sdf.format(now.getTime());
          
      		Date d1 = null;
                Date d2 = null;
            ArrayList<String[]> idlist = al;
            JsonArray aclist = new JsonArray();
            for(int x = 0;x<idlist.size();x++){
                 JsonObject ac = new JsonObject();
                 String[] list = idlist.get(x);
                 ac.addProperty("ID", list[0]);
                 ac.addProperty("Label", list[1]);
                 ac.addProperty("Power", list[2]);
                 
              
                String lasttime = list[3];
           
                 try {
                                 d1 = sdf.parse(lasttime);
                                 d2 = sdf.parse(time);
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
               
              ac.addProperty("LastTime", updatetime);
                aclist.add(ac);
            }
            JsonObject items = new JsonObject();
            JsonArray aclistmain = new JsonArray();
            JsonObject totalac = new JsonObject();
            
            totalac.addProperty("name", "Total");
            totalac.addProperty("count", idlist.size());
            totalac.add("children", aclist);
            aclistmain.add(totalac);
            items.add("items", aclistmain);
            
            Gson gsonBuilder = new GsonBuilder().create();
            String acliststr = gsonBuilder.toJson(items);
           System.out.println(acliststr);
            
       
           
                       
            
            
    }
}
