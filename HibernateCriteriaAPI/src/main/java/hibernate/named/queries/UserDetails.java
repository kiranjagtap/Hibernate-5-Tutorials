package hibernate.named.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;

@Entity
@NamedQuery(name="userDetails.byId", query="from UserDetails where id = ?")
@NamedNativeQuery(name="userDetails.byName", query="select * from user_detail where name = ?", resultClass=UserDetails.class)
@Table(name = "USER_DETAIL")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
