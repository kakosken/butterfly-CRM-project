package CRM;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@ManagedBean
@RequestScoped
@Entity
@NamedQuery(name = "searchAllAccounts", query = "SELECT a from Account a") 
public class Account implements Serializable{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 private String name;
	 private String userName;
	 private String password; 
	 private String email;
	 private enum Role { 
			ADMINISTRATOR, SALESPERSON, CUSTOMERSERVANT;
	 }
	 
	 public Account(){
		 
	 }
	 
	 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", userName="
				+ userName + ", password=" + password + "]";
	}
	 
	 

}
