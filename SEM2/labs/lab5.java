//Program 2 (done before program 1)
package codes;
public class CityBlockConservation
{
	public static void main(String args[])
	{
		int arr[] = {5,11,4,9,2,7};
		int l = -1; int f=1,t=0,water=-1;
		int len = arr.length;
		for(int i = 0; i < len; i++)
			if(arr[i] > l)
				l = arr[i];
		int mat[][] = new int[l][len];
		for(int j = 0; j < len; j++)
			for(int i = l-1; i >= 0; i--)
				if(arr[j]>0)
				{
					mat[i][j] = 0;
					arr[j]--;
				}
				else
					mat[i][j] = 1;
		
		for(int i = 0; i < l; i++)
		{
			for(int j = 0; j < len; j++)
//				if(mat[i][j]==0)
					System.out.print(mat[i][j]+" ");
//				else
//					System.out.print("  ");
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < l; i++)
		{
//			c = 0;
//			while(mat[i][c]==1 && mat[i][c+1]==1)
//			{
//				mat[i][c] = 0;
//				c++;
//			}
			for(int j = 0; j < len; j++)
			{
				if(mat[i][j]==0)
				{
					f *= -1;
					continue;
				}
				t += mat[i][j];
				if(f==1)
				{
//					System.out.println("hehe "+t);
					water += t;
					t = 0;
				}
			}
		}
		System.out.println(water);
	}
}

//---------------------------------------------------------------------------------------------------------------
//Program 1
import java.util.Scanner;
interface BankInterface {
    double getBalance();
    double getInterestRate();
}
class BankA implements BankInterface {
    private double balance;
    BankA(double depositAmount) {
        this.balance = depositAmount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
    @Override
    public double getInterestRate() {
        return 0.07; // 7% interest rate
    }
}
class BankB implements BankInterface {
    private double balance;
    BankB(double depositAmount) {
        this.balance = depositAmount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
    @Override
    public double getInterestRate() {
        return 0.074; // 7.4% interest rate
    }
}
class BankC implements BankInterface {
    private double balance;
    BankC(double depositAmount) {
        this.balance = depositAmount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
    @Override
    public double getInterestRate() {
        return 0.079; // 7.9% interest rate
    }
}

public class Main1 {
    public static void main(String[] args) {
        // Test cases
        BankA bankA = new BankA(10000);
        BankB bankB = new BankB(150000);
        BankC bankC = new BankC(200000);
        // Display balance and interest rate for Bank A
        System.out.println("Bank A - Balance: Rs." + bankA.getBalance() + " | Interest Rate: " + String.format("%.2f", bankA.getInterestRate() * 100) + "%");
        // Display balance and interest rate for Bank B
        System.out.println("Bank B - Balance: Rs." + bankB.getBalance() + " | Interest Rate: " + String.format("%.2f", bankB.getInterestRate() * 100) + "%");
        // Displaying balance and interest rate for Bank C
        System.out.println("Bank C - Balance: Rs." + bankC.getBalance() + " | Interest Rate: " + String.format("%.2f", bankC.getInterestRate() * 100) + "%");
        //user input
        Scanner scanner = new Scanner(System.in);
        // Example of taking input for Bank A
        System.out.print("Enter deposit amount for Bank A: ");
        double depositAmountA = scanner.nextDouble();
        BankA userBankA = new BankA(depositAmountA);
        System.out.println("Bank A - Balance: Rs." + userBankA.getBalance() + " | Interest Rate: " + String.format("%.2f", userBankA.getInterestRate() * 100) + "%");
        //for Bank B
        System.out.print("Enter deposit amount for Bank B: ");
        double depositAmountB = scanner.nextDouble();
        BankB userBankB = new BankB(depositAmountB);
        System.out.println("Bank B - Balance: Rs." + userBankB.getBalance() + " | Interest Rate: " + String.format("%.2f", userBankB.getInterestRate() * 100) + "%");
        //for Bank C
        System.out.print("Enter deposit amount for Bank C: ");
        double depositAmountC = scanner.nextDouble();
        BankC userBankC = new BankC(depositAmountC);
        System.out.println("Bank C - Balance: Rs." + userBankC.getBalance() + " | Interest Rate: " + String.format("%.2f", userBankC.getInterestRate() * 100) + "%");
        scanner.close();
    }
}
