import java.util.ArrayList;
public class l001{
    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    //O(2E)
    public static void display(ArrayList<Edge>[] graph, int N){
        for(int i=0; i<N; i++){
            System.out.print(i + "->");
            for(Edge e : graph[i]){
                System.out.print("("+ e.v + "," + e.w + ")");
            }
            System.out.println();
        }
    }
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v){
        for(int i=0; i<graph[u].size(); i++){
            Edge e = graph[u].get(i);
            if(e.v == v){
                return i;
            }
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v){
        int idx1 = findEdge(graph, u, v);
        if(idx1 != -1){
            graph[u].remove(idx1);
        }
        int idx2 = findEdge(graph, v, u);
        if(idx2 != -1){
            graph[v].remove(idx2);
        } 
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u){
        for(int i = graph[u].size()-1; i>=0 ; i--){
            Edge e = graph[u].get(i);
            removeEdge(graph, u, e.v);
        }
    }
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis){
        if(src == dest){
            return true;
        }
        vis[src] = true;
        boolean res = false;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                res = res || hasPath(graph, e.v, dest, vis);
            }
        }
        return res;
    }
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        return hasPath(graph, src, dest, vis);
    }
    
    public static int allPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, String psf){
        if(src == dest){
            System.out.println(psf + "" + src);
            return 1;
        }
        int count = 0;
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                count += allPath(graph, e.v, dest, vis, psf + src + "");
            }
        }
        vis[src] = false;
        return count;
    }

    public static int allPath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        return allPath(graph, src, dest, vis, "");
    }

    public static void preorder(ArrayList<Edge>[] graph, int src, int dest, String psf, boolean[] vis, int wsf){
        System.out.println(psf + "" + src + "@" + wsf);
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                preorder(graph, e.v, dest, psf + "" + src, vis, wsf + e.w);
            }
        }
        vis[src] = false;
    }

    public static void preorder(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        preorder(graph, src, dest, "", vis, 0);
    }

    public static void constructGraph(){
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N]; 
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>();
        }
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

        // removeEdge(graph, 0, 1);
        // removeVtx(graph, 0);
        // System.out.println(hasPath(graph, 0, 3));
        //System.out.println(allPath(graph, 0, 6));
        preorder(graph, 0, 6);
        display(graph, N);

    }
    public static void main(String[] args){
        constructGraph();
        
    }
}