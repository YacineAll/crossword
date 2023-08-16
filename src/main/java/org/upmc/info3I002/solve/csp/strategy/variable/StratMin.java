package org.upmc.info3I002.solve.csp.strategy.variable;

import org.upmc.info3I002.solve.csp.ICSP;
import org.upmc.info3I002.solve.csp.IVariable;

import java.util.Comparator;

public class StratMin implements IChoixVar{
    @Override
    public IVariable chooseVar(ICSP problem) {
        return problem.getVars()
                .stream()
                .parallel()
                .min(Comparator.comparingInt(o -> o.getDomain().size()))
                .orElseThrow();
    }
}
