import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        ArrayList<ArrayList<Integer>>graph = new ArrayList<>();
        for(int i = 0;i<V;i++){
            graph.add(new ArrayList<>());
        }
        System.out.print("Enter number of edges:");
        int E = sc.nextInt();

        System.out.println("Enter vertices(u v):");
        for(int i = 0;i<E;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }

        boolean[] visited = new boolean[V];
        Stack<Integer>stack = new Stack<>();

        for(int i = 0;i<V;i++){
            if(!visited[i]){
                dfsTopo(graph,i,visited,stack);
            }
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();
    }

    static void dfsTopo(ArrayList<ArrayList<Integer>>graph,int u,boolean[]visited,Stack<Integer>stack){
        visited[u] = true;
        ArrayList<Integer>neighbors = graph.get(u);
        for(int i = 0;i< neighbors.size();i++){
            int v = neighbors.get(i);
            if(!visited[v]){
                dfsTopo(graph, v, visited, stack);
            }
        }
        stack.push(u);

    }
}
