package codes;
class TopK
{
	private static int ar[];
	private static int fr[];
	static void bubble(int arr[])
	{
		int n = arr.length;  
	    int temp = 0;  
	    for(int i=0; i < n; i++)
	    {  
	    	for(int j=1; j < (n-i); j++)
	    	{  
	    		if(arr[j-1] > arr[j])
	    		{
	    			temp = arr[j-1];  
	                arr[j-1] = arr[j];  
	                arr[j] = temp;  
	            }  
	                          
	        }  
	    }
	}
	static void custombubble(int arr[])
	{
		int n = arr.length;  
	    int temp = 0; int temp1 = 0;  
	    for(int i=0; i < n; i++)
	    {  
	    	for(int j=2; j < (n-i-1); j+=2)
	    	{  
	    		if(arr[j-1] < arr[j+1])
	    		{
	    			temp = arr[j-1];
	    			temp1 = arr[j-2];
	                arr[j-1] = arr[j+1];
	    			arr[j-2] = arr[j];
	    			arr[j+1] = temp;
	                arr[j] = temp1;  
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
