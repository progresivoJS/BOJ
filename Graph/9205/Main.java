import java.util.*;
import java.io.*;

/**
 * problem 9205
 * 맥주 마시면서 걸어가기
 * https://www.acmicpc.net/problem/9205
 * written by progresivoJS on 2017.10.10
 */
public class Main
{
    private static StringBuilder str;
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        
        boolean[] marked = new boolean[n];
        dfs(adj, 0, marked);
        
        str.append(marked[n-1] ? "happy" : "sad").append('\n');
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v, boolean[] marked)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w, marked);
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            n += 2;
            
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            Point[] point = new Point[n];
            
            for (int i = 0; i < n; i++)
                point[i] = new Point(In.nextInt(), In.nextInt());
            
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    int distance = point[i].distanceTo(point[j]);
                    if (distance <= 1000)
                    {
                        adj[i].add(j);
                        adj[j].add(i);
                    }
                }
            
            solve(adj);
        }
        
        System.out.println(str.toString());
    }
    
    public static class Point
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        public int distanceTo(Point p)
        {
            return Math.abs(this.y - p.y) + Math.abs(this.x - p.x);
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