import java.util.Arrays;

public class DSUQuestions{
    int[] par, size;
    int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public int findPar(int u){
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    // leetcode 684
    int[] findRedundantConnection(int[][] edges){
        int n = edges.length + 1;
        par = new int[n];
        for(int i = 0; i<n; i++){
            par[i] = i;
        }
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                par[p1] = p2;
            }
            else{
                return e;
            }
        }
        return new int[0];
    }

    // leetcode 1061

    public String smallestEquivalentString(String s1, String s2, String baseStr){
        for(int i=0;i<26;i++){
            par = new int[26];
            par[i] = i;
        }
        for(int i=0;i<s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            int p1 = findPar(c1-'a'), p2 = findPar(c2 - 'a');
            par[p1] = Math.min(p1, p2);
            par[p2] = Math.min(p1, p2);
        }

        StringBuilder sb = new StringBuilder();
        for(int j=0;j<baseStr.length(); j++){
            char ch = baseStr.charAt(j);
            int p = findPar(ch - 'a');
            char t = (char)(p + 'a');
            sb.append(t);
        }
        return sb.toString();
    }

    // leetcode 305
    public int[] numIslands2(int n, int m, int[][] positions){
        int[][] grid = new int[n][m];
        par = new int[n*m];
        for(int i=0; i < n*m; i++){
             par[i] = i;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int count = 0;
        for(int[] pos : positions){
            int i = pos[0];
            int j = pos[1];
            if(grid[i][j] == 1){
                ans.add(count);
                continue;
            }
            grid[i][j] = 1;
            count++;
            int p1 = findPar(i*m + j);
            for(int[] d : dir){
                int r = i + d[0];
                int c = j + d[1];

                if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                    int p2 = findPar(r*m + c);
                    if(p1 != p2){
                        count--;
                        par[p2] = p1;
                    }
                }
            }
            ans.add(count);
        }
        int[] res = new int[ans.size()];
        for(int k=0; k<ans.size(); k++){
            res[i] = ans.get(i);
        }
        return res;
    }

    // leetcode 839
    public boolean isSimilar(String s1, String s2){
        int count = 0;
        for(int i=0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2){
                return false;
            }
        }
        return true;
    }
     public int numSimilarGroups(String[] strs){
         int n = strs.length;
         par = new int[n];
         for(int i=0;i<n;i++){
             par[i] = i;
         }
         int noOfSet = n;
         for(int i=0; i<n; i++){
             for(int j= i+1; j<n; j++){
                 if(isSimilar(strs[i], strs[j])){
                     int p1 = findPar(i);
                     int p2 = findPar(j);

                     if(p1 != p2){
                         par[p1] = p2;
                         noOfSet--;
                     }
                 }
             }
         }
         return noOfSet;
     }
     // leetcode 1905
     
     int n,m;

     public boolean countSubIslands_dfs(int[][] grid1, int[][] grid2, int i, int j){
         grid2[i][j] = 0;
         boolean res = true;
         for(int[] d : dir){
             int r = i + d[0];
             int c = j + d[1];

             if(r>=0 && c>=0 && r<n && c<m && grid2[r][c] == 1){
                 res = countSubIslands_dfs(grid1, grid2, r, c) && res;
             }
         }
         return res && (grid1[i][j] == 1);
     }

     public int countSubIslands(int[][] grid1, int[][] grid2){
         n = grid1.length;
         m = grid1[0].length;

         int count = 0;
         for(int i=0; i<n;i++){
             for(int j=0;j<m;j++){
                 if(grid2[i][j] == 1){
                     count+= countSubIslands_dfs(grid1, grid2, i, j) ? 1 : 0;
                 }
             }
         }
         return count;
     }
}