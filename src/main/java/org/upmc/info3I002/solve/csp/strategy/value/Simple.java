package org.upmc.info3I002.solve.csp.strategy.value;

import org.upmc.info3I002.solve.csp.IVariable;

import java.util.List;

public class Simple implements IChoixValeur{
    @Override
    public List<String> orderValues(IVariable v) {
        return v.getDomain();
    }
}
