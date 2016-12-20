import java.math.BigDecimal;
import java.util.ArrayList;
 /**Accountant is employee for shareholders who keeps track of and modifies records
 * within database. He/She may add, delete, or modify record
 * @author Michael Smith*/
public class Accountant extends User{
	public Accountant(String name){
		super.setName(name);
	}
	public Accountant(){
		
	}
	public void setName(String name){
		super.setName(name);
	}

	/**@param record added to database
	 * @param database adds new record*/
	public void add(Record record, ArrayList<Record> database)
	//Effects: adds record to database
	{
		database.add(record);
	}
	/**
	@param record is replaced with newRecord 
	@param newRecord replaces record
	@param database is modified*/
	public void modify(int id, BigDecimal amount, ArrayList<Record> database)
	//Modifies: database
	//Effects: replaces record with newRecord
	{
		for(Record r : database){
			if (r.id() == id){
				r.setAmount(amount);
			}
		}
	} 
	/**
	@param record is removed from database
	@param database is modified*/
	public void delete(int id, ArrayList<Record> database)
	//Modifies: database
	//Effects: removes record from database
	{
		for(Record r : database){
			if (r.id() == id){
				database.remove(r);
			}
		}
	}
}
