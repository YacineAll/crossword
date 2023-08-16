package org.upmc.info3I002.utils;

import org.upmc.info3I002.core.GrillePotentiel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tools {
    public static void testNombrePot(GrillePotentiel gp, int[] expected) {
        assertEquals(expected.length, gp.getMotsPot().size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], gp.getMotsPot().get(i).size());
        }
    }
}
