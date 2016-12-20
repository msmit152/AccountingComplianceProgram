import java.math.BigDecimal;

/** Record maintains amount of a transaction and whether it is a credit or debit  
 * @author Michael Smith*/
public class Record {
	private boolean credit;
	private BigDecimal amount;
	private int id;
	/**@param amount saves to this
	 * @param id saves to this*/
	public Record(BigDecimal amount, int id)
	//if amount is has + sign before amount then credit is true, 
	//else if amount has - sign then credit is false (amount is debit)
	{
		this.amount = amount;
		this.id = id;
		if(amount.compareTo(new BigDecimal(0)) < 0){
			setCredit(false);
		}
		else{
			setCredit(true);
		}
	}
	public Record(){
		
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public BigDecimal amount(){
		return this.amount;
	}
	public boolean credit(){
		return this.credit;
	}
	public void setCredit(boolean credit){
		this.credit = credit;
	}
	public void setId(int id){
		this.id = id;
	}
	public int id(){
		return this.id;
	}
	public String toString(){
		//Effects: return string representation of this RecordHistory
		StringBuilder str = new StringBuilder();
		if(credit()){
			str.append("credit: ").append(amount().toString()).append(" id: ").append(this.id);
		}
		else{
			str.append("debit: ").append(amount().toString()).append(" id: ").append(this.id);
		}
		return str.toString();
	}
}
