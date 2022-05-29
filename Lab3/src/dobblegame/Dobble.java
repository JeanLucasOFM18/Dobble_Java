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

        System.out.println("Recuerde no ingresar elementos repetidos");
        Scanner in = new Scanner(System.in);
        List<String> lis_elementos = new ArrayList<>();

        int i = 0;
        int j = 0;
        int aux = 0;
        while(i < cantElementos){
            System.out.println("Ingrese el elemento: ");
            String elemento = in.nextLine();
            int largo = lis_elementos.size();
            while(j < largo){
                String elemento2 = lis_elementos.get(j);
                if(equals(elemento, elemento2) == true){
                    aux = 1;
                    j = largo;
                }
                else{
                    j = j + 1;
                }
            }
            if(aux == 1){
                System.out.println("El elemento es repetido");
                j = 0;
                aux = 0;
            }
            else{
                lis_elementos.add(elemento);
                i = i + 1;
                j = 0;
            }
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

    public void mostrarMazo(int numC, int maxC, List<String> mazo){

        int i = 0;
        int j = 0;
        while(i < maxC){
            List sublista = mazo.subList(j,(j+numC));
            System.out.println(sublista);
            i = i + 1;
            j = j + numC;
        }
    }

    public void dobbleGame(List<String> mazo, int numC){

        int i = 0;
        int largo = mazo.size();
        int calculo = calculo(numC);
        int j;
        int comparacion = 0;

        int k = 0;
        int cantCartas = 0;
        while(k < largo){
            k = k + numC;
            cantCartas = cantCartas + 1;
        }

        if(cantCartas != calculo){
            System.out.println("El set de cartas es invalido");
        }
        else{
            while((i + numC) < largo) {
                j = i + numC;
                List sublista = mazo.subList(i, j);
                while (j < largo) {
                    List sublista2 = mazo.subList(j, (j + numC));
                    comparacion = comparaCartas(sublista, sublista2);
                    if(comparacion != 0){
                        i = largo + 1;
                        j = largo + 1;
                        comparacion = 1;
                    }
                    j = j + numC;
                }
                i = i + numC;
            }

            if(comparacion == 0){
                System.out.println("El set de cartas es valido");
            }
            else{
                System.out.println("El set de cartas es invalido");
            }
        }

    }

    public void numCards(List<String> mazo, int numC){

        int largo = mazo.size();
        int i = 0;
        int j = 0;
        while(i < largo){
            i = i + numC;
            j = j + 1;
        }

        System.out.println("La cantidad de cartas en el set es: " + j);
    }

    public void missingCards(List<String> elementos, List<String> mazo, int numC){

        List<String> setCompleto = new ArrayList<>();
        int maxC = calculo(numC);

        setCompleto = generarMazo(elementos, numC, maxC);
        int largo2 = mazo.size();
        int largo = setCompleto.size();

        int i = 0;
        int j = 0;
        int k;
        int comparacion = 0;
        while(i < largo){
            k = i + numC;
            List sublista = setCompleto.subList(i, k);
            while(j < largo2){
                List sublista2 = mazo.subList(j, j + numC);
                comparacion = comparaCartas(sublista, sublista2);
                if(comparacion == 1){
                    j = largo2 + 1;
                }
                else{
                    j = j + numC;
                }
            }
            if(comparacion == 0){
                System.out.println(sublista);
                j = 0;
                i = i + numC;
            }
            else{
                j = 0;
                i = i + numC;
            }
        }
    }

    public void cardsSetToString(List<String> mazo, int numC){

        int i = 0;
        int j = 1;
        int largo = mazo.size();
        while(i < largo){
            List sublista = mazo.subList(i, i + numC);
            System.out.println("Carta " + j + ": " + sublista);
            i = i + numC;
            j = j + 1;
        }
    }

    public int comparaCartas(List<String> sublista, List<String> sublista2){

        int i = 0;
        int j = 0;
        int comparacion = 0;
        int largo = sublista.size();
        String elemento;
        String elemento2;
        while(i < largo){
            elemento = sublista.get(i);
            while(j < largo){
                elemento2 = sublista2.get(j);
                if(equals(elemento, elemento2) == true){
                    comparacion = comparacion + 1;
                }
                j = j + 1;
            }
            j = 0;
            i = i + 1;
        }

        if(comparacion == 1){
            return 0;
        }
        else{
            return 1;
        }
    }

    public int calculo (int numC){
        int resultado = ((numC - 1) * (numC - 1)) + (numC - 1) + 1;

        return resultado;
    }

    public boolean equals(String objeto1, String objeto2){

        if(objeto1.compareTo(objeto2) == 0){
            return true;
        }

        else{
            return false;
        }
    }
}
