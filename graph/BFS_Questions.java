public class BFS_Questions{

    // leetcode 785
    public boolean bipartite(int[][] graph, int src, int[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);

        int color = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size -- > 0){
                int rvtx = que.removeFirst();
                if(vis[rvtx] != -1){
                    if(color != vis[rvtx]){
                        return false;
                    }
                    continue;
                }

                vis[rvtx] = color;
                for(int v : graph[rvtx]){
                    if(vis[v] == -1){
                        que.addLast(v);
                    }
                }
            }
            color = (color + 1) % 2;
        }
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n]; // vector<int> vis(n,-1);
        Arrays.fill(vis, -1);

        for (int i = 0; i < n; i++) {
            if (vis[i] == -1 && !bipartite(graph, i, vis))
                return false;
        }

        return true;
    }

    // leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid){
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int shortestPath = 1;
        if(grid[0][0] == 1 && grid[n-1][m-1] == 1){
            return -1;
            // entry and exit is blocked.
        }
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir =  { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
        que.addLast(0);
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int idx = que.removeFirst();
                int sr = idx / m;
                int sc = idx % m;
                if(sr == n-1 && sc == m-1){
                    return shortestPath;
                }
                for(int[] d : dir){
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 0){
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            shortestPath++;
        }
        return -1;
    }

    // leetcode 542
    public int[][] updateMatrix(int[][]grid){
        if(grid.length == 0 && grid[0].length == 0){
            return grid;
        }
        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        boolean[][] vis = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 0){
                    que.addLast(i*m + j);
                    vis[i][j] = true;
                }
            }
        }

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int idx = que.removeFirst();
                int sr = idx/m;
                int sc = idx%m;

                for(int[] d : dir){
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        grid[r][c] = grid[sr][sc] + 1;
                        vis[r][c] = true;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
        return grid;
    }

    // Rotten Oranges
    
}