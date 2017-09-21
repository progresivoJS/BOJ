import java.util.*;
import java.io.*;

/**
 * problem 1012
 * 유기농 배추
 * https://www.acmicpc.net/problem/1012
 * written by progresivoJS on 2017.09.21
 */
public class Main
{
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    private static int m, n;
    private static void solve(boolean[][] field)
    {
        m = field.length;
        n = field[0].length;
        
        int count = 0;
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++)
                if (field[i][j])
                {
                    count ++;
                    dfs(field, i, j);
                }
        System.out.println(count);
    }
    
    private static void dfs(boolean[][] field, int row, int col)
    {
        if (row >= m || row < 0 || col >= n || col < 0)
            return;
        if (!field[row][col])
            return;
            
        field[row][col] = false;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            dfs(field, newRow, newCol);
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int row = In.nextInt();
            int col = In.nextInt();
            boolean[][] field = new boolean[row][col];
            int k = In.nextInt();
            for (int j = 0; j < k; j++)
                field[In.nextInt()][In.nextInt()] = true;
                
            solve(field);
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