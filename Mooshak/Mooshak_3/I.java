// I: Pai Natal insuflável com escada
import java.util.*;

class Edge {
    private int enode;
    
    Edge(int endv){
	enode = endv;
    }

    public int endnode() {
	return enode;
    }

}


class Node {
    //private int label;
    private LinkedList<Edge> neighbours;

    Node() {
	neighbours  = new LinkedList<Edge>();
    }

    public LinkedList<Edge> adjs() {
	return neighbours;
    }
   
}


class Graph0 {
    private Node verts[];
    private int nverts, nedges;
			
    public Graph0(int n) {
	nverts = n;
	nedges = 0;
	verts  = new Node[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new Node();
        // vertex labels are integers from 1 to n (position 0 is not used)
    }
    
    public int num_vertices(){
	return nverts;
    }

    public int num_edges(){
	return nedges;
    }

    public LinkedList<Edge> adjs_no(int i) {
	return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j){
	verts[i].adjs().addFirst(new Edge(j));
        nedges++;
    }

    public Edge find_edge(int i, int j){
	for (Edge adj: adjs_no(i))
	    if (adj.endnode() == j) return adj;
	return null;
    }
}


public class I {

    public static int[] bfs(Graph0 g, int v) {
        boolean[] visited = new boolean[g.num_vertices() + 1];
        int[] dist = new int[g.num_vertices() + 1];
        
        visited[v] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        dist[v] = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(Edge adj : g.adjs_no(cur)) {
                int neighbor = adj.endnode();
                if (!visited[neighbor]) {
                    q.add(neighbor);
                    visited[neighbor] = true;
                    dist[neighbor] = dist[cur] + 1;
                }
            }
            
        }
        return dist;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        Graph0 g = new Graph0(n);

        int[] pais = new int[n + 1];
        for(int i = 1; i <= n; i++) {pais[i] = stdin.nextInt();}

        int r = stdin.nextInt();
        for (int i = 0; i < r; i++) {
            int o = stdin.nextInt();
            int d = stdin.nextInt();
            g.insert_new_edge(o, d);
            g.insert_new_edge(d, o);
        }

        int p = stdin.nextInt();
        int k = stdin.nextInt();

        if (pais[p] != 0) {
            System.out.println("Que sorte");
        } else {
            int[] dist = bfs(g, p);
            int counter = 0;
            for (int v = 1; v <= g.num_vertices(); v++) {
                if (dist[v] > 0 && dist[v] <= k && pais[v] > 0) {counter += 1;}
            }
            System.out.println(counter);
        }
    }
}