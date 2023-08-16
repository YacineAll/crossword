package org.upmc.info3I002.solve.csp;

import java.util.List;

public interface ICSP {
    List<IVariable> getVars();
    boolean isConsistent();
    ICSP assign(IVariable vi, String val);
}
