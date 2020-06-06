import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class fireAlarm implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	int floor;
	int roomNumber;
	int smokelevel;
	int co2level;
	Boolean status;
	Boolean smokeAlert;
	Boolean co2Alert;
	
	public fireAlarm() throws RemoteException {
		super();
	}
	public fireAlarm(String id, int floor, int roomNumber, int smokelevel, int co2level) throws RemoteException {
		super();
		this.id = id;
		this.floor = floor;
		this.roomNumber = roomNumber;
		this.smokelevel = smokelevel;
		this.co2level = co2level;
		this.smokeAlert=false;
		this.co2Alert=false;
		this.status=false;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getSmokelevel() {
		return smokelevel;
	}
	public void setSmokelevel(int smokelevel) {
		this.smokelevel = smokelevel;
	}
	public int getCo2level() {
		return co2level;
	}
	public void setCo2level(int co2level) {
		this.co2level = co2level;
	}
	public Boolean getSmokeAlert() {
		return smokeAlert;
	}
	public void setSmokeAlert(Boolean smokeAlert) {
		this.smokeAlert = smokeAlert;
	}
	public Boolean getCo2Alert() {
		return co2Alert;
	}
	public void setCo2Alert(Boolean co2Alert) {
		this.co2Alert = co2Alert;
	}
	public String getId() {
		return id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
