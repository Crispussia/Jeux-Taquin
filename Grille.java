import java.util.Arrays;
import java.util.Objects;

public class Grille {

    /*Attributs de la classe grille*/

    /**
     * grille:correspond à la grille du puzzle.Elle est implémentée avec une matrice carrée d’entiers (nombre de lignes = nombre de colonnes).
     * taille :représente la taille du puzzle.
     * ligne0 :représente l’indice de la ligne ou se trouve la case vide (la case 0).
     * colonne0 :représente l’indice de la colonne ou se trouve la case vide (la case 0).
     */
    private  int[][] grille;
    private int taille;
    private int ligne0;
    private int colonne0;

    /* Constructeur */

    /**
     * constructeur Grille(g:int[][]) initialise la grille de l’instance avec la grille g passée en paramètre.
     *
     */
    public Grille(int[][] g){
        this.grille = g;
        this.taille = g.length;

        for(int i=0;i< this.taille ; i++){
            for(int j=0; j< this.taille ; j++){
                if (g[i][j]==0){
                    this.ligne0=i;
                    this.colonne0=j;
                }
            }
        }
    }

    /*Accesseur*/

    /**
     *méthode getValeur retourne la valeur de la case qui se trouve à la ligne i et la colonne j
     * méthodes getGrille,getTaille,getLigne0,getColonne0 permettent de retourner les valeurs des attributs.
     */
    public int getColonne0() {
        return colonne0;
    }

    public int getLigne0() {
        return ligne0;
    }

    public int getTaille() {
        return taille;
    }

    public int[][] getGrille() {
        return grille;
    }

    public int getValeur(int i,int j){
        return this.grille [i][j];
    }

    /**
     * méthode copier() retourne une copie de la grille de l’instance.
     */
    public int[][] copier(){
        int [][] NewGrille = new int [this.taille][this.taille];
        for(int i = 0; i < this.taille; i++) {
            for(int j = 0; j < this.taille; j++) {
                NewGrille[i][j] = this.grille[i][j];
            }
        }
        return NewGrille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grille grille1 = (Grille) o;
        return taille == grille1.taille && ligne0 == grille1.ligne0 &&
                colonne0 == grille1.colonne0 && Arrays.deepEquals(grille, grille1.grille);
    }

    /**
     *méthode equals prend un objet en paramètre et return true ou false
     */



    /* méthode toString() : Affichage */
    public String toString(){
        String str = "";
        String bord="";
        for (int k = 0; k < this.taille; k++) {
            bord= bord + "+---";
        }
        str=bord +"+\n| ";
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
               str = str + this.getValeur(i, j) + " | ";
            }
            if(i==this.taille -1  ){
                str= str + "\n"+ bord +"+\n";
            }else{
                str= str + "\n"+ bord +"+\n| ";
            }
        }

        return str;
    }
}

