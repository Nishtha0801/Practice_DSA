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

    public static void postorder(ArrayList<Edge>[] graph, int src, int dest, String psf, boolean[] vis, int wsf){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                postorder(graph, e.v, dest, psf + "" + src, vis, wsf + e.w);
            }
        }
        System.out.println(psf + "" + src + "@" + wsf);
        vis[src] = false;
    }

    public static void postorder(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        postorder(graph, src, dest, "", vis, 0);
    }

    public static class Pair{
        int wsf = 0;
        String psf = "";

        Pair(int wsf, String psf){
            this.wsf = wsf;
            this.psf = psf;
        }
    }

    public static Pair HeavyPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis){
        if(src == dest){
            return new Pair(0, src+"");
        }
        vis[src] = true;
        Pair myAns = new Pair(-1, "");
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                Pair recAns = HeavyPath(graph, e.v, dest, vis);
                if(recAns.wsf != -1 && recAns.wsf + e.w > myAns.wsf){
                    myAns.wsf = recAns.wsf + e.w;
                    myAns.psf = src +""+recAns.psf;
                }
            } 
        }
        vis[src] = false;
        return myAns;
    }

    public static Pair HeavyPath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        return HeavyPath(graph, src, dest, vis);
    }

    public static Pair LightestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis){
        if(src == dest){
            return new Pair(0, src+"");
        }
        vis[src] = true;
        Pair myAns = new Pair((int)1e9, "");
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                Pair recAns = LightestPath(graph, e.v, dest, vis);
                if(recAns.wsf != (int)1e9 && recAns.wsf + e.w < myAns.wsf){
                    myAns.wsf = recAns.wsf + e.w;
                    myAns.psf = src +""+recAns.psf;
                }
            } 
        }
        vis[src] = false;
        return myAns;
    }

    public static Pair LightestPath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] vis = new boolean[graph.length];
        return LightestPath(graph, src, dest, vis);
    }

    public static void HamintonianPathAndCycle(ArrayList<Edge>[] graph, int src, int osrc, boolean[] vis, int edgeCount, int N, String psf){
        if(edgeCount == N-1){
            int idx = findEdge(graph, src, osrc);
            if(idx != -1){
                System.out.println(psf + "" + src + "*");
                return;
            }
            System.out.println(psf + "" + src);
        }
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                HamintonianPathAndCycle(graph, e.v, osrc, vis, edgeCount + 1, N, psf + "" + src);
            }
        }
        vis[src] = false;
        return;
    }
    public static void HamintonianPathAndCycle(ArrayList<Edge>[] graph, int src, int dest, int N){
        boolean[] vis = new boolean[graph.length];
        HamintonianPathAndCycle(graph, src, dest, vis, 0, N, "");
    }

    public static void gcc_dfs(ArrayList<Edge>[] graph, boolean[] vis, int src){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                gcc_dfs(graph, vis, e.v);
            }
        }
        return;
    }

    public static int getConnectedComponents(ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[graph.length];
        int components = 0;
        for(int i=0; i< graph.length; i++){
            if(!vis[i]){
                gcc_dfs(graph, vis, i);
                components++;
            }
        }
        return components;
    }

    public static void constructGraph(){
        int N = 9;
        // int N = 7;
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
        // addEdge(graph, 0, 6, 2);
        // addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // removeEdge(graph, 0, 1);
        // removeVtx(graph, 0);
        // System.out.println(hasPath(graph, 0, 3));
        //System.out.println(allPath(graph, 0, 6));
        // preorder(graph, 0, 6);
        //postorder(graph, 0, 6);
        // Pair ans = HeavyPath(graph, 0, 6);
        // System.out.println(ans.psf + "@" + ans.wsf);
        // Pair ans = LightestPath(graph, 0, 6);
        // System.out.println(ans.psf + "@" + ans.wsf);
        // HamintonianPathAndCycle(graph, 0, 0, N);
        System.out.println(getConnectedComponents(graph));
        //display(graph, N);

    }
    public static void main(String[] args){
        constructGraph();
        
    }
}