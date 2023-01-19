import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opcioMenu = mostrarMenu();
        final int NMINES_L1 = 10;
        final int NMINES_L2 = 20;
        final int NMINES_L3 = 40;
        final int MIDA_L1 = 10;
        final int MIDA_L2 = 20;
        final int MIDA_L3 = 40;

        switch (opcioMenu) {
            case 0 -> {
                System.out.println("Fins aviat!");
                System.exit(0);
            }
            case 1 -> {
                int opcioDificultat = demanarNivell();
                switch (opcioDificultat) {
                    case 1 -> {
                        crearIDibuixarTaulellM(NMINES_L1, MIDA_L1);
                    }
                    case 2 -> {
                        crearIDibuixarTaulellM(NMINES_L2, MIDA_L2);
                    }
                    case 3 -> {
                        crearIDibuixarTaulellM(NMINES_L3, MIDA_L3);
                    }
                    default -> System.out.println("Opció no disponible. Entra una opció vàlida.");
                }

            }
        }

    }

    static int mostrarMenu() {
        Scanner lector = new Scanner(System.in);
        System.out.println("""
                --------------------Menú Buscamines-------------------
                ------------------------------------------------------
                ------------------Selecciona una opció----------------
                                   1-> Jugar Partida
                                   0-> Sortir del Joc
                ------------------------------------------------------
                """);
        int opcioMenu = Integer.parseInt(lector.nextLine());
        return opcioMenu;
    }

    static int demanarNivell() {
        Scanner lector = new Scanner(System.in);
        System.out.println("""
                ------------------------------------------------------
                ---------------Selecciona una dificultat--------------
                ------------------------------------------------------
                                   1-> Fàcil
                                   2-> Intermig
                                   3-> Difícil
                ------------------------------------------------------
                """);
        int opcioDificultat = Integer.parseInt(lector.nextLine());
        return opcioDificultat;
    }

    static boolean[][] crearIDibuixarTaulellM(int mines, int mida) { //Provisionalment ho tenim junt
        //Creem taulell de mines
        boolean[][] taulell = new boolean[mida][mida];
        Random r = new Random();
        int coorX = 0;
        int coorY = 0;
        //Inicialitzem comptador de mines (0 abans de començar)
        int minesPosades = 0;

        do {
            //Generem coordenades aleatòries vàlides
            coorX = r.nextInt(0, mida - 1);
            coorY = r.nextInt(0, mida - 1);
            //Comprovem si no hi ha mina, i si no hi ha mina la posem i comptem que l'hem posat
            if (!taulell[coorX][coorY]) {
                taulell[coorX][coorY] = true;
                minesPosades++;
            }
        } while (minesPosades < mines);

        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                if (!taulell[i][j]){
                    System.out.print(" O ");
                }else System.out.print(" X ");
            }
            System.out.println();
        }
        return taulell;
    }
    static void mostrarTaulellM() {

    }
}
