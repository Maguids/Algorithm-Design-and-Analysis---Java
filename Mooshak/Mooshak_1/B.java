// B: Não lhe dês troco

import java.util.Scanner;

class Slot_Machine {
    private int[] coins = {200, 100, 50, 20, 10, 5};    // valores das moedas em centimos
    private int[] q;
    private int dinheiro_retido = 0; // dinheiro que ficou retido - [euros, centimos]
    private int transacao_r = 0;     // número de transações que tiveram dinheiro retido
    private int transacoes = 0;  // número total de transações
    private int gave = 0;

    Slot_Machine(int[] quantidades) {
        q = quantidades; // quantidade de cada moeda no inicio
    }

    public void print_quant() {
        String ans = "[";
        for (int i = 0; i<5; i++) {
            ans += Integer.toString(q[i]) + ", ";
        }
        ans += Integer.toString(q[5]) + "]";
        System.out.println(ans);
    }

    public void add_coins (int moeda) {
        if (moeda == 2) {
            q[0] += 1;
            moeda = 200;
        } else if (moeda == 1) {
            q[1] += 1;
            moeda = 100;
        } else if (moeda == 50) {
            q[2] += 1;
        } else if (moeda == 20) {
            q[3] += 1;
        } else if (moeda == 10) {
            q[4] += 1;
        } else if (moeda == 5) {
            q[5] += 1;
        }
        gave += moeda;
    }

    private void reset_gave() {
        gave = 0;
    }

    public void payment(int e, int c) {
        gave = gave - e*100 - c;
        for (int i = 0; i<6; i++) {
            while (gave >= coins[i] && q[i] != 0) {
                gave -= coins[i];
                q[i] -= 1;
            }
        }
        if (gave != 0) {
            transacao_r += 1;
            dinheiro_retido += gave;
            reset_gave();
        }
        transacoes += 1;
    }

    public void print_retido() {
        System.out.println(dinheiro_retido/100 + " " + dinheiro_retido%100);
    }

    public void print_fracao() {
        System.out.println(transacao_r + "/" + transacoes);
    }

} 

public class B {
    public static void main (String[] args) {
        Scanner stdin = new Scanner(System.in);

        int[] inicio = new int[6]; // vai guardar a quantidade de moedas
        String[] quant = stdin.nextLine().split(" ");
        for (int i=0; i<6; i++) {inicio[i] = Integer.valueOf(quant[i]);}
        
        Slot_Machine sm = new Slot_Machine(inicio); //inicializar a máquina das moedas

        int e = stdin.nextInt();
        int c = stdin.nextInt();
        while (e != 0 || c!= 0) {
            int m = stdin.nextInt();
            while (m != 0) {
                sm.add_coins(m);
                m = stdin.nextInt();
            }
            sm.payment(e,c);
            //System.out.println("e = " + e + " c = " + c);
            //sm.print_quant();
            e = stdin.nextInt();
            c = stdin.nextInt();
        }
        sm.print_retido();
        sm.print_fracao();
    }
}