package org.upmc.info3I002.solve.csp.strategy.variable;

import org.upmc.info3I002.solve.csp.ICSP;
import org.upmc.info3I002.solve.csp.IVariable;

public class StratFirst implements IChoixVar{
    @Override
    public IVariable chooseVar(ICSP problem) {
        return problem.getVars().get(0);
    }
}
