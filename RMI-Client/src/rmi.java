import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class rmi extends UnicastRemoteObject implements sensorListener {

	protected rmi() throws RemoteException {
		super();
	}
	public void add() {
		try {
			ServerInt obj=(ServerInt)Naming.lookup("server");
			
			fireAlarm fm1=new fireAlarm("asdsad", 0, 6, 0, 0);
			
			ArrayList<fireAlarm> myResponse =obj.addItem(fm1);
			
			System.out.println(myResponse.get(0).id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void read() {
		try {
			ServerInt obj=(ServerInt)Naming.lookup("server");
			
			//Id of the row that needs to be read
			String id="5eb06ef914bd66d6f6a8e662";
			
			ArrayList<fireAlarm> myResponse =obj.getItem(id);
			
			System.out.println(myResponse.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<fireAlarm> readAll() {
		ArrayList<fireAlarm> myResponse=null;
		try {
			ServerInt obj=(ServerInt)Naming.lookup("server");
			
			myResponse =obj.getItems();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myResponse;
	}
	
	public void update() {
		try {
			ServerInt obj=(ServerInt)Naming.lookup("server");
			//object that needs to be updated 
			fireAlarm fm1=new fireAlarm("5eb06ef914bd66d6f6a8e662", 44, 44, 2, 3);
			
			ArrayList<fireAlarm> myResponse =obj.updateItem(fm1);
			
			System.out.println(myResponse.get(0).id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete() {
		try {
			ServerInt obj=(ServerInt)Naming.lookup("server");
			
			//Id of the row that needs to be read
			String id="5eb06ef914bd66d6f6a8e662";
			
			ArrayList<fireAlarm> myResponse =obj.deleteItem(id);
			
			System.out.println(myResponse.get(0).id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	@Override
	public ArrayList<fireAlarm> sensorStatusChanged() throws RemoteException {
		rmi cli=new rmi();
		ArrayList<fireAlarm> array=cli.readAll();
		
		return array;
	}
	public static void main(String[] args)throws Exception {
		
		System.setProperty("java.security.policy", "file:allowall.policy");
		
		 //check the server by uncommenting following
		ServerInt obj=(ServerInt)Naming.lookup("server");
		obj.serverCheck();
		
		rmi cli=new rmi();
		
		obj.addSensorListener(cli);
		//cli.add();
		//cli.read();
		//cli.readAll();
		//cli.update();
		//cli.delete();
		
		
		
	}
	

}
