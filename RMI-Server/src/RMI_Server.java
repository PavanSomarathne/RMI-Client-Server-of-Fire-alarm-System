import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;


public class RMI_Server extends UnicastRemoteObject implements ServerInt {
	
	
	private static List<sensorListener> listeners = new ArrayList<>();

	
	protected RMI_Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void serverCheck() throws Exception {
		System.out.println("Server is Checked");
		
	}
	@Override
	public ArrayList<fireAlarm> addItem(fireAlarm item) throws Exception {
		
		ArrayList<fireAlarm> faList = new ArrayList<>();
		
		final String POST_PARAMS = 
				"{\n" + "\"floorNo\": "+item.floor+",\r\n" +
		        "    \"roomNo\": "+item.roomNumber+",\r\n" +
		        "    \"smokelevel\":"+item.smokelevel+",\r\n" +
		        "    \"co2_level\": "+item.co2level+",\r\n" +
		        "    \"smoke_Alert\":"+item.smokeAlert+",\r\n" +
		        "    \"co2_Alert\": "+item.co2Alert + "\n}";
		    
		    URL obj = new URL("http://localhost:4000/monitoring/details/");
		    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    OutputStream os = postConnection.getOutputStream();
		    os.write(POST_PARAMS.getBytes());
		    os.flush();
		    os.close();
		    int responseCode = postConnection.getResponseCode();
		   
		    int req=HttpURLConnection.HTTP_CREATED ;
		    
		    if (responseCode == (req-1)) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		        
		        JSONObject sensor = new JSONObject(response.toString());
		       
                    fireAlarm currentSensor = new fireAlarm();
                    
                    currentSensor.setId(String.valueOf(sensor.getString("_id")));
                    currentSensor.setFloor(Integer.valueOf(sensor.getInt("floorNo")));
                    currentSensor.setRoomNumber(Integer.valueOf(sensor.getInt("roomNo")));
                    currentSensor.setSmokelevel(Integer.valueOf(sensor.getInt("smokelevel")));
                    currentSensor.setCo2level(Integer.valueOf(sensor.getInt("co2_level")));
                    currentSensor.setSmokeAlert(Boolean.valueOf(sensor.getBoolean("smoke_Alert")));
                    currentSensor.setCo2Alert(Boolean.valueOf(sensor.getBoolean("co2_Alert")));
                   
					faList.add(currentSensor);
                  
                
		    } else {
		        System.out.println("POST NOT WORKED");
		    }
			return faList;

	}
	
	@Override
	public ArrayList<fireAlarm> getItem(String id) throws Exception {
		
		ArrayList<fireAlarm> faList = new ArrayList<>();
        JSONArray myResponse ;
        try {
        	String url = "http://localhost:4000/monitoring/search/"+id;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
            	response.append(inputLine);
            }
            in.close();
            
              myResponse = new JSONArray(response.toString());
        
              JSONObject sensor;
                
                for (int i = 0; i < myResponse.length(); i++) {
                    sensor =  myResponse.getJSONObject(i);
                   
                    fireAlarm currentSensor = new fireAlarm();
                    
                    currentSensor.setId(String.valueOf(sensor.getString("_id")));
                    currentSensor.setFloor(Integer.valueOf(sensor.getInt("floorNo")));
                    currentSensor.setRoomNumber(Integer.valueOf(sensor.getInt("roomNo")));
                    currentSensor.setSmokelevel(Integer.valueOf(sensor.getInt("smokelevel")));
                    currentSensor.setCo2level(Integer.valueOf(sensor.getInt("co2_level")));
                    currentSensor.setSmokeAlert(Boolean.valueOf(sensor.getBoolean("smoke_Alert")));
                    currentSensor.setCo2Alert(Boolean.valueOf(sensor.getBoolean("co2_Alert")));
                   
                    faList.add(currentSensor);
                  
                }
               
		} catch (Exception e) {
			System.out.println(e);
		}
	
		return faList;
	}
	
	@Override
	public ArrayList<fireAlarm> getItems() throws Exception {
		
		ArrayList<fireAlarm> faList = new ArrayList<>();
        JSONArray myResponse ;
        try {
        	String url = "http://localhost:4000/monitoring/details/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
           
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
            	response.append(inputLine);
            }
            in.close();
           
              myResponse = new JSONArray(response.toString());
           
                JSONObject sensor;
                
                for (int i = 0; i < myResponse.length(); i++) {
                    sensor =  myResponse.getJSONObject(i);
                   
                    fireAlarm currentSensor = new fireAlarm();
                    
                    currentSensor.setId(String.valueOf(sensor.getString("_id")));
                    currentSensor.setFloor(Integer.valueOf(sensor.getInt("floorNo")));
                    currentSensor.setRoomNumber(Integer.valueOf(sensor.getInt("roomNo")));
                    currentSensor.setSmokelevel(Integer.valueOf(sensor.getInt("smokelevel")));
                    currentSensor.setCo2level(Integer.valueOf(sensor.getInt("co2_level")));
                    currentSensor.setSmokeAlert(Boolean.valueOf(sensor.getBoolean("smoke_Alert")));
                    currentSensor.setCo2Alert(Boolean.valueOf(sensor.getBoolean("co2_Alert")));
                   
                    faList.add(currentSensor);
                  
                }
               
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return faList;
	}
	
	@Override
	public ArrayList<fireAlarm> updateItem(fireAlarm item) throws Exception {
		
		ArrayList<fireAlarm> faList = new ArrayList<>();
		
		final String POST_PARAMS = 
				"{\n" + "\"floorNo\": "+item.floor+",\r\n" +
				        "    \"roomNo\": "+item.roomNumber+",\r\n" +
				        "    \"smokelevel\":"+item.smokelevel+",\r\n" +
				        "    \"co2_level\": "+item.co2level+",\r\n" +
				        "    \"status\":"+item.status+",\r\n" +
				        "    \"smoke_Alert\":"+item.smokeAlert+",\r\n" +
				        "    \"co2_Alert\": "+item.co2Alert+ "\n}";
		    
		    URL obj = new URL("http://localhost:4000/monitoring/details/"+item.id);
		    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("PUT");
		    //postConnection.setRequestProperty("IdNumber","101");
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    OutputStream os = postConnection.getOutputStream();
		    os.write(POST_PARAMS.getBytes());
		    os.flush();
		    os.close();
		    int responseCode = postConnection.getResponseCode();
		    
		    int req=HttpURLConnection.HTTP_CREATED ;
		    
		    if (responseCode == (req-1)) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		        
		        JSONObject sensor = new JSONObject(response.toString());
                  
                fireAlarm currentSensor = new fireAlarm();
                    
                    currentSensor.setId(String.valueOf(sensor.getString("_id")));
                    currentSensor.setFloor(Integer.valueOf(sensor.getInt("floorNo")));
                    currentSensor.setRoomNumber(Integer.valueOf(sensor.getInt("roomNo")));
                    currentSensor.setSmokelevel(Integer.valueOf(sensor.getInt("smokelevel")));
                    currentSensor.setCo2level(Integer.valueOf(sensor.getInt("co2_level")));
                    currentSensor.setSmokeAlert(Boolean.valueOf(sensor.getBoolean("smoke_Alert")));
                    currentSensor.setCo2Alert(Boolean.valueOf(sensor.getBoolean("co2_Alert")));
                   
                    faList.add(currentSensor);

		    } else {
		        System.out.println("UPDATE NOT WORKED");
		    }

		return faList;
	}
	
	@Override
	public ArrayList<fireAlarm> deleteItem(String id) throws Exception {
		
		ArrayList<fireAlarm> faList = new ArrayList<>();
		
		final String POST_PARAMS = null;	
		 
		    URL obj = new URL("http://localhost:4000/monitoring/details/"+id);
		    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("DELETE");
		    
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setDoOutput(true);
		    OutputStream os = postConnection.getOutputStream();
		    
		    os.flush();
		    os.close();
		    int responseCode = postConnection.getResponseCode();
		   
		    int req=HttpURLConnection.HTTP_CREATED ;
		    
		    if (responseCode == (req-1)) { //success
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		            postConnection.getInputStream()));
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = in .readLine()) != null) {
		            response.append(inputLine);
		        } in .close();
		       
		        JSONObject sensor =  new JSONObject(response.toString());
                  
                 fireAlarm currentSensor = new fireAlarm();
                    
                    currentSensor.setId(String.valueOf(sensor.getString("_id")));
                    currentSensor.setFloor(Integer.valueOf(sensor.getInt("floorNo")));
                    currentSensor.setRoomNumber(Integer.valueOf(sensor.getInt("roomNo")));
                    currentSensor.setSmokelevel(Integer.valueOf(sensor.getInt("smokelevel")));
                    currentSensor.setCo2level(Integer.valueOf(sensor.getInt("co2_level")));
                    currentSensor.setSmokeAlert(Boolean.valueOf(sensor.getBoolean("smoke_Alert")));
                    currentSensor.setCo2Alert(Boolean.valueOf(sensor.getBoolean("co2_Alert")));

                    faList.add(currentSensor);
		        
		    } else {
		        System.out.println("DELETE NOT WORKED");
		    }

		return faList;
	}
	public void addSensorListener(sensorListener sl) throws RemoteException
    {
        listeners.add(sl);
    }
	public void smokeAlert(fireAlarm item) throws Exception{
		//System.out.println("smoke Alert"+ item.id);
		this.updateItem(item);

		
	}
	public void co2Alert(fireAlarm item) throws Exception{
		//System.out.println("Co2 Alert" + item.id);
		this.updateItem(item); 
		    
	}

	public static void main(String[] args) throws Exception {
System.out.println("Server is started");
		
System.setProperty("java.security.policy", "file:allowall.policy");
		
		RMI_Server s1 = new RMI_Server();			   		   
		Naming.rebind("server", s1);
		
		 
		
		//RMI Server checks the Alert status and send Alerts to Rest every 15 Secs while updating desktop Client
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		         
		    	  for (sensorListener lListener : listeners)
		          {
		              try
		              {
		            	  ArrayList<fireAlarm> array=lListener.sensorStatusChanged();
		            	  
		            	  for (fireAlarm item : array) {
							if(item.co2level>5) {
								item.setCo2Alert(true);
								s1.co2Alert(item);
								
							}else if (item.smokelevel>5) {
								item.setSmokeAlert(true);
								s1.smokeAlert(item);
							}
						}
		              }
		              catch (Exception aInE)
		              {
		                  
		              }
		          }
		    	  
		      }
		    };
		    Timer timer = new Timer();
		    long delay = 0;
		    long intevalPeriod = 1 * 15000; 

		    timer.scheduleAtFixedRate(task, delay,intevalPeriod);
		   
		 System.out.println("Server is running");
	}

	

	


	

	

	

}
