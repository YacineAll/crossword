package org.upmc.info3I002.solve.grille;

import org.upmc.info3I002.core.Dictionnaire;
import org.upmc.info3I002.core.GrillePotentiel;
import org.upmc.info3I002.grille.Emplacement;
import org.upmc.info3I002.grille.GrillePlaces;

import java.util.ArrayList;
import java.util.List;


public class GrilleContrainte extends GrillePotentiel {
	private final boolean estRealisable;
	private final List<IContrainte> contraintes;


	public GrilleContrainte(GrillePlaces grillePlaces, Dictionnaire dictionnaire, List<Dictionnaire> dictionnaireList){
		super(grillePlaces, dictionnaire, dictionnaireList);
		contraintes = new ArrayList<>();
		initContraintesList();
		estRealisable = this.propage();
	}

	public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet){
		super(grille, dicoComplet);
		contraintes = new ArrayList<>();
		initContraintesList();
		estRealisable = this.propage();
	}
	private void initContraintesList() {
		for (int i = 0; i < this.getGrillePlaces().getNbHorizontal(); i++) {
			Emplacement m1 = this.getGrillePlaces().getPlaces().get(i);
			for (int j = this.getGrillePlaces().getNbHorizontal(); j < this.getGrillePlaces().getPlaces().size(); j++) {
				Emplacement m2 = this.getGrillePlaces().getPlaces().get(j);
				for (int c1 = 0; c1 < m1.getCases().size(); c1++) {
					for (int c2 = 0; c2 < m2.getCases().size(); c2++) {
						if (m1.getCases().get(c1).equals(m2.getCases().get(c2)) && m1.getCases().get(c1).isVide()) {
							IContrainte contrainte = new CroixContrainte(i, c1, j, c2);
							if (!contraintes.contains(contrainte)) {
								contraintes.add(contrainte);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Accede et renvoie la liste des contraintes de croisement trouvees
	 *
	 * @return list<IContrainte> c'est la liste des contraintes de croisement
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}

	private boolean propage() {
		while (true) {
			int nbreDeMotsEliminees = contraintes
					.stream()
					.map(e->e.reduce(this))
					.reduce(Integer::sum)
					.orElseGet(()->0);
			if (nbreDeMotsEliminees == 0) {
				return true;
			}
			if (this.isDead()) {
				return false;
			}
		}
	}

	public boolean estRealisable() {
		return estRealisable;
	}
}