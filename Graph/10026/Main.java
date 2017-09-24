import java.util.*;
import java.io.*;

/**
 * problem 10026
 * 적록색약
 * https://www.acmicpc.net/problem/10026
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int n;
    private static final char MARKED = '.';
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static void solve(char[][] map)
    {
        Main.n = map.length;
        
        // G는 없고 R, B만 있다.
        char[][] mapForBlind = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (map[i][j] == 'G')
                    mapForBlind[i][j] = 'R';
                else
                    mapForBlind[i][j] = map[i][j];
            }
        
        int count = 0;
        int countForBlind = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (map[i][j] != MARKED)
                {
                    count ++;
                    dfs(map, i, j);
                }
                
                if (mapForBlind[i][j] != MARKED)
                {
                    countForBlind ++;
                    dfs(mapForBlind, i, j);
                }
            }
        
        System.out.println(count + ' ' + countForBlind);
    }
    
    private static void dfs(char[][] map, int row, int col)
    {
        char color = map[row][col];
        map[row][col] = MARKED;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (map[newRow][newCol] == color)
                dfs(map, newRow, newCol);
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            String str = In.next();
            for (int j = 0; j < n; j++)
            {
                map[i][j] = str.charAt(j);
            }
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