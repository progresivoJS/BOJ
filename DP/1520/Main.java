import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] cache;
    private static int row, col;
    private static int[][] matrix;
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static int solve(int[][] matrix)
    {
        Main.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;

        cache = new int[row][col];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);

        return move(0, 0);
    }

    /**
     * row, col 에서 출발한다고 가정 했을 때, 가장 오른쪽 아래 지점까지 내리막길로만 가는 경우의 수.
     */
    private static int move(int x, int y)
    {
        if (x == row - 1 && y == col - 1)
            return 1;

        if (cache[x][y] != -1)
            return cache[x][y];

        int result = 0;
        int currentHeight = matrix[x][y];

        for (int i = 0; i < 4; i++)
        {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX < row && newY < col && newX >= 0 && newY >= 0)
                if (currentHeight > matrix[newX][newY])
                    result += move(newX, newY);
        }

        return cache[x][y] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int row = In.nextInt();
        int col = In.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0;j < col; j++)
                matrix[i][j] = In.nextInt();
        System.out.println(solve(matrix));
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
