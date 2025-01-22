// A: Viagens na minha terra
import java.util.*;


class A {

    public static int[][] build_graph(Scanner stdin) {
        // Ler a rede
        int n = stdin.nextInt();
        int[][] graph = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                graph[i][j] = stdin.nextInt();
            }
        }
        return graph;
    }


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int nelementos = stdin.nextInt();
        int origin = stdin.nextInt();
        int destiny = stdin.nextInt();   
        int departure = stdin.nextInt()*60; // minutos
        int arrival = stdin.nextInt()*60;   //minutos

        int[][] graph = build_graph(stdin);

        int possible_routes = 0;    // guarda o número de rotas possiveis com o minimo de imprevistos
        int troubles = 10000;   // guarda o valor minimo dos imprevistos

        int k = stdin.nextInt();
        int h = stdin.nextInt()*60;
        while (k != 0 && h != 0) {
            int first = 0;  // vê quem foi o primeiro a aparecer, a origem ou o destino
            int t = 0;  // conta o número de problemas
            int o = stdin.nextInt();
            for(int i = 1; i < k; i++) {
                int seats = stdin.nextInt();
                int duration = stdin.nextInt();
                int d = stdin.nextInt();

                if (o == destiny && first == 0) {
                    first = 2;
                } else if (o == origin && first == 0) {
                    first = 1;
                }
            
                if (first == 1) {
                    if (h >= departure && h <= arrival) {
                        int caminho = graph[o][d];
                        if (caminho == 1 && seats >= nelementos) {t += 1;}  // teve problemas
                        if (caminho == 2 || seats < nelementos) {first = 2;}    // não há caminho ou não há lugares
                        else if (d == destiny && h+duration <= arrival) {first = 3;}  // acabou o percurso
                    } else {first = 2;}
                } else if (first == 0) {
                    if (graph[o][d] == 1) {t += 1;}
                }
                h += duration;
                o = d;
            }

            if (first == 3) {   // chegou ao fim
                if (troubles > t) {
                    possible_routes = 1;
                    troubles = t;
                } else if (t == troubles) {
                    possible_routes += 1;
                }
            }

            k = stdin.nextInt();
            h = stdin.nextInt()*60;
        }

        if (possible_routes == 0) {System.out.println("Impossivel");}
        else {System.out.println(possible_routes + " " + troubles);}
    }
}