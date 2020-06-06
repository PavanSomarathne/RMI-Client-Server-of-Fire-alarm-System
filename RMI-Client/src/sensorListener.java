import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface sensorListener extends Remote{
	ArrayList<fireAlarm> sensorStatusChanged() throws RemoteException;
}
