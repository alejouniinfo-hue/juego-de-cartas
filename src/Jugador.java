import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MARGEN_IZQUIERDA = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.setLayout(null);
        pnl.removeAll();
        int posicion = MARGEN_IZQUIERDA + DISTANCIA * (TOTAL_CARTAS - 1);
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN_SUPERIOR);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos(){

    int[] contadores = new int[NombreCarta.values().length];
    boolean[] usada = new boolean[TOTAL_CARTAS];

    String resultado = "";

    // contar cartas iguales
    for(int i=0;i<cartas.length;i++){
        contadores[cartas[i].getNombre().ordinal()]++;
    }

    // detectar pares, ternas, cuartas
    for(int i=0;i<contadores.length;i++){
        if(contadores[i] >= 2){

            resultado += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";

            for(int j=0;j<cartas.length;j++){
                if(cartas[j].getNombre().ordinal() == i){
                    usada[j] = true;
                }
            }
        }
    }

    // detectar escalera con misma pinta
    for(int i=0;i<cartas.length;i++){
        for(int j=i+1;j<cartas.length;j++){

            int valor1 = cartas[i].getNombre().ordinal();
            int valor2 = cartas[j].getNombre().ordinal();

            if(Math.abs(valor1 - valor2) == 1 &&
               cartas[i].getPinta() == cartas[j].getPinta()){

                resultado += "Escalera de "
                        + cartas[i].getNombre() + " y "
                        + cartas[j].getNombre()
                        + " de " + cartas[i].getPinta() + "\n";

                usada[i] = true;
                usada[j] = true;
            }
        }
    }

    // sumar cartas que no pertenecen a grupos
    int puntaje = 0;

    for(int i=0;i<cartas.length;i++){
        if(!usada[i]){
            puntaje += cartas[i].getNombre().ordinal() + 1;
        }
    }

    resultado += "\nPuntaje final: " + puntaje;

    return resultado;
}
}
