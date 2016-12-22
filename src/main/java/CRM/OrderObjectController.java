package CRM;

import javax.faces.bean.ManagedProperty;

public class OrderObjectController {
//	@EJB
	//	private OrderObjecEjb orderObjectEjb;

	@ManagedProperty(value = "#{orderObject}")
	private OrderObject orderObject;
	
	public OrderObject getOrderObject() {
		return orderObject;
	}

	public void setOrderObject(OrderObject orderObject) {
		this.orderObject = orderObject;
	}
	
	public String saveOrderObject() {
		return ("OrderObject saved");
	}
	
	
	
	//public  List<OrderObject> listOrdersObjects() {
	//	return null;
	//}
	

	public String initializeOrderObject() {
		return null;
	}


}
