import java.util.*;
import java.io.*;

/**
 * problem 10816
 * 숫자 카드 2
 * 해결안됨.
 * https://www.acmicpc.net/problem/10816
 * written by progresivoJS on 2017.09.17
 */
public class Main
{
    private static StringBuilder str;
    public static void solve(int[] seq, int target)
    {
        int upperBound = upperBound(seq, target);
        int lowerBound = lowerBound(seq, target);
        if (upperBound == -1)
            str.append(0).append(' ');
        else
           str.append(upperBound(seq, target) - lowerBound(seq, target) + 1).append(' '); 
    }
    
    // return lowerbound index.
    public static int lowerBound(int[] seq, int target)
    {
        int lo = 0;
        int hi = seq.length - 1;
        
        while (lo < hi)
        {
            int mid = (lo + hi) / 2;
            if (seq[mid] >= target)
                hi = mid;
            else
                lo = mid + 1;
        }
        
        if (seq[lo] > target)
            return -1;
        
        return lo;
    }
    
    public static int upperBound(int[] seq, int target)
    {
        int lo = 0;
        int hi = seq.length - 1;
        
        while (lo < hi)
        {
            int mid = (lo + hi + 1) / 2;
            if (seq[mid] > target)
                hi = mid - 1;
            else
                lo = mid;
        }
        
        if (seq[lo] < target)
            return -1;
        
        return lo;
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