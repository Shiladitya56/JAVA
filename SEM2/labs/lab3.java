package codes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Employeee
{
	int id;
	String name, desig;
	Employeee()
	{
		id = -1;
		name = "unavailable";
		desig = "unavailable";
	}
	protected void set(int id, String name, String desig)
	{
		this.id = id;
		this.name = name;
		this.desig = desig;
	}
	void get()
	{
		System.out.println("Employee Details...");
		System.out.println("Name        : "+name);
		System.out.println("ID          : "+id);
		System.out.println("Designation : "+desig);
	}
	void bonus()
	{
		System.out.print("The bonus is calculated ");
	}
}

class HourlyEmployee extends Employeee
{
	int worked; double rate;
	HourlyEmployee()
	{
		super();
	}
	void set(int id, String name, String desig, double rate)
	{
		super.set(id, name, desig);
		this.rate = rate;
	}
	void weekly()
	{
		System.out.println("This week's earning is : "+(rate*worked));
	}
	@Override void bonus()
	{
		System.out.println("weekly as : "+(rate*worked*0.25));
	}
}

class SalariedEmployee extends Employeee
{
	double salary;
	SalariedEmployee()
	{
		super();
	}
	void set(int id, String name, String desig, double salary)
	{
		super.set(id, name, desig);
		this.salary = salary;
	}
	void weekly()
	{
		System.out.println("Weekly earning : "+(salary/4));
	}
	@Override void bonus()
	{
		super.bonus();
		System.out.println("monthly as : "+(salary*0.25));
	}
}

class ExecutiveEmployee extends SalariedEmployee
{
	double bonusper;
	ExecutiveEmployee()
	{
		super();
		this.bonusper = -1;
	}
	void set(int id, String name, double salary, double bonusper)
	{
		super.set(id, name, "Executive", salary);
		this.bonusper = bonusper;
	}
	@Override void bonus()
	{
		System.out.println("The annual bonus is : "+bonusper);
	}
}

public class Payroll
{
	static BufferedReader x = new BufferedReader (new InputStreamReader(System.in));
	static void what()
	{
		System.out.println("What do you want to do?");
		System.out.println("1. Create");
		System.out.println("2. Retrive");
		System.out.print("Make selection... ");
	}
	
	static void exemp() throws IOException
	{
		ExecutiveEmployee em = new ExecutiveEmployee();
		System.out.print("1. Enter name : ");
		String name = x.readLine();
		System.out.println("2. Enter ID : ");
		int id = Integer.parseInt(x.readLine());
		System.out.println("3. Enter salary : ");
		String salary = x.readLine();
		System.out.println("Creating Salaried Employee...");
		em.set(id, name, salary);
		System.out.println("Employee created successfully!");
	}
	
	static void hoemp() throws IOException
	{
		HourlyEmployee em = new HourlyEmployee();
		System.out.print("1. Enter name : ");
		String name = x.readLine();
		System.out.println("2. Enter ID : ");
		int id = Integer.parseInt(x.readLine());
		System.out.println("3. Enter rate : ");
		double rate = Double.parseDouble(x.readLine());
		System.out.println("Creating Hourly Employee...");
		em.set(id, name,"Hourly", rate);
		System.out.println("Employee created successfully!");
	}
	
	static void salemp() throws IOException
	{
		SalariedEmployee em = new SalariedEmployee();
		System.out.print("1. Enter name : ");
		String name = x.readLine();
		System.out.println("2. Enter ID : ");
		int id = Integer.parseInt(x.readLine());
		System.out.println("3. Enter salary : ");
		double salary = Double.parseDouble(x.readLine());
		System.out.println("Creating Salaried Employee...");
		em.set(id, name, "Salaried", salary);
		System.out.println("Employee created successfully!");
	}

	public static void main(String args[]) throws IOException
	{
		int c;
		System.out.println("Enter Employee id, name and designation...");
		System.out.println("Which type of employee are we going forward with?");
		System.out.println("1. Executive Employee");
		System.out.println("2. Monthy Employee");
		System.out.println("3. Hourly Employee");
		System.out.print("Make selection... ");
		c = Integer.parseInt(x.readLine());
		switch(c)
		{
		case 1:
			exemp();
			break;
		case 2:
			salemp();
			break;
		case 3:
			hoemp();
			break;
		default:
			System.out.print("Bad choice!");
		}
		x.close();
	}
}
