import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInt extends Remote {
	public void serverCheck() throws Exception;
	public ArrayList<fireAlarm> addItem(fireAlarm Item) throws Exception;
	public ArrayList<fireAlarm> getItem(String id) throws Exception;
	public ArrayList<fireAlarm> getItems() throws Exception;
	public ArrayList<fireAlarm> updateItem(fireAlarm Item) throws Exception;
	public ArrayList<fireAlarm> deleteItem(String id) throws Exception;
	public void addSensorListener(sensorListener sl)throws Exception;
	public void co2Alert(fireAlarm id) throws Exception;
	public void smokeAlert(fireAlarm id) throws Exception;
	
}
