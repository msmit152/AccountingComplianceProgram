import java.math.BigDecimal;
import java.util.ArrayList;
/** Shareholder is an investor in a financial institution who wants to be updated 
 * on financial status of company
 * @author Michael Smith */
public class Shareholder extends User {
	private String name;
	public Shareholder(String name){
		super.setName(name);
	}
	public Shareholder(){
		
	}
	public void setName(String name){
		super.setName(name);
	}
}
