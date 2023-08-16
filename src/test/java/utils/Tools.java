package utils;

import org.upmc.info3I002.grille.Grille;
import org.upmc.info3I002.grille.GrilleLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Tools {
    public static Grille readGrillFromResources(String path) {
        try (InputStream inputStream = Tools.class
                .getClassLoader()
                .getResourceAsStream(path)
        ) {
            return GrilleLoader.loadGrille(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDestinationPath(String path) {
        Path source = Paths.get(Objects.requireNonNull(Tools.class.getResource("/")).getPath());
        return Paths.get(String.format("%s/%s", source.toAbsolutePath(), path)).toString();
    }

}
