// F: Onde está o Wally?
import java.util.*;

//-----------------------------------------------------
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
//-----------------------------------------------------


class F {

    public static void find(Graph0 g, int n, int[] data, int k, int[] objects, int s) {
        int wally = 0; // enquanto for 0, ainda não foi encontrado 
        boolean[] visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {visited[i] = false;}
        Queue<Integer> fila = new LinkedList<>();
        visited[s] = true;
        fila.add(s);

        while (!fila.isEmpty()) {
            int v = fila.poll();
            //System.out.println(v);
            if (data[v] == 0) {wally = v;}  // o Wally é encontrado
            if (data[v] > 0 && data[v] <= k) {objects[data[v]] = 1;}  // encontrou um objeto

            for (Edge0 adj : g.adjs_no(v)) {
                int neighboor = adj.endnode();
                if (!visited[neighboor]) {
                    visited[neighboor] = true;
                    fila.add(neighboor);
                }
            }
        }

        if (wally == 0) {System.out.println("Wally not found");}
        else {System.out.println("Wally: " + wally);}
        
        int counter = 0;
        String ans = "";
        for (int i = 1; i <= k; i++) {
            if (objects[i] == 1) {
                counter += 1;
                if (counter == 1) {ans += i;}
                else {ans += " " + i;}
            }
        }
        if (counter == 0) {System.out.println(counter);}
        else{System.out.println(counter + " " + ans);}
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();    // numero de nós
        int r = stdin.nextInt();    // número de ramos
        int k = stdin.nextInt();    // objetos a procurar
        int[] objects = new int[k+1];   // enquanto for zero não encontrou
        for (int i = 1; i <= k; i++) {objects[i] = 0;}

        int s = stdin.nextInt();    // nó inicial
        Graph0 g = new Graph0(n);

        int[] data = new int[n+1];  // guarda os dados de cada nó
        for (int i =  1; i <= n; i++) {data[i] = stdin.nextInt();}
        
        // leitura dos ramos
        for (int i = 0; i < r; i++) {
            int origin = stdin.nextInt();
            int destiny = stdin.nextInt();
            g.insert_new_edge(origin, destiny);
            g.insert_new_edge(destiny, origin);
        }
        
        find(g, n, data, k, objects, s);

    }   

}