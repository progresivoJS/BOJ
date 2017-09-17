import java.util.*;
import java.io.*;

/**
 * problem 2110
 * 공유기 설치
 * https://www.acmicpc.net/problem/2110
 * written by progresivoJS on 2017.09.17
 */
public class Main
{
    private static int c;
    private static int[] coords;
    public static void solve(int c, int[] coords)
    {
        Main.c = c;
        Main.coords = coords;
        
        int lo = 0;
        int hi = 1000000000;
        
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
    
    // 공유기 사이 최소 거리를 mid로 했을 때, C개의 공유기를 N개의 집에 설치할 수 있는가?
    private static boolean ok(int mid)
    {
        int count = 0;
        int j;
        for (int i = 0; i < coords.length; i = j)
        {
            int restrict = coords[i] + mid;
            count ++;
            for (j = i + 1; j < coords.length; j++)
                if (coords[j] >= restrict)
                    break;
        }
        
        if (count >= c)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int c = In.nextInt();
        int[] coords = new int[n];
        for (int i = 0; i < n; i++)
            coords[i] = In.nextInt();
        Arrays.sort(coords);
        solve(c, coords);
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