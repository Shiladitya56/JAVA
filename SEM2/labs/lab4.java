package codes;

abstract class Robber
{
	abstract int Rowhouses(int x[]);
	abstract int Roundhouses(int x[]);
	abstract int Squarehouse(int x[]);
	abstract int Multihouse(int x[]);
	void RobbingClass()
	{
		System.out.println("MScAIML");
	}
	void MachineLearning()
	{
		System.out.println("I love Machine Learning");
	}
}

public class JavaProfessionalRobber
{
	int common(int x[])
	{
		if(x.length!=4)
		{
			System.out.println("Only 4 houses accepted!");
			return -1;
		}
		int a = x[0]+x[2];
		int b = x[1]+x[3];
		return a>b?a:b;
	}
	int Squarehouse(int x[])
	{
		return common(x);
	}
	int Rowhouses(int x[])
	{
		return common(x);
	}
	int Roundhouses(int x[])
	{
		return common(x);
	}
	int Multihouse(int x[])
	{
		return common(x);
	}
	public static void main(String[] args)
	{
		JavaProfessionalRobber ob = new JavaProfessionalRobber();
		int a[] = {5,2,6,8};
		System.out.println("For square houses: "+ob.Squarehouse(a));
		System.out.println("For round houses: "+ob.Roundhouses(a));
		System.out.println("For row houses: "+ob.Rowhouses(a));
		System.out.println("For multi houses: "+ob.Multihouse(a));
	}
}
