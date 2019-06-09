package hibernate.inheritance.mapping;

public class SalesEmployee extends Employee {

	int hrs;
	int rates;

	public SalesEmployee() {
	}

	public SalesEmployee(int hrs, int rates) {
		super();
		this.hrs = hrs;
		this.rates = rates;
	}

	public int getHrs() {
		return hrs;
	}

	public void setHrs(int hrs) {
		this.hrs = hrs;
	}

	public int getRates() {
		return rates;
	}

	public void setRates(int rates) {
		this.rates = rates;
	}

}
