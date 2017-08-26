import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    private static int[][] color;
    private static int n;
    private static final int r = 0;
    private static final int g = 1;
    private static final int b = 2;
    
    private static int[][] cache;
    
    public static int solve(int[][] color)
    {
        Main.color = color;
        n = color.length;
        
        cache = new int[n][3];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = 987654321;
        result = Math.min(result, coloring(0, r));
        result = Math.min(result, coloring(0, g));
        result = Math.min(result, coloring(0, b));
        return result;
    }
    
    /**
     * index 번째 집을 rgb 색으로 색칠했을 때, 전체 최솟값.
     */
    private static int coloring(int index, int rgb)
    {
        if (index == n-1)
            return color[index][rgb];
        
        if (cache[index][rgb] != -1)
            return cache[index][rgb];
        
        int result = 987654321;
        if (rgb == r)
        {
            result = Math.min(result, color[index][rgb] + coloring(index+1, g));
            result = Math.min(result, color[index][rgb] + coloring(index+1, b));
        }
        else if (rgb == g)
        {
            result = Math.min(result, color[index][rgb] + coloring(index+1, r));
            result = Math.min(result, color[index][rgb] + coloring(index+1, b));
        }
        else if (rgb == b)
        {
            result = Math.min(result, color[index][rgb] + coloring(index+1, r));
            result = Math.min(result, color[index][rgb] + coloring(index+1, g));
        }
        
        return cache[index][rgb] = result;
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] color = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                color[i][j] = sc.nextInt();
        System.out.println(solve(color));
    }
}