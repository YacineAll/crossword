package org.upmc.info3I002.core;

import org.upmc.info3I002.grille.Case;
import org.upmc.info3I002.grille.Emplacement;
import org.upmc.info3I002.grille.GrillePlaces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrillePotentiel {
    private final GrillePlaces grillePlaces;
    private final Dictionnaire dictionnaire;
    private final List<Dictionnaire> motsPot;


    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grillePlaces = grille;
        this.dictionnaire = dicoComplet;
        this.motsPot = this.grillePlaces
                .getPlaces()
                .stream()
                .map(e -> {
                    Dictionnaire dict = this.dictionnaire.copy();
                    dict.filtreLongueur(e.size());
                    IntStream.range(0, e.size())
                            .filter(f -> !e.getCases().get(f).isPleine() && !e.getCases().get(f).isVide())
                            .forEach(f -> {
                                dict.filtreParLettre(e.getCases().get(f).getChar(), f);
                            });
                    return dict;
                })
                .collect(Collectors.toList());
    }

    public GrillePotentiel(GrillePlaces grillePlaces, Dictionnaire dictionnaire, List<Dictionnaire> dictionnaireList){
        this.grillePlaces = grillePlaces;
        this.dictionnaire = dictionnaire;
        this.motsPot = IntStream.range(0, grillePlaces.getPlaces().size())
                .mapToObj(idx -> {
                    Emplacement e = grillePlaces.getPlaces().get(idx);
                    Dictionnaire dict = dictionnaireList.get(idx).copy();
                    dict.filtreLongueur(e.size());
                    IntStream.range(0, e.size())
                            .filter(f -> !e.getCases().get(f).isPleine() && !e.getCases().get(f).isVide())
                            .forEach(f -> {
                                dict.filtreParLettre(e.getCases().get(f).getChar(), f);
                            });
                    return dict;
                }).collect(Collectors.toList());
    }

    private void initListOfWordToPlac(GrillePlaces grille, Dictionnaire dicoComplet) {
        for (Emplacement emplacement : grille.getPlaces()) {
            Dictionnaire dico = dicoComplet.copy();
            dico.filtreLongueur(emplacement.size());
            int i = 0;
            for (Case c : emplacement.getCases()) {
                if (!(c.isPleine() || c.isVide())) {
                    dico.filtreParLettre(c.getChar(), i);
                }
                i++;
            }
            motsPot.add(dico);
        }
    }
    public GrillePotentiel fixer(int m, String soluce) {
        return new GrillePotentiel(grillePlaces.fixer(m, soluce), dictionnaire, this.motsPot);
    }

    public boolean isDead() {
        return this
                .motsPot
                .stream()
                .parallel()
                .anyMatch(e -> e.size() == 0);
    }

    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

    public GrillePlaces getGrillePlaces() {
        return grillePlaces;
    }

    public Dictionnaire getDictionnaire() {
        return dictionnaire;
    }
}
