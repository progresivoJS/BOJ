import java.util.*;
import java.io.*;

/**
 * problem 1987
 * 알파벳
 * https://www.acmicpc.net/problem/1987
 * written by progresivoJS on 2017.09.26
 */
public class Main
{
    private static int m, n;
    private static boolean[][] marked;
    private static boolean[] alphabet;
    private static int count = 0;
    
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        marked = new boolean[m][n];
        alphabet = new boolean[26];
        
        System.out.println(dfs(map, 0, 0));
    }
    
    private static int dfs(int[][] map, int row, int col)
    {
        alphabet[map[row][col]] = true;
        
        int count = 1;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (!alphabet[map[newRow][newCol]])
                count = Math.max(count, 1 + dfs(map, newRow, newCol));
        }
        
        alphabet[map[row][col]] = false;
        
        return count;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int row = In.nextInt();
        int col = In.nextInt();
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++)
        {
            String str = In.next();
            for (int j = 0; j < col; j++)
                map[i][j] = str.charAt(j) -'A';
        }
        
        solve(map);
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