package org.upmc.info3I002.grille;

import java.util.ArrayList;
import java.util.List;

public class Emplacement {

    private final int id;
    private static int cpt = 0;

    /** Une liste de Case */
    private List<Case> lettres = new ArrayList<Case>();

    public Emplacement() {
        id = cpt++;
    }

    public int getId() {
        return id;
    }

    /**
     * Obtenir la taille de l'emplacement
     *
     * @return la taille de la liste des Cases
     */
    public int size() {
        return this.lettres.size();
    }

    /**
     * Ajouter une Case à l'emplacement
     *
     * @param c une case à ajouter dans l'emplacement
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(Case c) {
        return this.lettres.add(c);
    }

    /**
     * Accède a la liste des cases de l'emplacement
     *
     * @return liste des lettres de l'emplacement
     */
    public List<Case> getCases() {
        return this.lettres;
    }

    /**
     * Vide la liste des cases
     *
     * La liste des cases devient vide apre avoir appeler a cette methode
     */
    public void vider() {
        this.lettres.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("id: " + id + " ");
        sb.append(String.format("(%d,%d)-->(%d,%d)", lettres.get(0).getLig(), lettres.get(0).getCol(),
                lettres.get(lettres.size() - 1).getLig(), lettres.get(lettres.size() - 1).getCol()));
        return sb.toString();
    }

    /**
     * Parcour la liste des cases de l'emplacement et renvoit true si il a une case
     * vide false sinon
     *
     * @return true si l'emplacement a une case vide false sinon
     */
    public boolean hasCaseVide() {
        for (Case case1 : lettres) {
            if (case1.isVide()) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Emplacement))
            return false;
        Emplacement other = (Emplacement) obj;
        if (other.id != id) {
            return false;
        }
        if (lettres == null) {
            return other.lettres == null;
        } else {
            return lettres.equals(other.lettres);
        }
    }
}
