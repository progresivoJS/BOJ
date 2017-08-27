import java.io.*;
import java.util.*;
public class Main
{
    private static int[] alchol;
    private static int n;
    private static int[][] cache;
    public static int solve(int n, int[] alchol)
    {
        Main.alchol = alchol;
        Main.n = n;
        
        cache = new int[n][3];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = Math.max(drink(0, 1), drink(1, 1));
        return result;
    }
    
    /**
     * index 번째 술을 마신다고 가정했을 때, 최댓값.
     */
    private static int drink(int index, int count)
    {
        if (index >= n)
            return 0;
            
        if (cache[index][count] != -1)
            return cache[index][count];
        
        int result = -1;
        if (count < 2)
            result = Math.max(result, alchol[index] + drink(index + 1, 2));
        result = Math.max(result, alchol[index] + drink(index + 2, 1));
        
        // 이 케이스 놓쳤었다.. 왜지? 계단 문제에서는 3칸 뛸 수 있는 것은 없었기 때문에..
        result = Math.max(result, alchol[index] + drink(index + 3, 1));
        
        return cache[index][count] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] alchol = new int[n];
        for (int i = 0; i < n; i++)
            alchol[i] = Integer.parseInt(in.readLine());
        System.out.println(solve(n, alchol));
    }
}