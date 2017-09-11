import java.util.*;
import java.io.*;

/**
 * problem 1074
 * Z
 * https://www.acmicpc.net/problem/1074
 * written by progresivoJS
 */
public class Main
{
    private static int r, c;
    
    public static int solve(int n, int r, int c)
    {
        Main.r = r;
        Main.c = c;
        
        return DQ(n, r, c) - 1;
    }
    
    private static int DQ(int n, int row, int col)
    {
        if (n == 0)
            return 1;
            
        int halfDimension = (int) Math.pow(2, n-1);
        int count = 0;
        int quater = halfDimension * halfDimension;
        
        if (row < halfDimension && col < halfDimension)
        {
            count += DQ(n-1, row, col);
        }
        else if (row < halfDimension && col >= halfDimension)
        {
            count += quater * 1;
            count += DQ(n-1, row, col - halfDimension);
        }
        else if (row >= halfDimension && col < halfDimension)
        {
            count += quater * 2;
            count += DQ(n-1, row - halfDimension, col);
        }
        else if (row >= halfDimension && col >= halfDimension)
        {
            count += quater * 3;
            count += DQ(n-1, row - halfDimension, col - halfDimension);
        }
        
        return count;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int r = In.nextInt();
        int c = In.nextInt();
        
        System.out.println(solve(n, r, c));
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