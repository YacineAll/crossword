package org.upmc.info3I002.grille;

public class Case {
    /** la ligne de la Case */
    private int ligne;
    /** la colonne de la Case */
    private int colonne;

    /** le caractere qui est dans la Case une lettre,espace ou * */
    private char caractere;

    /**
     * Constructeur de la case
     *
     * @param lig: ligne de la Case
     * @param col: colonne de la case
     * @param c: le caractere, une lettre, un espace pour une case vide, un * pour
     *        une case pleine
     */
    public Case(int lig, int col, char c) {
        this.ligne = lig;
        this.colonne = col;
        this.caractere = c;
    }

    /**
     * @return true si la case contient un espace, false si non
     */
    public boolean isVide() {
        return this.caractere == ' ';
    }

    /**
     * @return true si la case contient un * (donc elle est pleine) , false Si non
     */
    public boolean isPleine() {
        return this.caractere == '*';
    }

    /**
     * modifier la ligne de la Case avec la nouvelle valeur ligne
     *
     * @param ligne la nouvelle ligne de la Case
     */
    public void setLig(int ligne) {
        this.ligne = ligne;
    }

    /**
     * modifier la colonne de la Case avec la nouvelle valeur colonne
     *
     * @param ligne la nouvelle colonne de la Case
     */
    public void setCol(int colonne) {
        this.colonne = colonne;
    }

    /**
     * modifier le caractere qui est dans la Case avec la nouvelle valeur caractere
     *
     * @param carctere le nouveau caractere de la Case
     */
    public void setChar(char caractere) {
        this.caractere = caractere;
    }

    /**
     * Accède aux carctere de la Case
     *
     * @return le carctere qui est dans la case
     */
    public char getChar() {
        return caractere;
    }

    /**
     * Accède à la ligne de la Case
     *
     * @return la ligne de la case
     */
    public int getCol() {
        return colonne;
    }

    /**
     * Accède à la colonne de la Case
     *
     * @return la colonne de la case
     */
    public int getLig() {
        return ligne;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Case))
            return false;
        Case other = (Case) obj;
        if (caractere != other.caractere)
            return false;
        if (colonne != other.colonne)
            return false;
        if (ligne != other.ligne)
            return false;
        return true;
    }
}
