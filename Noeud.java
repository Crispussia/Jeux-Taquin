import java.util.Arrays;
import java.util.ArrayList;

public class Noeud {

    /* Attribut */
    /**
     * —grille représente la grille du jeu.
     * —pere est la référence vers le noeud père dans l’arbre de recherche.
     * —g est un champs qui sauvegarde le nombre de mouvements effectués depuis la racine de l’arbre
     **/
    private Grille grille;
    private Noeud pere;
    private int g;
    private int heuristique;

    /* Constructeur */

    public Noeud(Grille grille, Noeud p, int g,int heuristique) {

        this.grille = grille;
        this.pere = p;
        this.g = g;
        this.heuristique=heuristique;

    }

    /*Methodes*/
/**
 * méthode getGrille retourne la grille du puzzle.
 * méthode getPere retourne la référence du noeud pere.
 *  fonction h() calcule et retourne l’estimation du nombre de mouvements restant pour arriver à l’état final
 *  méthode g() retourne simplement la valeur de l’attribut g.
 *  methode f() retourne l’évaluation du noeud qui sera utilisée par l’algorithme A* :f=g() +h()
 */
    public Grille getGrille() {
        return grille;
    }

    public Noeud getPere() {
        return pere;
    }

    public int h1() {

        int h = 0;
        int compteur=1;
        int [][] g = new int [this.grille.getTaille()][this.grille.getTaille()];
        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(i!=this.grille.getTaille()-1 || j!=this.grille.getTaille()-1){
                    g[i][j]=compteur;
                    compteur++;
                }else{
                    g[this.grille.getTaille()-1][this.grille.getTaille()-1]=0;
                }

            }
        }
        Grille grille1=new Grille(g);

        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(this.grille.getGrille()[i][j]!=0 && grille1.getValeur(i,j)!=this.grille.getValeur(i,j)  ) {
                    h=h+1;
                }
            }
        }
        return h;
    }

    public int h2() {

        int h = 0;
        int compteur=1;
        int x_initial=0;
        int y_initial=0;
        int x_final=0;
        int y_final=0;


        int [][] g = new int [this.grille.getTaille()][this.grille.getTaille()];
        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(i!=this.grille.getTaille()-1 || j!=this.grille.getTaille()-1){
                    g[i][j]=compteur;
                    compteur++;
                }else{
                    g[this.grille.getTaille()-1][this.grille.getTaille()-1]=0;
                }

            }
        }
        Grille grille1=new Grille(g);

        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(this.grille.getGrille()[i][j]!=0 && this.grille.getValeur(i,j) !=grille1.getValeur(i,j) ) {
                    x_initial=i;
                    y_initial=j;

                    for(int k=0;k<grille1.getTaille();k++) {
                        for(int l=0;l<grille1.getTaille();l++) {
                            if(grille1.getGrille()[k][l]!=0 && grille1.getValeur(k,l)==this.grille.getValeur(i,j)  ) {
                                x_final=k;
                                y_final=l;
                                h=h+ Math.abs(x_initial-x_final)+ Math.abs(y_initial-y_final);
                            }
                        }
                    }
                }
            }
        }
        return h;
    }

    public int g(){
        return this.g;
    }

    public int f(){
        int f=0;
        if(heuristique==1){
            f = this.g() + this.h1();
        }else{
            f = this.g() + this.h2();
        }

        return f;
    }

    public boolean estUnEtatFinal()
    {
        int compteur = 1;
        boolean etatGrille = true;
        int [][] g = new int [this.grille.getTaille()][this.grille.getTaille()];
        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(i!=this.grille.getTaille()-1 || j!=this.grille.getTaille()-1){
                    g[i][j]=compteur;
                    compteur++;
                }else{
                    g[this.grille.getTaille()-1][this.grille.getTaille()-1]=0;
                }
            }
        }
        Grille grille1=new Grille(g);

        for(int i=0;i<this.grille.getTaille();i++) {
            for(int j=0;j<this.grille.getTaille();j++) {
                if(grille1.getValeur(i,j)!=this.grille.getValeur(i,j) && this.grille.getGrille()[i][j]!=0) {
                    etatGrille = false;
                }
            }
        }
        return etatGrille;
    }

    /**
     * La méthode successeurs() retourne une liste de tous les noeuds successeurs obtenus en déplacant la case vide vers le haut, le bas, la gauche et la droite.
     */
    public ArrayList<Grille> Successeurs()
    {
        ArrayList<Grille> GrilleSuccesseur = new ArrayList<Grille>();
        int ligne = grille.getLigne0();
        int colonne = grille.getColonne0();


        if(ligne == 0)
        {
            if(colonne == 0)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
            else if(colonne>0 && colonne<grille.getTaille()-1)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
            else if(colonne==grille.getTaille()-1)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
        }
        else if(ligne>0 && ligne<this.grille.getTaille()-1)
        {
            if(colonne==0)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
            else if(colonne>0 && colonne<this.grille.getTaille()-1)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
            else if(colonne==this.grille.getTaille()-1)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne+1][colonne];
                t[ligne+1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

            }
        }
        else if(ligne==this.grille.getTaille()-1)
        {
            if(colonne==0)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));

            }
            else if(colonne>0 && colonne<this.grille.getTaille()-1)
            {

                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne+1];
                t[ligne][colonne+1]=0;
                GrilleSuccesseur.add(new Grille(t));
            }
            else if(colonne==this.grille.getTaille()-1)
            {
                int[][] t=grille.copier();
                t[ligne][colonne]=t[ligne-1][colonne];
                t[ligne-1][colonne]=0;
                GrilleSuccesseur.add(new Grille(t));

                t=grille.copier();
                t[ligne][colonne]=t[ligne][colonne-1];
                t[ligne][colonne-1]=0;
                GrilleSuccesseur.add(new Grille(t));

            }
        }

        return GrilleSuccesseur;
    }

}
