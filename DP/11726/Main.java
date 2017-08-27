import java.util.*;
import java.io.*;
public class Main
{
    private static int n;
    private static int[] cache;
    private static int M = 10007;
    public static int solve(int n)
    {
        Main.n = n;
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        return tile(n);
    }
    
    /**
     * 2 * col 직사각형이 있을 때 이것을 채우는 방법의 수.
     */
    private static int tile(int col)
    {
        if (col == 1)
            return 1;
        if (col == 2)
            return 2;
        
        if (cache[col] != -1)
            return cache[col];
        
        int result;
        result = tile(col-1) % M + tile(col-2) % M;
        
        return cache[col] = result % M;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        System.out.println(solve(n));
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