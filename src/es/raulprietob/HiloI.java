package es.raulprietob;

public class HiloI implements Runnable {
    private Thread miHilo;
    /**
     * Volatile hace que no se cachee, sólo busca en memoria RAM. Ineficiente pero
     * más seguro, sobretodo trabajando con hilos. Evita lecturas sucias.
     */
    private volatile boolean vivo; // Flag que controla la vida del hilo
    private volatile boolean suspendido;
    public int siestaT;

    public HiloI() {
        miHilo = new Thread(this, "Nuevo hilo");
        this.vivo = true;
        miHilo.start();
    }

    public boolean estaVivo() {
        return this.vivo;
    }

    public void siesta(int tiempo) throws InterruptedException {
        System.out.println("Siesta obligada desde el hilo principal");
        suspendido = true;
        siestaT = tiempo;
    }

    public void interrumpir() {
        if (this.miHilo != null && suspendido) // Garantiza que el hilo tiene memoria y que se va a dormir
            this.miHilo.interrupt();
    }

    public void detener() {
        this.vivo = false;
    }

    public void esperar() throws InterruptedException {
        this.miHilo.join();
    }

    @Override
    public void run() {
        long suma = 0;
        System.out.println("Corriendo el cuerpo del hijo");
        while (vivo) {
            if (suspendido) {
                System.out.println("El hijo pasa a dormir ");
                try {
                    System.out.println("Hilo comienza suspensión");
                    Thread.sleep(siestaT);
                    System.out.println("Hilo vuelve de la suspensión");
                } catch (InterruptedException e) {
                    System.err.println("El hilo ha sido despertado de su suspendido, o sea, interrumpido");
                }
                finally {
                    suspendido = false;
                }
            } else {
                suma = 0;
                for (int i = 0; i < 100000; i++) {
                    suma += i;
//                    System.out.println("Hilo sumando... " + suma);
                }
            }
        }
        System.out.println("Hilo finalizando y muriendo...");
    }

}
