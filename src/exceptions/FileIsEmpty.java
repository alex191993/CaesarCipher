package exceptions;

import java.nio.file.Path;

public class FileIsEmpty extends RuntimeException {
    public FileIsEmpty(Path fileName) {
        super("Невозможно работать с файлом. Файл "+ fileName.toString() + " пустой");
    }
}
