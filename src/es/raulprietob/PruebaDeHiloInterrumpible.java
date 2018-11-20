package es.raulprietob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PruebaDeHiloInterrumpible {

    public static void main(String[] args) throws IOException, InterruptedException {
        HiloI unHilo = new HiloI();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tecla;

        System.out.println("Iniciando el hilo principal--");
        while (unHilo.estaVivo()) {
            System.out.println("Hilo vivo, [i] interrumpir, [k] matar, [s] siesta");
            tecla = br.readLine();
            if (tecla.equals("i"))
                unHilo.interrumpir();
            if (tecla.equals("k"))
                unHilo.detener();
            if (tecla.equals("s"))
                unHilo.siesta(5000);
        }
        unHilo.esperar();
        System.out.println("Hilo principal finalizado");


    }

}
