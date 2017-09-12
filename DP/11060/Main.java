import java.util.*;
import java.io.*;

/**
 * problem 11060
 * 점프 점프
 * https://www.acmicpc.net/problem/11060
 * written by progresivoJS on 2017.09.12
 */
public class Main
{
    private static int n;
    private static int[] maze;
    private static int[] cache;
    
    private static int INF = 987654321;
    
    public static void solve(int n, int[] maze)
    {
        Main.n = n;
        Main.maze = maze;
        
        cache = new int[n];
        Arrays.fill(cache, -1);
        
        int result = jump(0);
        if (result != INF)
            System.out.println(result);
        else
            System.out.println(-1);
    }
    
    /**
     * index 에서 출발 할 때, 끝까지 도달할 수 있는 최소 점프의 수
     */
    private static int jump(int index)
    {
        if (index == n - 1)
            return 0;
        if (index >= n)
            return INF;
        
        if (cache[index] != -1)
            return cache[index];
        
        int result = INF;
        for (int i = 1; i <= maze[index]; i++)
            result = Math.min(result, 1 + jump(index + i));
        return cache[index] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] maze = new int[n];
        for (int i = 0; i < n; i++)
            maze[i] = In.nextInt();
        solve(n, maze);
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