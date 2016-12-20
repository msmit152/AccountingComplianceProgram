import java.math.BigDecimal;
import java.util.ArrayList;
 /** A User may search records in a BalanceSheet database, 
 * request a financial disclosure, or request history of transactions within database 
 * @author Michael Smith*/
public class User {
	private String name;
	/**
	@param amount used as key to search database
	@param database is searched by amount*/
	public ArrayList<Record> search(BigDecimal amount, ArrayList<Record> database)
	//Effects: loops through RecordHistory to find all transactions with specified amount
	//return transactions
	{
		ArrayList<Record> transactions = new ArrayList<Record>();
		for(Record r : database){
			if (r.amount().equals(amount)){
				transactions.add(r);
			}
		}
		return transactions;
	}
	/**
	@param credit used as key to search database
	@param database is searched by credit*/
	public ArrayList<Record> search(boolean credit, ArrayList<Record> database)
	//Effects: loops through RecordHistory to find all transactions with specified name,
	//return transactions
	{
		ArrayList<Record> transactions = new ArrayList<Record>();
		//return all credits to database
		if(credit){
			for(Record r : database){
				if (r.credit()){
					transactions.add(r);
				}
			}
		}
		//return all debits from database
		else{
			for(Record r : database){
				if (r.credit() == false){
					transactions.add(r);
				}
			}
		}
		return transactions;	
	}	
	/**
	 * @param id used as key to search database
	 * @param database searched using id*/
	public Record search(int id, ArrayList<Record> database)
	//Effects return r in database with unique id
	{
		for(Record r : database){
			if (id == r.id()){
				return r;
			}
		}
		return null;
	}
	/**
	 * @param recordHistories used to print string representation*/
	public String history(ArrayList<RecordHistory> recordHistories)
	//return history of modifications of records in database
	{
		StringBuilder str = new StringBuilder();
		for (RecordHistory rh : recordHistories){
			str.append(rh.toString());
			str.append('\n');
		}
		return str.toString();
	}
	/**
	 * @param databse used to print string representation*/
	public String financialDisclosure(ArrayList<Record> database)
	//return representation of entire list records within database
	{
		StringBuilder str = new StringBuilder();
		for (Record r : database){
			str.append(r.toString());
			str.append('\n');
		}
		BigDecimal assets = new BigDecimal(0);
		for (Record r : database){
			assets = assets.add(r.amount());
		}
		str.append("total assets: ").append(assets);
		return str.toString();
	}
	public void setName(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
}
