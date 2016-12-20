import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.*;
 /** A BalanceSheet maintains a list of records in a database and a history of all transactions that have taken
 * place within the database. 
 * @author Michael Smith */
public class BalanceSheet {
	protected ArrayList<Record> records = new ArrayList<Record>();
	protected ArrayList<RecordHistory> saveToHistory = new ArrayList<RecordHistory>();
	private int id = 0;
	public int id(){
		return this.id;
	}
	/**@param user
	 * @param scan
	 * */
	public void search(User user, Scanner scan)
	//Effects: user may search for specific record with id key, or may
	//search for all transactions of type debit/credit, or may search for all transactions
	//of particular amount
	{
		System.out.println("search by 'type', 'amount', or 'id'");
		String search = scan.nextLine();
		try{
			if(search.equalsIgnoreCase("type")){
				System.out.println("Enter 'credits' or 'debits'");
				String type = scan.nextLine();
				//search for all credits in records
				if(type.equalsIgnoreCase("credits")){
					System.out.println(user.search(true, this.records).toString());
				}
				//search for all debits in records
				else if(type.equalsIgnoreCase("debits")){
					System.out.println(user.search(false, this.records).toString());
				}
				else{
					System.out.println("Wrong input");
					return;
				}
			}
			else if(search.equalsIgnoreCase("amount")){
				System.out.println("Enter amount");
				BigDecimal amount = scan.nextBigDecimal();
				System.out.println(user.search(amount, this.records).toString());
			}
			else if(search.equalsIgnoreCase("id")){
				System.out.println("Enter id");
				int transactionId = scan.nextInt();
				System.out.println(user.search(transactionId, this.records).toString());
			}	
		}catch(InputMismatchException e){
			System.out.println("Wrong input");
			return;
		}
	}
	/**
	 * If user modifies or deletes any records in database, print "Warning: whoever alters, destroys, mutilates, conceals, covers up, falsifies, or makes a false entry in any record, document, or tangible object with the intent to impede,"
	 * obstruct, or influence any Federal investigation shall be fined $5,000,000 and imprisoned not more than 20 years, or both."
	 * @param scan*/
	public void accountant(Scanner scan)
	//Effects: after each transaction (whether login, logout, add, modify, delete), save to history
	{
		System.out.println("Name?");
		String name = scan.nextLine();
		Accountant accountant = new Accountant(name);
		//record login
		saveToHistory.add(new RecordHistory(true, name));
		while(true){
			System.out.println("add, modify, delete, search, or logout?");
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("add")){
				try{
					
					System.out.println("Enter amount");
					BigDecimal amount = scan.nextBigDecimal();
					scan.nextLine();
					accountant.add(new Record(amount, this.id++), this.records);
					saveToHistory.add(new RecordHistory(amount, name));
				}catch(InputMismatchException e){
					System.out.println("Invalid input");
					break;
				}
			}
			else if(line.equalsIgnoreCase("modify")){
				System.out.println("Warning: whoever alters, destroys, mutilates, conceals, covers up, falsifies, "
						+ "or makes a false entry in any record, document, or tangible object with the intent to impede,"
						+ " obstruct, or influence any Federal investigation shall be fined $5,000,000 and imprisoned not"
						+ " more than 20 years, or both. Proceed...");
				try{
					System.out.println("Enter record ID number");
					int id = scan.nextInt();
					scan.nextLine();
					System.out.println("Change amount to...");
					BigDecimal amount = scan.nextBigDecimal();
					scan.nextLine();
					accountant.modify(id, amount, this.records);
					saveToHistory.add(new RecordHistory(amount, name));
				}catch(Exception e){
					System.out.println("Wrong input");
					break;
				}
			}
			else if(line.equalsIgnoreCase("delete")){
				System.out.println("Warning: whoever alters, destroys, mutilates, conceals, covers up, falsifies, "
						+ "or makes a false entry in any record, document, or tangible object with the intent to impede,"
						+ " obstruct, or influence any Federal investigation shall be fined $5,000,000 and imprisoned not"
						+ " more than 20 years, or both. Proceed...");
				System.out.println("Enter record ID number");
				try{
					int id = scan.nextInt();
					scan.nextLine();
					accountant.delete(id, this.records);
					saveToHistory.add(new RecordHistory(name, id));
				}catch(Exception e){
					System.out.println("Wrong input");
					break;
				}
			}
			else if(line.equalsIgnoreCase("search")){
				search(accountant, scan);
			}
			else if(line.equalsIgnoreCase("logout")){
				//record logout
				saveToHistory.add(new RecordHistory(false, name));
				return;
			}
		}
	}
	
	/**
	 * If user requests financial disclosure, print "Financial disclosure report must not contain any untrue statement of a material fact or omit to state a material "
	 * fact necessary in order to make the statements made, in light of the circumstances under which such statements were made, not misleading"
	 * @param scan*/
	public void auditor(Scanner scan)
	//Effects: after each transaction (whether login, logout, add, modify, delete), save to history
	{
		User auditor = new Auditor();
		System.out.println("Name?");
		String name = scan.nextLine();	
		//record login
		saveToHistory.add(new RecordHistory(true, name));
		while(true){
			System.out.println("history, search, financial disclosure, or logout?");
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("history")){
				System.out.println(auditor.history(saveToHistory));
			}
			else if(line.equalsIgnoreCase("search")){
				search(auditor, scan);
			}
			else if(line.equalsIgnoreCase("logout")){
				//record logout
				saveToHistory.add(new RecordHistory(false, name));
				return;
			}
			
			else if(line.equalsIgnoreCase("financial disclosure")){
				System.out.println("Financial disclosure report must not contain "
						+ "any untrue statement of a material fact or omit to state a material "
						+ "fact necessary in order to make the statements made, in light of the circumstances "
						+ "under which such statements were made, not misleading");
				System.out.println(auditor.financialDisclosure(this.records));
			}
			else{
				System.out.println("Wrong input, try again");
			}
		}
	}
	/**
	 * If user requests financial disclosure, print "Financial disclosure report must not contain any untrue statement of a material fact or omit to state a material "
	 * fact necessary in order to make the statements made, in light of the circumstances under which such statements were made, not misleading"
	 * @param scan*/
	public void shareholder(Scanner scan)
	//Effects: after each transaction (whether login, logout, add, modify, delete), save to history
	{
		User shareholder = new Shareholder();
		//record login
		System.out.println("Name?");
		String name = scan.nextLine();	
		saveToHistory.add(new RecordHistory(true, name));
		while(true){
			System.out.println("financial disclosure, or logout?");
			String line = scan.nextLine();
			if(line.equalsIgnoreCase("financial disclosure")){
				System.out.println("Financial disclosure report must not contain "
						+ "any untrue statement of a material fact or omit to state a material "
						+ "fact necessary in order to make the statements made, in light of the circumstances "
						+ "under which such statements were made, not misleading");
				System.out.println(shareholder.financialDisclosure(this.records));
			}
			else if(line.equalsIgnoreCase("logout")){
				//record logout
				saveToHistory.add(new RecordHistory(false, name));
				return;
			}
			else{
				System.out.println("Wrong input, try again");
			}
		}
	}
	public static void main(String[] args){
		BalanceSheet bs = new BalanceSheet();
		while (true){
			Scanner scan = new Scanner(System.in);
			System.out.println("Login: enter 'accountant', 'auditor', or 'shareholder'");
			String user = scan.nextLine();
			if(user.equalsIgnoreCase("accountant")){
				bs.accountant(scan);
			}
			else if(user.equalsIgnoreCase("auditor")){
				bs.auditor(scan);
			}
			else if(user.equalsIgnoreCase("shareholder")){
				bs.shareholder(scan);
			}
			else{
				System.out.print("Wrong input, try again");
			}
		}
	}
}
