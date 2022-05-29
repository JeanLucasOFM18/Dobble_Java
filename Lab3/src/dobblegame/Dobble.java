package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dobble {

    private int cantElementos;
    private int numC;
    private int maxC;

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }

    public int getMaxC() {
        return maxC;
    }

    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }

    public List<String> generarLista(int cantElementos){

        Scanner in = new Scanner(System.in);
        List<String> lis_elementos = new ArrayList<>();

        int i = 0;
        while(i < cantElementos){
            System.out.println("Ingrese el elemento: ");
            String elemento = in.nextLine();
            lis_elementos.add(elemento);
            i = i + 1;
        }

        return lis_elementos;
    }

    public List<String> generarMazo (List<String> lis_elementos, int numC, int maxC){
        List<String> cards = new ArrayList<>();
        List<String> setUsuario = new ArrayList<>();
        String elemento;

        firstCard(cards, lis_elementos, numC);

        int k = 0;
        int i = 0;
        int j = 0;
        while(i < maxC){
            while(j < numC){
                elemento = cards.get(k);
                setUsuario.add(elemento);
                j = j + 1;
                k = k + 1;
            }
            i = i + 1;
            j = 0;
        }

        return setUsuario;
    }

    public void firstCard(List<String> cards, List<String> lis_elementos, int numC){
        // PRIMERA CARTA
        int i = 0;
        String elemento;
        while(i < numC){
            elemento = lis_elementos.get(i);
            cards.add(elemento);
            i = i + 1;
        }
        nextCards(cards, lis_elementos, numC);
    }

    public void nextCards(List<String> cards, List<String> lis_elementos, int numC){
        // N CARTAS DESPUES
        int j = 1;
        int k = 0;
        int aux;
        String elemento;
        while(j < numC){
            elemento = lis_elementos.get(0);
            cards.add(elemento);
            k = 0;
            while(k < (numC-1)){
                aux = ((numC - 1) * j) + (k + 1);
                elemento = lis_elementos.get(aux);
                cards.add(elemento);
                k = k + 1;
            }
            j = j + 1;
        }
        lastCards(cards, lis_elementos, numC);
    }

    public void lastCards(List<String> cards, List<String> lis_elementos, int numC){
        // N**2 CARTAS DESPUÉS
        int i = 0;
        int j = 0;
        int k = 0;
        int aux2 = numC - 1;
        String elemento;
        int aux;
        while(i < aux2){
            j = 0;
            while(j < aux2){
                aux = i + 1;
                elemento = lis_elementos.get(aux);
                cards.add(elemento);
                k = 0;
                while(k < aux2){
                    aux = (aux2 + 1 + aux2 * k + ((i * k + j) % aux2));
                    elemento = lis_elementos.get(aux);
                    cards.add(elemento);
                    k = k + 1;
                }
                j = j + 1;
            }
            i = i + 1;
        }

    }
}
