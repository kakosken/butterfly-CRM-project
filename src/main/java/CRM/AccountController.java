package CRM;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/*
 * Piia Loukeinen, team 3
 */

@ManagedBean
public class AccountController {
	

	
	
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{account}")
	private Account account;

	public AccountController() {
		// testidatan alustus?

	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void init(){
		crmEjb.init();
		
	}
	
	
	
	public String addAccount(){
		//uuden käyttäjätili lisääminen
		
		String viesti = "Uuden käyttäjätilin lisääminen onnistui "+ account;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//CRMejb.addAccount(account);
		crmEjb.addAccount(account);
		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);

		return "index";
	}
	
	public void deleteAccount(Account a){
		
		long id = a.getId();
		try{
			  System.out.println("Deleted account (id)= "+ a);
			  crmEjb.deleteAccount(id);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Poistaminen ei onnistunut");
		}
	}
	

	public List<Account> getAccounts() {
		return crmEjb.getAccounts();
	}


	
	
	
	
} 
