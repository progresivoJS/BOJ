import java.util.*;
import java.io.*;

/**
 * problem 2613
 * 숫자구슬
 * https://www.acmicpc.net/problem/2613
 * written by progresivoJS on 2017.09.18
 */
public class Main
{
    private static int[] bead;
    private static int m;
    
    public static void solve(int[] bead, int m)
    {
        Main.bead = bead;
        Main.m = m;
        
        int lo = 1;
        int hi = 30000;
        
        while (lo < hi)
        {
            int mid = (lo + hi) / 2;
            
            if (ok(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        
        System.out.println(lo);
        printCounts(lo);
    }
    
    private static void printCounts(int answer)
    {
        int[] index = new int[m];
        
        int group = 1;
        int maxSum = answer;
        
        for (int i = 0; i < bead.length; i++)
        {
            if (maxSum - bead[i] < 0 || bead.length - i == m - group)
            {
                maxSum = answer;
                index[group ++] = i;
            }
            maxSum -= bead[i];
        }
        
        for (int j = 0; j < index.length; j++)
        {
            if (j == 0)
                System.out.print(index[j] + " ");
            else
                System.out.print(index[j] - index[j-1] + " ");
        }
    }
    
    private static boolean ok(int mid)
    {
        for (int i = 0; i < bead.length; i++)
            if (mid < bead[i])
                return false;
        
        int count = 1;
        int maxSum = mid;
        for (int i = 0; i < bead.length; i++)
        {
            if (maxSum - bead[i] < 0)
            {
                maxSum = mid;
                count ++;
            }
            
            maxSum -= bead[i];
        }
        
        if (count <= m)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt(); // 구슬 수
        int m = In.nextInt(); // 그룹 수
        int[] bead = new int[n]; // 각 구슬에 새겨진 숫자.
        for (int i = 0; i < n; i++)
            bead[i] = In.nextInt();
        
        solve(bead, m);
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