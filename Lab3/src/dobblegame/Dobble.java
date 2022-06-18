package dobblegame;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Clase que simula una mazo Dobble, que contiene una lista de cartas(List<Card>, una lista de elementos (List<String>),
 * la cantidad de elementos (Integer), número de elementos por carta (Integer) y el máximo de cartas a generar (Integer)
 * @version 11.0.2
 * @autor: Jean Lucas Rivera
 */
public class Dobble implements IDobble {

    private List<Card> mazo;
    private List<String> lis_elementos;
    private int cantElementos;
    private int numC;
    private int maxC;

    public Dobble() {
        this.mazo = new ArrayList <>();
        this.lis_elementos = new ArrayList<>();
        this.cantElementos = 0;
        this.maxC = 0;
        this.numC = 0;
    }

    /**
     * Obtiene el mazo (List<Card>)
     * @return List<Card> Si se obtiene el mazo
     */
    public List<Card> getMazo() {
        return mazo;
    }

    /**
     * Modifica el mazo (List<Card>) por otro mazo
     * @param mazo (List<Card>). Corresponde a un nuevo mazo
     */
    public void setMazo(List<Card> mazo) {
        this.mazo = mazo;
    }

    /**
     * Obtiene los elementos para la construcción del mazo (List<String>)
     * @return List<String> Si se obtiene la lista de elementos
     */
    public List<String> getLis_elementos() {
        return lis_elementos;
    }

    /**
     * Modifica la lista de elementos (List<String>) por otra lista
     * @param lis_elementos (List<String>). Corresponde a una nueva lista de elementos
     */
    public void setLis_elementos(List<String> lis_elementos) {
        this.lis_elementos = lis_elementos;
    }

    /**
     * Obtiene la cantidad de elementos (Integer)
     * @return Integer Si se obtiene la cantidad de elementos
     */
    public int getCantElementos() {
        return cantElementos;
    }

    /**
     * Modifica la cantidad de elementos (Integer) por otra nueva cantidad
     * @param cantElementos (Integer). Corresponde a una nueva cantidad de elementos
     */
    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    /**
     * Obtiene la cantidad de elementos por carta (Integer)
     * @return Integer Si se obtiene la cantidad de elementos por carta
     */
    public int getNumC() {
        return numC;
    }

    /**
     * Modifica la cantidad de elementos por carta (Integer) por otra nueva cantidad de elementos por carta
     * @param numC (Integer). Corresponde a una nueva cantidad de elementos por carta
     */
    public void setNumC(int numC) {
        this.numC = numC;
    }

    /**
     * Obtiene la cantidad de cartas a generar (Integer)
     * @return Integer Si se obtiene la cantidad de cartas a generar
     */
    public int getMaxC() {
        return maxC;
    }

    /**
     * Modifica la cantidad de cartas a generar (Integer) por otra nueva cantidad de cartas a generar
     * @param maxC (Integer). Corresponde a una nueva cantidad de cartas a generar
     */
    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }

    /**
     * Crea una lista de elementos a medida que el usuario los va ingresando (List<String>)
     */
    public void generarLista(){

        System.out.println("Recuerde no ingresar elementos repetidos");
        Scanner in = new Scanner(System.in);
        List<String> lis_elementos = getLis_elementos();

        int i = 0;
        int j = 0;
        int aux = 0;
        while(i < getCantElementos()){
            System.out.println("Ingrese el " + (i + 1) + " elemento: ");
            String elemento = in.nextLine();
            int largo = getLis_elementos().size();
            while(j < largo){
                String elemento2 = getLis_elementos().get(j);
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

        Collections.shuffle(getLis_elementos());
        System.out.println("Su lista de elementos aleatorizada es: " + getLis_elementos());
    }

    /**
     * Comprueba si se cuenta con la cantidad necesaria de cartas para iniciar el juego
     * @return Boolean Dependiendo si se tiene la cantidad de cartas necesarias o no
     */
    public boolean comprobarDatos(){

        int resultado = calculo(getNumC());
        int largo = getLis_elementos().size();

        if(resultado > largo || resultado < getMaxC()){
            return false;
        }

        return true;
    }

    /**
     * Señala la cantidad de elementos que hacen falta para crear el mazo que solicita el usuario
     */
    public void senalarError(){

        int resultado = calculo(getNumC());
        int largo = getLis_elementos().size();

        int faltantes = resultado - largo;

        if(resultado > largo){
            System.out.println("Para crear el set de cartas correcto se necesita agregar " + faltantes + " elementos");
        }
        else{
            System.out.println("No se puede generar esta cantidad de cartas");
        }

    }

    /**
     * Agrega un nuevo usuario (Usuario) a la lista de usuarios registrados del Editor
     * @param opcion (Integer). La opción depende de si el usuario esté recién creando el mazo o bien decidió que el
     * programa cree uno mazo completo
     */
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

        int i = 0;
        int largo = getMazo().size();
        while(i < largo){
            Collections.shuffle(getMazo().get(i).getCarta());
            i = i + 1;
        }

    }

    /**
     * Agrega la primera carta (Card) al mazo (List<Card>)
     */
    public void firstCard(){
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

    /**
     * Agrega las siguientes N cartas (Card) al mazo (List<Card>)
     */
    public void nextCards(){
        int j = 1;
        int k;
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

    /**
     * Agrega las siguientes N*N cartas (Card) al mazo (List<Card>)
     */
    public void lastCards(){
        int i = 0;
        int j;
        int k;
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

    /**
     * Muestra el mazo generado
     */
    public void mostrarMazo(){

        int i = 0;
        System.out.println("El mazo es: ");
        while(i < getMaxC()){
            System.out.println(getMazo().get(i).getCarta());
            i = i + 1;
        }

    }

    /**
     * Determina si el set de cartas generado es válido o no para jugar
     */
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

    /**
     * Obtiene la cantidad de cartas que tiene el mazo generado
     */
    public void numCards(){

        System.out.println("La cantidad de cartas en el set es: " + getMazo().size());
    }

    /**
     * Obtiene la carta ubicada en la posición que indica el usuario, siempre y cuando esta posición exista
     */
    public void nthCard(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta busca?");
        int posicion = in.nextInt();

        if(posicion > getMaxC() - 1){
            System.out.println("No existe la carta " + posicion + " en su mazo");
        }
        else{
            System.out.println("La carta escogida es: " + getMazo().get(posicion).getCarta());
        }
    }

    /**
     * Obtiene la cantidad necesaria de cartas que se debe tener para poder tener un set válido,
     * todo esto a partir de una carta de muestra
     */
    public void findTotalCards(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int posicion = in.nextInt();

        if(posicion > getMaxC() - 1){
            System.out.println("No existe la carta " + posicion + " en su mazo");
        }
        else{
            List<String> sublista = getMazo().get(posicion).getCarta();
            int largo = sublista.size();
            int total = calculo(largo);
            System.out.println("La cantidad de cartas necesarias son: " + total);
        }
    }

    /**
     * Obtiene la cantidad necesaria de elementos que se debe tener para poder tener un set válido,
     * todo esto a partir de una carta de muestra
     */
    public void requiredElements(){

        Scanner in = new Scanner(System.in);
        System.out.println("Que carta quiere usar de muestra?");
        int posicion = in.nextInt();

        if(posicion > getMaxC() - 1){
            System.out.println("No existe la carta " + posicion + " en  su mazo");
        }
        else{
            List<String> sublista = getMazo().get(posicion).getCarta();
            int largo = sublista.size();
            int total = calculo(largo);
            System.out.println("La cantidad de elementos necesarios son: " + total);
        }
    }

    /**
     * Obtiene las cartas (Card) que hacen falta para poder tener un set válido
     */
    public void missingCards(){

        int maxC = calculo(getNumC());
        if(getMazo().size() == maxC){
            System.out.println("El set de cartas esta completo");
        }
        else{
            System.out.println("Las cartas faltantes son:");
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
                }
                j = 0;
                i = i + 1;
            }
        }
    }

    /**
     * Obtiene una representación en base string del set de cartas
     */
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

    /**
     * Compara los elementos de 2 cartas y verifica si solo existe una igualdad entre ellas
     * @param sublista (List<String>) Corresponde a la primera carta
     * @param sublista2 (List<String>). Corresponde a la segunda carta
     * @return Integer Dependiendo del caso que se cumpla
     */
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
                if(elemento.equals(elemento2)){
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

    /**
     * Determina cuantos elementos y cantidad de cartas se necesita para tener un set de cartas válido
     * @param numC (Integer) Corresponde a la cantidad de elementos por carta
     * @return Integer Si se obtiene la cantidad total de elementos y cantidad de cartas necesarias
     */
    public int calculo (int numC){

        return ((numC - 1) * (numC - 1)) + (numC - 1) + 1;
    }

    /**
     * Transforma todo el contenido de un Dobble a String
     * @return String Si se convierte todo el contenido de un Dobble a String
     */
    @Override
    public String toString() {

        return "Dobble{\n" +
                "mazo=" + mazo +
                "\nlis_elementos=" + lis_elementos +
                "\ncantElementos=" + cantElementos +
                "\nnumC=" + numC +
                "\nmaxC=" + maxC +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dobble dobble = (Dobble) o;
        return getCantElementos() == dobble.getCantElementos() && getNumC() == dobble.getNumC() && getMaxC() == dobble.getMaxC() && getMazo().equals(dobble.getMazo()) && getLis_elementos().equals(dobble.getLis_elementos());
    }

}