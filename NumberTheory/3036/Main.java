import java.util.*;
import java.io.*;

/**
 * problem 3036
 * 링
 * https://www.acmicpc.net/problem/3036
 * written by progresivoJS on 2017.09.19
 */
public class Main
{
    private static StringBuilder str;
    // express (firstRing / n) in irreducible fraction.
    public static void solve(int firstRing, int n)
    {
        int GCM = GCM(firstRing, n);
        str.append(firstRing / GCM).append('/').append(n / GCM).append('\n');
    }
    
    private static int GCM(int a, int b)
    {
        return b == 0 ? a : GCM(b, a % b);
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int n = In.nextInt();
        int firstRing = In.nextInt();
        for (int i = 1; i < n; i++)
            solve(firstRing, In.nextInt());
        System.out.println(str.toString());
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