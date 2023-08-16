package org.upmc.info3I002.solve.csp.adapt;

import org.upmc.info3I002.core.GrillePotentiel;
import org.upmc.info3I002.solve.csp.ICSP;
import org.upmc.info3I002.solve.csp.IVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MotX implements ICSP {

    private final GrillePotentiel gc;
    private final List<IVariable> vars;

    public MotX(GrillePotentiel gc) {
        this.gc = gc;
        vars = IntStream.range(0, gc.getGrillePlaces().getPlaces().size())
                .filter(i->gc.getGrillePlaces().hasCaseVide(i))
                .mapToObj(i -> new DicoVariable(i, gc))
                .collect(Collectors.toList());
    }

    public GrillePotentiel getGrillePotentiel() {
        return gc;
    }

    @Override
    public List<IVariable> getVars() {
        return vars;
    }

    @Override
    public boolean isConsistent() {
        return !gc.isDead();
    }

    @Override
    public ICSP assign(IVariable vi, String val) {
        if (!(vi instanceof DicoVariable)){
            throw new RuntimeException();
        }
        DicoVariable dv = (DicoVariable) vi;
        return new MotX(dv.getGrilleContrainte().fixer(dv.getIndex(), val));
    }
}
