package org.upmc.info3I002.solve.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.upmc.info3I002.solve.csp.strategy.value.IChoixValeur;
import org.upmc.info3I002.solve.csp.strategy.variable.IChoixVar;

public class CSPSolver {
    private static final Logger logger = LogManager.getLogger(CSPSolver.class);
    private IChoixVar varSelector;
    private IChoixValeur valueSelector;


    public ICSP solve (ICSP problem){
        logger.info(String.format("Solve : %s", problem));
        if (problem.getVars().isEmpty()){
            logger.info("Problem solved");
            return problem;
        }
        if(!problem.isConsistent()){
            logger.info("Problem not solvable");
            return problem;
        }
        IVariable vi = varSelector.chooseVar(problem);
        ICSP next = null;
        for (String value: valueSelector.orderValues(vi)) {
            next = problem.assign(vi, value);
            next = solve(next);
            if (next.isConsistent()){
                return next;
            }else {
                logger.info("Try the next value");
            }
        }
        logger.info(String.format("Backtrack with this value %s", vi));
        return next;
    }

    public void setChoixVarStrat(IChoixVar strat){
        this.varSelector = strat;
    }
    public void setValueSelector(IChoixValeur iValueSelector){
        this.valueSelector = iValueSelector;
    }
}
