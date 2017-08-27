import java.util.*;
import java.io.*;
public class Main
{
    private static int n, k, finalTech;
    private static int[] delay;
    private static boolean[][] tech;
    private static int[] cache;
    public static int solve(int n, int k, int[] delay, boolean[][] tech, int finalTech)
    {
        Main.n = n;
        Main.k = k;
        Main.delay = delay;
        Main.tech = tech;
        Main.finalTech = finalTech;
        
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        
        return build(finalTech);
    }
    
    /**
     * building 번 건물을 짓는데 걸리는 최소시간.
     */
    private static int build(int building)
    {
        if (cache[building] != -1)
            return cache[building];
        
        int nextDelay = -1;
        int next = -1;
        for (int i = 1; i < delay.length; i++) // < 주목
            if (tech[i][building])
            {
                if (delay[i] > nextDelay)
                {
                    nextDelay = delay[i];
                    next = i;
                }
            }
        if (next == -1)
            return delay[building];
        
        int result = delay[building] + build(next);
        return cache[building] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int k = In.nextInt();
            int[] delay = new int[n+1];
            for (int j = 1; j <= n; j++)
                delay[j] = In.nextInt();
            boolean[][] tech = new boolean[n+1][n+1];
            for (int j = 0; j < k; j++)
                tech[In.nextInt()][In.nextInt()] = true;
            int finalTech = In.nextInt();
            
            System.out.println(solve(n, k, delay, tech, finalTech));
        }
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            // br = new BufferedReader(new InputStreamReader(System.in));
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