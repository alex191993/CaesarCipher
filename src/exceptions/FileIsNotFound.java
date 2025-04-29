package exceptions;

import java.nio.file.Path;

public class FileIsNotFound extends RuntimeException {
    public FileIsNotFound(Path filePath) {
        super("Файл " + filePath.toString() + " не найден. Попробуйте снова ввести наименование файла");
    }
}
