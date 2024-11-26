package graphqe;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        loadNativeLibraries();
        Runner runner = new Runner();
        runner.run();
    }
    private static void loadNativeLibraries() {
        try {
            loadLibraryFromJar("/lib/libz3.so");
            loadLibraryFromJar("/lib/libz3java.so");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load native libraries", e);
        }
    }

    private static void loadLibraryFromJar(String path) throws IOException {
        File temp = File.createTempFile("lib", ".so");
        temp.deleteOnExit();
        try (var is = Main.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Library not found: " + path);
            }
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        System.load(temp.getAbsolutePath());
    }
}
