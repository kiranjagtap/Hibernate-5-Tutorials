package hibernate.first.level.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USER_DETAIL")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
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
	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + "]";
	}

	
}
