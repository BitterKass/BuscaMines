import java.util.Scanner;

class Dificultat{
    int nMines;
    int midaVertical;
    int midaHoritzontal;
}

public class Main {
    static int opcioDificultat;

    public static void main(String[] args) {
        int opcioMenu = mostrarMenu();
        Dificultat[] dificultats;
        dificultats = new Dificultat[3];
        switch (opcioMenu){
            case 0 -> {
                System.out.println("Fins a la proxima");
                System.exit(0);
            }
            case 1 -> {
                opcioDificultat = demanarNivell();
                switch (opcioDificultat){
                    case 1-> {
                        dificultats[1].nMines = 10;
                        dificultats[1].midaVertical = 10;
                        dificultats[1].midaHoritzontal = 10;
                    }
                    case 2-> {
                        dificultats[2].nMines = 20;
                        dificultats[2].midaVertical = 20;
                        dificultats[2].midaHoritzontal = 20;
                    }
                    case 3-> {
                        dificultats[3].nMines = 40;
                        dificultats[3].midaVertical =40;
                        dificultats[3].midaHoritzontal = 40;
                    }
                    default -> System.out.println("Selecciona una opció valida.");
                }
                inicialitzarTaulellM(dificultats);
            }
        }
    }
    static int mostrarMenu(){
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
    static int demanarNivell(){
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
        opcioDificultat = Integer.parseInt(lector.nextLine());
        return opcioDificultat;
    }
    static boolean inicialitzarTaulellM(Dificultat[] dificultats){
        boolean[][] taulellM = new boolean [dificultats[opcioDificultat-1].midaVertical][dificultats[opcioDificultat-1].midaHoritzontal];
        return false;
    }
}
