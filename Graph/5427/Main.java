import java.util.*;
import java.io.*;

/**
 * problem 4963
 * 섬의 개수
 * https://www.acmicpc.net/problem/4963
 * written by progresivoJS on 2017.09.28
 */
public class Main
{
    private static final int PERSON = 0;
    private static final int FIRE = 1;
    private static final int WALL = 2;
    private static final int PASSABLE = 3;

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int m, n;

    public static void solve(int[][] map, LinkedList<Point> fires, Point startPoint)
    {
        m = map.length;
        n = map[0].length;

        // marked[PERSON][j][k] = PERSON이 j행 k열을 방문했습니까?
        // marked[FIRE][j][k] = FIRE가 j행 k열을 방문했습니까?
        boolean[][][] marked = new boolean[2][m][n];
        bfs(map, fires, marked, startPoint);
    }

    private static void bfs(int[][] map, LinkedList<Point> fires, boolean[][][] marked, Point startPoint)
    {
        Queue<Point> q = new LinkedList<>();

        // 불 먼저 추가
        for (Point s : fires)
        {
            q.add(s);
            marked[FIRE][s.row][s.col] = true;
        }

        // 사람 추가
        q.add(startPoint);
        marked[PERSON][startPoint.row][startPoint.col] = true;

        int second = 1;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();

                for (int i = 0; i < 4; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];

                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                    {
                        if (!v.isPerson)
                            continue;
                        else
                        {
                            System.out.println(second);
                            return;
                        }
                    }

                    if (!marked[v.isPerson ? PERSON : FIRE][newRow][newCol] && map[newRow][newCol] == PASSABLE)
                    {
                        marked[v.isPerson ? PERSON : FIRE][newRow][newCol] = true;
                        q.add(new Point(newRow, newCol, v.isPerson));
                        if (!v.isPerson)
                            map[newRow][newCol] = FIRE;
                    }
                }
            }

            second ++;
        }
        System.out.println("IMPOSSIBLE");
    }

    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            int[][] map = new int[m][n];
            LinkedList<Point> fires = new LinkedList<>();
            Point startPoint = new Point(0, 0, true);
            for (int i = 0; i < m; i++)
            {
                String str = In.next();
                for (int j = 0; j < n; j++)
                {
                    char ch = str.charAt(j);
                    if (ch == '#')
                        map[i][j] = WALL;
                    else if (ch == '*')
                    {
                        fires.add(new Point(i, j, false));
                        map[i][j] = FIRE;
                    }
                    else if (ch == '@')
                    {
                        startPoint = new Point(i, j, true);
                        map[i][j] = PASSABLE;
                    }
                    else
                        map[i][j] = PASSABLE;
                }
            }

            solve(map, fires, startPoint);
        }
    }

    public static class Point
    {
        int row, col;
        boolean isPerson;
        public Point(int row, int col, boolean isPerson)
        {
            this.row = row;
            this.col = col;
            this.isPerson = isPerson;
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
