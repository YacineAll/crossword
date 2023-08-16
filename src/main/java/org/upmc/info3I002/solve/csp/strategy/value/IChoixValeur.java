package org.upmc.info3I002.solve.csp.strategy.value;

import org.upmc.info3I002.solve.csp.IVariable;

import java.util.List;

public interface IChoixValeur {
    List<String> orderValues (IVariable v);
}
