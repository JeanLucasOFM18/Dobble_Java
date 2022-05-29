package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Card {

    public void nthCard(List<String> mazo, int numC){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta busca?");
        int posicion = in.nextInt();

        int i = 0;
        int j = 0;
        while(i < posicion){
            j = j + numC;
            i = i + 1;
        }

        List sublista = mazo.subList(j,(j+numC));
        System.out.println("La carta escogida es: " + sublista);
    }

    public void findTotalCards(List<String> mazo, int numC){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int carta = in.nextInt();

        int i = 0;
        int j = 0;
        while(i < carta){
            j = j + numC;
            i = i + 1;
        }

        List sublista = mazo.subList(j,(j+numC));

        int largoSublista = sublista.size();

        int total = calculo(largoSublista);

        System.out.println("La cantidad de cartas necesarias son: " + total);
    }

    public void requiredElements(List<String> mazo, int numC){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int carta = in.nextInt();

        int i = 0;
        int j = 0;
        while(i < carta){
            j = j + numC;
            i = i + 1;
        }

        List sublista = mazo.subList(j,(j+numC));

        int largoSublista = sublista.size();

        int total = calculo(largoSublista);

        System.out.println("La cantidad de elementos necesarios son: " + total);
    }

    public int calculo (int numC){
        int resultado = ((numC - 1) * (numC - 1)) + (numC - 1) + 1;

        return resultado;
    }

}
