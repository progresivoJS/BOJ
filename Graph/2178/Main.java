import java.util.*;
import java.io.*;

/**
 * problem 2178
 * 미로탐색
 * https://www.acmicpc.net/problem/2178
 * written by progresivoJS on 2017.09.19
 */
public class Main
{
    private static boolean[][] marked;
    public static void solve(int[][] adj)
    {
        int row = adj.length;
        int col = adj[0].length;
        
        int[][] dist = new int[row][col];
        marked = new boolean[row][col];
        bfs(adj, 0, 0);
    }
    
    private static void bfs(int[][] adj, int startRow, int startCol)
    {
        Queue<Integer> rowQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        rowQueue.add(startRow);
        colQueue.add(startCol);
        
        marked[startRow][startCol] = true;
        
        while (!rowQueue.isEmpty() && !colQueue.isEmpty())
        {
            int row = rowQueue.remove();
            int col = colQueue.remove();
            if (row + 1 )
            if (!marked[row + 1][col])
                rowQueue
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int row = In.nextInt();
        int col = In.nextInt();
        boolean[][] adj = new boolean[row][col];
        for (int i = 0; i < row; i++)
        {
            String str = In.next();
            for (int j = 0; j < col; j++)
                adj[i][j] = str.charAt(j) == 1;
        }
        
        solve(adj);
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