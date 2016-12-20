import java.math.BigDecimal;

/**RecordHistory is a Record that maintains each transaction in BalanceSheet database, 
	whether that be login, logout, add, modify, or delete
	@author Michael Smith*/
public class RecordHistory extends Record{
	private String name;
	private boolean login = false;
	private boolean logout = false;
	private boolean delete = false;
	/**@param amount saves to this  
	 * @param name saves to this
	*/
	public RecordHistory(BigDecimal amount, String name)
	//Modifies: super.credit,
	//Effects: sets super.amount to amount and this.name to name
	//sets super.credit to false if negative amount or true if positive amount
	{
		super(amount, -1);
		if(amount.compareTo(new BigDecimal(0)) < 0){
			super.setCredit(false);
		}
		else{
			super.setCredit(true);
		}
		this.name = name;
	}
	
	/**
	@param log is true is login or false if logout
	@param name saves to this*/
	public RecordHistory(boolean log, String name)
	//Modifies: this.login, this.logout,
	//Effects: initializes login, logout depending on boolean log
	//initializes this.name to name
	{
		this.name = name;
		if(log){
			this.login = true;
		}
		else{
			this.logout = true;
		}
	}
	public RecordHistory(String name, int id){
		this.name = name;
		this.delete = true;
		super.setId(id);
	}
	public String getName(){
		return this.name;
	}
	public String toString()
	//Effects: returns string representation of this RecordHistory
	{
		StringBuilder str = new StringBuilder();
		if(this.login){
			str.append(this.name).append(" ").append("login");
		}
		else if(this.logout){
			str.append(this.name).append(" ").append("logout");
		}
		else if(this.delete){
			str.append(this.name).append(" ").append("delete id#: ").append(super.id());
		}
		else{
			if(super.credit()){
				str.append(this.name).append(" ").append("credit: ").append(super.amount().
						toString());
			}
			else{
				str.append(this.name).append(" ").append("debit: ").append(super.amount().
						toString());
			}
		}
		return str.toString();
	}
}
