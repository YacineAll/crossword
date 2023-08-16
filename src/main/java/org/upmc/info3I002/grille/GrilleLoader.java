package org.upmc.info3I002.grille;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


public class GrilleLoader {
    private static Logger logger = LogManager.getLogger(GrilleLoader.class);

    /**
     * Loads a grid from a file in the "grl" format.
     * The first line of the file contains two integers: nblig nbcol,
     * which give the size of the grid. The following lines contain the content of the grid,
     * where '*' represents a filled cell, ' ' represents an empty cell, or a letter 'a-z'.
     *
     * @param String path to the grl file
     * @return a grid loaded from the file or null in case of file access problems.
     */
    public static Grille loadGrille(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return getGrille(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Grille loadGrille(InputStream inputStream){
        assert inputStream != null: "inputStream should not be null";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return getGrille(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Grille getGrille(BufferedReader br) throws IOException {
        int lig = 0;
        Grille gc = null;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (gc == null) {
                String[] digits = line.split("\\s+");
                int haut = Integer.parseInt(digits[0]);
                int larg = Integer.parseInt(digits[1]);
                logger.info("Chargement grille " + haut + " lignes x " + larg + " colonnes");
                gc = new Grille(haut, larg);
            } else {
                String linelc = line.toLowerCase();
                for (int col = 0; col < line.length() && col < gc.nbCol(); col++) {
                    // mise à jour du contenu de la case
                    gc.getCase(lig, col).setChar(linelc.charAt(col));
                }
                lig++;
                if (lig == gc.nbLig()) {
                    // si le fichier est trop long, on ignore les lignes
                    // restantes.
                    break;
                }
            }
        }
        return gc;
    }

    public static void saveGrille(Grille g, String path) {
        try {
            PrintWriter pw = new PrintWriter(path);
            pw.print(serialize(g,true));
            pw.close();
        } catch (IOException e) {
            logger.error("Save Grille raised an IOException :" + e);
            e.printStackTrace();
        }
    }

    /**
     * Fournit une représentation de la grille comme une String. L'affichage est
     * contrôlé par le style, cf. méthode setStyle().
     *
     * @param g
     *            une grille
     * @return une String représentant g.
     */
    public static String serialize(Grille g, boolean isGrlFormat) {
        StringBuilder sb = new StringBuilder();

        String espace;
        if (! isGrlFormat)
            espace = " "; // version aérée
        else
            espace = ""; // version grl

        // Préface
        if (isGrlFormat) {
            sb.append(g.nbLig()).append(" ").append(g.nbCol()).append("\n");
        } else {
            sb.append("===".repeat(Math.max(0, g.nbCol())));
            sb.append("\n");
        }

        // La grille
        for (int lig = 0; lig < g.nbLig(); lig++) {
            for (int col = 0; col < g.nbCol(); col++) {
                sb.append(espace).append(Character.toUpperCase(g.getCase(lig, col).getChar())).append(espace);
            }
            sb.append("\n");
        }

        // post face
        if (! isGrlFormat) {
            sb.append("===".repeat(Math.max(0, g.nbCol())));
            sb.append("\n");
        }

        return sb.toString();
    }

}
