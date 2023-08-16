package org.upmc.info3I002.grille;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrillePlaces {

    /** La Grille a explorer */
    private Grille grille;

    /** La liste des Emplacement ou bien des places */
    private List<Emplacement> places;

    /** nombre de mots horizontaux */
    private int nbh;

    /**
     * Construit un GrillePlaces avec qui on explore la Grille grille pour trouver
     * les emplacements des mots On initialise les attributs On parcour toutes les
     * ligne de la grille et on stock les emplacements trouvés dans la liste places
     * On calcules les emplacement horizontaux On parcour toutes les colonnes de la
     * grille et on stock les emplacements trouvés dans la liste places
     *
     * @param grille une instance de la classe Grille à explorer
     */
    public GrillePlaces(Grille grille) {

        this.grille = grille;

        this.places = new ArrayList<Emplacement>();

        // On parcour toutes les ligne de la grille et on stock les emplacements trouvés
        // dans la liste places
        IntStream.range(0, grille.nbLig()).forEach(i->cherchePlaces(getLig(i)));

        // On calcules les emplacement horizontaux , on la stock dans la variable nbh
        nbh = this.places.size();

        IntStream.range(0, grille.nbCol()).forEach(i->cherchePlaces(getCol(i)));
    }

    /**
     * Accede a la grille
     *
     * @return grille : c'est la grille que on explore
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Accede a la liste des emplacement
     *
     * @return places: liste<Emplacement> c'est la liste des emplacement
     */
    public List<Emplacement> getPlaces() {
        return places;
    }

    /**
     * Get Emplacement of the given index
     * @param index: int the index
     * @return emplacement: Emplacement
     */
    public Emplacement getIndex(int index){
        return places.get(index);
    }

    /**
     * True if emplacement has least one empty case
     * @param index: int the index
     * @return boolean: true or false
     */
    public boolean hasCaseVide(int index){
        return places
                .get(index)
                .getCases()
                .stream()
                .parallel()
                .anyMatch(Case::isVide);
    }

    /**
     * Nombre de mots horizontaux trouve lors de l'exploration de la Grille
     *
     * @return int nombre de mots horizontaux
     */
    public int getNbHorizontal() {
        return this.nbh;
    }

    /**
     * Parcour la ligne lig de la grille et retourne la liste de ces cases
     *
     * @param lig
     * @return Liste<Cases> qui est la liste des cases de la ligne lig
     */
    private List<Case> getLig(int lig) {
        return IntStream
                .range(0, grille.nbCol())
                .mapToObj(i->grille.getCase(lig,i))
                .collect(Collectors.toList());
    }

    /**
     * Parcour colonne col de la grille et retourne la liste de ces cases
     *
     * @param col
     * @return Liste<Cases> qui est la liste des cases de la colonne col
     */
    private List<Case> getCol(int col) {
        return IntStream.range(0, grille.nbLig())
                .mapToObj(i->grille.getCase(i,col))
                .collect(Collectors.toList());
    }

    /**
     * Parcour la liste cases, trouve les emplacements, les stocker dans la liste
     * places
     *
     * @param cases qui est une Liste<Cases>
     */
    private void cherchePlaces(List<Case> cases) {
        // un nouveau emplacement
        Emplacement emplacement = new Emplacement();
        // parcour de la liste des cases
        for (Case c : cases) {
            // si la case n'est pas pleine
            if (!c.isPleine()) {
                // on l'ajoute a l'emplacement
                emplacement.add(c);
                // si non si l'emplacement a au moin deux lettre et on l'a pas deja ajouter dans
                // la liste places
            } else if (emplacement.size() >= 2) {
                // On ajoute l'emplacement dans places
                this.places.add(emplacement);
                emplacement = new Emplacement();
                // Si non
            } else {
                // on le vide
                emplacement.vider();
            }
        }
        // Si on a à la fin un emplacement assez long on l'ajoute dans l'emplacement
        if (emplacement.size() >= 2) {
            this.places.add(emplacement);
        }

    }

    /**
     * Remplie le m iéme emplacment de la grille avec le mot qui est donnee "soluce"
     * renvoie une nouvelle gille avec l'emplacement qui est remplie
     *
     * @param m  l'index de l'emplacment a remplir avec les lettre de mot soluce
     * @param soluce le mot avec ui remplir le m iéme emplacement de la Grille
     * @return une nouvelle Grille avec le m iéme emplacement remplie
     */
    public GrillePlaces fixer(int m, String soluce) {
        Grille copy = this.grille.copy();
        GrillePlaces newGrillePlaces = new GrillePlaces(copy);
        Emplacement mEmplacement = newGrillePlaces.getPlaces().get(m);
        IntStream.range(0, mEmplacement.getCases().size())
                .forEach(idx -> mEmplacement.getCases().get(idx).setChar(soluce.charAt(idx)));
        //int i = 0;
        //for (Case ca : mEmplacement.getCases()) {
        //    ca.setChar(soluce.charAt(i));
        //    i++;
        //}
        return newGrillePlaces;
    }

    @Override
    public String toString() {
        char[][] c = new char[grille.nbLig()][grille.nbCol()];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                c[i][j] = ' ';
            }
        }
        for (Emplacement em : this.places) {
            for (Case ca : em.getCases()) {
                c[ca.getLig()][ca.getCol()] = '*';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                sb.append(c[i][j] + "");
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
