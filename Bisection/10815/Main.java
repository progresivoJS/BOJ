import java.util.*;
import java.io.*;

/**
 * problem 10815
 * 숫자 카드
 * https://www.acmicpc.net/problem/10815
 * written by progresivoJS on 2017.09.16
 */
public class Main
{
    private static StringBuilder str;
    public static void solve(int[] seq, int x)
    {
        if (contained(seq, x))
            str.append(1);
        else
            str.append(0);
        str.append(' ');
    }
    
    private static boolean contained(int[] seq, int x)
    {
        int lo = 0;
        int hi = seq.length - 1;
        if (seq[hi] < x || seq[lo] > x)
            return false;
        
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (seq[mid] > x)
                hi = mid - 1;
            else if (seq[mid] < x)
                lo = mid + 1;
            else
                return true;
        }
        
        return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        
        Arrays.sort(seq);
        
        int m = In.nextInt();
        for (int i = 0; i < m; i++)
            solve(seq, In.nextInt());
        
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