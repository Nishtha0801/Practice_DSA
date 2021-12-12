// https://codeforces.com/problemset/problem/69/A
import java.util.*;
import java.io.*;

public class test{
    public static String YoungPhysicist(int n, int[][]vectors){
        int x=0;
        int y=0;
        int z=0;
        for (int[] vec : vectors){
            x+=vec[0];
            y+=vec[1];
            z+=vec[2];
        }
        if(x==0 && y==0 && z==0){
            return "YES";
        }
        return "NO";
    }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n][3];
        for(int i=0;i<n;i++){
            for(int j=0;j<3;j++){
                arr[i][j] = scn.nextInt();
            }
            System.out.println();
        }
        System.out.println(YoungPhysicist(n, arr));
    }
}
