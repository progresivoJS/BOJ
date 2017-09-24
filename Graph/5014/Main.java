import java.util.*;
import java.io.*;

/**
 * problem 5014
 * 스타트링크
 * https://www.acmicpc.net/problem/5014
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int n, goal, up, down;
    public static void solve(int f, int s, int g, int u, int d)
    {
        Main.n = f;
        Main.goal = g;
        Main.up = u;
        Main.down = d;
        
        boolean[] marked = new boolean[n + 1];
        bfs(marked, s);
    }
    
    private static void bfs(boolean[] marked, int s)
    {
        marked[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        
        int count = -1;
        while (!q.isEmpty())
        {
            int size = q.size();
            count ++;
            while (size -- > 0)
            {
                int v = q.poll();
                
                if (v == goal)
                {
                    System.out.println(count);
                    return;
                }
                
                if (v + up <= n && !marked[v + up])
                {
                    q.add(v + up);
                    marked[v + up] = true;
                }
                
                if (v - down >= 1 && !marked[v - down])
                {
                    q.add(v - down);
                    marked[v - down] = true;
                }
            }
        }
        
        System.out.println("use the stairs");
    }
    
    public static void main(String[] args)
    {
        In.init();
        int f = In.nextInt();
        int s = In.nextInt();
        int g = In.nextInt();
        int u = In.nextInt();
        int d = In.nextInt();
        
        solve(f, s, g, u, d);
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