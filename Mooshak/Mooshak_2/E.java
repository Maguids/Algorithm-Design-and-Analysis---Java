// E: Halloween
import java.util.*;

class Edge0 {
    private int enode;
    
    Edge0(int endv){
	enode = endv;
    }

    public int endnode() {
	return enode;
    }

}

class Node0 {
    //private int label;
    private LinkedList<Edge0> neighbours;

    Node0() {
	neighbours  = new LinkedList<Edge0>();
    }

    public LinkedList<Edge0> adjs() {
	return neighbours;
    }
   
}

class Graph0 {
    private Node0 verts[];
    private int nverts, nedges;
			
    public Graph0(int n) {
	nverts = n;
	nedges = 0;
	verts  = new Node0[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new Node0();
    }
    
    public int num_vertices(){
	return nverts;
    }

    public int num_edges(){
	return nedges;
    }

    public LinkedList<Edge0> adjs_no(int i) {
	return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j){
	    verts[i].adjs().addFirst(new Edge0(j));
        nedges++;
    }

    public Edge0 find_edge(int i, int j){
	for (Edge0 adj: adjs_no(i))
	    if (adj.endnode() == j) return adj;
	return null;
    }
}

// ----------------------------------------------------------------

class E {

    public static int visit_nodes(Graph0 g, int n, int v, int[] q) {
        boolean[] visited = new boolean[n+1];
        for(int i = 0; i <= v; i++) {visited[i] = false;}
        Queue<Integer> fila = new LinkedList<>();
        visited[v] = true;
        fila.add(v);
        int min = q[v-1];
        int imin = v;
        while (!fila.isEmpty()) {
            int cur = fila.poll();
            if (q[cur-1] == min && cur < imin) {imin = cur;} 
            if (q[cur-1] > min) {
                imin = cur;
                min = q[cur-1];
            }
            for (Edge0 adj : g.adjs_no(cur)) {
                int neighboor = adj.endnode();
                if (!visited[neighboor]) {
                    visited[neighboor] = true;
                    fila.add(neighboor);
                }
            }
        }  
        return imin;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();    // nós da rede
        Graph0 g = new Graph0(n);

        int[] quant = new int[n+1];
        for (int i = 0; i < n; i++) {
            quant[i] = stdin.nextInt();
        }

        int r = stdin.nextInt();
        for(int i = 0; i < r; i++) {
            int o = stdin.nextInt();
            int d = stdin.nextInt();
            g.insert_new_edge(o,d);
            g.insert_new_edge(d,o);
        }

        int k = stdin.nextInt();
        for (int i = 0; i < k; i++) {
            int node = stdin.nextInt();
            if (quant[node-1] != 0) {
                System.out.println(node);
            } else {
                int x = visit_nodes(g, n, node, quant);
                if (quant[x-1] == 0) {
                    System.out.println("Impossivel");
                } else {
                    System.out.println(x);
                }
            }
            
        }
    }
}