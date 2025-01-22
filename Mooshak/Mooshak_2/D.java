// D: Reservas
import java.util.*;

// ---------------- Grafo que guarda um int[][] -------------------
class Edge1 {
    private int enode;
    private int[] value;
    
    Edge1(int endv, int[] v){
	    enode = endv;
	    value = v;
    }

    public int endnode() {
	    return enode;
    }

    public int[] value() {
	    return value;
    }

    public void newvalue(int[] v) {
	    value = v;
    }
}

class Node1 {
    //private int label;
    private LinkedList<Edge1> neighbours;

    Node1() {
	    neighbours  = new LinkedList<Edge1>();
    }

    public LinkedList<Edge1> adjs() {
	    return neighbours;
    }
   
}

class Graph1 {
    private Node1 verts[];
    private int nverts, nedges;
			
    public Graph1(int n) {
        nverts = n;
        nedges = 0;
        verts  = new Node1[n+1];
        for (int i = 0 ; i <= n ; i++)
            verts[i] = new Node1();
    }
    
    public int num_vertices(){
	    return nverts;
    }

    public int num_edges(){
	    return nedges;
    }

    public LinkedList<Edge1> adjs_no(int i) {
	    return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j, int[] value_ij){
	    verts[i].adjs().addFirst(new Edge1(j,value_ij));
        nedges++;
    }

    public Edge1 find_edge(int i, int j){
        for (Edge1 adj: adjs_no(i))
            if (adj.endnode() == j) return adj;
        return null;
    }
}

class D {
    public static void main (String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();    // número de nós da rede
        int r = stdin.nextInt();    // número de ligações
        Graph1 g = new Graph1(n);

        // leitura das ligações
        for (int i = 0; i < r; i++) {
            int o = stdin.nextInt();
            int d = stdin.nextInt();
            int seats = stdin.nextInt();
            int preco = stdin.nextInt();
            int[] aux = {seats, preco};
            g.insert_new_edge(o, d, aux);
        }

        int t = stdin.nextInt();
        for (int i = 0; i < t; i++) {
            int k = stdin.nextInt();
            int p = stdin.nextInt();
            int[] keeper = new int[p];
            int o = stdin.nextInt();
            keeper[0] = o;
            int control = 1;
            for (int j = 1; j < p; j++) {
                int d = stdin.nextInt();
                //System.out.println(k + " " + p + " " + o + " " + d);
                if (control == 1) {
                    if (g.find_edge(o, d) == null) {
                        System.out.println("(" + o + "," + d + ") inexistente");
                        control = 0;
                    } else if (g.find_edge(o, d).value()[0] < k) {
                        System.out.println("Sem lugares suficientes em (" + o + "," + d + ")");
                        control = 0;
                    }
                }

                o = d;
                keeper[j] = d;
            }

            int payment = 0;
            if (control == 1) {
                int x = keeper[0];
                for (int j = 1; j < p; j++) {
                    int y = keeper[j];
                    int seat = g.find_edge(x, y).value()[0];
                    int price = g.find_edge(x, y).value()[1];
                    payment += price*k;
                    int[] aux = {seat-k, price} ;
                    g.find_edge(x, y).newvalue(aux);
                    x = y;
                }
                System.out.println("Total a pagar: " + payment);
            }
        }
    }
}