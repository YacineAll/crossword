package org.upmc.info3I002.solve.csp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.upmc.info3I002.core.Dictionnaire;
import org.upmc.info3I002.grille.Grille;
import org.upmc.info3I002.grille.GrilleLoader;
import org.upmc.info3I002.grille.GrillePlaces;
import org.upmc.info3I002.solve.csp.adapt.MotX;
import org.upmc.info3I002.solve.csp.strategy.value.Random;
import org.upmc.info3I002.solve.csp.strategy.value.Simple;
import org.upmc.info3I002.solve.csp.strategy.variable.StratMin;
import org.upmc.info3I002.solve.grille.GrilleContrainte;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CSPSolverTest {
    @ParameterizedTest
    @ValueSource(strings = {"easy", "medium", "hard"}) //, "large", "large2", "large3", "large4", "larger"})
    void testSolverEasy(String name){
        Dictionnaire gut = Dictionnaire.loadDictionnaire("src/test/resources/frgut.txt");
        Grille gr = GrilleLoader.loadGrille(String.format("src/test/resources/data/%s.grl", name));
        GrillePlaces grille = new GrillePlaces(gr);
        GrilleContrainte gp = new GrilleContrainte(grille, gut);
        assertFalse(gp.isDead());
        ICSP icsp = new MotX(gp);

        long timestamp = System.currentTimeMillis();
        CSPSolver cspSolver = new CSPSolver();
        cspSolver.setChoixVarStrat(new StratMin());
        cspSolver.setValueSelector(new Simple());
        icsp = cspSolver.solve(icsp);
        timestamp = System.currentTimeMillis() - timestamp;

        MotX mx = (MotX) icsp;
        GrilleLoader.saveGrille(
                mx.getGrillePotentiel().getGrillePlaces().getGrille(),
                String.format("src/test/resources/solutions/solved-%s.grl", name)
        );
        System.out.printf("Solved in %d milliseconds%n", timestamp);
    }

}
