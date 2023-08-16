package org.upmc.info3I002.grille;

public class Grille {
    /** Un tableau multidimensionnel de Cases */
    private Case[][] cases;

    /**
     * nombre de ligne de la matrice
     */
    private int hauteur;

    /**
     * nombre de colonne de la matrice
     */
    private int largeur;

    /**
     * Construit une Grille de taille hauteur*largeur spécifiées de cases vides
     *
     * @param hauteur nombre de ligne de la Grille
     * @param largeur nombre de Colonne de la Grille
     */
    public Grille(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;

        this.cases = new Case[hauteur][largeur];

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                cases[i][j] = new Case(i, j, ' ');
            }
        }

    }

    /**
     * Accede a une case de ligne lig et de la colonne col de la grille
     *
     * @param lig la ligne de la case
     * @param col la colonne de la case
     * @return la case de la ligne lig et de la colonne col
     */
    public Case getCase(int lig, int col) {
        return this.cases[lig][col];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                sb.append(cases[i][j].getChar());
                sb.append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Accede aux nombre des lignes
     *
     * @return nombre des lignes de la Grille
     */
    public int nbLig() {
        return hauteur;
    }

    /**
     * Accede aux nombre des colonnes
     *
     * @return nombre des colonnes de la Grille
     */
    public int nbCol() {
        return this.largeur;
    }

    /**
     *
     * Crée et retourne une copie a cette grille
     *
     * @return une copie de cette Grille
     */
    public Grille copy() {

        Grille g = new Grille(hauteur, largeur);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                g.cases[i][j] = new Case(i, j, this.cases[i][j].getChar());
            }
        }
        return g;
    }
}
