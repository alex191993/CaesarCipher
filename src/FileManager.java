import exceptions.FileIsEmpty;
import exceptions.FileIsNotFound;
import validations.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileManager {
    Validator checkList = new Validator();
    public List <String> readFile(String filePath) throws IOException, FileIsEmpty {
        Path path = Paths.get(filePath);
            checkList.isFileEmpty(path);

        return Files.readAllLines(path);
    }
    public void writeFile(List <String> content, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (String line : content){
                writer.write(line);
                writer.newLine();
            }

        }



    }
}