import java.util.*;
import java.io.*;

/**
 * problem 2667
 * 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 * written by progresivoJS on 2017.09.21
 */
public class Main
{
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int id, n;
    public static void solve(int[][] field)
    {
        StringBuilder str = new StringBuilder();
        n = field.length;
        id = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (field[i][j] == 0)
                {
                    id ++;
                    dfs(field, i, j);
                }
        str.append(id).append('\n');
        int[] count = new int[id];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (field[i][j] > 0)
                    count[field[i][j] - 1] ++;
        Arrays.sort(count);
        for (int i = 0; i < count.length; i++)
            str.append(count[i]).append('\n');
        
        System.out.println(str.toString());
    }
    
    private static void dfs(int[][] field, int v, int w)
    {
        field[v][w] = id;
        
        for (int i = 0; i < 4; i++)
        {
            int newRow = v + dy[i];
            int newCol = w + dx[i];
            if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            if (field[newRow][newCol] == 0)
                dfs(field, newRow, newCol);
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] field = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            String str = In.next();
            for (int j = 0; j < n; j++)
                field[i][j] = str.charAt(j) - '0' - 1;
        }
        
        solve(field);
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