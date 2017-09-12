import java.util.*;
import java.io.*;

/**
 * problem 11729
 * 하노이 탑 이동 순서
 * https://www.acmicpc.net/problem/11729
 * written by progresivoJS on 2017.09.12
 */
public class Main
{
    private static int count;
    private static StringBuilder s;
    public static void solve(int n)
    {
        count = 0;
        s = new StringBuilder();
        DQ(n, 1, 3, 2);
        System.out.println(count);
        System.out.print(s);
    }
    
    private static void DQ(int n, int from, int to, int via)
    {
        if (n == 0)
            return;
        DQ(n-1, from, via, to);
        count++;
        s.append(from).append(" ").append(to).append("\n");
        DQ(n-1, via, to, from);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt(); 
        solve(n);
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
