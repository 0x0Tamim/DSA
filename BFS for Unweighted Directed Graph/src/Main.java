import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();;
        ArrayList<ArrayList<Integer>>graph = new ArrayList<>();

        for(int i = 0;i<V;i++){
            graph.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        System.out.println("Enter edges(u v): ");
        for (int i = 0;i<E;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }
            System.out.print("Enter starting vertex: ");
            int start = sc.nextInt();
            boolean[] visited = new boolean[V];

            bfs(graph,start,visited);

    }

    static void bfs(ArrayList<ArrayList<Integer>>graph,int start,boolean[]visited){
        Queue<Integer>q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        System.out.print("BFS order: ");

        while (!q.isEmpty()){
            int u = q.poll();
            System.out.print(u+" ");

            ArrayList<Integer>neighbors = graph.get(u);
            for(int i = 0;i<neighbors.size();i++){
                int v = neighbors.get(i);

                if(!visited[v]){
                    q.add(v);
                    visited[v] = true;
                }
            }
        }
        System.out.println();
    }
}
