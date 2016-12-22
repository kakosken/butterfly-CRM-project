package CRM;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class CustomerController {
	
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{customer}")
	private Customer customer;

	
	public CustomerController(){
		
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String addCustomer(){
		//uuden käyttäjätili lisääminen
		
		String viesti = "Uuden asiakkaan lisääminen onnistui "+ customer;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//CRMejb.addAccount(account);
		crmEjb.addCustomer(customer);
		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);

		return "index";
	}
	
	public List<Customer> getCustomers(){
		return crmEjb.getCustomers();
	}
	
	
}
