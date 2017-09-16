import java.util.*;
import java.io.*;

/**
 * problem 1920
 * 수 찾기
 * https://www.acmicpc.net/problem/1920
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
        str.append('\n');
    }
    
    // 정렬된 seq 안에 x가 있습니까?
    private static boolean contained(int[] seq, int x)
    {
        // lo <= x < hi
        int lo = 0;
        int hi = seq.length - 1;
        
        if (seq[hi] < x)
            return false;
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (x < seq[mid])
                hi = mid - 1;
            else if (x > seq[mid])
                lo = mid + 1;
            else
                return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        str = new StringBuilder();
        
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < seq.length; i++)
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