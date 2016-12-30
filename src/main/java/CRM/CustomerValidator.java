package CRM;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



@ManagedBean
public class CustomerValidator implements Validator {

	@EJB
	private CRMejb crmEjb;
	
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value)
			throws ValidatorException {
		
		   if (value == null) {
	            return; // Let required="true" handle.
	        }
		
		if (crmEjb.customerExist((String)value)){
			FacesMessage msg = new FacesMessage("Asiakas on jo olemassa, korjaa nimi!");
			throw new ValidatorException(msg);
		}
		
	}

}
