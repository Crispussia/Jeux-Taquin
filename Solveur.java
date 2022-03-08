import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solveur {

    /* Attribut  */

    private ArrayList<Noeud> liste_noeuds_ouverts=new ArrayList<Noeud>();
    private ArrayList<Noeud> liste_noeuds_fermes=new ArrayList<Noeud>();
    private  int heuristique=1;

    public int getHeuristique() {
        return heuristique;
    }

    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }

    /**
     * méthode chargerFichier() permet de lire un puzzle à partir d’un fichier passé en paramètre et retourne la grille initial du jeu.
     */
    public static Grille chargerFichier(String nomFichier){

        int [][] grille = null;
        try {

            File fichier = new File(nomFichier);
            Scanner scanner = new Scanner(fichier);
            Scanner sc = new Scanner(scanner.nextLine());
            int N= sc.nextInt();
            grille = new int [N][N];
            int i=0;

            while(scanner.hasNext()) {
                sc = new Scanner(scanner.nextLine());
                int j=0;
                while(sc.hasNext()) {
                    grille[i][j]= sc.nextInt();
                    j++;
                }
                i++;
            }
            scanner.close();
            sc.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        Grille etatInitial = new Grille(grille);
        return etatInitial;
    }

    public Noeud meilleurNoeud(ArrayList<Noeud> liste_noeuds) {
        int f=0;
        Noeud meilleurNoeud = null;
        for( Noeud noeudTemp : liste_noeuds)
        {
            if(meilleurNoeud == null)
            {
                meilleurNoeud = noeudTemp;
                f = noeudTemp.f();
            }
            else {
                if(noeudTemp.f()<f) {
                    f=noeudTemp.f();
                    meilleurNoeud=noeudTemp;
                }
            }
        }
        return meilleurNoeud;
    }

    public boolean existe(ArrayList<Noeud> liste,Grille grille)
    {
        for( Noeud noeud : liste)
        {
            if(grille.equals(noeud.getGrille())==true)
                return true;
        }
        return false;
    }
    public Noeud identique(ArrayList<Noeud> liste,Grille grille)
    {
        for( Noeud noeud : liste)
        {
            if(grille.equals(noeud.getGrille())==true)
                return noeud;
        }
        return null;
    }

    public Noeud algoAstar(Grille initiale){
        Scanner clavier = new Scanner(System.in);
        System.out.println("Choisissez la fonction heuristique: \n\n1 : H1  \n2 : H2\n");
        System.out.print("Choix : ");
        int choix=0;
        int position = 1;
        while (position == 1) {
            choix = 0;
            clavier = new Scanner(System.in);
            try {
                position = 2;
                choix = clavier.nextInt();
                if (choix == 1 || choix == 2) {
                    position = 2;
                } else {
                    position = 1;
                    System.out.println("Choisissez la fonction heuristique: \n\n1 : H1  \n2 : H2\n");
                    System.out.print("Choix : ");
                }

            } catch (Exception e) {
                position = 1;
                System.out.println("Choisissez la fonction heuristique: \n\n1 : H1  \n2 : H2\n");
                System.out.print("Choix : ");

            }
        }

        setHeuristique(choix);
        int j = 1;
        this.liste_noeuds_ouverts.add(new Noeud(initiale,null,0,heuristique));
        Noeud noeudCourant = meilleurNoeud(this.liste_noeuds_ouverts);
        while(!this.liste_noeuds_ouverts.isEmpty() && !noeudCourant.estUnEtatFinal()){

            noeudCourant = meilleurNoeud(this.liste_noeuds_ouverts);
            this.liste_noeuds_fermes.add(noeudCourant);
            this.liste_noeuds_ouverts.remove(noeudCourant);
            ArrayList<Grille> Successeurs = noeudCourant.Successeurs();

            for( Grille s : Successeurs) {

                Noeud n = new Noeud(s,noeudCourant,noeudCourant.g()+1,heuristique);
                if(existe(this.liste_noeuds_fermes,s)==false) {

                    if(existe(liste_noeuds_ouverts,s)==false)
                        this.liste_noeuds_ouverts.add(n);
                    else{
                        for( Noeud noeud : this.liste_noeuds_ouverts)
                        {
                            if(s.equals(noeud.getGrille())==true)
                            {
                                if(noeud.f()>n.f()) {
                                    this.liste_noeuds_ouverts.set(this.liste_noeuds_ouverts.indexOf(noeud),n);
                                }
                            }
                        }

                    }

                }

            }

        }

        if((this.liste_noeuds_ouverts.isEmpty() && !noeudCourant.estUnEtatFinal()))
        {
            return null;
        }
        return noeudCourant;
    }
    public ArrayList<Grille> cheminComplet(Noeud noeudFinal)
    {
        ArrayList<Grille> liste_grilles=new ArrayList<Grille>();
        while(noeudFinal.getPere()!=null)
        {
            Grille g=noeudFinal.getGrille();
            noeudFinal=noeudFinal.getPere();
            liste_grilles.add(g);
        }
        for (int k = 0, j =liste_grilles.size() - 1; k < j; k++)
        {
            liste_grilles.add(k, liste_grilles.remove(j));
        }

        return liste_grilles;
    }


}
