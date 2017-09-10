import java.util.*;
import java.io.*;

/**
 * problem 9184
 * 신나는 함수 실행
 * https://www.acmicpc.net/problem/9184
 * written by progresivoJS
 */
public class Main
{
    private static int[][][] cache;
    public static void solve(int a, int b, int c)
    {
        int result = w(a, b, c);
        System.out.printf("w(%d, %d, %d) = %d",a, b, c, result);
        System.out.println();
    }
    
    private static int w(int a, int b, int c)
    {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        else if (a > 20 || b > 20 || c > 20)
            return w(20, 20, 20);
        
        if (cache[a][b][c] != -1)
            return cache[a][b][c];
        
        if (a < b && b < c)
            return cache[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        
        return cache[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        cache = new int[21][21][21];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);
        
        int a,b,c;
        while(true)
        {
            a = In.nextInt();
            b = In.nextInt();
            c = In.nextInt();
            if (a == -1 && b == -1 && c == -1)
                break;
            solve(a, b, c);
        }
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