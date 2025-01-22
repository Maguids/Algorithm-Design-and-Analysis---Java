// G: Transporte Rápido

import java.util.*;

// ----------------------------------------
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
        // vertex labels are integers from 1 to n (position 0 is not used)
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
// ----------------------------------------

class G {
    public static void percursos(Graph0 g, int o, int d) {
        boolean[] visited = new boolean[g.num_vertices()+1];
        for(int i = 0; i <= g.num_vertices(); i++) {visited[i] = false;}
        int[] dist = new int[g.num_vertices()+1];
        for(int i = 0; i <= g.num_vertices(); i++) {dist[i] = -1;}    // enquanto for -1 não foi visitado
        
        Queue<Integer> fila = new LinkedList<>();
        fila.add(o);
        visited[o] = true;
        dist[o] = 0;

        while (!fila.isEmpty() && visited[d] == false) {
            int cur = fila.poll();
            for (Edge0 adj : g.adjs_no(cur)) {
                if (visited[adj.endnode()] == false) {
                    visited[adj.endnode()] = true;
                    dist[adj.endnode()] = dist[cur] + 1;
                    fila.add(adj.endnode());
                }
            }
        }
        
        if (dist[d] == -1) {System.out.println("Impossivel");}
        else {System.out.println(dist[d]);}
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        // primeira linha
        int n = stdin.nextInt();    // número de nós
        Graph0 g = new Graph0(n);
        // segunda linha
        int lmin = stdin.nextInt();
        int lmax = stdin.nextInt();
        int cmin = stdin.nextInt();
        int cmax = stdin.nextInt();
        int alt = stdin.nextInt();
        // terceira linha
        int origin = stdin.nextInt();
        int destiny = stdin.nextInt();
        // resto
        int o = stdin.nextInt();
        while (o != -1) {
            int d = stdin.nextInt();
            int maxl = stdin.nextInt();
            int maxc = stdin.nextInt();
            int maxa = stdin.nextInt();
            if (maxl >= lmin && maxc >= cmin && maxa >= alt) {
                g.insert_new_edge(o, d);
                g.insert_new_edge(d, o);
            }
            o = stdin.nextInt();
        }

        percursos(g, origin, destiny);
    }
}