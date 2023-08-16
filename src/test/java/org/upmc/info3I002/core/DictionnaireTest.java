package org.upmc.info3I002.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DictionnaireTest {
    @Test
    public void testAdd() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");

        System.out.println(String.format("La taille de dictionnaire est: %d", gut.size()));
        assertEquals(323422, gut.size());
        System.out.println("Succès load/add Dictionnaire");
    }

    @Test
    public void testCopy() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        // test copie
        Dictionnaire red2 = gut.copy();
        Dictionnaire red3 = gut.copy();
        assertEquals(323422, red2.size());
        assertEquals(323422, red3.size());
        System.out.println("Succès test copy Dictionnaire");
    }

    @Test
    public void testFiltreLen() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");

        // nombres de mots de longueur : 2,3,4...
        int[] expected = { 81, 427, 1807, 5916, 13945, 25577, 38343, 47671, 50254, 45851, 36343, 25313, 15462, 8530,
                4287, 2022, 894, 395, 181, 77, 32, 9, 2, 1, 0 };

        for (int len = 2; len < expected.length + 2; len++) {
            Dictionnaire red = gut.copy();
            int notX = red.filtreLongueur(len);
            // gut n'est pas modifié
            assertEquals(323422, gut.size());
            // taille attendue pour cet ensemble de mots
            assertEquals(expected[len - 2], red.size());
            // resultat attendu : nombre de mots reduits
            assertEquals(323422, red.size() + notX);
            // on ne réduit pas plus en faisant deux fois
            assertEquals(0, red.filtreLongueur(len));
        }
        System.out.println("Succès filtre par longueur");
    }

    @Test
    public void testFiltreLettreFin() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");

        // nombres de mots de longueur : 2,3,4...
        int[] expected = { 81, 427, 1807, 5916, 13945, 25577, 38343, 47671, 50254, 45851, 36343, 25313, 15462, 8530,
                4287, 2022, 894, 395, 181, 77, 32, 9, 2, 1, 0 };
        int[] filtered = { 5, 52, 346, 1406, 3991, 8274, 14045, 19199, 21460, 20105, 16106, 11334, 7050, 3879, 2003, 930, 435, 189, 93, 39, 17, 5, 1, 0, 0 };

        for (int len = 2; len < expected.length + 2; len++) {
            Dictionnaire red = gut.copy();
            red.filtreLongueur(len);
            int notX = red.filtreParLettre('s', len-1);
            // gut n'est pas modifié
            assertEquals(323422, gut.size());
            // taille attendue pour cet ensemble de mots
            assertEquals(filtered[len - 2], red.size());
            // resultat attendu : nombre de mots reduits
            assertEquals(expected[len-2], red.size() + notX);
            // on ne réduit pas plus en faisant deux fois
            assertEquals(0, red.filtreParLettre('s',len-1));
        }
        System.out.println("\nSuccès filtre par lettre");
    }

    @Test
    public void testFiltreLettreDebut() {
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");

        // nombres de mots de longueur : 2,3,4...
        int[] expected = { 81, 427, 1807, 5916, 13945, 25577, 38343, 47671, 50254, 45851, 36343, 25313, 15462, 8530,
                4287, 2022, 894, 395, 181, 77, 32, 9, 2, 1, 0 };
        int[] filtered = { 9, 20, 131, 493, 1269, 2510, 3755, 4784, 5101, 4661, 3859, 2682, 1671, 945, 495, 264, 122, 60, 29, 19, 11, 3, 2, 0, 0 };

        for (int len = 2; len < expected.length + 2; len++) {
            Dictionnaire red = gut.copy();
            red.filtreLongueur(len);
            int notX = red.filtreParLettre('c', 0);
            // gut n'est pas modifié
            assertEquals(323422, gut.size());
            // taille attendue pour cet ensemble de mots
            assertEquals(filtered[len - 2], red.size());
            // resultat attendu : nombre de mots reduits
            assertEquals(expected[len-2], red.size() + notX);
            // on ne réduit pas plus en faisant deux fois
            assertEquals(0, red.filtreParLettre('c',0));
        }
        System.out.println("\nSuccès filtre par lettre");
    }
}
