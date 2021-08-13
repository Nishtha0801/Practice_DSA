import java.util.ArrayList;
import java.util.LinkedList;
public class l002_directed{
    public static class Edge{
        int v, w;
        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, v, w){
        graph[u].add(new Edge(v,w));
    }

    // O(2E) == O(E)
    public static void display(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void topo_DFS(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> ans){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                topo_DFS(e.v, graph, vis, ans);
            }
        }
        ans.add(src); // post order m add kro -- pehle children resolved firr parent.
    }

    public static void topologicalOrder(int n, ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<N; i++){
            if(!vis[i]){
                topo_DFS(i, graph, vis, ans);
            }
        }
        for(int i=ans.size();i>=0;i--){
            System.out.println(ans.get(i));
        }
    }

    public static ArrayList<Integer> kahnsAlgo(int n, ArrayList<Edge>[] graph){
        int[] indegree = new int[n];
        for(ArrayList<Edge> edgesList : graph){
            for(Edge e : edgesList){
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<n; i++){
            if(indegree[i] == 0){
                que.addLast(i);
            }
        }

        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int vtx = que.removeFirst();
                ans.add(vtx);
                for(Edge e : graph[vtx]){
                    if(--indegree[e.v] == 0){
                        que.addLast(e.v);
                    }
                }
            }
            level++;
        }
        if(ans.size() != n){
            System.out.println("Topological Order is not posiible due to cycle");

        }
        return ans;
    }
    public static void kahnsAlgo_01(int n, ArrayList<Edge>[] graph) {
        int[] indegree = new int[n];
        for (ArrayList<Edge> edgesList : graph) {
            for (Edge e : edgesList) {
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                smallAns.add(vtx);

                for (Edge e : graph[vtx]) {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }

            }
            ans.add(smallAns);
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Topological Order is not possible due to Cycle");
            ans.clear();
        }
    }

    // detect Cycle
    public static boolean TopoDFS(int src, int[]vis, ArrayList<Integer> ans){
        vis[src] = 1;
        for(Integer e : graph[src]){
            if(vis[e] == 0){
                if(TopoDFS(e,vis, ans)){
                    return true;
                }
                
            } else if(vis[e] == 1){
                return true; // Cycle detected - because It is visited as well as part of my path.
            }
        }
        ans.add(src);
        vis[src] = 2; // visited but not a part of my path.
        return false;
    }

    public static void TopoDFS(){
        ArrayList<Integer> ans = new ArrayList<>();
        int[] vis = new boolean[N];
        boolean isCycle = false;
        for(int i=0;i<N;i++){
            if(vis[i] == 0){
                if(TopoDFS(i,vis,ans)){
                    isCycle = true;
                    break;
                }
            }
        }
        if(!isCycle){
            System.out.println(ans);
        } else{
            System.out.println("Cycle");
        }
    }

    public static void constructGraph() {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);

        addEdge(graph, 2, 7, 2);
        addEdge(graph, 2, 8, 4);
        addEdge(graph, 7, 8, 3);

        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        addEdge(graph, 0, 6, 3);

        boolean[] vis = new boolean[V];
    }

    public static void main(String[] args) {
        constructGraph();
    }
}