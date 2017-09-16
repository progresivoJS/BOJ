import java.util.*;
import java.io.*;

/**
 * problem 2805
 * 나무 자르기
 * https://www.acmicpc.net/problem/2805
 * written by progresivoJS on 2017.09.16
 */
public class Main
{
    // 나무 높이 seq 배열이 주어질 때, m 길이 이상 만큼을 자르기 위한 높이의 최댓값.
    private static int maxHeight(int[] seq, int m)
    {
        int lo = 0;
        int hi = 1000000000;
        
        while (lo + 1 < hi)
        {
            int mid = (lo + hi) / 2;
            long cut = 0;
            for (int i = 0; i < seq.length; i++)
            {
                if (seq[i] > mid)
                    cut += seq[i] - mid;
                if (cut >= m)
                    break;
            }
            if (cut < m)
                hi = mid;
            else
                lo = mid;
        }
        return lo;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        
        System.out.println(maxHeight(seq, m));
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