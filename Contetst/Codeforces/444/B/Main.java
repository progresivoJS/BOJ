import java.util.*;
import java.io.*;

public class Main
{
    private static boolean[] marked;
    
    private static void solve(int[][] cube)
    {
        marked = new boolean[1000];
        process(cube);
        
        int index = 0;
        for (int i = 1; i < 1000; i++)
        {
            if (!marked[i])
            {
                index = i - 1;
                break;
            }
        }
        
        System.out.println(index);
    }
    
    private static void process(int[][] cube)
    {
        int m = cube.length;
        int n = cube[0].length;
        
        // 한자릿 수.
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                marked[cube[i][j]] = true;
        
        if (m < 2)
            return;
        // 두자릿 수.
        for (int row1 = 0; row1 < m; row1++)
        {
            for (int col1 = 0; col1 < n; col1++)
            {
                for (int row2 = row1 + 1; row2 < m; row2++)
                {
                    for (int col2 = 0; col2 < n; col2++)
                    {
                        int a = cube[row1][col1];
                        int b = cube[row2][col2];
                        
                        marked[a * 10 + b] = true;
                        marked[b * 10 + a] = true;
                    }
                }
            }
        }
        
        // 세자릿 수.
        if (m < 3)
            return;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    int a = cube[0][i];
                    int b = cube[1][j];
                    int c = cube[2][k];
                    
                    marked[a * 100 + b * 10 + c] = true;
                    marked[a * 100 + c * 10 + b] = true;
                    marked[b * 100 + a * 10 + c] = true;
                    marked[b * 100 + c * 10 + a] = true;
                    marked[c * 100 + a * 10 + b] = true;
                    marked[c * 100 + b * 10 + a] = true;
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] cube = new int[n][6];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 6; j++)
                cube[i][j] = In.nextInt();
        solve(cube);
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