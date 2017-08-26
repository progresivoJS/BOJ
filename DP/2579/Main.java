import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    private static int[] seq;
    private static int n;
    private static int[][] cache;
    public static int solve(int[] seq)
    {
        Main.seq = seq;
        n = seq.length;
        cache = new int[n+1][3];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        int result = stair(0, 1);
        result = Math.max(result, stair(1, 1));
        return result;
    }
    
    private static int stair(int index, int count)
    {
        if (index == n-1)
            return seq[index];
        
        if (cache[index][count] != -1)
            return cache[index][count];
            
        int result = -1;
        if (index + 1 <= n-1 && count < 2)
            result = Math.max(result, seq[index] + stair(index + 1, count + 1));
        if (index + 2 <= n-1)
            result = Math.max(result, seq[index] + stair(index + 2, 1));
        
        return cache[index][count] = result;
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

/**
 * 계단 초기 조건때문에 좀 헤맸다. 문제 이해가 부족했음.
 * 그리고 부분문제 설정을 좀 더 직관적으로 할 필요가 있다.
 */