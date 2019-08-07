/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PKClass;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;   
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import me.xdrop.fuzzywuzzy.FuzzySearch;
/**
 *
 * @author Faisal
 */
public class AirDB {
    static String dbadress = "jdbc:mysql://pkujala2-cluster.cluster-c66yg152cqdw.us-west-2.rds.amazonaws.com:3306/ffaheem_wiir";
    //static String dbadress = "jdbc:mysql://127.0.0.1:3306/ffaheem_wiir";
     private String user;
     private String pass;
    
// Retrieving Tree Data    
    
     
     AirDB(String user,String pass){
//         this.user=user;
//         this.pass=pass;
 this.user="ffaheem";
         this.pass="fai12345";
     }
     
     AirDB(){
         this.user="ffaheem";
         this.pass="fai12345";
     }
     
     
     
      public String getCurrent(String AC_LABEL){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT PIC FROM PIC WHERE AC_ID='"+AC_LABEL+"' ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("PIC"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
    public ArrayList CurrentHist(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM PIC WHERE AC_ID='"+userid+"' ORDER BY ID DESC LIMIT 20");  
            while (resultSet.next()) { 
                String[] list = new String[4];
                list[0] = resultSet.getString("PIC").split(";")[0];
                list[1] = resultSet.getString("PIC").split(";")[1];
                list[2] = resultSet.getString("PIC").split(";")[2];
                list[3] = resultSet.getString("TIME");
               
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}

 public String[] GetProfileList(){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT USER_NAME FROM PROFILE");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("USER_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
public String[] GetProfileIdList(){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT PROFILE_ID FROM PROFILE");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("PROFILE_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }    



public String getBrandFromIR(String ircode) throws SQLException{
  
    String vals= ""; 
    int ratio = 0;
    int found = 0;
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM IR_BIN");  
            while (resultSet.next()) {
               ratio = FuzzySearch.ratio(ircode,resultSet.getString("BIN"));
               if(ratio>found){
                   found = ratio;
                   if(found>40){
                   vals = resultSet.getString("BRAND") + "," + found + "," + resultSet.getString("POWER") +"," +resultSet.getString("THERMOSTAT")+"," +resultSet.getString("MODE")+"," +resultSet.getString("SWING");
                   }
               }
               
            }  
            statement.close();
            connection.close();
          
        } catch (Exception e) { 
             e.printStackTrace();  
             connection.close();
}
        
       
        return vals;
       
}
public String[] GetGroupIdList(String userid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_ID FROM GRP_DET WHERE PROFILE_ID='"+userid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }



public String[] GetSubGroupIdList(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+GroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
public String[] GetSubGroupACIdList(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+GroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }

public String[][] GetSubGroupAllACList(String SubGroupId){
     ArrayList al = new ArrayList(); 
     ArrayList a2 = new ArrayList();
     ArrayList a3 = new ArrayList();
     ArrayList a4 = new ArrayList();
     ArrayList a5 = new ArrayList();
     ArrayList a6 = new ArrayList(); 
          ArrayList a7 = new ArrayList(); 

    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                
                al.add(resultSet.getString("LABEL"));
                 a2.add(resultSet.getString("ID"));
                  a3.add(resultSet.getString("POWER"));
                   a4.add(resultSet.getString("THERMOSTAT"));
                    a5.add(resultSet.getString("TEMP"));
                     a6.add(resultSet.getString("SIGNAL"));
                      a7.add(resultSet.getString("LAST_UPDATE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data1[] = al.toArray();
         Object data2[] = a2.toArray();
         Object data3[] = a3.toArray();
         Object data4[] = a4.toArray();
         Object data5[] = a5.toArray();
         Object data6[] = a6.toArray();
         Object data7[] = a7.toArray();

        String[][] filterdata = new String[7][data1.length];
        for(int x = 0; x< data1.length;x++){
            filterdata[0][x] = data1[x].toString();
            filterdata[1][x] = data2[x].toString();
            filterdata[2][x] = data3[x].toString();
            filterdata[3][x] = data4[x].toString();
            filterdata[4][x] = data5[x].toString();
            filterdata[5][x] = data6[x].toString();
            filterdata[6][x] = data7[x].toString();
        }
        return filterdata;
    }
    public String[] GetSubGroupACList(String SubGroupId){
     ArrayList al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
    
    public String[] GetThermoList(String BrandId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DISTINCT THERMOSTAT FROM IR_STATE WHERE BRAND='"+BrandId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
    
     public String[] GetThermoListSubGroup(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE SUBGROUP_ID = '"+SubGroupId+"' AND INHERITED='1')  GROUP BY THERMOSTAT HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     public String[] GetThermoListGroup(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE (SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM GRP_DET WHERE GROUP_ID='"+GroupId+"') AND INHERITED='1') OR GROUP_ID='"+GroupId+"') AND INHERITED ='1') GROUP BY THERMOSTAT HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     
      public String[] GetSwingList(String BrandId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DISTINCT SWING FROM IR_STATE WHERE BRAND='"+BrandId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SWING"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
    
     public String[] GetSwingListSubGroup(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE SUBGROUP_ID = '"+SubGroupId+"' AND INHERITED='1')  GROUP BY SWING HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SWING"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     public String[] GetSwingListGroup(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE (SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM GRP_DET WHERE GROUP_ID='"+GroupId+"') AND INHERITED='1') OR GROUP_ID='"+GroupId+"') AND INHERITED ='1') GROUP BY SWING HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SWING"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
    
     public String[] GetFanList(String BrandId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DISTINCT FAN_SPEED FROM IR_STATE WHERE BRAND='"+BrandId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN_SPEED"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     
     public String[] GetFanListSubGroup(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE SUBGROUP_ID = '"+SubGroupId+"' AND INHERITED='1')  GROUP BY FAN_SPEED HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN_SPEED"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     public String[] GetFanListGroup(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE (SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM GRP_DET WHERE GROUP_ID='"+GroupId+"') AND INHERITED='1') OR GROUP_ID='"+GroupId+"') AND INHERITED ='1') GROUP BY FAN_SPEED HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN_SPEED"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     
     public String[] GetModeList(String BrandId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DISTINCT MODE FROM IR_STATE WHERE BRAND='"+BrandId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MODE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     
     public String[] GetModeListSubGroup(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE SUBGROUP_ID = '"+SubGroupId+"' AND INHERITED='1')  GROUP BY MODE HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MODE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     public String[] GetModeListGroup(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM IR_STATE WHERE BRAND IN(SELECT DISTINCT BRAND FROM AC_MAIN WHERE (SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM GRP_DET WHERE GROUP_ID='"+GroupId+"') AND INHERITED='1') OR GROUP_ID='"+GroupId+"') AND INHERITED ='1') GROUP BY MODE HAVING COUNT(*)>1");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MODE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
     
  
      public String getACId(String AC_LABEL){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM AC_MAIN WHERE LABEL='"+AC_LABEL+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
     
     public String getBrandId(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT BRAND FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("BRAND"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
 public String getInheritGroupIdAC(String AC_ID){
  
    String data="";
    String groupid="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_ID,SUBGROUP_ID FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SUBGROUP_ID"));
                groupid = (resultSet.getString("GROUP_ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        if(groupid.equals("0")){
            data = "S"+data;
        }else{
            data = "G"+groupid;
        }
        return data;
}
    public String getInheritedGroupIdSubGroup(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();
                    ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("GROUP_ID"));
            }
            statement.close();
            connection.close(); 
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
 
 public String getNodeType(String LABEL){
     String AC = "";
     String SubGrp = "";
     String Grp = "";
     String data ="";
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE LABEL='"+LABEL+"'");  
                        
            while (resultSet.next()) {
                data = "A" + resultSet.getString("ID");
                
            }  
            
            
            statement = connection.createStatement();  
           
                        resultSet = statement  
                    .executeQuery("SELECT * FROM GRP_DET WHERE GROUP_NAME='"+LABEL+"'");  
                        
            while (resultSet.next()) {
                data = "G" + resultSet.getString("GROUP_ID");
                
            }  
            
            
            
           statement = connection.createStatement();  
           
                         resultSet = statement  
                    .executeQuery("SELECT * FROM SUBGRP_DET WHERE SUBGROUP_NAME='"+LABEL+"'");  
                        
            while (resultSet.next()) {
                data = "S" + resultSet.getString("SUBGROUP_ID");
                
            }  
            
            statement.close();
            connection.close();
            
            
            
        } catch (Exception e) {  
            data=e.toString();  
        }
        
       
        return data;
 }
 
  public String getSubGroupLabel(String SubGroup_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SUBGROUP_NAME FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGroup_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SUBGROUP_NAME"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}    
   public String getGroupLabel(String Group_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM GRP_DET WHERE GROUP_ID='"+Group_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("GROUP_NAME"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
    public String getPowerStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
    public String getPowerStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     public String getPowerStateGrp(String Grp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM GRP_DET WHERE GROUP_ID='"+Grp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     
     
       public String getThermoStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
     
      public String getThermoStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     public String getThermoStateGrp(String Grp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM GRP_DET WHERE GROUP_ID='"+Grp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
 public String getFanStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
     
      public String getFanStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     public String getFanStateGrp(String Grp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM GRP_DET WHERE GROUP_ID='"+Grp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     
     public String getSwingStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
     
      public String getSwingStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     public String getSwingStateGrp(String Grp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM GRP_DET WHERE GROUP_ID='"+Grp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     
     public String getModeStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
     
      public String getModeStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
     public String getModeStateGrp(String Grp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM GRP_DET WHERE GROUP_ID='"+Grp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
     
     public String getInheritStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT INHERITED FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("INHERITED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   
   
     
      public String getInheritStateSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT INHERITED FROM SUBGRP_DET WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("INHERITED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
public void SetHistoryAC(String TrackerID, String Thermostat, String Speed, String Power, String Temp, String humid,String time, String Mode, String Swing){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `AC_DATA` (`ID`, `AC_ID`, `TIME`, `TEMP`, `HUMID`, `THERMOSTAT`, `FAN_SPEED`, `POWER` , `MODE`, `SWING`) VALUES (NULL, '"+TrackerID+"', '"+time+"', '"+Temp+"', '"+humid+"', '"+Thermostat+"', '"+Speed+"', '"+Power+"', '"+Mode+"', '"+Swing+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}

public void SetHistorySubGrp(String TrackerID, String Thermostat, String Speed, String Power, String Temp, String humid,String time, String Mode, String Swing){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `SUBGRP_DATA` (`ID`, `SUBGRP_ID`, `TIME`, `TEMP`, `HUMID`, `THERMOSTAT`, `FAN_SPEED`, `POWER` , `MODE`, `SWING`) VALUES (NULL, '"+TrackerID+"', '"+time+"', '"+Temp+"', '"+humid+"', '"+Thermostat+"', '"+Speed+"', '"+Power+"', '"+Mode+"', '"+Swing+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}
public void SetHistoryGrp(String TrackerID, String Thermostat, String Speed, String Power, String Temp, String humid,String time, String Mode, String Swing){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `GRP_DATA` (`ID`, `GRP_ID`, `TIME`, `TEMP`, `HUMID`, `THERMOSTAT`, `FAN_SPEED`, `POWER` , `MODE`, `SWING`) VALUES (NULL, '"+TrackerID+"', '"+time+"', '"+Temp+"', '"+humid+"', '"+Thermostat+"', '"+Speed+"', '"+Power+"', '"+Mode+"', '"+Swing+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}


public void SetHistoryMotion(String TrackerID, String Move, String Time){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `MOTION_HIST` (`ID`, `MOTION_ID`, `TIME`, `VAL`) VALUES (NULL, '"+TrackerID+"', '"+Time+"', '"+Move+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}


   public String getBrand(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT BRAND FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("BRAND"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
 public String getCode(String Thermostat,String Speed,String Power,String Brand,String Mode,String Swing){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT IR_CODE FROM IR_STATE WHERE THERMOSTAT='"+Thermostat+"' AND FAN_SPEED='"+Speed+"' AND POWER='"+Power+"' AND BRAND='"+Brand+"' AND MODE='"+Mode+"' AND SWING='"+Swing+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("IR_CODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
 
 
 public String getCodePower14(String Thermostat,String Speed,String Power,String Brand,String Mode,String Swing){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT IR_CODE FROM IR_BIN WHERE THERMOSTAT='"+Thermostat+"' AND FAN_SPEED='"+Speed+"' AND POWER='"+Power+"' AND BRAND='"+Brand+"' AND MODE='"+Mode+"' AND SWING='"+Swing+"' AND BUTTON='POWER'");  
            while (resultSet.next()) {
                data = (resultSet.getString("IR_CODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
   
 public String getTempSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(TEMP) FROM AC_DATA WHERE ID IN(SELECT MAX(ID) FROM AC_DATA WHERE AC_ID IN (SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1') GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString(1));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
 
 public String getTempGrp(String Group_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(TEMP) FROM AC_DATA WHERE ID IN(SELECT MAX(ID) FROM AC_DATA WHERE AC_ID IN (SELECT ID FROM AC_MAIN WHERE (GROUP_ID='"+Group_ID+"' OR SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Group_ID+"') )AND INHERITED='1') GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString(1));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
 public String getHumidSubGrp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(HUMID) FROM AC_DATA WHERE ID IN(SELECT MAX(ID) FROM AC_DATA WHERE AC_ID IN (SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1') GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString(1));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
 
 public String getHumidGrp(String Group_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(HUMID) FROM AC_DATA WHERE ID IN(SELECT MAX(ID) FROM AC_DATA WHERE AC_ID IN (SELECT ID FROM AC_MAIN WHERE (GROUP_ID='"+Group_ID+"' OR SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Group_ID+"') )AND INHERITED='1') GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString(1));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
 public void UpdateSUBGRP(String AC_ID, String Profile,String Subgrp){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SUBGROUP_ID='"+Subgrp+"',PROFILE_ID='"+Profile+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdateThermostatAC(String AC_ID, String Thermo){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET THERMOSTAT='"+Thermo+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateThermostatSubGrp(String SubGrp_ID, String Thermo){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET THERMOSTAT='"+Thermo+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET THERMOSTAT='"+Thermo+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateThermostatGrp(String Grp_ID, String Thermo){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET THERMOSTAT='"+Thermo+"' WHERE GROUP_ID='"+Grp_ID+"'"); 
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET THERMOSTAT='"+Thermo+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET THERMOSTAT='"+Thermo+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET THERMOSTAT='"+Thermo+"' WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1') AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdatePowerAC(String AC_ID, String POWER){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER='"+POWER+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateLabelAC(String AC_ID, String LABEL){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET LABEL='"+LABEL+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdateRemoteAC(String AC_ID, String Remote){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET REMOTE='"+Remote+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdateGroupLabel(String ID, String LABEL){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET GROUP_NAME='"+LABEL+"' WHERE GROUP_ID='"+ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void AssignDevice(String DEV_ID, String SUBGROUP_ID){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SUBGROUP_ID='"+SUBGROUP_ID+"' WHERE ID='"+DEV_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 
 public void UpdateSubGroupLabel(String ID, String LABEL){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET SUBGROUP_NAME='"+LABEL+"' WHERE SUBGROUP_ID='"+ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdatePowerSubGrp(String SubGrp_ID, String POWER){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET POWER='"+POWER+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER='"+POWER+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdatePowerGrp(String Grp_ID, String POWER){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET POWER='"+POWER+"' WHERE GROUP_ID='"+Grp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER='"+POWER+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET POWER='"+POWER+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER='"+POWER+"' WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1') AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateFanAC(String AC_ID, String FAN){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET FAN_SPEED='"+FAN+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateFanSubGrp(String SubGrp_ID, String FAN){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET FAN_SPEED='"+FAN+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET FAN_SPEED='"+FAN+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateFanGrp(String Grp_ID, String FAN){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET FAN_SPEED='"+FAN+"' WHERE GROUP_ID='"+Grp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET FAN_SPEED='"+FAN+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET FAN_SPEED='"+FAN+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET FAN_SPEED='"+FAN+"' WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1') AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdateSwingAC(String AC_ID, String SWING){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SWING='"+SWING+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateSwingSubGrp(String SubGrp_ID, String SWING){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET SWING='"+SWING+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SWING='"+SWING+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateSwingGrp(String Grp_ID, String SWING){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET SWING='"+SWING+"' WHERE GROUP_ID='"+Grp_ID+"'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SWING='"+SWING+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET SWING='"+SWING+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET SWING='"+SWING+"' WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1') AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void UpdateModeAC(String AC_ID, String MODE){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET MODE='"+MODE+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateModeSubGrp(String SubGrp_ID, String MODE){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET MODE='"+MODE+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'"); 
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET MODE='"+MODE+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"' AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateModeGrp(String Grp_ID, String MODE){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE GRP_DET SET MODE='"+MODE+"' WHERE GROUP_ID='"+Grp_ID+"'"); 
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET MODE='"+MODE+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET MODE='"+MODE+"' WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1'");
            statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET MODE='"+MODE+"' WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID='"+Grp_ID+"' AND INHERITED='1') AND INHERITED='1'");
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateInheritedAC(String AC_ID, String Inherited){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET Inherited='"+Inherited+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateInheritedSubGrp(String SubGrp_ID, String Inherited){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE SUBGRP_DET SET INHERITED='"+Inherited+"' WHERE SUBGROUP_ID='"+SubGrp_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
 }
    

 
 public void NewDevice(String AC_ID,String Group_Id,String SubGroup_Id,String Label,String Brand,String Inherited, String Thermostat, String Speed, String Power,String Swing,String Mode, String time){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `AC_MAIN` (`ID`, `GROUP_ID`, `SUBGROUP_ID`, `LABEL`, `BRAND`, `INHERITED`, `THERMOSTAT`, `POWER`, `FAN_SPEED`, `MODE`, `SWING`,`INSTALL_DATE`) VALUES ('"+AC_ID+"', '"+Group_Id+"', '"+SubGroup_Id+"', '"+Label+"', '"+Brand+"', '"+Inherited+"', '"+Thermostat+"', '"+Power+"', '"+Speed+"', '"+Mode+"', '"+Swing+"', '"+time+"')");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
    }

 }
 
  public String getNewId(){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM AC_MAIN ORDER BY ID ASC");  
            while (resultSet.next()) {
                data = (resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        int i = Integer.parseInt(data) +1;
        return String.valueOf(i);
}

  
  
  
  public String[] GetTimeGraph(String TrackerID,String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,TIME FROM AC_DATA WHERE AC_ID='"+TrackerID+"' ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) { 
           String time  = resultSet.getString("TIME");
           
           //2015-08-23_09:24:30
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
  
  public String[] GetTempGraph(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,TEMP FROM AC_DATA WHERE AC_ID= '"+TrackerID+"' AND TEMP>0 ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("TEMP"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
 
  public String[] GetHumidGraph(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,HUMID FROM AC_DATA WHERE AC_ID= '"+TrackerID+"' AND HUMID>0 ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("HUMID"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
  
  public String[] GetThermoGraph(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,THERMOSTAT FROM AC_DATA WHERE AC_ID= '"+TrackerID+"' ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
      public String getLastUpdateTime(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LAST_UPDATE FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("LAST_UPDATE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
      
      public String getBrandName(String B_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT BRAND_NAME FROM BRAND_DET WHERE BRAND_ID='"+B_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("BRAND_NAME"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
           public String getBrandDetails(String B_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COMMENTS FROM BRAND_DET WHERE BRAND_ID='"+B_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("COMMENTS"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  public String getInstallDate(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT INSTALL_DATE FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("INSTALL_DATE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
   public String getTotalInheritedSubGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"' AND INHERITED='1'");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
   public String getTotalOnlineSubGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"' AND ID IN(SELECT AC_ID FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<2  GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
      public String getTotalSubGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
      public String getTotalInheritedGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                     ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE GROUP_ID='"+SUBGRP_ID+"' AND INHERITED='1'");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
   public String getTotalOnlineGrp(String GRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE GROUP_ID='"+GRP_ID+"' AND ID IN(SELECT AC_ID FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)-60)<2 AND (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)-60)>0 GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
   
   public String getOnlineInheritedSubGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"' AND INHERITED='1' AND ID IN(SELECT AC_ID FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<2 GROUP BY AC_ID)");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  public String getTotalGrp(String SUBGRP_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
 
public String[] GetTempSubGroupGraph(String SUBGRP_ID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(TEMP) FROM AC_DATA WHERE AC_ID IN(SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("AVG(TEMP)"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}     
public String[] GetHumidSubGroupGraph(String SUBGRP_ID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(HUMID) FROM AC_DATA WHERE AC_ID IN(SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("AVG(HUMID)"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}      
public String[] GetThermoSubGroupGraph(String SUBGRP_ID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(THERMOSTAT) FROM AC_DATA WHERE AC_ID IN(SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) {  
               al.add("na"); //al.add(resultSet.getString("AVG(THERMOSTAT)"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}      

public String[] GetTimeSubGroupGraph(String SUBGRP_ID,String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TIME,AVG(TEMP) FROM AC_DATA WHERE AC_ID IN(SELECT ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SUBGRP_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT "+limit);  
                        
              while (resultSet.next()) { 
           String time  = resultSet.getString("TIME");
           
           //2015-08-23_09:24:30
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}

public String getACTemp(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TEMP FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("TEMP"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
public String getACHumid(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT HUMID FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("HUMID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
public String getSubGrpTemp(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(TEMP) FROM AC_DATA WHERE AC_ID IN(SELECT AC_ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGrp_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("AVG(TEMP)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
public String getSubGrpHumid(String SubGrp_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AVG(HUMID) FROM AC_DATA WHERE AC_ID IN(SELECT AC_ID FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGrp_ID+"') GROUP BY TIME ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("AVG(HUMID)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}

public String getRemoteVal(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT IRCODE FROM IR_DATA WHERE AC_ID='"+AC_ID+"' ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("IRCODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}   

public String getRemoteState(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT REMOTE FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("REMOTE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

public String getOverrideState(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT INHERITED FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("INHERITED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

public void UpdateRemoteState(String AC_ID, String Remote){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET REMOTE='"+Remote+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}

public void UpdateOverrideState(String AC_ID, String Remote){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET INHERITED='"+Remote+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}

public String getRemoteId(String ir,String brand){
  
    String data="";
    String result = "0";
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,IR_CODE FROM IR_STATE WHERE BRAND='"+brand+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("IR_CODE"));
                String match ="";
                String[] nums  = data.split(",");
                for(int x = 0;x<(nums.length);x++){
                if(Integer.parseInt(nums[x].trim())>900){
                    match=match+"1";
                }else{
                    match=match+"0";
                }
              }
                
              if(ir.substring(0,match.length()).trim().equalsIgnoreCase(match.trim()) || match.trim().equalsIgnoreCase(ir.substring(0,match.length()).trim())){
              
                  result =  (resultSet.getString("ID"));
                  
              }
             
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            result=e.toString();  
}
        return result;
} 

public String getPowerStatefromId(String IR_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM IR_STATE WHERE ID='"+IR_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
public String getFanStatefromId(String IR_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM IR_STATE WHERE ID='"+IR_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
public String getThermoStatefromId(String IR_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM IR_STATE WHERE ID='"+IR_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

public String getModeStatefromId(String IR_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM IR_STATE WHERE ID='"+IR_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
public String getSwingStatefromId(String IR_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM IR_STATE WHERE ID='"+IR_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
public String[] GetTimeGraphMinute(String TrackerID,String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,TIME FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID);  
                        
              while (resultSet.next()) { 
           String time  = resultSet.getString("TIME");
           
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
  
  public String[] GetTempGraphMinute(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID,TEMP FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("TEMP"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
 
  public String[] GetHumidGraphMinute(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                   .executeQuery("SELECT ID,HUMID FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("HUMID"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
  
  public String[] GetThermoGraphMinute(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                   .executeQuery("SELECT ID,THERMOSTAT FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID);  
                        
              while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
                
            }  
            Collections.reverse(al);
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
}
  public String getSignalLevel(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `SIGNAL` FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SIGNAL"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  public String GetGraphMinute(String TrackerID, String limit){
     ArrayList al = new ArrayList(); 
      ArrayList a2 = new ArrayList(); 
       ArrayList a3 = new ArrayList(); 
        ArrayList a4 = new ArrayList(); 
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT ID,TIME,TEMP,HUMID,THERMOSTAT FROM AC_DATA WHERE AC_ID="+TrackerID+" AND (ID%"+limit+"=0) AND TEMP<>'nan' AND HUMID<>'nan' ORDER BY `AC_DATA`.`ID` DESC LIMIT "+limit);
                   // .executeQuery("SELECT ID,TIME,TEMP,HUMID,THERMOSTAT FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID+" AND (ID%4=0)");  
                        
              while (resultSet.next()) {  
                  
                  String time  = resultSet.getString("TIME");
           
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                a2.add(resultSet.getString("TEMP"));
                a3.add(resultSet.getString("HUMID"));
                a4.add(resultSet.getString("THERMOSTAT"));
                
            }  
            Collections.reverse(al);
            Collections.reverse(a2);
            Collections.reverse(a3);
            Collections.reverse(a4);
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object temp[] = a2.toArray();
        Object thermo[] = a4.toArray();
        Object humid[] = a3.toArray();
        Object time[] = al.toArray();
        
        String line ="";
         for (int x = 0;x < temp.length;x++){
     
                  line = line + (temp[x]+";");
              
              }
              line = line + ("|");
              for (int x = 0;x < thermo.length;x++){
     
                  line = line + (thermo[x]+";");
              
              }
             line = line + ("|");
              for (int x = 0;x < humid.length;x++){
     
                  line = line + (humid[x]+";");
              
              }
              line = line + ("|");
               for (int x = 0;x < time.length;x++){
                   
                  line = line + (time[x]+";");
             
              }
        
        
        
        return line;
}
  
  public String getIP(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `IP` FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("IP"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

  public String[] getStateIdListTimer(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT STATE_ID FROM TIMER WHERE AC_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("STATE_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getDaysListTimer(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DAY FROM TIMER WHERE AC_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("DAY"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getHoursListTimer(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT HOUR FROM TIMER WHERE AC_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("HOUR"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getMinutesListTimer(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MINUTE FROM TIMER WHERE AC_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MINUTE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getTimerIDList(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM TIMER WHERE AC_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   public String getPowerStateTimer(String STATE_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `POWER` FROM IR_STATE WHERE ID='"+STATE_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getTempStateTimer(String STATE_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `THERMOSTAT` FROM IR_STATE WHERE ID='"+STATE_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
  public String getFanStateTimer(String STATE_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `FAN_SPEED` FROM IR_STATE WHERE ID='"+STATE_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  public String getModeStateTimer(String STATE_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `MODE` FROM IR_STATE WHERE ID='"+STATE_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  
  public String getSwingStateTimer(String STATE_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `SWING` FROM IR_STATE WHERE ID='"+STATE_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getStateID(String AC_ID,String power,String temp,String fansp,String mode,String swing){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `ID` FROM IR_STATE WHERE BRAND=(SELECT BRAND FROM AC_MAIN WHERE ID='"+ AC_ID +"') AND POWER='"+ power +"' AND FAN_SPEED='"+ fansp +"' AND THERMOSTAT='"+ temp +"' AND MODE='"+ mode +"' AND SWING='"+ swing +"'");   
            while (resultSet.next()) {
                data = (resultSet.getString("ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  public void NewTimer(String TrackerID, String Stateid, String Day, String hour, String minute){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `TIMER` (`ID`, `AC_ID`, `STATE_ID`, `DAY`, `HOUR`, `MINUTE`) VALUES (NULL, '"+TrackerID+"', '"+Stateid+"', '"+Day+"', '"+hour+"', '"+minute+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}
  
  
  public void deleteTimer(String timerid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM TIMER where ID='"+timerid+"'");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
  
  
   public String[] getStateIdListTimerToday(String AC_ID,String day){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT STATE_ID FROM TIMER WHERE AC_ID='"+AC_ID+"' AND DAY="+day+" ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("STATE_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getDaysListTimerToday(String AC_ID,String day){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DAY FROM TIMER WHERE AC_ID='"+AC_ID+"' AND DAY="+day+" ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("DAY"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getHoursListTimerToday(String AC_ID, String day){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT HOUR FROM TIMER WHERE AC_ID='"+AC_ID+"' AND DAY="+day+" ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("HOUR"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getMinutesListTimerToday(String AC_ID, String day){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MINUTE FROM TIMER WHERE AC_ID='"+AC_ID+"' AND DAY="+day+" ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MINUTE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getTimerIDListToday(String AC_ID, String day){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM TIMER WHERE AC_ID='"+AC_ID+"' AND DAY="+day+" ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   public String[] getTimeListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TIME FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("TIME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   public String[] getPowerListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("POWER"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
  public String[] getThermoListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getFanListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN_SPEED"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getModeListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MODE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getSwingListRemote(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM IR_DATA WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SWING"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  
  public String[] GetMoveid(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MOVE_ID FROM MOVE_AC WHERE AC_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MOVE_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] GetMoveActive(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ACTIVATE FROM MOVE_AC WHERE AC_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ACTIVATE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] GetMoveState(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT REMOTE_ID FROM MOVE_AC WHERE AC_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("REMOTE_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] GetMoveLabel(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM MOVE_MAIN WHERE ESP_ID IN(SELECT MOVE_ID FROM MOVE_AC WHERE AC_ID='"+acid+"')");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  
  public String[] GetMoveDelay(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DELAY_MINS FROM MOVE_AC WHERE AC_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("DELAY_MINS"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  
  public String getMovePowerStateAC(String State_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `POWER` FROM IR_STATE WHERE ID="+ State_ID );   
            while (resultSet.next()) {
                data = (resultSet.getString("POWER"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getMoveThermoStateAC(String State_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `THERMOSTAT` FROM IR_STATE WHERE ID="+ State_ID );   
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  public String getMoveFanStateAC(String State_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `FAN_SPEED` FROM IR_STATE WHERE ID="+ State_ID );   
            while (resultSet.next()) {
                data = (resultSet.getString("FAN_SPEED"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getMoveModeStateAC(String State_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `MODE` FROM IR_STATE WHERE ID="+ State_ID );   
            while (resultSet.next()) {
                data = (resultSet.getString("MODE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getMoveSwingStateAC(String State_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `SWING` FROM IR_STATE WHERE ID="+ State_ID );   
            while (resultSet.next()) {
                data = (resultSet.getString("SWING"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  public void UpdateMoveActive(String moveid, String active){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOVE_AC SET ACTIVATE='"+active+"' WHERE MOVE_ID='"+moveid+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
  public void UpdateMovedelay(String moveid, String delay){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOVE_AC SET DELAY_MINS='"+delay+"' WHERE MOVE_ID='"+moveid+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
   public void UpdateMoveRemote(String moveid, String remote){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOVE_AC SET REMOTE_ID='"+remote+"' WHERE MOVE_ID='"+moveid+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
  
  public String getMoveActiveState(String Move_id){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT `ACTIVATE` FROM MOVE_AC WHERE MOVE_ID="+ Move_id );   
            while (resultSet.next()) {
                data = (resultSet.getString("ACTIVATE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  
  public String getPowerStateACGroup(String group_id){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID IN (SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE GROUP_ID="+ group_id+") AND POWER='ON'" );   
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  
  
  public String getPowerStateACSubGroup(String subgroup_id){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT COUNT(*) FROM AC_MAIN WHERE SUBGROUP_ID IN ("+subgroup_id+") AND POWER='ON'" );   
            while (resultSet.next()) {
                data = (resultSet.getString("COUNT(*)"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  
  public String[] getSchdIDList(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM SCHEDULE_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  
   public ArrayList GetSchdList(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM ffaheem_wiir.SCHEDULE_MAIN LEFT JOIN (SELECT SCHEDULE_DET.S_ID,COUNT(*)  AS DEV  FROM SCHEDULE_DET GROUP BY SCHEDULE_DET.S_ID) AS S_GROUP ON SCHEDULE_MAIN.ID = S_GROUP.S_ID WHERE SCHEDULE_MAIN.USER_ID='"+userid+"'");  
            while (resultSet.next()) { 
                String[] list = new String[3];
                list[0] = resultSet.getString("ID");
                list[1] = resultSet.getString("LABEL");
                list[2] = resultSet.getString("DEV");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}
   public ArrayList GetGroupList(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM ffaheem_wiir.GRP_DET LEFT JOIN (SELECT SUBGRP_DET.GROUP_ID,SUBGRP_DET.SUBGROUP_ID,COUNT(ACM.ID) AS DEV FROM SUBGRP_DET LEFT JOIN (SELECT * FROM AC_MAIN) AS ACM ON SUBGRP_DET.SUBGROUP_ID=ACM.SUBGROUP_ID GROUP BY SUBGRP_DET.GROUP_ID) AS SUBGRP ON GRP_DET.GROUP_ID=SUBGRP.GROUP_ID LEFT JOIN (SELECT SUBGRP_DET.GROUP_ID,SUBGRP_DET.SUBGROUP_ID,COUNT(ACM.ID) AS OFFLINEDEV FROM SUBGRP_DET LEFT JOIN (SELECT * FROM AC_MAIN WHERE str_to_date(LAST_UPDATE, '%Y-%m-%d_%H:%i:%s')<  date_add(NOW(), INTERVAL '4:58' hour_minute)) AS ACM ON SUBGRP_DET.SUBGROUP_ID=ACM.SUBGROUP_ID GROUP BY SUBGRP_DET.GROUP_ID) AS SUBGRPOFF ON GRP_DET.GROUP_ID=SUBGRPOFF.GROUP_ID WHERE GRP_DET.PROFILE_ID='"+userid+"' GROUP BY GRP_DET.GROUP_ID");  
            while (resultSet.next()) { 
                String[] list = new String[4];
                list[0] = resultSet.getString("GROUP_ID");
                list[1] = resultSet.getString("GROUP_NAME");
                list[2] = resultSet.getString("DEV");
                list[3] = resultSet.getString("OFFLINEDEV");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}
   
    public ArrayList GetSubGroupList(String groupid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM ffaheem_wiir.SUBGRP_DET LEFT JOIN (SELECT AC_MAIN.SUBGROUP_ID,COUNT(*) AS DEV FROM AC_MAIN GROUP BY AC_MAIN.SUBGROUP_ID) AS ACMAIN ON SUBGRP_DET.SUBGROUP_ID=ACMAIN.SUBGROUP_ID LEFT JOIN (SELECT AC_MAIN.SUBGROUP_ID,COUNT(*) AS OFFLINEDEV FROM AC_MAIN WHERE str_to_date(LAST_UPDATE, '%Y-%m-%d_%H:%i:%s')<  date_add(NOW(), INTERVAL '4:58' hour_minute) GROUP BY AC_MAIN.SUBGROUP_ID) AS ACMAINOFF ON SUBGRP_DET.SUBGROUP_ID=ACMAINOFF.SUBGROUP_ID WHERE GROUP_ID='"+groupid+"'");  
            while (resultSet.next()) { 
                String[] list = new String[4];
                list[0] = resultSet.getString("SUBGROUP_ID");
                list[1] = resultSet.getString("SUBGROUP_NAME");
                list[2] = resultSet.getString("DEV");
                list[3] = resultSet.getString("OFFLINEDEV");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}
   
   
     public  ArrayList getScheduledACList(String AC_ID){
    ArrayList<String[]> al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE ID IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID)");  
            while (resultSet.next()) {  
               String[] list = new String[2];
                list[0] = resultSet.getString("ID");
                list[1] = resultSet.getString("LABEL");
                al.add(list);
                
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
       
        return al;
    }
     
     
     public  ArrayList getUnScheduledACList(String SID,String UID){
    ArrayList<String[]> al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE ID NOT IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+SID+"' ORDER BY AC_ID) AND PROFILE_ID="+UID);  
            while (resultSet.next()) {  
               String[] list = new String[2];
                list[0] = resultSet.getString("ID");
                list[1] = resultSet.getString("LABEL");
                al.add(list);
                
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
       
        return al;
    }
  
  public String[] getSchdLabel(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM SCHEDULE_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
   public String[] getSchdStart(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT START_DATE FROM SCHEDULE_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("START_DATE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   public String[] getSchdExpiry(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT EXPIRY FROM SCHEDULE_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("EXPIRY"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
    public String[] getSchdAutoOn(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AUTO_ON FROM SCHEDULE_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("AUTO_ON"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   
public void setnewSchedule(String USER_ID, String LABEL,String START, String EXPIRE, String AUTO){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `SCHEDULE_MAIN`(`ID`, `LABEL`, `START_DATE`, `EXPIRY`, `USER_ID`, `AUTO_ON`) VALUES(NULL,'"+LABEL+"','"+START+"','"+EXPIRE+"','"+USER_ID+"','"+AUTO+"')");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
}


public void setnewGroup(String USER_ID, String LABEL){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `GRP_DET`(`GROUP_ID`, `GROUP_NAME`, `PROFILE_ID`) VALUES(NULL,'"+LABEL+"','"+USER_ID+"')");  
            statement.close();
            connection.close();
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
}
public void setnewSubGroup(String USER_ID, String LABEL){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `SUBGRP_DET`(`SUBGROUP_ID`, `SUBGROUP_NAME`, `GROUP_ID`) VALUES(NULL,'"+LABEL+"','"+USER_ID+"')");  
            statement.close();
            connection.close();
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
}
public void setnewScheduleDetail(String power,String day,String lastid,String hour,String minute,String thermo,String fan,String mode,String swing){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `SCHEDULE_DATA`(`ID`, `S_ID`, `DAY`, `HOUR`, `MINUTE`, `POWER`, `THERMOSTAT`, `FAN_SPEED`, `MODE`, `SWING`) VALUES(NULL,'"+lastid+"','"+day+"','"+hour+"','"+minute+"','"+power+"','"+thermo+"','"+fan+"','"+mode+"','"+swing+"')");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
}

 public String getLastScheduleID(String userid){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT ID\n" +
"FROM SCHEDULE_MAIN\n" +
"WHERE USER_ID =  '"+ userid +"'\n" +
"ORDER BY ID DESC \n" +
"LIMIT 1");   
            while (resultSet.next()) {
                data = (resultSet.getString("ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

 public void deleteSchedule(String timerid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SCHEDULE_DATA where ID='"+timerid+"'");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
 public String[] getScheduleIDList(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
 public String[] getPowerSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("POWER"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getThermoSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getFanSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN_SPEED FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN_SPEED"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getModeSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MODE FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MODE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getSwingSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SWING FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SWING"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getDaysListSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT DAY FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("DAY"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getHoursListSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT HOUR FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("HOUR"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getMinutesListSchedule(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MINUTE FROM SCHEDULE_DATA WHERE S_ID='"+AC_ID+"' ORDER BY DAY ASC,HOUR ASC,MINUTE ASC");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("MINUTE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String getSchdAssigned(String userid){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT COUNT(*) AS NMB FROM SCHEDULE_DET WHERE S_ID='"+userid+"'");   
            while (resultSet.next()) {
                data = (resultSet.getString("NMB"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  
  
  
  public String[] getScheduledACLabelList(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE ID IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getScheduledACSubGroupList(String AC_ID, String profile){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("(SELECT SUBGROUP_NAME FROM AC_MAIN JOIN SUBGRP_DET\n" +
"ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"') AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY AC_MAIN.ID)");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getScheduledACGroupList(String AC_ID,String profile){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM (SELECT SUBGRP_DET.GROUP_ID AS NC FROM AC_MAIN  JOIN SUBGRP_DET ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"') AND PROFILE_ID="+profile+" ORDER BY ID) AS ALI LEFT JOIN GRP_DET ON ALI.NC=GRP_DET.GROUP_ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getUnScheduledACIDList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM AC_MAIN WHERE ID NOT IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND PROFILE_ID="+profile+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getUnScheduledACLabelList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE ID NOT IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND PROFILE_ID="+profile+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getUnScheduledACSubGroupList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("(SELECT SUBGROUP_NAME FROM AC_MAIN JOIN SUBGRP_DET\n" +
"ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID NOT IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY AC_MAIN.ID)");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getUnScheduledACGroupList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM (SELECT SUBGRP_DET.GROUP_ID AS NC FROM AC_MAIN JOIN SUBGRP_DET ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID NOT IN(SELECT AC_ID FROM SCHEDULE_DET WHERE S_ID='"+AC_ID+"') AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY ID) AS ALI LEFT JOIN GRP_DET ON ALI.NC=GRP_DET.GROUP_ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
 public void setnewRelACSchedule(String SID,String ACID){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `SCHEDULE_DET`(`ID`, `S_ID`, `AC_ID`) VALUES(NULL,'"+SID+"','"+ACID+"')");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
} 
 
 
 public void deleteScheduleAC(String sid,String acid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SCHEDULE_DET where S_ID='"+sid+"' AND AC_ID='"+acid+"' ");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
 
 public void deleteGroup(String sid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM GRP_DET where GROUP_ID='"+sid+"'");  
            
            
            statement.close();
            connection.close();
            
            
            
            
             
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
 public void deleteSubGroup(String sid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
           
            
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SUBGRP_DET where SUBGROUP_ID='"+sid+"'"); 
            
             
            statement.close();
            connection.close();
            
             
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
  public void deleteScheduleProfile(String sid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SCHEDULE_MAIN where ID='"+sid+"'");  
            
            
            statement.close();
            connection.close();
            
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SCHEDULE_DET where S_ID='"+sid+"'"); 
            
             
            statement.close();
            connection.close();
            
              connection = DriverManager.getConnection(dbadress, user, pass);
            statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM SCHEDULE_DATA where S_ID='"+sid+"'"); 
            
             
            statement.close();
            connection.close();
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
  
   public String[] GetSubGroupACPowerList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("POWER"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
    public String[] GetSubGroupACThermoList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] GetSubGroupACFanList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TEMP FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("TEMP"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
  
  
  public String[] GetSubGroupACSignalList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `SIGNAL` FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SIGNAL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
  
  
  public String[] GetSubGroupACTempList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TEMP FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("TEMP"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
    public String[] GetSubGroupACHumidList(String SubGroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT HUMID FROM AC_MAIN WHERE SUBGROUP_ID='"+SubGroupId+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("HUMID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 
    public String getWifiName(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT `WIFI` FROM AC_MAIN WHERE ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("WIFI"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 

 public String GetGraphAC(String ID){
     ArrayList al = new ArrayList(); 
      ArrayList a2 = new ArrayList(); 
       ArrayList a3 = new ArrayList(); 
        
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT (STR_TO_DATE(TIME,'%Y-%m-%d_%H')  + INTERVAL 1 \n" +
"SECOND) AS DAY,ROUND(AVG(TEMP),0) AS TEMPAVG FROM `AC_DATA`  WHERE AC_ID='"+ID+"' GROUP BY DAY ORDER BY ID DESC LIMIT 40");
                   // .executeQuery("SELECT ID,TIME,TEMP,HUMID,THERMOSTAT FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID+" AND (ID%4=0)");  
                        
              while (resultSet.next()) {  
                  
                  String time  = resultSet.getString("DAY");
           
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                a2.add(resultSet.getString("TEMPAVG"));
                
               
                
            }  
            Collections.reverse(al);
            Collections.reverse(a2);
            
    
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object temp[] = a2.toArray();
       
        
        Object time[] = al.toArray();
        
        String line ="";
         for (int x = 0;x < temp.length;x++){
     
                  line = line + (temp[x]+";");
              
              }
              
              
              line = line + ("|");
               for (int x = 0;x < time.length;x++){
                   
                  line = line + (time[x]+";");
              
              }
        
        
        
        return line;
}   


public String GetGraphACDates(String ID,String begintime,String endtime){
     ArrayList al = new ArrayList(); 
      ArrayList a2 = new ArrayList(); 
       ArrayList a3 = new ArrayList(); 
        
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT (STR_TO_DATE(TIME,'%Y-%m-%d_%H')  + INTERVAL 1 SECOND) AS DAY,ROUND(AVG(TEMP),0) AS TEMPAVG FROM `AC_DATA`  WHERE AC_ID='"+ID+"' AND STR_TO_DATE(TIME,'%Y-%m-%d')>=STR_TO_DATE('"+begintime+"','%m/%d/%Y') AND STR_TO_DATE(TIME,'%Y-%m-%d')<=STR_TO_DATE('"+endtime+"','%m/%d/%Y') AND TEMP<>'nan' GROUP BY DAY ORDER BY ID DESC");
                   // .executeQuery("SELECT ID,TIME,TEMP,HUMID,THERMOSTAT FROM AC_DATA WHERE (TIMESTAMPDIFF(MINUTE,TIME,CURRENT_TIMESTAMP)+659)<"+limit+" AND AC_ID="+TrackerID+" AND (ID%4=0)");  
                        
              while (resultSet.next()) {  
                  
                  String time  = resultSet.getString("DAY");
           
           String year = time.substring(0, 4);
           String month = time.substring(5, 7);
           int monthnew = Integer.parseInt(month);
           
           String day = time.substring(8,10);
           String hour =  time.substring(11,13);
           String minute = time.substring(14,16);
           String second = time.substring(17,19);
                al.add(year + " ," + (monthnew-1) + " ," + day + " ," + hour + " ," + minute + " ," + second );
                a2.add(resultSet.getString("TEMPAVG"));
                
               
                
            }  
            Collections.reverse(al);
            Collections.reverse(a2);
            
    
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object temp[] = a2.toArray();
       
        
        Object time[] = al.toArray();
        
        String line ="";
         for (int x = 0;x < temp.length;x++){
     
                  line = line + (temp[x]+";");
              
              }
              
              
              line = line + ("|");
               for (int x = 0;x < time.length;x++){
                   
                  line = line + (time[x]+";");
              
              }
        
        
        
        return line;
}   

 
 public String GetPanoAC(String ID){
     ArrayList al = new ArrayList(); 
      ArrayList a2 = new ArrayList(); 
       ArrayList a3 = new ArrayList(); 
       ArrayList a4 = new ArrayList(); 
       ArrayList a5 = new ArrayList(); 
    Connection connection = null;  
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
           SimpleDateFormat sdf2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
           Calendar now = Calendar.getInstance(); // in your case now will be the server time after getting from DB
           now.add(Calendar.HOUR, +5);  
           String timenow = sdf.format(now.getTime());
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT ID,LABEL,POWER,THERMOSTAT,LAST_UPDATE,TEMP,HUMID,AC_PANO.YAW,AC_PANO.PITCH FROM `AC_MAIN` JOIN AC_PANO ON AC_MAIN.ID=AC_PANO.AC_ID WHERE SUBGROUP_ID='"+ID+"'");
                   
                        
              while (resultSet.next()) {  
                  String id =  resultSet.getString("ID");
                  String label = resultSet.getString("LABEL");
                  String pitch  = resultSet.getString("PITCH");
                  String yaw  = resultSet.getString("YAW");
                  
                  
                  
                  String lasttime = resultSet.getString("LAST_UPDATE");;
           
          
      		Date d1 = null;
                Date d2 = null;
                 try {
                                 d1 = sdf.parse(lasttime);
                                 d2 = sdf.parse(timenow);
                             } catch (ParseException ex) {
                                 
                             }
		
                    long diff = ((d2.getTime() - d1.getTime())/1000)/60;
		String status = "Online";
	
                        if((diff)>1){
               try {
                   status = "Offline since "+sdf2.format(sdf.parse(lasttime));
               } catch (ParseException ex) {
                  
               }
                        }
                  
                  
                  String text = resultSet.getString("LABEL")+" is "+ status + ". Temperature is " + resultSet.getString("TEMP") + "°C with humidity of " + resultSet.getString("HUMID") + "%" ;
                al.add(pitch);
                a2.add(yaw);
                a3.add(text);
                a4.add(id);
                a5.add(label);
        
                
            }  
            Collections.reverse(al);
            Collections.reverse(a2);
            Collections.reverse(a3);
            Collections.reverse(a4);
            Collections.reverse(a5);
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
        Object temp[] = a2.toArray();
       
        Object humid[] = a3.toArray();
        Object time[] = al.toArray();
        Object id[] = a4.toArray();
        Object label[] = a5.toArray();
        String line ="";
         for (int x = 0;x < temp.length;x++){
     
                  line = line + (temp[x]+";");
              
              }
              
             line = line + ("|");
              for (int x = 0;x < humid.length;x++){
     
                  line = line + (humid[x]+";");
              
              }
              line = line + ("|");
               for (int x = 0;x < time.length;x++){
                   
                  line = line + (time[x]+";");
              
              }
               line = line + ("|");
               for (int x = 0;x < id.length;x++){
                   
                  line = line + (id[x]+";");
              
              }
               line = line + ("|");
               for (int x = 0;x < label.length;x++){
                   
                  line = line + (label[x]+";");
              
              }
        
        
        
        return line;
}
 public String[] GetSearchACList(String search,String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM `AC_MAIN` WHERE PROFILE_ID="+uid+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACIdList(String search,String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM `AC_MAIN` WHERE  PROFILE_ID="+uid+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACPowerList(String search,String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT POWER FROM `AC_MAIN` WHERE PROFILE_ID="+uid+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("POWER"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACThermoList(String search, String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM `AC_MAIN` WHERE (SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_NAME LIKE'%"+search+"%' OR GROUP_ID IN(SELECT GROUP_ID FROM GRP_DET WHERE GROUP_NAME LIKE '%"+search+"%'))) OR LABEL LIKE '%"+search+"%' AND PROFILE_ID="+uid);  
            while (resultSet.next()) {  
                al.add(resultSet.getString("THERMOSTAT"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACFanList(String search, String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT FAN FROM `AC_MAIN` WHERE (SUBGROUP_ID IN(SELECT SUBGROUP_ID FROM SUBGRP_DET WHERE SUBGROUP_NAME LIKE'%"+search+"%' OR GROUP_ID IN(SELECT GROUP_ID FROM GRP_DET WHERE GROUP_NAME LIKE '%"+search+"%'))) OR LABEL LIKE '%"+search+"%' AND PROFILE_ID="+uid);  
            while (resultSet.next()) {  
                al.add(resultSet.getString("FAN"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACSubGroupList(String search, String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT SUBGROUP_NAME FROM SUBGRP_DET JOIN AC_MAIN ON  SUBGRP_DET.SUBGROUP_ID=AC_MAIN.SUBGROUP_ID WHERE PROFILE_ID="+uid+" ORDER BY AC_MAIN.ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    } 
 public String[] GetSearchACGroupList(String search,String uid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM GRP_DET JOIN(SELECT SUBGRP_DET.GROUP_ID AS GID,AC_MAIN.ID AS ID FROM SUBGRP_DET JOIN AC_MAIN ON  SUBGRP_DET.SUBGROUP_ID=AC_MAIN.SUBGROUP_ID WHERE PROFILE_ID="+uid+" ORDER BY AC_MAIN.ID) AS NEQ ON NEQ.GID=GRP_DET.GROUP_ID ORDER BY NEQ.ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
 public void setnewHoliday(String USER_ID, String LABEL,String START, String EXPIRE){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `HOLIDAY_MAIN`(`ID`, `LABEL`, `START_DATE`, `EXPIRY`, `USER_ID`) VALUES(NULL,'"+LABEL+"','"+START+"','"+EXPIRE+"','"+USER_ID+"')");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
}
 public String getHolidayAssigned(String userid){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                     .executeQuery("SELECT COUNT(*) AS NMB FROM HOLIDAY_DET WHERE S_ID='"+userid+"'");   
            while (resultSet.next()) {
                data = (resultSet.getString("NMB"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
 
 public String[] getHolIDList(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM HOLIDAY_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getHolLabel(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM HOLIDAY_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
   public String[] getHolStart(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT START_DATE FROM HOLIDAY_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("START_DATE"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   public String[] getHolExpiry(String acid){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT EXPIRY FROM HOLIDAY_MAIN WHERE USER_ID='"+acid+"'");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("EXPIRY"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
   
   
   public String[] getHoldidayACIDList(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("AC_ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getHoldidayACLabelList(String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE ID IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getHolidayACSubGroupList(String AC_ID, String profile){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("(SELECT SUBGROUP_NAME FROM AC_MAIN JOIN SUBGRP_DET\n" +
"ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"') AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY AC_MAIN.ID)");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getHoldidayACGroupList(String AC_ID,String profile){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM (SELECT SUBGRP_DET.GROUP_ID AS NC FROM AC_MAIN  JOIN SUBGRP_DET ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"') AND PROFILE_ID="+profile+" ORDER BY ID) AS ALI LEFT JOIN GRP_DET ON ALI.NC=GRP_DET.GROUP_ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getUnHoldidayACIDList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ID FROM AC_MAIN WHERE ID NOT IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND PROFILE_ID="+profile+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("ID"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getUnHolidayACLabelList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE ID NOT IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND PROFILE_ID="+profile+" ORDER BY ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("LABEL"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  
  public String[] getUnHolidayACSubGroupList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("(SELECT SUBGROUP_NAME FROM AC_MAIN JOIN SUBGRP_DET\n" +
"ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID NOT IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"' ORDER BY AC_ID) AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY AC_MAIN.ID)");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("SUBGROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public String[] getUnHolidayACGroupList(String profile,String AC_ID){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT GROUP_NAME FROM (SELECT SUBGRP_DET.GROUP_ID AS NC FROM AC_MAIN JOIN SUBGRP_DET ON AC_MAIN.SUBGROUP_ID=SUBGRP_DET.SUBGROUP_ID WHERE AC_MAIN.ID NOT IN(SELECT AC_ID FROM HOLIDAY_DET WHERE S_ID='"+AC_ID+"') AND AC_MAIN.PROFILE_ID="+profile+" ORDER BY ID) AS ALI LEFT JOIN GRP_DET ON ALI.NC=GRP_DET.GROUP_ID");  
            while (resultSet.next()) {  
                al.add(resultSet.getString("GROUP_NAME"));
            } 
            
            statement.close();
            connection.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Object data[] = al.toArray();
        String[] filterdata = new String[data.length];
        for(int x = 0; x< data.length;x++){
            filterdata[x] = data[x].toString();
        }
        return filterdata;
    }
  public void setnewRelACHoliday(String SID,String ACID){
    
   
    Connection connection = null;  
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `HOLIDAY_DET`(`ID`, `S_ID`, `AC_ID`) VALUES(NULL,'"+SID+"','"+ACID+"')");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
   
} 
 
 
 public void deleteHolidayAC(String sid,String acid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM HOLIDAY_DET where S_ID='"+sid+"' AND AC_ID='"+acid+"' ");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
  public void deleteHolidayProfile(String sid){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM HOLIDAY_MAIN where ID='"+sid+"'");  
            
            
            statement.close();
            connection.close();
            
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM HOLIDAY_DET where S_ID='"+sid+"'"); 
            
             
            statement.close();
            connection.close();
            
              
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
  public String getMotionState(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT ENABLE FROM MOTION WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("ENABLE"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public String getMotionVal(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT MOTION_VAL FROM MOTION WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("MOTION_VAL"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public void UpdateMotionState(String AC_ID, String Remote){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOTION SET ENABLE='"+Remote+"' WHERE AC_ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
  
  public void UpdateAutoThermostatAC(String AC_ID, String Thermo){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOTION SET THERMOSTAT='"+Thermo+"' WHERE AC_ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
  
    public String getAutoThermoStateAC(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT THERMOSTAT FROM MOTION WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("THERMOSTAT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
    
    public String getPic(String ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT PIC FROM PIC WHERE AC_ID ='"+ID+"' ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("PIC"));
                
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
    
   public String getCam(String ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT AC_ID FROM PIC_MAIN WHERE AC_ID ='"+ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("AC_ID"));
                
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}  
   
   
   
   public String getMotionTimerVal(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TIMEOUT FROM MOTION WHERE AC_ID='"+AC_ID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("TIMEOUT"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
} 
  public void UpdateMotionTimer(String AC_ID, String Time){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE MOTION SET TIMEOUT='"+Time+"' WHERE AC_ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
  
  public void UpdateIrCode(String AC_ID, String ircode){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET IR_CODE='"+ircode+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
   
   public String[] getState(String AC_ID) throws SQLException{
  
    String[] vals= new String[6]; 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE ID="+AC_ID);  
            while (resultSet.next()) {
               vals[0]=(resultSet.getString("FAN_SPEED"));
               vals[1]=(resultSet.getString("THERMOSTAT"));
               vals[2]=(resultSet.getString("POWER"));
               vals[3]=(resultSet.getString("MODE"));
               vals[4]=(resultSet.getString("SWING"));
               vals[5]=(resultSet.getString("BRAND"));
            }  
            statement.close();
            connection.close();
          
        } catch (Exception e) { 
             e.printStackTrace();  
             connection.close();
}
        
       
        return vals;
       
}
   

}