import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    private static int[][] tri;
    private static int n;
    private static int[][] cache;
    public static int solve(int[][] tri)
    {
        Main.tri = tri;
        n = tri.length;
        cache = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
        return triPath(0, 0);
    }
    
    /**
     * row 번째 행의 col 번째 열에서 마지막 행까지 가는데 최솟값
     */
    private static int triPath(int row, int col)
    {
        if (row == n-1)
            return tri[row][col];
        
        if (cache[row][col] != -1)
            return cache[row][col];
        
        int result = -1;
        result = Math.max(result, tri[row][col] + triPath(row+1, col));
        result = Math.max(result, tri[row][col] + triPath(row+1, col+1));
        
        return cache[row][col] = result;
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] tri = new int[n][];
        for (int i = 0; i < n; i++)
        {
            tri[i] = new int[i+1];
            for (int j = 0; j < i+1; j++)
                tri[i][j] = sc.nextInt();
        }
        
        System.out.println(solve(tri));
    }
}