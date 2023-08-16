package org.upmc.info3I002.solve.grille;

import org.junit.jupiter.api.Test;
import org.upmc.info3I002.core.Dictionnaire;
import org.upmc.info3I002.core.GrillePotentiel;
import org.upmc.info3I002.grille.Grille;
import org.upmc.info3I002.grille.GrilleLoader;
import org.upmc.info3I002.grille.GrillePlaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CroixContrainteTest {
    @Test
    public void test1() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        Grille gr = GrilleLoader.loadGrille("src/test/resources/data/easy2.grl");

        System.out.println(gr);

        GrillePlaces grille = new GrillePlaces(gr);
        GrillePotentiel gp = new GrillePotentiel(grille, gut);

        CroixContrainte c1 = new CroixContrainte(0,3,2,0);
        assertEquals(242, c1.reduce(gp));

        CroixContrainte c2 = new CroixContrainte(1,3,2,4);
        assertEquals(236, c2.reduce(gp));
    }

    @Test
    public void test2() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        Grille gr = GrilleLoader.loadGrille("src/test/resources/data/hard.grl");

        System.out.println(gr);

        GrillePlaces grille = new GrillePlaces(gr);
        GrillePotentiel gp = new GrillePotentiel(grille, gut);

        CroixContrainte c = new CroixContrainte(0,0,5,0);
        assertEquals(0, c.reduce(gp));
    }

}
