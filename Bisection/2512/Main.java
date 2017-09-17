import java.util.*;
import java.io.*;

/**
 * problem 2512
 * 예산
 * https://www.acmicpc.net/problem/2512
 * written by progresivoJS on 2017.09.16
 */
public class Main
{
    private static int n;
    private static int[] budget;
    private static int totalBudget;
    private static void solve(int[] budget, int totalBudget)
    {
        Main.n = budget.length;
        Main.budget = budget;
        Main.totalBudget = totalBudget;
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, budget[i]);
            
        int lo = 0;
        int hi = max;
        
        while (lo < hi)
        {
            int mid = (lo + hi + 1) / 2;
            if (ok(mid))
                lo = mid;
            else
                hi = mid - 1;
        }
        
        System.out.println(lo);
    }
        
    private static boolean ok(int mid)
    {
        int need = 0;
        for (int i = 0; i < budget.length; i++)
            if (budget[i] > mid)
                need += mid;
            else
                need += budget[i];
        
        if (need > totalBudget)
            return false;
        else
            return true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] budget = new int[n];
        for (int i = 0; i < n; i++)
            budget[i] = In.nextInt();
        int totalBudget = In.nextInt();
        
        solve(budget, totalBudget);
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