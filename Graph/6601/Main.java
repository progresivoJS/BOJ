import java.util.*;
import java.io.*;

/**
 * problem 6601
 * Knight Moves
 * https://www.acmicpc.net/problem/6601
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    private static final int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static int m, n;

    private static StringBuilder str;
    
    public static void solve(String from, String to)
    {
        m = 8;
        n = 8;

        Point start = new Point(from.charAt(1) - '1', from.charAt(0) - 'a');
        Point target = new Point(to.charAt(1) - '1', to.charAt(0) - 'a');

        Queue<Point> q = new LinkedList<>();
        q.add(start);

        boolean[][] marked = new boolean[m][n];

        int move = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                if (v.row == target.row && v.col == target.col)
                {
                    str.append("To get from ").append(from).append(" to ")
                    .append(to).append(" takes ").append(move).append(" knight moves.").append('\n');
                    return;
                }

                for (int i = 0; i < 8; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];

                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    if (!marked[newRow][newCol])
                    {
                        q.add(new Point(newRow, newCol));
                        marked[newRow][newCol] = true;
                    }
                }
            }
            move ++;
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        str = new StringBuilder();

        String line;
        while ((line = In.nextLine()) != null)
        {
            StringTokenizer st = new StringTokenizer(line);
            String from = st.nextToken();
            String to = st.nextToken();
            
            solve(from, to);
        }

        System.out.println(str);
    }

    private static class Point
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
        }

        public static String nextLine() throws IOException
        {
            return br.readLine();
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
