import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	private static int[] cache;
	private static int[] seq;
	private static int n;
	public static int solve(int[] seq)
	{
		Main.seq = seq;
		n = seq.length;
		cache = new int[n];
		Arrays.fill(cache, Integer.MAX_VALUE);
		
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++)
			result = Math.max(result, sum(i));
		return result;
	}
	
	private static int sum(int index)
	{
		if (index == n-1)
			return seq[index];
		
		if (cache[index] != Integer.MAX_VALUE)
			return cache[index];
		
		int result = seq[index];
		result = Math.max(result, seq[index] + sum(index + 1));
		return cache[index] = result;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] seq = new int[n];
		for (int i = 0; i < n; i++)
			seq[i] = sc.nextInt();
		System.out.println(solve(seq));
	}
}