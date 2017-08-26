import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    private static int[][] cache;
    public static int solve(int n, int m)
    {
        cache = new int[n+1][m+1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        return breakChoco(n, m);
    }
    
    private static int breakChoco(int n, int m)
    {
        if (n == 1 && m == 2)
            return 1;
        else if (n == 2 && m == 1)
            return 1;
        else if (n == 1 && m == 1)
            return 0;
        
        if (cache[n][m] != -1)
            return cache[n][m];
        
        int result = 987654321;
        for (int i = 1; i < n; i++)
            result = Math.min(result, 1 + breakChoco(i, m) + breakChoco(n-i, m));
        for (int j = 1; j < m; j++)
            result = Math.min(result, 1 + breakChoco(n, j) + breakChoco(n, m-j));
        return cache[n][m] = result;
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(solve(n, m));
    }
}