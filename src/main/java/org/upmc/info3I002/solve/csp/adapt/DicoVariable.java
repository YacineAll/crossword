package org.upmc.info3I002.solve.csp.adapt;

import org.upmc.info3I002.core.GrillePotentiel;
import org.upmc.info3I002.solve.csp.IVariable;

import java.util.List;

public class DicoVariable implements IVariable {
    private final int index;
    private final GrillePotentiel gp;

    public DicoVariable(int index, GrillePotentiel gp) {
        this.index = index;
        this.gp = gp;
    }


    @Override
    public List<String> getDomain() {
        return gp.getMotsPot().get(index).getMots();
    }

    public GrillePotentiel getGrilleContrainte () {
        return gp;
    }

    public int getIndex() {
        return index;
    }
}
