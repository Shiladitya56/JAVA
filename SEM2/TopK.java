package codes;
class TopK
{
	private static int ar[];
	private static int fr[];
	static void bubble(int arr[])
	{
		int n = arr.length; 
	    for(int i=0; i < n; i++)
	    {  
	    	for(int j=1; j < (n-i); j++)
	    	{  
	    		if(arr[j-1] > arr[j])
	    		{
	    			arr[j-1] = arr[j-1]^arr[j];  
	    			arr[j] = arr[j-1]^arr[j];  
	    			arr[j-1] = arr[j-1]^arr[j];  
	            }  
	                          
	        }  
	    }
	}
	static void custombubble(int arr[])
	{
		int n = arr.length;
	    for(int i=0; i < n; i++)
	    {  
	    	for(int j=2; j < (n-i-1); j+=2)
	    	{  
	    		if(arr[j-1] < arr[j+1])
	    		{
	    			arr[j-1] = arr[j-1]^arr[j+1];
	    			arr[j-2] = arr[j-2]^arr[j];
	    			arr[j+1] = arr[j-1]^arr[j+1];
	    			arr[j] = arr[j-2]^arr[j];
	    			arr[j-1] = arr[j-1]^arr[j+1];
	    			arr[j-2] = arr[j-2]^arr[j];
	            }
	    		else if(arr[j-1]==arr[j+1])
	    				if(arr[j-2]<arr[j])
	    				{
	    					arr[j-1] = arr[j-1]^arr[j+1];
	    	    			arr[j-2] = arr[j-2]^arr[j];
	    	    			arr[j+1] = arr[j-1]^arr[j+1];
	    	    			arr[j] = arr[j-2]^arr[j];
	    	    			arr[j-1] = arr[j-1]^arr[j+1];
	    	    			arr[j-2] = arr[j-2]^arr[j];
	    				}
	                          
	        }  
	    }
	    fr = arr;
	}
	static void topk(int k)
	{
		bubble(ar);
		int n = ar.length;
		int fre[] = new int[n*2];
		int c = -1;
		int st = 0; int en = 0;
		while(en < n)
		{
			while(en < n && ar[en]==ar[st])
				en++;
			fre[++c] = ar[st];
			fre[++c] = en-st;
			st = en;
		}
		custombubble(fre);
		for(int i=0; i<k*2; i++)
		{
			if(fr[i+1]==0)
				break;
			System.out.println("Number = "+fr[i]+" "+"Frequency = "+fr[++i]);
		}
	}
	public static void main(String args[])
	{
		ar = new int[]{1,2,4,2,6,6,6,7,1,9,8,1,9,1,0,0,5,1,3,5,0,5};
//		ar = new int[] {1,2,3,4,5,1,1,2,3};
		topk(9);
	}
}


import java.util.InputMismatchException;
import java.util.Scanner;

class ShareTrader {
    static int maxProfit;
    static void findMaxProfit(int[] p) {
        int n = p.length;
        if (n < 2) {
            System.out.println("Not enough prices to trade.");
            return;
        }
        maxProfit = 0;
        int b1, s1, b2, s2;
        for (int i = 0; i < n; i++) {
            b1 = p[i];
            for (int j = i + 1; j < n; j++) {
                s1 = p[j];
                int pt = s1 - b1;
                b2 = p[j];
                for (int k = j + 1; k < n; k++) {
                    s2 = p[k];
                    int pt2 = s2 - b2;
                    maxProfit = Math.max(maxProfit, pt + pt2);
                }
            }
        }
    }
    public static void main(String[] a) {
        int[] p1 = {10, 22, 5, 75, 65, 80};
        findMaxProfit(p1);
        System.out.println("Input: price[] = {10, 22, 5, 75, 65, 80}");
        System.out.println("Output: " + maxProfit);
        System.out.println("Trader earns " + maxProfit + " as sum of 12 and 75");
        System.out.println("Buy at 10, sell at 22, buy at 5 and sell at 80");
        System.out.println("");
        int[] p2 = {2, 30, 15, 10, 8, 25, 80};
        findMaxProfit(p2);
        System.out.println("\nInput: price[] = {2, 30, 15, 10, 8, 25, 80}");
        System.out.println("Output: " + maxProfit);
        System.out.println("Trader earns " + maxProfit + " as sum of 28 and 72");
        System.out.println("Buy at price 2, sell at 30, buy at 8 and sell at 80");
        Scanner s = new Scanner(System.in);
        System.out.print("\nEnter the number of stock prices: ");
        int n = 0;
        try {
            n = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid number.");
            return;
        }
        int[] u = new int[n];
        System.out.println("Enter the stock prices:");
        for (int i = 0; i < n; i++) {
            try {
                u[i] = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter valid numbers for stock prices.");
                return;
            }
        }
        findMaxProfit(u);
        System.out.println("\nMaximum Profit based on input: " + maxProfit);
    }
}
