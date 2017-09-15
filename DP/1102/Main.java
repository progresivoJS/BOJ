import java.util.*;
import java.io.*;

/**
 * problem 1102
 * 발전소
 * https://www.acmicpc.net/problem/1102
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    private static int[][] cost;
    private static int n, p;
    private static int[] cache;
    
    private static int INF = 987654321;
    
    public static void solve(int[][] cost, boolean[] isOn, int p)
    {
        Main.cost = cost;
        Main.p = p;
        n = cost.length;
        
        cache = new int[1 << n];
        Arrays.fill(cache, -1);
        
        int isOnBitMask = 0;
        for (int i = 0; i < isOn.length; i++)
            if (isOn[i])
                isOnBitMask = (isOnBitMask) | (1 << i);
        
        int result = fix(isOnBitMask);
        if (result < INF)
            System.out.println(result);
        else
            System.out.println(-1);
    }
    
    private static int fix(int isOnBitMask)
    {
        if (Integer.bitCount(isOnBitMask) >= p)
            return 0;
        
        if (cache[isOnBitMask] != -1)
            return cache[isOnBitMask];
        
        int result = INF;
        for (int i = 0; i < n; i++)
        {
            // 안고쳐진 애들 중에.
            if ((isOnBitMask & (1 << i)) == 0)
                for (int j = 0; j < n; j++)
                {
                    // 고쳐진 애들 중에.
                    if ((isOnBitMask & (1 << j)) > 0)
                        result = Math.min(result, cost[j][i] + fix(isOnBitMask | (1 << i)));
                }
        }
        
        return cache[isOnBitMask] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        
        int n = In.nextInt();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = In.nextInt();
        boolean[] isOn = new boolean[n];
        String str = In.next();
        for (int i = 0; i < n; i++)
            isOn[i] = str.charAt(i) == 'Y';
        int p = In.nextInt();
        
        solve(cost, isOn, p);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}
