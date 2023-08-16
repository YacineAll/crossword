package org.upmc.info3I002.core;

import org.junit.jupiter.api.Test;
import org.upmc.info3I002.grille.Grille;
import org.upmc.info3I002.grille.GrilleLoader;
import org.upmc.info3I002.grille.GrillePlaces;
import org.upmc.info3I002.utils.Tools;

import static org.junit.jupiter.api.Assertions.*;

public class GrillePotentielTest {


    @Test
    public void testSplit() {

        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        // grille 6x5, mots sans croisement
        Grille gr = GrilleLoader.loadGrille("src/test/resources/data/split.grl");

        assertEquals(5, gr.nbCol());
        assertEquals(6, gr.nbLig());


        GrillePlaces grille = new GrillePlaces(gr);

        GrillePotentiel gp = new GrillePotentiel(grille, gut);

        assertFalse(gp.isDead());

        int[] expected = { 5916, 427, 81, 81 };

        Tools.testNombrePot(gp, expected);

        System.out.println("Succès test GrillePotentiel : split.");
    }

    @Test
    public void testEasy2() {

        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        // grille 6x5, mots sans croisement
        Grille gr = GrilleLoader.loadGrille("src/test/resources/data/easy2.grl");

        assertEquals(5, gr.nbCol());
        assertEquals(5, gr.nbLig());


        GrillePlaces grille = new GrillePlaces(gr);

        GrillePotentiel gp = new GrillePotentiel(grille, gut);

        assertFalse(gp.isDead());

        int[] expected = { 245, 302, 1 };

        Tools.testNombrePot(gp, expected);

        System.out.println("Succès test GrillePotentiel : easy2.");
    }


    @Test
    public void testMakeEasy2() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        Grille gr = GrilleLoader.loadGrille("src/test/resources/data/easy.grl");
        assertEquals(5, gr.nbCol());
        assertEquals(5, gr.nbLig());
        GrillePlaces grille = new GrillePlaces(gr);
        GrillePotentiel gp = new GrillePotentiel(grille, gut);
        assertTrue(gp.getMotsPot().get(2).size() > 1);
        GrillePotentiel gp2 = gp.fixer(2, "chats");
        assertTrue(gp.getMotsPot().get(2).size() > 1);
        int[] expected = { 245, 302, 1 };
        Tools.testNombrePot(gp2, expected);
        System.out.println(gp2.getGrillePlaces().getGrille());
        System.out.println("Succès test GrillePotentiel : make easy 2.");
    }



}
