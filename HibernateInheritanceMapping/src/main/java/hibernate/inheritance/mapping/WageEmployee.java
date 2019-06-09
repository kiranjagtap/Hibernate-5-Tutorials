package hibernate.inheritance.mapping;

public class WageEmployee extends Employee {

	int sales;
	double commission;

	public WageEmployee() {
	}

	public WageEmployee(int sales, double commission) {
		super();
		this.sales = sales;
		this.commission = commission;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

}
