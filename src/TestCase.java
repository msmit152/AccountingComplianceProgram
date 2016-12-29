import static org.junit.Assert.*;
import org.junit.*;
import java.math.BigDecimal;
/**@author Michael Smith*/
import java.util.ArrayList;
public class TestCase {
	@Test
	public void testAddRecord(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100),0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(-545345), 2), bs.records);
		ArrayList<Record> actual = bs.records;
		ArrayList<Record> expected = new ArrayList<Record>();
		expected.add(new Record(new BigDecimal(100),0));
		expected.add(new Record(new BigDecimal(238439843), 1));
		expected.add(new Record(new BigDecimal(-545345), 2));
		assertEquals(actual.toString(), expected.toString());
	}
	@Test
	public void testModifyRecord(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100),0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(-545345), 2), bs.records);
		a.modify(1, new BigDecimal(10), bs.records);
		ArrayList<Record> actual = bs.records;
		ArrayList<Record> expected = new ArrayList<Record>();
		expected.add(new Record(new BigDecimal(100),0));
		expected.add(new Record(new BigDecimal(10), 1));
		expected.add(new Record(new BigDecimal(-545345), 2));
		assertEquals(actual.toString(), expected.toString());
	}
	@Test
	public void testDeleteRecord(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100),0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(-545345), 2), bs.records);
		a.delete(1, bs.records);
		ArrayList<Record> actual = bs.records;
		ArrayList<Record> expected = new ArrayList<Record>();
		expected.add(new Record(new BigDecimal(100),0));
		expected.add(new Record(new BigDecimal(-545345), 2));
		assertEquals(actual.toString(), expected.toString());
	}
	@Test
	public void testHistory(){
		BalanceSheet bs = new BalanceSheet();
		bs.saveToHistory.add(new RecordHistory(true, "mike"));
		bs.saveToHistory.add(new RecordHistory(new BigDecimal(100), "mike"));
		bs.saveToHistory.add(new RecordHistory(false, "mike"));
		Auditor ad = new Auditor();
		String actual = ad.history(bs.saveToHistory);
		String expected = new StringBuilder().append("mike").append(" ").append("login").append('\n').append("mike").append(" ").
				append("credit: ").append("100").append('\n').append("mike").append(" ").append("logout").append('\n').toString();
		assertEquals(actual, expected);
	}
	@Test
	public void testFinacialDisclosure(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("mike");
		a.add(new Record(new BigDecimal(100), 0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		Shareholder s = new Shareholder("chris");
		String actual = s.financialDisclosure(bs.records);
		String expected = new StringBuilder().append("credit: ").append("100").append(" id: ").append("0").
				append('\n').append("credit: ").append("238439843").append(" id: ").append("1").append('\n').append("total assets: 238439943").toString();
		assertEquals(actual, expected);
	}
	@Test
	public void testSearchAmount(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100),0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(100), 2), bs.records);
		ArrayList<Record> actual = a.search(new BigDecimal(100), bs.records);
		ArrayList<Record> expected = new ArrayList<Record>();
		expected.add(new Record(new BigDecimal(100), 0));
		expected.add(new Record(new BigDecimal(100), 2));
		assertEquals(actual.toString(), expected.toString());
	}
	@Test
	public void testSearchTransactionType(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100), 0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(-100), 2), bs.records);
		a.add(new Record(new BigDecimal(-238439843), 3), bs.records);
		ArrayList<Record> actual = a.search(true, bs.records);
		ArrayList<Record> expected = new ArrayList<Record>();
		expected.add(new Record(new BigDecimal(100), 0));
		expected.add(new Record(new BigDecimal(238439843), 1));
		assertEquals(actual.toString(), expected.toString());
	}
	@Test
	public void testSearchId(){
		BalanceSheet bs = new BalanceSheet();
		Accountant a = new Accountant("Mike");
		a.add(new Record(new BigDecimal(100), 0), bs.records);
		a.add(new Record(new BigDecimal(238439843), 1), bs.records);
		a.add(new Record(new BigDecimal(-100), 2), bs.records);
		a.add(new Record(new BigDecimal(-238439843), 3), bs.records);
		Record actual = a.search(2, bs.records);
		Record expected = new Record(new BigDecimal(-100), 2);
		assertEquals(actual.toString(), expected.toString());
	}
}
