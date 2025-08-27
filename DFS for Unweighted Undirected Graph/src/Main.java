import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices:");
        int V = sc.nextInt();
        ArrayList<ArrayList<Integer>>graph = new ArrayList<>();
        for (int i = 0;i<V;i++){
            graph.add(new ArrayList<>());
        }
        System.out.print("Enter number of edges:");
        int E = sc.nextInt();

        System.out.println("Enter egdes (u v):");
        for (int i = 0;i<E;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }

        System.out.print("Enter starting vertex:");
        int start = sc.nextInt();

        boolean[] visited = new boolean[V];
        System.out.print("DFS order: ");
        dfs(graph,start,visited);
        System.out.println();

    }

    static void dfs(ArrayList<ArrayList<Integer>>graph, int start, boolean[]visited){

        visited[start] = true;
        System.out.print(start+" ");
        ArrayList<Integer>neighbors = graph.get(start);
        for (int i = 0;i< neighbors.size();i++){
            int v = neighbors.get(i);
            if(!visited[v]){
                dfs(graph,v,visited);

            }
        }

    }
}
