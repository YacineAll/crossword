package org.upmc.info3I002.solve.csp.strategy.value;

import org.upmc.info3I002.solve.csp.IVariable;

import java.util.Collections;
import java.util.List;

public class Random implements IChoixValeur{

    @Override
    public List<String> orderValues(IVariable v) {
        List<String> domain = v.getDomain();
        Collections.shuffle(domain);
        return domain;
    }
}
