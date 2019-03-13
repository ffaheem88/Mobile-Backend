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
import java.util.ArrayList;
/**
 *
 * @author Faisal
 */
public class AirDBMobile {
//    static String dbadress = "jdbc:mysql://50.31.147.154:3306/ffaheem_wiir";
//    static String user="ffaheem_mobile";
//    static String pass="mobile@123";
    
    static String dbadress = "jdbc:mysql://pkujala.c66yg152cqdw.us-west-2.rds.amazonaws.com:3306/ffaheem_wiir";
    static String user="ffaheem";
    static String pass="fai12345";
    
// Retrieving Tree Data    
    
    public String getUserId(String username, String password){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT PROFILE_ID FROM PROFILE WHERE EMAIL='"+username.trim()+"' AND PASSWORD='"+password.trim()+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("PROFILE_ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
    
    
     public ArrayList GetGroupList(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM GRP_DET WHERE PROFILE_ID='"+userid+"'");  
            while (resultSet.next()) { 
                String[] list = new String[2];
                list[0] = resultSet.getString("GROUP_ID");
                list[1] = resultSet.getString("GROUP_NAME");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}
     
     public ArrayList GetSubGroupList(String grpid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM SUBGRP_DET WHERE GROUP_ID='"+grpid+"'");  
            while (resultSet.next()) { 
                String[] list = new String[2];
                list[0] = resultSet.getString("SUBGROUP_ID");
                list[1] = resultSet.getString("SUBGROUP_NAME");
                al.add(list);
                
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return al;
}
    
    
     public ArrayList GetidList(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE PROFILE_ID='"+userid+"' AND SUBGROUP_ID=0");  
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
       
        return al;

     }     

     
      public ArrayList GetSubGroupDevList(String userid){
     ArrayList<String[]> al = new ArrayList(); 
     
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE SUBGROUP_ID='"+userid+"'");  
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
       
        return al;

     }     

     public String[] GetState(String TrackerID){
    
     String[] list = new String[13];
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT * FROM AC_MAIN WHERE ID='"+TrackerID+"'");  
            while (resultSet.next()) { 
                list[0] = resultSet.getString("ID");
                list[1] = resultSet.getString("LABEL");
                list[2] = resultSet.getString("POWER");
                list[3] = resultSet.getString("THERMOSTAT");
                list[4] = resultSet.getString("FAN_SPEED");
                list[5] = resultSet.getString("MODE");
                list[6] = resultSet.getString("SWING");
                list[7] = resultSet.getString("TEMP");
                list[8] = resultSet.getString("HUMID");
                list[9] = resultSet.getString("SIGNAL");
                list[10] = resultSet.getString("REMOTE");
                list[11] = resultSet.getString("LAST_UPDATE");
                list[12] = resultSet.getString("BRAND");
            }  
            
                
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
       
        return list;
}
     
     
      
      public String getACBrand(String TrackerID){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT BRAND FROM AC_MAIN WHERE ID='"+TrackerID+"'");  
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
     
  public String getDevName(String TrackerID){
    
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE ID='"+TrackerID+"'");  
            while (resultSet.next()) {
                data = (resultSet.getString("LABEL"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
  public String getACTemp(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TEMP FROM AC_DATA WHERE AC_ID='"+AC_ID+"' ORDER BY ID DESC LIMIT 1");  
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
                    .executeQuery("SELECT HUMID FROM AC_DATA WHERE AC_ID='"+AC_ID+"' ORDER BY ID DESC LIMIT 1");  
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

 public String getLastUpdateTime(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT TIME FROM AC_DATA WHERE AC_ID='"+AC_ID+"' ORDER BY ID DESC LIMIT 1");  
            while (resultSet.next()) {
                data = (resultSet.getString("TIME"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
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
public String[] GetGroupACList(String GroupId){
     ArrayList al = new ArrayList(); 
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT LABEL FROM AC_MAIN WHERE GROUP_ID='"+GroupId+"'");  
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
 
 public void UpdateThermoAC(String AC_ID, String Thermo){
    
   
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
    

 
 public void NewDevice(String AC_ID,String Group_Id,String SubGroup_Id,String Label,String Brand,String Inherited, String Thermostat, String Speed, String Power,String Swing,String Mode,String remote, String time,String profile){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `AC_MAIN` (`ID`, `GROUP_ID`, `SUBGROUP_ID`, `LABEL`, `BRAND`, `INHERITED`, `THERMOSTAT`, `POWER`, `FAN_SPEED`, `MODE`, `SWING`,`REMOTE`,`INSTALL_DATE`,`PROFILE_ID`) VALUES ('"+AC_ID+"', '"+Group_Id+"', '"+SubGroup_Id+"', '"+Label+"', '"+Brand+"', '"+Inherited+"', '"+Thermostat+"', '"+Power+"', '"+Speed+"', '"+Mode+"', '"+Swing+"','"+remote+"', '"+time+"','"+profile+"');");  
            
            
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

  public void AddHistoryGrpSubGrp(String tracker_id,String time){
               if(getInheritStateAC(tracker_id).equals("1")){
                String Inheritedvals = getInheritGroupIdAC(tracker_id);
                if(Inheritedvals.startsWith("S")){ 
                     String id = (Inheritedvals.substring(1));
                     String Speed = getFanStateSubGrp(id);
                     String Thermo = getThermoStateSubGrp(id);
                     String operation = getPowerStateSubGrp(id);
                     String mode = getModeStateSubGrp(id);
                     String swing = getSwingStateSubGrp(id);
                     String temp = getTempSubGrp(id);
                     String humid = getHumidSubGrp(id);
                    SetHistorySubGrp(id, Thermo, Speed, operation, temp, humid, time,mode,swing);
                    if(getInheritStateSubGrp(id).equals("1")){
                    String GroupId = getInheritedGroupIdSubGroup(id);
                    SetHistoryGrp(GroupId, Thermo, Speed, operation, temp, humid, time,mode,swing);
                    }
                }else if(Inheritedvals.startsWith("G")) {
                     String id = (Inheritedvals.substring(1));
                     String Speed = getFanStateGrp(id);
                     String Thermo = getThermoStateGrp(id);
                     String operation = getPowerStateGrp(id);
                     String mode = getModeStateGrp(id);
                     String swing = getSwingStateGrp(id);
                     String temp  = getTempGrp(id);
                     String humid = getHumidGrp(id);
                    SetHistoryGrp(id, Thermo, Speed, operation, temp, humid, time,mode,swing);
                }
            }
  }
  
  public String getRemote(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT REMOTE FROM AC_MAIN WHERE ID ='"+AC_ID+"'");  
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
  
  
  public void SetHistoryIR(String AC_ID, String TIME, String IR){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("INSERT INTO `IR_DATA` (`ID`, `AC_ID`, `TIME`, `IRCODE`) VALUES (NULL, '"+AC_ID+"', '"+TIME+"', '"+IR+"');");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}      
      
}
 
 public void setSignal(String AC_ID, String signal){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET `SIGNAL`='"+signal+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
 }
 
 
 public void UpdateSettingAC(String AC_ID, String POWER,String THERMO,String FAN,String MODE,String SWING,String REMOTE){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER='"+POWER+"',THERMOSTAT='"+THERMO+"',FAN_SPEED='"+FAN+"',MODE='"+MODE+"',SWING='"+SWING+"',REMOTE='"+REMOTE+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public String getRemoteVal(String AC_ID){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT IRCODE FROM IR_DATA WHERE AC_ID='"+AC_ID+"' AND TIME=(SELECT TIME FROM AC_DATA WHERE AC_ID='"+AC_ID+"' ORDER BY ID DESC LIMIT 1) ORDER BY ID DESC LIMIT 1");  
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

 public void renameAC(String AC_ID, String label){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET LABEL='"+label+"' WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 
 public void deleteAC(String TrackerID){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("DELETE FROM AC_MAIN where ID='"+TrackerID+"'");  
            
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}
      
    
}
 
 
 public String getTimerState(String AC_ID,int day,int hour){
  
    String data="";
    
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
           
                        ResultSet resultSet = statement  
                    .executeQuery("SELECT STATE_ID FROM TIMER WHERE AC_ID ='"+AC_ID+"' AND DAY="+day+" AND HOUR<"+hour+" ORDER BY HOUR ASC");  
            while (resultSet.next()) {
                data = (resultSet.getString("STATE_ID"));
            }  
            
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            data=e.toString();  
}
        return data;
}
 
 public void UpdateRemoteSettings(String AC_ID, String StateID){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE AC_MAIN SET POWER=(SELECT POWER FROM IR_STATE WHERE ID="+StateID+"),THERMOSTAT=(SELECT THERMOSTAT FROM IR_STATE WHERE ID="+StateID+"),FAN_SPEED=(SELECT FAN_SPEED FROM IR_STATE WHERE ID="+StateID+"),MODE=(SELECT MODE FROM IR_STATE WHERE ID="+StateID+"),SWING=(SELECT SWING FROM IR_STATE WHERE ID="+StateID+") WHERE ID='"+AC_ID+"'");  
            statement.close();
            connection.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
}     
    
}
 public void UpdateTimer(String AC_ID, int day,int hour){
    
   
    Connection connection = null;  
        try{
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();  
            
            connection = DriverManager.getConnection(dbadress, user, pass);
            Statement statement = connection.createStatement();  
            statement.executeUpdate("UPDATE TIMER SET DAY="+day+",HOUR="+hour+" WHERE AC_ID='"+AC_ID+"'");  
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