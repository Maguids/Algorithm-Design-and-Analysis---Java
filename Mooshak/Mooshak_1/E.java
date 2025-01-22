// E: Cigarras Tonta
import java.util.Scanner;

public class E {

    // vê se o número está na lista e retorna a primeira posição em que aparece
    public static int in_list (int[] l, int n, int p) {
        for (int i=0; i<p; i++) {
            if (l[i] == n) {
                return i;   // retorna a primeira posição em que encontrou o número
            }
        }
        return -1; // não está na lista
    }

    public static void print_list(int[] l, int p) {
        for (int i=0; i<p; i++) {
            System.out.println(l[i]);
        }
    }

    public static void main (String[] args) {
        Scanner stdin = new Scanner(System.in);

        int[] keeper = new int[30]; // guarda a reposta
        int pos = 0;    // guarda a posição no array em que estamos

        int local = stdin.nextInt();    // guarda a posição atual
        while (local != 0) {
            int new_pos = in_list(keeper, local, pos);
            if (new_pos == -1) {
                keeper[pos] = local;
                pos += 1;
            } else {
                pos = new_pos+1;
            }
            local = stdin.nextInt();    // pede o próximo local
        }
        print_list(keeper, pos);
    }
}