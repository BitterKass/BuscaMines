import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static final int NMINES_L1 = 10;
    static final int NMINES_L2 = 20;
    static final int NMINES_L3 = 40;
    static final int MIDA_L1 = 10;
    static final int MIDA_L2 = 20;
    static final int MIDA_L3 = 40;

    public static void main(String[] args) {
        int opcioMenu = mostrarMenu();

        switch (opcioMenu) {
            case 0 -> {
                System.out.println("Fins aviat!");
                System.exit(0);
            }
            case 1 -> {
                int dificultat = demanarDificultat();

                boolean[][] taulell = crearTaulellSegonsDificultat(dificultat);
                if (taulell == null) {
                    System.out.println("Error en la creació del taulell!");
                    System.exit(-1);
                }

                if (jugar(taulell)) {
                    System.out.println("Felicitats! Ets un/a campió/na!");
                } else {
                    System.out.println("Oh! Has perdut...");
                }

                dibuixarTaulell(taulell, null, true);
            }
        }
    }

    static int calcularCasellesDisponibles(boolean[][] taulell) {
        int casellesDisponibles = 0;

        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell.length; j++) {
                if (!taulell[i][j]) {
                    casellesDisponibles += 1;
                }
            }
        }
        return casellesDisponibles;
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

    static int demanarDificultat() {
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

    static boolean[][] crearTaulellSegonsDificultat(int dificultat) {
        boolean[][] taulellM;
        boolean[][] taulellJ;
        switch (dificultat) {
            case 1 -> {
                taulellM = crearTaulellM(NMINES_L1, MIDA_L1);
                taulellJ = crearTaulellM(NMINES_L1, MIDA_L1);
            }
            case 2 -> {
                taulellM = crearTaulellM(NMINES_L2, MIDA_L2);
                taulellJ = crearTaulellM(NMINES_L2, MIDA_L2);
            }
            case 3 -> {
                taulellM = crearTaulellM(NMINES_L3, MIDA_L3);
                taulellJ = crearTaulellM(NMINES_L3, MIDA_L3);
            }
            default -> {
                taulell = null;
            }
        }

        return taulell;
    }

    static boolean[][] crearTaulellM(int mines, int mida) { //Provisionalment ho tenim junt
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

        return taulell;
    }

    static boolean[][] crearTaulellM(int mines, int mida) { //Provisionalment ho tenim junt
        //Creem taulell de mines
        boolean[][] taulellM = new boolean[mida][mida];
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
            if (!taulellM[coorX][coorY]) {
                taulellM[coorX][coorY] = true;
                minesPosades++;
            }
        } while (minesPosades < mines);

        return taulellM;
    }

    static boolean[][] crearTaulellJ(int mines, int mida) { //Provisionalment ho tenim junt
        //Creem taulell de mines
        boolean[][] taulellJ = new boolean[mida][mida];
        int coorX = 0;
        int coorY = 0;
        int casellesDisponibles = mida * mida - mines

        do {
            //Comprovem si no hi ha mina, i si no hi ha mina la posem i comptem que l'hem posat
            if (!taulellJ[coorX][coorY]) {
                taulellJ[coorX][coorY] = true;
                casellesDisponibles++;
            }
        } while (casellesDisponibles > 0);

        return taulellJ;
    }
/*    static void dibuixarTaulell(boolean[][] taulell, ArrayList<int[]> coordenadesJugades, boolean mostrarSolucio) {
        for (int i = 0; i < taulell.length; i++) {
            for (int j = 0; j < taulell.length; j++) {
                if (mostrarSolucio) {
                    if (!taulell[i][j]) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" X ");
                    }
                } else {
                    if (coordenadaTrobada(coordenadesJugades, new int[]{i, j})) {
                        if (!taulell[i][j]) {
                            System.out.print(" # ");
                        } else {
                            System.out.print(" X ");
                        }
                    } else {
                        System.out.print(" O ");
                    }
                }
            }
            System.out.println();
        }
    }*/


    static int[] demanarCoordenades() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Entra la coordenada X: ");
        int x = Integer.parseInt(lector.nextLine());
        System.out.println("Entra la coordenada Y: ");
        int y = Integer.parseInt(lector.nextLine());
        return new int[]{x - 1, y - 1};
    }
}
