package org.upmc.info3I002.solve.grille;

import org.upmc.info3I002.core.GrillePotentiel;

import java.util.Objects;

public class CroixContrainte implements IContrainte{
    private final int m1,c1,m2,c2;

    public CroixContrainte(int m1, int c1, int m2, int c2) {
        this.m1 = m1;
        this.c1 = c1;
        this.m2 = m2;
        this.c2 = c2;
    }

    @Override
    public int reduce(GrillePotentiel grille) {
        EnsembleLettre liste1 = grille.getMotsPot().get(m1).charAt(c1);

        EnsembleLettre liste2 = grille.getMotsPot().get(m2).charAt(c2);

        EnsembleLettre s = new EnsembleLettre(liste1);

        s.retainAll(liste2);

        int nombreDeMotFiltrees = 0;

        if (liste1.size() > s.size()) {
            nombreDeMotFiltrees += grille.getMotsPot().get(m1).filterParEnsembleDeLettrePo(c1, s);
        }

        if (liste2.size() > s.size()) {
            nombreDeMotFiltrees += grille.getMotsPot().get(m2).filterParEnsembleDeLettrePo(c2, s);
        }

        return nombreDeMotFiltrees;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CroixContrainte that = (CroixContrainte) o;
        return m1 == that.m1 && c1 == that.c1 && m2 == that.m2 && c2 == that.c2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m1, c1, m2, c2);
    }
}
