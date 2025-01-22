// C: Sentar ou não sentar

import java.util.Scanner;

public class C {

    // devolve 1 se a pessoa se sentar e 0 caso contrário
    public static int possible(int n_tipos, int[] tipos, int[] quantidades, int t) {
        int pos = 0;
        for (int i=0; i<n_tipos; i++) {
            if (t == tipos[i]) {pos = i;}
        }
        if (quantidades[pos] != 0) {
            quantidades[pos] -= 1;
            return 1;
        }
        return 0;
    }

    public static void print_list(int[] x, int n) {
        String ans = "[";
        for (int i=0; i<n-1; i++){
            ans += x[i] + ", ";
        }
        ans += x[n-1] + "]";
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n_cadeiras = stdin.nextInt();
        int[] t_cadeiras = new int[n_cadeiras]; // guarda  os tipos de cadeiras
        int[] q_cadeiras = new int[n_cadeiras]; // guarda a quantidade de cada tipo de cadeira
        for (int i=0; i<n_cadeiras; i++) {
            t_cadeiras[i] = stdin.nextInt();
            q_cadeiras[i] = stdin.nextInt();
        }
        //print_list(q_cadeiras, n_cadeiras);

        int n_habitantes = stdin.nextInt();
        int de_pe = 0;  // guarda i númerod e habitantes que fica de pé
        for (int i=0; i<n_habitantes; i++) {
            int n_tipos = stdin.nextInt();
            int control = 0; // se continuar 0 o habitante ficou de pé, se mudar para 1 sentou-se
            for (int j=0; j<n_tipos; j++) {
                int tipo = stdin.nextInt();
                if (control == 0) {
                    control = possible(n_cadeiras, t_cadeiras, q_cadeiras, tipo);
                }
                //print_list(q_cadeiras, n_cadeiras);
            }
            if (control == 0) {de_pe += 1;}
        }

        int sobra = 0;
        for (int i=0; i<n_cadeiras; i++) {
            sobra += q_cadeiras[i];
        } 
        System.out.println(de_pe);
        System.out.println(sobra);
    }
}