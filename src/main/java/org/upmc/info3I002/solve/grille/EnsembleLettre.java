package org.upmc.info3I002.solve.grille;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class EnsembleLettre extends ArrayList<Character> {

    private static final long serialVersionUID = 1L;


    /**
     * Construit une liste vide qui prend des caractere
     */
    public EnsembleLettre() {
        super();
    }

    /**
     * Construit une liste qui contient la collection de character passe en parametre
     *
     * @param c c'est une collection de caractere
     */
    public EnsembleLettre(Collection<? extends Character> c) {
        super(c);
    }

    /**
     * Ajouter un caractere dans la liste sans doublon
     */
    @Override
    public boolean add(Character e) {
        if (!this.contains(e)) {
            return super.add(e);
        } else
            return false;
    }

    @Override
    public String toString() {
        return this.stream()
                .parallel()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "{", "}"));
    }

}


