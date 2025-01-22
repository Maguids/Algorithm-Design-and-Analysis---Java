// A: Disse que disse

import java.util.Scanner;

public class A {

    public static void order_print(String s, int c) {
        String ans = Integer.toString(c);
        String[] group = s.split(" ");
        // encontrar o valor máximo na lista de números
        int max_value = Integer.valueOf(group[0]);
        int max_pos = 0;
        for (int i = 0; i<c; i++){
            if(max_value < Integer.valueOf(group[i])){
                max_value = Integer.valueOf(group[i]);
                max_pos = i;
            }
        }
        
        // imprimir ordenado
        for (int i = 0; i < c; i++) {
            if (max_pos + i < c) {
                ans += " " +  group[max_pos+i];
            } else {
                ans += " " +  group[max_pos+i-c];
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();    // Lê o número de pessoas
        
        int[] keeper = new int[n];   // Cria um array para guardar a lista de números dados no input 
        for (int i=0; i<n; i++) {keeper[i] = stdin.nextInt();}   // lê o que é dado no input
        
        int[] visited = new int[n];     // Array que guarda os sitios já visitados. 0 se não foi visitado, 1 se já foi
        for (int i=0; i<n; i++) {visited[i] = 0;}

        int counter = 0;     // conta o número de pessoas que não estavam em grupos de 3 ou mais pessoas
        // criar os grupos, ordená-los e imprimir
        for (int i=0; i<n; i++) {
            // cria o grupo
            if (visited[i] == 0) {
                int temp = 1;    // conta as pessoas por grupo
                int pos = i+1;  // guarda o identificador da primeira pessoa do grupo
                int value = keeper[pos-1];  // guarda o valor da primeira pessoa do grupo
                String ans = "";
                while (pos != value) {
                    ans += Integer.toString(value) + " ";
                    visited[value-1] = 1;
                    value = keeper[value-1];
                    temp += 1;
                }
                visited[value-1] = 1;
                ans += Integer.toString(value);

                // vê quantas pessoas tem o grupo, se tem >=3 ordena o grupo e imprime a resposta
                // caso contrário aumenta o número do counter para imprimir no fim de tudo
                if (temp <= 2) {counter += temp;}
                else {order_print(ans, temp);}
            }
        }
        System.out.println(counter);
    }
}