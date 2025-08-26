import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        ArrayList<ArrayList<int[]>>mat = new ArrayList<>();

        for(int i = 0;i<V;i++){
            mat.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges u v and weight w(u v w)");
        for(int i = 0;i<E;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();;
            int w = sc.nextInt();
            AddEdgeWithWeight(mat,u,v,w);
        }

        printGraph(mat);

    }

    static void AddEdgeWithWeight(ArrayList<ArrayList<int[]>>adj,int u,int v,int w){
        adj.get(u).add(new int[] {v,w});
        adj.get(v).add(new int[]{u,w});

    }

    static void printGraph(ArrayList<ArrayList<int[]>>adj){
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(i + " --> ");
            for (int j = 0; j < adj.get(i).size(); j++) {
                int[] edge = adj.get(i).get(j);
                System.out.print("(" + edge[0] + ", w=" + edge[1] + ")");
                if (j < adj.get(i).size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }
}
