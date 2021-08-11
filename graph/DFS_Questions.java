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

        // Journey To The Moon
        public static int dfs(int src, ArrayList<ArrayList<Integer>> graph, boolean[] vis){
            int size = 1;
            vis[src] = true;
            for(int v : graph[src]){
                if(!vis[v]){
                    size+= dfs(v, graph, vis);
                }
            }
            return size;
        }
        public static long journeyToMoon(int n, int[][] edges){
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N);
            for(int[] e : edges){
                graph[e[0]].add(e[1]);
                graph[e[1]].add(e[0]); 
            }
            boolean[] vis = new boolean[n];
            long sum = 0, ans = 0;
            for(int i=0;i<n;i++){
                if(!vis[i]){
                    int size = dfs(i, graph, vis);
                    ans += size*sum;
                    sum+=size;
                }
            }
            return ans;
        }
       
        // 207 
        boolean canFinish(int N, int[][] prerequisites){
            ArrayList<Integer>[] graph = new ArrayList[N];
            int[] indegree = new int[N];
            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
            }
            for(int[] e : prerequisites){
                graph[e[0]].add(e[1]);
                indegree[e[1]]++;
            }
            LinkedList<Integer> que = new LinkedList<>();
            for(int i=0;i<N;i++){
                if(indegree[i]==0){
                    que.addLast(i);
                }
            }

            int vtxCount = 0;
            while(que.size() != 0){
                int vtx = que.removeFirst();
                vtxCount++;
                for(int v : graph[vtx]){
                    if(--indegree[v] == 0){
                        que.addLast(v);
                    }
                }
            }
            return vtxCount == N;
        }

        public int[] findOrder(int N, int[][] prerequisites) {
            ArrayList<Integer>[] graph = new ArrayList[N];
            int[] indegree = new int[N];
            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
            }
            for(int[] e : prerequisites){
                graph[e[1]].add(e[0]);
                indegree[e[0]]++;
            }
            LinkedList<Integer> que = new LinkedList<>();
            for(int i=0;i<N;i++){
                if(indegree[i]==0){
                    que.addLast(i);
                }
            }
            
            ArrayList<Integer> ans = new ArrayList<>();
            while(que.size() != 0){
                int vtx = que.removeFirst();
                ans.add(vtx);
                for(int v : graph[vtx]){
                    if(--indegree[v] == 0){
                        que.addLast(v);
                    }
                }
            }
            if(ans.size() != N){
                return new int[0];
            }
            int[] res = new int[ans.size()];
            for(int i=0;i<ans.size();i++){
                res[i] = ans.get(i);
            }
            return res;
        }
}