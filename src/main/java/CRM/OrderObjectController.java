package CRM;

import java.util.List;




import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class OrderObjectController {
	@EJB
	private CRMejb crmEjb;

	@ManagedProperty(value = "#{orderObject}")
	private OrderObject orderObject;
	
	public OrderObjectController(){
		
	}
	
	public OrderObject getOrderObject() {
		return orderObject;
	}

	public void setOrderObject(OrderObject orderObject) {
		this.orderObject = orderObject;
	}
	
	
	public String saveOrderObject() {
		
		String viesti = "Uuden tuotteen lisääminen onnistui "+ orderObject;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		FacesMessage facesMessage = new FacesMessage(viesti);
		facesContext.addMessage(null, facesMessage);
		crmEjb.saveOrderObject(orderObject);
		return "tuotteet";
		
		
	}
	
	
	
	public  List<OrderObject> getOrderObjects() {
		return crmEjb.getOrderObjects();
	}
	
	public  String searchByName(String name) {
		return crmEjb.getOrderObjects(name).toString();
	}
	

	public String initializeOrderObject() {
		return null;
	}


}
