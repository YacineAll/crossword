package org.upmc.info3I002.solve.csp.strategy.value;

import org.upmc.info3I002.solve.csp.IVariable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frequency implements IChoixValeur{
    private final static Map<Character, Integer> scrabbleScores = new HashMap<>();

    static {
        scrabbleScores.put('A', 1); scrabbleScores.put('B', 3);
        scrabbleScores.put('C', 3); scrabbleScores.put('D', 2);
        scrabbleScores.put('E', 1); scrabbleScores.put('F', 4);
        scrabbleScores.put('G', 2); scrabbleScores.put('H', 4);
        scrabbleScores.put('I', 1); scrabbleScores.put('J', 8);
        scrabbleScores.put('K', 5); scrabbleScores.put('L', 1);
        scrabbleScores.put('M', 3); scrabbleScores.put('N', 1);
        scrabbleScores.put('O', 1); scrabbleScores.put('P', 3);
        scrabbleScores.put('Q', 10); scrabbleScores.put('R', 1);
        scrabbleScores.put('S', 1); scrabbleScores.put('T', 1);
        scrabbleScores.put('U', 1); scrabbleScores.put('V', 4);
        scrabbleScores.put('W', 4); scrabbleScores.put('X', 8);
        scrabbleScores.put('Y', 4); scrabbleScores.put('Z', 10);
    }
    private static int calculerScore(String mot) {
        return mot.chars()
                .parallel()
                .map(e->scrabbleScores.getOrDefault(Character.toUpperCase((char)e), 0))
                .reduce(Integer::sum)
                .orElse(0);
    }
    @Override
    public List<String> orderValues(IVariable v) {
        List<String> domain = v.getDomain();
        domain.sort(Comparator.comparingInt(Frequency::calculerScore));
        return domain;
    }
}
