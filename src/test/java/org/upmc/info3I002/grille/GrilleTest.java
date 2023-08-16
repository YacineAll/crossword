package org.upmc.info3I002.grille;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Tools.readGrillFromResources;

public class GrilleTest {


    @Test
    public void testGrille() {

        Grille gr = readGrillFromResources("data/hard.grl");

        assertEquals(5, gr.nbCol());
        assertEquals(5, gr.nbLig());

        gr = readGrillFromResources("data/enonce.grl");

        assertEquals(16, gr.nbCol());
        assertEquals(12, gr.nbLig());

        gr = readGrillFromResources("data/large2.grl");

        assertEquals(20, gr.nbCol());
        assertEquals(20, gr.nbLig());

        // GrilleLoader.setStyle(Style.AIR);
        System.out.println(gr);

        // System.out.println(gr);
    }

    @Test
    public void testGetCase() {
        Grille gr = readGrillFromResources("data/large2.grl");

        assertEquals(20, gr.nbCol());
        assertEquals(20, gr.nbLig());

        testContenu(gr, 0, 0, ' ');
        testContenu(gr, 10, 0, ' ');
        testContenu(gr, 0, 14, 'a');
        testContenu(gr, 19, 19, '*');
        testContenu(gr, 5, 7, ' ');
        testContenu(gr, 7, 5, ' ');
        testContenu(gr, 12, 17, '*');
        testContenu(gr, 17, 12, 'm');
        testContenu(gr, 8, 19, 'e');
        testContenu(gr, 19, 8, 's');

        System.out.println("Succès test sur contenu des cases de Grille.");
    }

    private void testContenu(Grille gr, int i, int j, char ch) {
        Case c = gr.getCase(i, j);
        assertEquals(ch + "", c.getChar() + "");
        assertEquals(i, c.getLig());
        assertEquals(j, c.getCol());
    }

    @Test
    public void testCopy() {
        Grille gr = readGrillFromResources("data/large2.grl");

        assertEquals(20, gr.nbCol());
        assertEquals(20, gr.nbLig());

        Grille gr2 = gr.copy();

        assertEquals(20, gr2.nbCol());
        assertEquals(20, gr2.nbLig());

        for (int l = 0; l < gr2.nbLig(); l++) {
            for (int c = 0; c < gr.nbCol(); c++) {
                assertNotSame(gr.getCase(l, c), gr2.getCase(l, c));
            }
        }
        https:
//stl.algo-prog.info/3703169/motcroise

        testContenu(gr2, 0, 0, ' ');
        testContenu(gr2, 10, 0, ' ');
        testContenu(gr2, 0, 14, 'a');
        testContenu(gr2, 19, 19, '*');
        testContenu(gr2, 5, 7, ' ');
        testContenu(gr2, 7, 5, ' ');
        testContenu(gr2, 12, 17, '*');
        testContenu(gr2, 17, 12, 'm');
        testContenu(gr2, 8, 19, 'e');
        testContenu(gr2, 19, 8, 's');

        System.out.println("Succès test sur copy de Grille.");
    }

    @Test
    public void testIO() {
        String f1 = "src/test/resources/data/large2.grl";

        Path path = Paths.get("target/test-classes/data/reverse.grl");

        Grille gr = GrilleLoader.loadGrille(f1);

        try {

            Files.deleteIfExists(path);
            GrilleLoader.saveGrille(gr, path.toString());

            byte[] fc1 = Files.readAllBytes(Paths.get(f1));
            byte[] fc2 = Files.readAllBytes(path);

            assertArrayEquals(fc1, fc2);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}
