package codes;

public class MaxProfit
{
	static void sort(int array[])
	{
	    int l = array.length;
	    for (int step = 1; step < l; step++)
	    {
	      int k = array[step];
	      int j = step - 1;
	      while (j >= 0 && k > array[j])
	      {
	        array[j + 1] = array[j];
	        --j;
	      }
	      array[j + 1] = k;
	    }
	  }
	public static void main(String[] args)
	{
		int arr[] = {32, 56, 17, 60, 75, 29, 44, 81, 93};
		sort(arr);
		int trans1 = arr[0]-arr[arr.length-1];
		int trans2 = arr[1]-arr[arr.length-2];
		System.out.println("The maximum profit generated today is... "+
	    (trans1+trans2));
	}
}