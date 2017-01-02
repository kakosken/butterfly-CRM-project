package CRM;



import java.util.ArrayList;
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
	
	private ArrayList<OrderObject> hakutulokset = new ArrayList();
	
	public ArrayList<OrderObject> getHakutulokset() {
		return hakutulokset;
	}

	public void setHakutulokset(ArrayList<OrderObject> hakutulokset) {
		this.hakutulokset = hakutulokset;
	}

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
		return "index";
		
		
	}
	
	
	
	public void getOrderObjects() {
		hakutulokset = (ArrayList<OrderObject>) crmEjb.getOrderObjects();
	}
	
	public  String searchByName(String name) {
		return crmEjb.getOrderObjectsByName(name).toString();
	}
	
	public  String searchByState(String state) {
		return crmEjb.getOrderObjectsByState(state).toString();
	}
	
	public  String searchByCompanyId(long companyId) {
		return crmEjb.getOrderObjectsByCompany(companyId).toString();
	}
	
	public String searchByOrderDeliveryPlace(String deliveryPlace){
		return crmEjb.getOrderObjectsByDeliveryPlace(deliveryPlace).toString();
	}
	
	public String searchByOrderDeliveryDate(String date){
		return crmEjb.getOrderObjectsByDate(date).toString();
	}
	
	public String searchByOrderCustomer(long customerId){
		return crmEjb.getOrderObjectsByCustomer(customerId).toString();
	}
	

	public String initialize() {
		crmEjb.init();
		return "";
	}


}
