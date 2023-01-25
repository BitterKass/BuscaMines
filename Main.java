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

                boolean[][] taulellM = crearTaulellSegonsDificultatM(dificultat);
                boolean[][] taulellJ = crearTaulellSegonsDificultatJ(dificultat);

                if (taulellM == null) {
                    System.out.println("Error en la creació del taulell!");
                    System.exit(-1);
                }
                if (jugar(taulellJ, taulellM)) {
                    System.out.println("Felicitats! Ets un/a campió/na!");
                } else {
                    System.out.println("Oh! Has perdut...");
                    dibuixarTaulellM(taulellM);
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

    static boolean[][] crearTaulellSegonsDificultatM(int dificultat) {
        boolean[][] taulellM;
        switch (dificultat) {
            case 1 -> {
                taulellM = crearTaulellM(NMINES_L1, MIDA_L1);

            }
            case 2 -> {
                taulellM = crearTaulellM(NMINES_L2, MIDA_L2);
            }
            case 3 -> {
                taulellM = crearTaulellM(NMINES_L3, MIDA_L3);
            }
            default -> {
                taulellM = null;
            }
        }

        return taulellM;
    }

    static boolean[][] crearTaulellSegonsDificultatJ(int dificultat) {
        boolean[][] taulellJ;
        switch (dificultat) {
            case 1 -> {
                taulellJ = crearTaulellJ(MIDA_L1);
            }
            case 2 -> {
                taulellJ = crearTaulellJ(MIDA_L2);
            }
            case 3 -> {
                taulellJ = crearTaulellJ(MIDA_L3);
            }
            default -> {
                taulellJ = null;
            }
        }

        return taulellJ;
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

    static boolean[][] crearTaulellJ(int mida) { //Provisionalment ho tenim junt
        //Creem taulell de mines
        boolean[][] taulellJ = new boolean[mida][mida];
        return taulellJ;
    }

    static boolean jugar(boolean[][] taulellJ, boolean[][] taulellM) {
        int coorX = 0;
        int coorY = 0;
        int mida = taulellJ.length;
        int mines = 0;
        int cont = 0;
        do {
            for (int i = 0; i < taulellM.length; i++) {
                for (int j = 0; j < taulellM.length; j++) {
                    if (taulellM[i][j]) {
                        mines++;
                    }
                    cont++;
                }
            }
        } while (cont < mida);
        int casellesDisponibles = mida * mida - mines;

        do {
            dibuixarTaulellJ(taulellJ);
            int[] coordenades = demanarCoordenades(taulellJ);
            coorX = coordenades[0];
            coorY = coordenades[1];
            if (taulellM[coorX][coorY]) {
                return false;
            }
            //Comprovem si no hi ha mina, i si no hi ha mina la posem i comptem que l'hem posat
            if (!taulellJ[coorX][coorY]) {
                taulellJ[coorX][coorY] = true;
                casellesDisponibles--;
            } else System.out.println("Ja has entrat aquesta casella");

        } while (casellesDisponibles > 0);
        return true;
    }

    static void dibuixarTaulellJ(boolean[][] taulellJ) {
        for (int i = 0; i < taulellJ.length; i++) {
            for (int j = 0; j < taulellJ.length; j++) {
                if (taulellJ[i][j]) {
                    System.out.print(" # ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static void dibuixarTaulellM(boolean[][] taulellM) {
        for (int i = 0; i < taulellM.length; i++) {
            for (int j = 0; j < taulellM.length; j++) {
                if (taulellM[i][j]) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    static int[] demanarCoordenades(boolean[][] taulellJ) {
        boolean error = false;
        int coorX = 0;
        int coorY = 0;
        Scanner lector = new Scanner(System.in);
        do {
            error = false;
            try {
                System.out.println("Entra la coordenada X: ");
                coorX = Integer.parseInt(lector.nextLine());
                System.out.println("Entra la coordenada Y: ");
                coorY = Integer.parseInt(lector.nextLine());
                coorX --;
                coorY --;
                if (taulellJ[coorX][coorY] == true){
                    System.out.println("Hi ha una mina!!");
                }else System.out.println("No hi ha una mina");

            } catch (Exception e) {
                System.out.println("Vigila de no introduir nombres que exedeixin el tamany del taulell(0 i nombres negatius inclosos) i tambe no entris lletres o altres caracters similars!!");
                error = true;
            }
        } while (error == true);
        return new int[]{coorX, coorY};
    }
}
