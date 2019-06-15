package hibernate.many.to.many;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle")
public class Vehicle {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int vehicleId;
	String vehicleName;
	
	@ManyToMany(mappedBy="vehicle")
	Collection<UserDetails> user = new ArrayList<UserDetails>();
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Collection<UserDetails> getUser() {
		return user;
	}
	public void setUser(Collection<UserDetails> user) {
		this.user = user;
	}
	
}
