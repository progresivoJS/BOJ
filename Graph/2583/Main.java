import java.util.*;
import java.io.*;

/**
 * problem 2583
 * 영역 구하기
 * https://www.acmicpc.net/problem/2583
 * written by progresivoJS on 2017.09.23
 */
public class Main
{
    private static final int BLOCKED = 1;
    private static final int NOT_BLOCKED = 0;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int m, n;
    
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        LinkedList<Integer> district = new LinkedList<>();
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
            {
                if (map[i][j] == NOT_BLOCKED)
                {
                    int result = dfs(map, i, j);
                    district.add(result);
                }
            }
        
        StringBuilder str = new StringBuilder();
        str.append(district.size()).append('\n');
        Collections.sort(district);
        for (int i : district)
            str.append(i).append(' ');
        
        System.out.println(str.toString());
    }
    
    private static int dfs(int[][] map, int row, int col)
    {
        map[row][col] = BLOCKED;
        int count = 1;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (map[newRow][newCol] == NOT_BLOCKED)
                count += dfs(map, newRow, newCol);
        }
        
        return count;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        int[][] map = new int[m][n];
        int k = In.nextInt();
        
        for (int i = 0; i < k; i++)
        {
            int left = In.nextInt();
            int bottom = In.nextInt();
            int right = In.nextInt();
            int top = In.nextInt();
            
            for (int j = left; j < right; j++)
                for (int l = bottom; l < top; l++)
                    map[l][j] = BLOCKED;
        }
        
        solve(map);
    }
    
    public static class Point
    {
        int row, col;
        
        public Point(int row, int col)
        {
            this.row = row;
            this.col = col;
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