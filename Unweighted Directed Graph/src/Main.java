import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();

        for(int i = 0;i<V;i++){
            mat.add(new ArrayList<>());
        }
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges(u v):");
        for (int i = 0;i<E;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();

            AddEdge(mat,u,v);
        }

        printGraph(mat);


    }

    static void AddEdge(ArrayList<ArrayList<Integer>>adj,int u, int v){
        adj.get(u).add(v);
    }

    static void printGraph(ArrayList<ArrayList<Integer>>adj){
        for(int i = 0;i<adj.size();i++){
            System.out.print(i+" -> ");
            for(int j = 0;j<adj.get(i).size();j++){
                System.out.print(adj.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
}
