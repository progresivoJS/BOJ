import java.util.*;
import java.io.*;
public class Main
{
    private static int[] value;
    private static int n;
    private static int[] cache;
    public static int solve(int[] value)
    {
        Main.value = value;
        n = value.length;
        
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        
        return sell(n);
    }
    
    /**
     * index개의 붕어빵이 있을 때, 최대 수익.
     */
    private static int sell(int index)
    {
        if (cache[index] != -1)
            return cache[index];
        
        int result = 0;
        for (int i = 1; i <= index; i++)
        {
            result = Math.max(result, sell(index-i) + value[i-1]);
        }
        return cache[index] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] value = new int[n];
        for (int i = 0; i < n; i++)
            value[i] = In.nextInt();
        System.out.println(solve(value));
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