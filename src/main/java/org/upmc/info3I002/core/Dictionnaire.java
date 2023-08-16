package org.upmc.info3I002.core;

import org.upmc.info3I002.solve.grille.EnsembleLettre;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionnaire {
    // stockage des mots
    private List<String> mots = new ArrayList<>();
    private EnsembleLettre[] cache = null;

    /**
     * Ajoute un mot au Dictionnaire, en dernière position.
     *
     * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
     */
    public void add(String mot) {
        mots.add(mot.toLowerCase());
    }

    /**
     * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
     *
     * @return la taille
     */
    public int size() {
        return mots.size();
    }

    /**
     * Accès au i-eme mot du dictionnaire.
     *
     * @param i l'index du mot recherché, compris entre 0 et size-1.
     * @return le mot à cet index
     */
    public String get(int i) {
        return mots.get(i);
    }

    /**
     * Rend une copie de ce Dictionnaire.
     *
     * @return une copie identique de ce Dictionnaire
     */
    public Dictionnaire copy() {
        Dictionnaire copy = new Dictionnaire();
        copy.mots.addAll(mots);
        copy.cache = cache;
        return copy;
    }

    /**
     * Retire les mots qui ne font pas exactement "len" caractères de long.
     * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de
     * filtrer pour ne pas perdre d'information.
     *
     * @param len la longueur voulue
     * @return le nombre de mots supprimés
     */
    public int filtreLongueur(int len) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        for (String mot : mots) {
            if (mot.length() == len)
                cible.add(mot);
            else
                cpt++;
        }
        mots = cible;
        if (cpt>0){
            cache = null;
        }
        return cpt;
    }

    @Override
    public String toString() {
        if (size() == 1) {
            return mots.get(0);
        } else {
            return "Dico size =" + size();
        }
    }

    /**
     * Parcour un fichier de mot de dictionnaire Renvoie un Objet Dictionnaire qui
     * contient tous les mot de fichier
     *
     * @param path le chemin de fichier qui contient le dicionnaire des mots
     * @return Un dictionnaire remplie
     */
    public static Dictionnaire loadDictionnaire(String path) {
        Dictionnaire dictionnaire = new Dictionnaire();
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(path)))) {
            String line;
            while ((line = bf.readLine()) != null) {
                dictionnaire.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found !!" + e);
        } catch (IOException e1) {
            System.err.println("IOEXCEPTION !!!" + e1);
        }
        return dictionnaire;
    }

    /**
     * Calcule la liste de la iéme lettre des mots de domaine
     * @param i l'index de la position de la lettre
     * @return ensemble de lettre possible a l'index i dans le domaine potentiel
     */
    public EnsembleLettre charAt(int i) {
        if (this.mots.isEmpty()){
            return new EnsembleLettre();
        }
        if( cache == null){
            cache = new EnsembleLettre[this.mots.get(0).length()];
        }
        if (cache[i] == null){
            EnsembleLettre ensembleLettre = new EnsembleLettre();
            for (String mot : mots) {
                ensembleLettre.add(mot.charAt(i));
            }
            cache[i] = ensembleLettre;
            return ensembleLettre;
        }
        return cache[i];
    }

    /**
     * Modifiera le dictionnaire pour garder que les mots qui ont leur iéme lettre
     * dans l'ensemble des lettre Attention elle modifiera le dictionnaire danc
     * c'est conseiller de faire une copie
     *
     * @param i              la position de lattre
     * @param ensembleLettre l'ensemble des lettres possibles
     * @return nombre de mot filtrer de dictionnaire
     */
    public int filterParEnsembleDeLettrePo(int i, EnsembleLettre ensembleLettre) {
        int cpt = 0;
        List<String> cible = new ArrayList<>();
        for (String mot : this.mots) {
            if (ensembleLettre.contains(mot.charAt(i))) {
                cible.add(mot);
            } else
                cpt++;
        }
        mots = cible;
        if(cpt>0){
            cache = null;
        }
        return cpt;
    }

    /**
     * modifiera le dictionnaire pour ne garder que les mots dont la ième lettre est
     * égale a c Attention cette opération modifie le Dictionnaire, utiliser copy()
     * avant de filtrer pour ne pas perdre d'information.
     *
     * @param c une lettre de l'alphabet qui il faut que elle soit la i'iem lettre
     *          des mot dans le dictionnaire renvoye
     * @param i l'index de la lettre dans les mot
     * @return Dictionnaire qui contient des mots qui ont la lettre c a la iéme
     *         position
     */
    public int filtreParLettre(char c, int i) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        for (String mot : mots) {
            if (mot.charAt(i) == c)
                cible.add(mot);
            else
                cpt++;
        }
        mots = cible;
        if(cpt>0){
            cache = null;
        }
        return cpt;
    }

    /**
     * Accede et renvoie la liste des mot de dictionnaire
     *
     * @return List<String> c'est la liste des mots de dictionnaire
     */
    public List<String> getMots() {
        return mots;
    }
}