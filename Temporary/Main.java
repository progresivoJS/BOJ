import java.util.*;
import java.io.*;
public class Main
{
    private static int n;
    private static int[] height;
    private static boolean[][] areConnected;
    private static int[] cache;
    public static void solve(int n, int[] height, boolean[][] areConnected) throws IOException
    {
        Main.n = n;
        Main.height = height;
        Main.areConnected = areConnected;
        
        cache = new int[n];
        Arrays.fill(cache, -1);
        
        int result = 0;
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++)
        {
            out.write(String.valueOf(climb(i)));
            out.write("\n");
        }
        out.close();
    }
    
    // here에서 등산할 때, 돌아 볼 수 있는 쉼터의 최대 수.
    private static int climb(int here)
    {
        if (cache[here] != -1)
            return cache[here];
            
        int result = 1;
        for (int next = 0; next < n; next++)
            if (height[here] < height[next] && areConnected[here][next])
                result = Math.max(result, 1 + climb(next));
        return cache[here] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++)
            height[i] = In.nextInt();
        boolean[][] areConnected = new boolean[n][n];
        for (int i = 0; i < m; i++)
        {
            int first = In.nextInt() - 1;
            int second = In.nextInt() - 1;
            areConnected[first][second] = areConnected[second][first] = true;
        }
        
        solve(n, height, areConnected);
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