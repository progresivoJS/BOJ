import java.util.*;
import java.io.*;

/**
 * problem 2157
 * 여행
 * https://www.acmicpc.net/problem/2157
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static int n, m;
    private static int[][] content;
    private static int[][] cache;
    
    private static int INF = 987654321;
    
    public static void solve(int n, int m, int[][] content)
    {
        Main.n = n;
        Main.m = m;
        Main.content = content;
        
        cache = new int[n][m + 1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        System.out.println(travel(0, 1));
    }
    
    /**
     * 현재까지 count 개의 도시를 여행을 했고, (here, last] 를 여행할 때 만족값의 최댓값.
     */
    private static int travel(int here, int count)
    {
        if (here == n-1)
            return 0;
        
        if (cache[here][count] != -1)
            return cache[here][count];
        
        int maxContent = -INF;
        for (int next = here + 1; next < n; next ++)
            // 길이 존재할 때.
            if (content[here][next] != 0 && count < m)
                maxContent = Math.max(maxContent, content[here][next] + travel(next, count + 1));
        
        return cache[here][count] = maxContent;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int k = In.nextInt();
        
        // row 에서 col 갈때의 만족도. (항상 1 이상)
        // 만족도가 0 이면 길이 없는 것.
        int[][] content = new int[n][n];
        for (int i = 0; i < k; i++)
        {
            int row = In.nextInt() - 1;
            int col = In.nextInt() - 1;
            int candidate = In.nextInt();
            if (content[row][col] < candidate)
                content[row][col] = candidate;
        }
        
        solve(n, m, content);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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