package org.upmc.info3I002.solve.csp.strategy.variable;

import org.upmc.info3I002.solve.csp.ICSP;
import org.upmc.info3I002.solve.csp.IVariable;

public interface IChoixVar {
    IVariable chooseVar(ICSP problem);
}
