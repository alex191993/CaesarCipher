package validations;

import exceptions.FileIsEmpty;
import exceptions.FileIsNotFound;
import exceptions.KeyOutOfRange;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    public boolean isValidKey(int key, char[] alphabet) {
        boolean result = (key >= 0 && key < alphabet.length);
        if (!(key >= 0 && key < alphabet.length)) {
            throw new KeyOutOfRange();
        }
        return result;
    }
    public boolean isFileExists(String filePath){
        Path path = Paths.get(filePath);
        boolean result = !Files.exists(path);
        if (!Files.exists(path)) {
            throw new FileIsNotFound(path);
        }
        return result;
    }
    public boolean isFileEmpty(Path filePath) throws FileIsEmpty{
        boolean result = (filePath.toFile().length() == 0);
            if(filePath.toFile().length() == 0){
                throw new FileIsEmpty(filePath);
            }
        return result;
    }
}