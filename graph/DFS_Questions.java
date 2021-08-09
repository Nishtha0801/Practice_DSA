public class DFS_Questions{
    // leetcode 200 -- number of islands
    public void numIslands_DFS(char[][] grid, int i, int j, int[][] dir){
        grid[i][j] = '0';
        for(int d=0;d<dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c] == '1'){
                numIslands_DFS(grid, r, c, dir);
            }
        } 
    }
    public int numIslands(char[][] grid) {
        int ans = 0;
        int[][] dir = {{0,1}, {1,0}, {-1, 0}, {0, -1}};
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == '1'){
                    numIslands_DFS(grid, i, j, dir);
                    ans++;
                }
            }
        }
        return ans;
    }
    // leetcode 695
    public int maxAreaOfIsland_DFS(int[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = 0;
        int area = 0;
        for(int d = 0; d<4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c] == 1){
                area += maxAreaOfIsland_DFS(grid, r, c, dir);
            }
        }
         return area+1;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = {{0,1}, {1,0}, {-1, 0}, {0, -1}};
        int maxArea = 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0; i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    int area =  maxAreaOfIsland_DFS(grid, i, j, dir);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }
    // leetcode 463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ones = 0;
        int nbrs = 0;
        int[][] dir = {{1,0}, {0,1}, {0,-1}, {-1,0}};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    ones++;
                    for(int d=0; d<4; d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                        nbrs++;
                    }
                  } 
                }  
            }
        }
        return 4*ones - nbrs;
    }

    public void dfs_surrounded(char[][]grid, int i, int j, int[][] dir){
        int n = grid.length;
        int m = grid[0].length;
        grid[i][j] = '$';
        for(int d=0; d<4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r>=0 && c>=0 && r<n && c<m && grid[r][c]='O'){
                dfs_surrounded(grid, r, c, dir);
            }
        }
    }

    public void solve(char[][] grid){
        int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i == 0 || j == 0 || i == n-1 || j == m-1){
                    if(grid[i][j] == 'O'){
                        dfs_surrounded(grid, i, j, dir);
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 'O'){
                    grid[i][j] = 'X';
                }
                else if(grid[i][j] == '$'){
                    grid[i][j] = 'O';
                }
            }
        }
    }
}