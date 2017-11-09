import java.util.*;
import java.io.*;

/**
 * problem 1199
 * 오일러 회로
 * https://www.acmicpc.net/problem/1199
 * written by progresivoJS on 2017.11.09
 */
public class Main
{
    private static int n;
    private static int[][] adj;

    public static void solve(int[][] adj)
    {
        Main.adj = adj;
        n = adj.length;

        boolean[] marked = new boolean[n];
        int count = 0;
        for (int v = 0; v < n; v++)
            if (!marked[v])
            {
                int size = dfs(marked, v);
                if (size > 1)
                    if (++count > 1)
                    {
                        System.out.println(-1);
                        return;
                    }
            }


        LinkedList<Integer> circuit = new LinkedList<>();
        getEulerCircuit(0, circuit);

        StringBuilder str = new StringBuilder();
        for (int i : circuit)
            str.append(i + 1).append(' ');
        str.append('\n');
        System.out.println(str);
    }

    private static int dfs(boolean[] marked, int v)
    {
        int size = 1;
        marked[v] = true;
        for (int w = 0; w < n; w++)
            if (adj[v][w] == 1 && !marked[w])
                size += dfs(marked, w);
        return size;
    }

    public static void getEulerCircuit(int v, LinkedList<Integer> circuit)
    {
        for (int w = 0; w < n; w++)
        {
            while (adj[v][w] > 0)
            {
                adj[v][w]--;
                adj[w][v]--;
                getEulerCircuit(w, circuit);
            }
        }

        circuit.add(v);
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            int degree = 0;
            for (int j = 0; j < n; j++)
            {
                int count = In.nextInt();
                degree += count;
                adj[i][j] = count;
                adj[j][i] = count;
            }

            if (degree % 2 != 0)
            {
                System.out.println(-1);
                return;
            }
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
