// https://codeforces.com/problemset/problem/263/A
import java.util.*;
import java.io.*;
 
public class test{
    public static int solve(int[][]matrix, int r, int c){
        return Math.abs(2-r) + Math.abs(2-c);
    }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int[][] arr = new int[5][5];
        int r = -1;
        int c = -1;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                arr[i][j] = scn.nextInt();
                if(arr[i][j] == 1){
                     r = i;
                     c = j;
                }
            }
            System.out.println();
        }
        System.out.println(solve(arr,r,c));
    }
}
