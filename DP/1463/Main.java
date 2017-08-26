import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static int[] cache;
	
	public static int solve(int n)
	{
		cache = new int[n+1];
		Arrays.fill(cache, -1);
		
		return makeOne(n);
	}
	
	private static int makeOne(int n)
	{
		if (n == 3)
			return 1;
		else if (n == 2)
			return 1;
		else if (n == 1)
			return 0;
		
		if (cache[n] != -1)
			return cache[n];
		
		int result = 987654321;
		if (n % 3 == 0)
			result = Math.min(result, 1 + makeOne(n/3));
		if (n % 2 == 0)
			result = Math.min(result, 1 + makeOne(n/2));
		result = Math.min(result, 1 + makeOne(n-1));
		
		return cache[n] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(solve(n));
	}
}