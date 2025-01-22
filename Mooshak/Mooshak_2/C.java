// C: Mapa sem sentidos únicos
import java.util.*;

// -----------------------------------------------------

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

// -----------------------------------------------------

class C {

    public static void main (String[] args) {
        Scanner stdin = new Scanner(System.in);
        int trajetos = stdin.nextInt();
        int nos = stdin.nextInt();
        Graph0 g = new Graph0(nos);
        
        for (int i = 1; i <= trajetos; i++) {
            int k = stdin.nextInt();
            int distancia = 0;
            int o = stdin.nextInt();
            for(int j = 1; j < k; j++) {
                distancia += stdin.nextInt();
                int d = stdin.nextInt();
                if (g.find_edge(o, d) == null) {g.insert_new_edge(o, d);}
                if (g.find_edge(d, o) == null) {g.insert_new_edge(d, o);}                
                o = d;
            }
            System.out.println("Trajeto " + i + ": " + distancia);
        }

        for (int i = 1; i <= nos; i++) {
            System.out.println("No " + i + ": " + g.adjs_no(i).size());
        }
    }
}