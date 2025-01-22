// F: Barbearia Chic
import java.util.Scanner;

class Barbeiro {
    private  int inicio_1 = 9 * 60;   // horas em minutos a que abre
    private int fim_1 = 12 * 60 ;   // horas a que fecha de manhã em minutos
    private int inicio_2 = 15*60; //horas a que abre de tarde em minutos
    private int fim_2 = 19*60; //horas a que fecha em minutos
    private int[] pessoas = {0,0,0,0};  // guarda a hora a que as pessoas irão sair (a que está a ser atendida em 0 e as restantes a seguir)
    private int pos;
    private int control_break = 0;

    Barbeiro () {
        pos = 0;
    }

    public void print_lista () {
        System.out.println("pos = "+ pos);
        String ans = "[";
        for (int i=0; i<3; i++) {
            ans += pessoas[i] + ", ";
        }
        ans += pessoas[3] + "]";
        System.out.println(ans);
    }

    public boolean hora_possivel (int hora, int minuto) {
        int chegada = hora*60 + minuto;
        if (chegada >= inicio_1 && chegada <= fim_1){
            return true;    //pode ser atendido durante a manhã
        } else if (chegada >= inicio_2 && chegada <= fim_2){
            return true;    //pode ser atendido durante a tarde
        }
        return false;   //fora de horas
    }

    public void atualizar_pessoas() {
        pessoas[0] = pessoas[1];
        pessoas[1] = pessoas[2];
        pessoas[2] = pessoas[3];
        pessoas[3] = 0;
    }

    public void saiu (int hora, int minuto) {
        int tempo = hora*60 + minuto;
        int counter = 0;
        for (int i=0; i<pos; i++) {
            //System.out.println(tempo);
            if (tempo >= pessoas[i]) {
                atualizar_pessoas();
                //print_lista();
                pos -= 1;
            }
        }
    }

    public void atendido(int hora, int minuto, int duracao){
        if (pos == 0){
            pessoas[0] = hora*60 + minuto + duracao;
        } else {
            pessoas[pos] = pessoas[pos-1] + duracao;
        }
        pos += 1;
    }

    public boolean possivel_atender (int hora, int minuto, int duracao) {
        if (control_break == 0) {
            if (hora >= 15) {
                for (int i=0; i<4; i++) {
                    atualizar_pessoas();
                    pos = 0;
                }
                control_break = 1;
            }
        }
        
        if (hora_possivel(hora, minuto)) {
            saiu(hora, minuto);
            if (pos <= 3) {
                atendido(hora, minuto, duracao);
                //print_lista();
                return true;
            }
        }
        //print_lista();
        return false;
    }
} 

public class F {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Barbeiro b = new Barbeiro();
        int counter = 0;

        int n = stdin.nextInt();
        for (int i=0; i<n; i++) {
            int hora = stdin.nextInt();
            int minuto = stdin.nextInt();
            int duracao = stdin.nextInt();

            if (b.possivel_atender(hora, minuto, duracao) == false) {
                counter += 1;
            }
        }
        System.out.println("Perdeu = " + counter);
    }
}