import java.util.*;
import java.io.*;

/**
 * problem 2869
 * 달팽이는 올라가고 싶다.
 * https://www.acmicpc.net/problem/2869
 * written by progresivoJS on 2017.09.16
 */
public class Main
{
    private static void solve(int up, int down, int v)
    {
        // 반복문 불변식
        // 1. lo일이면 < v 만큼 올라간다.
        // 2. hi일면 >= v 만큼 올라간다.
        int lo = 0;
        int hi = 1000000000 / (up-down);
        
        while (lo + 1 < hi)
        {
            int mid = (lo + hi) / 2;
            int height = 0;
            height += up * mid;
            height -= down * (mid - 1);
            if (height >= v)
                hi = mid;
            else
                lo = mid;
        }
        System.out.println(hi);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int a = In.nextInt();
        int b = In.nextInt();
        int v = In.nextInt();
        
        solve(a, b, v);
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