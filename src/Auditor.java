import java.math.BigDecimal;
import java.util.ArrayList;
/** Auditor may be working on behalf of shareholders or government; his responsibility is
 * to investigate any discrepancies with records in Database and any independent records he may have 
 * @author Michael Smith*/
public class Auditor extends User{
	private String name;
	public Auditor(String name){
		super.setName(name);
	}
	public Auditor(){
		
	}
	public void setName(String name){
		super.setName(name);
	}
}
