import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("\nGrille initiale\n");
        Grille etatInitial=Solveur.chargerFichier("puzzles/puzzle03.txt");
        System.out.println(etatInitial);
        Solveur solver=new Solveur();
        Noeud AstarNoeud=solver.algoAstar(etatInitial);

            int i = 0;


        try {
            if(AstarNoeud!=null) {

                for(Grille grilleTest : solver.cheminComplet(AstarNoeud)){
                    i++;
                    System.out.println("\nEtape  " + i + " :");
                    System.out.println(grilleTest);
                }
                System.out.println("Nombre de mouvement depuis la racine : " + AstarNoeud.g());


            }else{
                System.out.println("\nCette grille n'a pas de solution");
            }
        } catch (Exception e) {
            System.out.println("\nCette grille n'a pas de solution");
        }


    }
}
