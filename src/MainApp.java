import exceptions.FileIsEmpty;
import fileServices.FileManager;
import services.Cipher;
import validations.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try {
            int key;
            String pathInput;
            String pathOutput;
            String pathExample;
            Validator check = new Validator();
            Cipher cipher = new Cipher();
            int alhabetLength = cipher.getAlhabet().length - 1;
            while (true){
                displayMenuFirst(); // Главное меню выбора
                String choice = scanner.nextLine();
                // В меню выбрали шифрование. Работаем с шифрованием: указываем какой файл шифруем, по какому ключу
                if (choice.equals("1")){
                    while(true){
                        System.out.println('\n' + "Введите путь файла, который требуется зашифровать: ");
                        String path = scanner.nextLine();
                        try {
                            check.isFileExists(path);
                            pathInput = path;
                            break;
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    //Условия для выбора корректного ключа для русского алфавита - 33.
                    while (true){
                        System.out.println('\n' + "Введите ключ шифрования от 1 до " + alhabetLength + ": ");
                        String keyString = scanner.nextLine();
                        try {
                            check.isValidKey(Integer.parseInt(keyString), cipher.getAlhabet());
                            key = Integer.parseInt(keyString);
                            break;
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    encryptFile(pathInput, key); //вызываем метод шифрования
                }
                if (choice.equals("2")) {
                    while (true) {
                        displayMenuEncryptOptions();
                        String choice2 = scanner.nextLine();

                        if (choice2.equals("1")) {
                            while (true) {
                                System.out.println('\n' + "Введите путь файла, который требуется расшифровать: ");
                                String path = scanner.nextLine();
                                try {
                                    check.isFileExists(path);
                                    pathOutput = path;
                                    break;
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            while (true) {
                                System.out.println('\n' + "Введите ключ шифрования от 1 до " + alhabetLength + ": ");
                                String keyString = scanner.nextLine();
                                try {
                                    check.isValidKey(Integer.parseInt(keyString), cipher.getAlhabet());
                                    key = Integer.parseInt(keyString);
                                    break;
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            decryptFile(pathOutput, key);
                        }
                        if (choice2.equals("2")){
                            while (true) {
                                System.out.println('\n' + "Введите путь файла, который требуется взломать: ");
                                String path = scanner.nextLine();
                                try {
                                    check.isFileExists(path);
                                    pathOutput = path;
                                    break;
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            while (true) {
                                System.out.println('\n' + "Введите путь файла для примера взлома: ");
                                String path = scanner.nextLine();
                                try {
                                    check.isFileExists(path);
                                    pathExample = path;
                                    break;
                                } catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            decryptFileBrutForce(pathOutput, pathExample);
                        }
                        if (choice2.equals("3"))
                            System.out.println("Раздел в разработке, выберите другой" + '\n');
                        if (choice2.equals("4"))
                            break;
                    }
                }
                if (choice.equals("3")){
                    System.out.println("Заходи в следующий раз!");
                    break;
                }
            }
        }catch (RuntimeException e){
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        }
    }

    // Метод для старта процесса шифрования по заданному ключу
    public static void encryptFile(String path, int key) throws IOException {
        FileManager file = new FileManager();
        try {
            List<String> textInput = file.readFile(path);
            Cipher fileCipher = new Cipher();
            List<String> textOutput = fileCipher.encryptNew(textInput, key);
            file.writeFile(textOutput, "Encrypted.txt");
        }catch (FileIsEmpty e){
            System.out.println(e.getMessage());
            return;
        }
        finishText("Encrypt");
    }

    public static void decryptFile(String path, int key) throws IOException{
        FileManager file = new FileManager();
        try {
            List<String> textInput = file.readFile(path);
            Cipher fileCipher = new Cipher();
            List<String> textOutput = fileCipher.decryptNew(textInput, key);
            file.writeFile(textOutput, "Decrypted.txt");
        }catch (FileIsEmpty e){
            System.out.println(e.getMessage());
            return;
        }
        finishText("Decrypt");
    }

    public static void decryptFileBrutForce(String pathToBrut, String originalPathFileExample) throws IOException {
        FileManager file = new FileManager();
        try {
            List<String> textInput = file.readFile(pathToBrut);
            List<String> textInputExample = file.readFile(originalPathFileExample);
            String example = textInputExample.get(0);
            Cipher fileCipher = new Cipher();
            List<String> textOutput = fileCipher.decryptBrutForceNew(textInput, example);
            file.writeFile(textOutput, "DecryptedByBrut.txt");
        } catch (FileIsEmpty e) {
            System.out.println(e.getMessage());
            return;
        }
        finishText("Brutforce");
    }

    private static void displayMenuFirst(){
        System.out.println('\n' +"Выберите действие:");
        System.out.println("""
                1. Зашифровать файл
                2. Расшифровать файл
                3. Выход
                """);
    }
    private static void displayMenuEncryptOptions(){
        System.out.println('\n' +"Выберите действие:" + '\n');
        System.out.println("""
                Каким образом расшифровать файл?
                1. По ключу
                2. Брут форсом
                3. Статистический анализ (в разработке)
                4. Вернуться в главное меню
                """);

    }
    private static void finishText(String type){
        System.out.println('\n' + "=================================================");
        switch (type){
            case "Encrypt" ->
                    System.out.println("Шифрование выполнено успешно :)");
            case "Decrypt" ->
                    System.out.println("Расшифровка выполнена успешно :)");
            case "Brutforce" ->
                    System.out.println("Взломано ^^");
        }
        System.out.println("=================================================" + '\n');
    }
}