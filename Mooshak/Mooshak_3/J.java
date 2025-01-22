// J: Negócio Eletrónico
import java.util.*;

class Edge {
    private int enode;
    private int value;
    
    Edge(int endv, int v){
	enode = endv;
	value = v;
    }

    public int endnode() {
	return enode;
    }

    public int value() {
	return value;
    }

    public void newvalue(int v) {
	value = v;
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


class Graph {
    private Node verts[];
    private int nverts, nedges;
			
    public Graph(int n) {
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
    
    public void insert_new_edge(int i, int j, int value_ij){
	verts[i].adjs().addFirst(new Edge(j,value_ij));
        nedges++;
    }

    public Edge find_edge(int i, int j){
	for (Edge adj: adjs_no(i))
	    if (adj.endnode() == j) return adj;
	return null;
    }
}


class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
	vert = v;
	vertkey = key;
    }
}

class Heapmin {
    private static int posinvalida = 0;
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmin(int vec[], int n) {
	a = new Qnode[n + 1];
	pos_a = new int[n + 1];
	sizeMax = n;
	size = n;
	for (int i = 1; i <= n; i++) {
	    a[i] = new Qnode(i,vec[i]);
	    pos_a[i] = i;
	}

	for (int i = n/2; i >= 1; i--)
	    heapify(i);
    }

    boolean isEmpty() {
	if (size == 0) return true;
	return false;
    }

    int extractMin() {
	int vertv = a[1].vert;
	swap(1,size);
	pos_a[vertv] = posinvalida;  // assinala vertv como removido
	size--;
	heapify(1);
	return vertv;
    }

    void decreaseKey(int vertv, int newkey) {

	int i = pos_a[vertv];
	a[i].vertkey = newkey;

	while (i > 1 && compare(i, parent(i)) < 0) { 
	    swap(i, parent(i));
	    i = parent(i);
	}
    }


    void insert(int vertv, int key)
    { 
	if (sizeMax == size)
	    new Error("Heap is full\n");
	
	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	decreaseKey(vertv,key);   // diminui a chave e corrige posicao se necessario
    }

    void write_heap(){
	System.out.printf("Max size: %d\n",sizeMax);
	System.out.printf("Current size: %d\n",size);
	System.out.printf("(Vert,Key)\n---------\n");
	for(int i=1; i <= size; i++)
	    System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
	
	System.out.printf("-------\n(Vert,PosVert)\n---------\n");

	for(int i=1; i <= sizeMax; i++)
	    if (pos_valida(pos_a[i]))
		System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }
    
    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
	return 2*i+1;
    }

    private int compare(int i, int j) {
	if (a[i].vertkey < a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }

  
    private void heapify(int i) {
	int l, r, smallest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	smallest = i;
	if (compare(l,smallest) < 0)
	    smallest = l;
	if (compare(r,smallest) < 0)
	    smallest = r;
	
	if (i != smallest) {
	    swap(i, smallest);
	    heapify(smallest);
	}
	
    }

    private void swap(int i, int j) {
	Qnode aux;
	pos_a[a[i].vert] = j;
	pos_a[a[j].vert] = i;
	aux = a[i];
	a[i] = a[j];
	a[j] = aux;
    }
    
    private boolean pos_valida(int i) {
	return (i >= 1 && i <= size);
    }
}


public class J {
    public static List<int[]> dijkstra(Graph g, int s) {
        int[] pai = new int[g.num_vertices() + 1];
        int[] dist = new int[g.num_vertices() + 1];
        for(int i = 1; i <= g.num_vertices(); i++) {pai[i] = -1; dist[i] = Integer.MAX_VALUE;}

        dist[s] = 0;
        pai[s] = 0;

        Heapmin h = new Heapmin(dist, g.num_vertices());
        int[] ans = new int[g.num_vertices()+1];
        int pos = 1;
        
        while (!h.isEmpty()) {
            int cur = h.extractMin();
            ans[pos] = cur;
            pos += 1;

            for (Edge adj : g.adjs_no(cur)) {
                int neighbor = adj.endnode();
                if (dist[cur] + adj.value() < dist[neighbor]) {
                    dist[neighbor] = dist[cur] + adj.value();
                    pai[neighbor] = cur;
                    h.decreaseKey(neighbor, dist[neighbor]);
                }
            }
        }
        List<int[]> resp = new ArrayList<>();
        resp.add(ans);
        resp.add(dist);
        return resp;
    }


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int verts = stdin.nextInt();
        Graph g = new Graph(verts);
        int destino = stdin.nextInt();

        int o = stdin.nextInt();
        while (o != -1) {
            int d = stdin.nextInt();
            int valor = stdin.nextInt();
            g.insert_new_edge(o, d, valor);
            g.insert_new_edge(d, o, valor);

            o = stdin.nextInt();
        }

        List<int[]> ans = dijkstra(g, destino);
        int[] order = ans.get(0);
        int[] dist = ans.get(1);

        boolean first = true;

        int pos = 0;
        for (int i = 1; i <= g.num_vertices(); i++) {
            int cur = order[i];
            for (int j = 1; j <= g.num_vertices(); j++) {
                if (dist[cur] == dist[j]) {pos += 1;}
            }
            int[] keeper = new int[pos];
            pos = 0;
            for (int j = 1; j <= g.num_vertices(); j++) {
                if (dist[cur] == dist[j]) {keeper[pos] = j; pos += 1;}
            }
            if (pos > 1) {
                Arrays.sort(keeper);
                for (int k = 0; k < pos; k++) {
                    if (first) {
                        System.out.print(keeper[k]);
                        first = false;
                    } else {System.out.print(" " + keeper[k]);}
                }
                i += pos-1;
            } else {
                if (first) {
                    System.out.print(cur);
                    first = false;
                } else {
                    System.out.print(" " + cur);
                }
            }
            pos = 0;
        }
        System.out.print("\n");
    }

}
