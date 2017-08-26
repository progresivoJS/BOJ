import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static int one, zero;
	private static int[][] cache;
	public static void solve(int n)
	{
		cache = new int[n+1][2];
		
		for (int i = 0; i < cache.length; i++)
			for (int j = 0; j < cache[i].length; j++)
				cache[i][j] = -1;
		int[] result = fibo(n);
		System.out.print(result[0] + " " + result[1]);
		System.out.println();
	}
	
	private static int[] fibo(int n)
	{
		int[] result;
		if (n == 0)
		{
			result = new int[2];
			result[0] = 1;
			result[1] = 0;
			return result;
		}
		else if (n == 1)
		{
			result = new int[2];
			result[0] = 0;
			result[1] = 1;
			return result;
		}
		
		if (cache[n][0] != -1)
			return cache[n];
		
		result = new int[2];
		result[0] = fibo(n-1)[0] + fibo(n-2)[0];
		result[1] = fibo(n-1)[1] + fibo(n-2)[1];
		
		return cache[n] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int i = 0; i < test; i++)
		{
			int n = sc.nextInt();
			solve(n);
		}
	}
}