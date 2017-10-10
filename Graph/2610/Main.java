import java.util.*;
import java.io.*;

/**
 * problem 2610
 * 회의준비
 * https://www.acmicpc.net/problem/2610
 * written by progresivoJS on 2017.10.10
 */
public class Main
{
    private static int INF = 987654321;
    
    public static void solve(int[][] dist, LinkedList<Integer>[] adj)
    {
        int n = dist.length;
        
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        // name id.
        boolean[] marked = new boolean[n];
        int count = 0;
        int[] id = new int[n];
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(adj, v, marked, id, count++);
        
        // count relay
        int[] relay = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (dist[i][j] != INF)
                    relay[i] = Math.max(relay[i], dist[i][j]);
        
        // election
        int[] represent = new int[count];
        Arrays.fill(represent, -1);
        for (int i = 0; i < n; i++)
            if (represent[id[i]] == -1 || relay[i] < relay[represent[id[i]]])
                represent[id[i]] = i;
        
        Arrays.sort(represent);
        
        StringBuilder str = new StringBuilder();
        str.append(count).append('\n');
        for (int i : represent)
            str.append(i + 1).append('\n');
        System.out.println(str.toString());
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v, boolean[] marked, int[] id, int count)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w, marked, id, count);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i == j) dist[i][j] = 0;
                else        dist[i][j] = INF;
                
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
            
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            dist[from][to] = dist[to][from] = 1;
            
            adj[from].add(to);
            adj[to].add(from);
        }
        
        solve(dist, adj);
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