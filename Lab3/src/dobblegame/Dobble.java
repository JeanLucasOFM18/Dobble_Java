package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dobble {

    private List<Card> mazo;
    private List<String> lis_elementos;
    private int cantElementos;
    private int numC;
    private int maxC;

    public Dobble() {
        this.mazo = new ArrayList <Card>();
        this.lis_elementos = new ArrayList<>();
        this.cantElementos = 0;
        this.maxC = 0;
        this.numC = 0;
    }

    public List<Card> getMazo() {
        return mazo;
    }

    public void setMazo(List<Card> mazo) {
        this.mazo = mazo;
    }

    public List<String> getLis_elementos() {
        return lis_elementos;
    }

    public void setLis_elementos(List<String> lis_elementos) {
        this.lis_elementos = lis_elementos;
    }

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

    public void generarLista(){

        System.out.println("Recuerde no ingresar elementos repetidos");
        Scanner in = new Scanner(System.in);
        List<String> lis_elementos = getLis_elementos();

        int i = 0;
        int j = 0;
        int aux = 0;
        while(i < getCantElementos()){
            System.out.println("Ingrese el elemento: ");
            String elemento = in.nextLine();
            int largo = getLis_elementos().size();
            while(j < largo){
                String elemento2 = getLis_elementos().get(j);;
                if(elemento.equals(elemento2)){
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
                setLis_elementos(lis_elementos);
                i = i + 1;
                j = 0;
            }
        }

        System.out.println(getLis_elementos());
    }

    public boolean comprobarDatos(){

        int resultado = calculo(getNumC());
        int largo = getLis_elementos().size();

        if(resultado <= largo){
            return true;
        }
        else{
            return false;
        }
    }

    public void senalarError(){

        int resultado = calculo(getNumC());
        int largo = getLis_elementos().size();

        int faltantes = resultado - largo;

        System.out.println("Para crear el set de cartas correcto se necesita agregar " + faltantes + " elementos");

    }

    public void generarMazo (int opcion){

        if(opcion == 1){
            firstCard();
            int maxC = calculo(getNumC());
            int i = maxC - 1;
            while(i >= getMaxC()){
                getMazo().remove(i);
                i = i - 1;
            }
        }

        else{
            getMazo().removeAll(getMazo());
            firstCard();
        }

    }

    public void firstCard(){
        // PRIMERA CARTA
        int i = 0;
        String elemento;
        List<String> elementos = new ArrayList<>();
        while(i < getNumC()){
            elemento = getLis_elementos().get(i);
            elementos.add(elemento);
            i = i + 1;
        }
        Card carta = new Card(elementos);
        List<Card> mazoaux = getMazo();
        mazoaux.add(carta);
        setMazo(mazoaux);

        nextCards();
    }

    public void nextCards(){
        // N CARTAS DESPUES
        int j = 1;
        int k = 0;
        int aux;
        String elemento;
        while(j < getNumC()){
            List<String> elementos = new ArrayList<>();
            elemento = getLis_elementos().get(0);
            elementos.add(elemento);
            k = 0;
            while(k < (getNumC()-1)){
                aux = ((getNumC() - 1) * j) + (k + 1);
                elemento = getLis_elementos().get(aux);
                elementos.add(elemento);
                k = k + 1;
            }
            Card carta = new Card(elementos);
            List<Card> mazoaux = getMazo();
            mazoaux.add(carta);
            setMazo(mazoaux);
            j = j + 1;
        }
        lastCards();
    }

    public void lastCards(){
        // N**2 CARTAS DESPUÉS
        int i = 0;
        int j = 0;
        int k = 0;
        int aux2 = getNumC() - 1;
        String elemento;
        int aux;
        while(i < aux2){
            j = 0;
            while(j < aux2){
                List<String> elementos = new ArrayList<>();
                aux = i + 1;
                elemento = getLis_elementos().get(aux);
                elementos.add(elemento);
                k = 0;
                while(k < aux2){
                    aux = (aux2 + 1 + aux2 * k + ((i * k + j) % aux2));
                    elemento = getLis_elementos().get(aux);
                    elementos.add(elemento);
                    k = k + 1;
                }
                j = j + 1;
                Card carta = new Card(elementos);
                List<Card> mazoaux = getMazo();
                mazoaux.add(carta);
                setMazo(mazoaux);
            }
            i = i + 1;
        }

    }

    public void mostrarMazo(){

        int i = 0;
        System.out.println("El mazo es: ");
        while(i < getMaxC()){
            System.out.println(getMazo().get(i).getCarta());
            i = i + 1;
        }

    }

    public void dobbleGame(){

        int i = 0;
        int largo = getMazo().size();
        int calculo = calculo(getNumC());
        int j = 1;
        int comparacion = 0;

        if(largo != calculo){
            System.out.println("El set de cartas es invalido");
        }
        else{
            while(i < largo) {
                List<String> carta1 = getMazo().get(i).getCarta();
                while (j < largo) {
                    List<String> carta2 = getMazo().get(j).getCarta();
                    comparacion = comparaCartas(carta1, carta2);
                    if(comparacion != 0){
                        i = largo + 1;
                        j = largo + 1;
                        comparacion = 1;
                    }
                    j = j + 1;
                }
                i = i + 1;
                j = i + 1;
            }

            if(comparacion == 0){
                System.out.println("El set de cartas es valido");
            }
            else{
                System.out.println("El set de cartas es invalido");
            }
        }

    }

    public void numCards(){

        System.out.println("La cantidad de cartas en el set es: " + getMazo().size());
    }

    public void nthCard(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta busca?");
        int posicion = in.nextInt();

        System.out.println("La carta escogida es: " + getMazo().get(posicion).getCarta());
    }

    public void findTotalCards(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int posicion = in.nextInt();

        List<String> sublista = getMazo().get(posicion).getCarta();

        int largo = sublista.size();

        int total = calculo(largo);

        System.out.println("La cantidad de cartas necesarias son: " + total);
    }

    public void requiredElements(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int posicion = in.nextInt();

        List<String> sublista = getMazo().get(posicion).getCarta();

        int largo = sublista.size();

        int total = calculo(largo);

        System.out.println("La cantidad de elementos necesarios son: " + total);
    }

    public void missingCards(){

        //List<String> setCompleto = new ArrayList<>();
        int maxC = calculo(getNumC());

        List<Card> setUsuario = getMazo();
        Dobble mazoEntero = new Dobble();
        mazoEntero.setMaxC(maxC);
        mazoEntero.setNumC(getNumC());
        mazoEntero.setLis_elementos(getLis_elementos());
        mazoEntero.generarMazo(1);

        int largo2 = getMazo().size();
        int largo = mazoEntero.getMazo().size();

        int i = 0;
        int j = 0;
        int k;
        int comparacion = 0;
        while(i < largo){
            List<String> sublista = mazoEntero.getMazo().get(i).getCarta();
            while(j < largo2){
                List<String> sublista2 = setUsuario.get(j).getCarta();
                comparacion = comparaCartas(sublista, sublista2);
                if(comparacion == 1){
                    j = largo2 + 1;
                }
                else{
                    j = j + 1;
                }
            }
            if(comparacion == 0){
                System.out.println(mazoEntero.getMazo().get(i).getCarta());
                j = 0;
                i = i + 1;
            }
            else{
                j = 0;
                i = i + 1;
            }
        }
    }

    public void cardsSetToString(){

        int i = 0;
        int j = 1;
        int largo = getMazo().size();
        while(i < largo){
            List<String> sublista = getMazo().get(i).getCarta();
            System.out.println("Carta " + j + ": " + sublista);
            i = i + 1;
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