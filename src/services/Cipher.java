package services;

import java.util.ArrayList;
import java.util.List;

public class Cipher {

    private static final char[] ALPHABET = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К', 'Л', 'Ь', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public Cipher() {
    }

    public char[] getAlhabet(){
        return ALPHABET;
    }
    public List <String> encrypt(List<String> toEncryptText, int key) {
        List<String> result = new ArrayList<>();
        int index = 0;
        for (String line : toEncryptText){
            char[] temp = new char[line.length()];
            char[] charLine = line.toCharArray();
            for (int i = 0; i < charLine.length; i++){
              for (int j = 0; j < ALPHABET.length; j++) {
                  if (charLine[i] == ALPHABET[j]){
                      if (key + j >= ALPHABET.length){
                          temp[i] = ALPHABET[key + j - ALPHABET.length];
                          break;
                      } else {
                          temp[i] = ALPHABET[j + key];
                          break;
                      }
                  }
                  if (j == ALPHABET.length - 1)
                      temp[i] = charLine[i];
              }
            }
            result.add(index, String.valueOf(temp));
            index++;
        }
        return result;
    }
    public List <String> decrypt(List<String> toDecryptText, int key) {
        List<String> result = new ArrayList<>();
        int index = 0;
        for (String line : toDecryptText){
            char[] temp = new char[line.length()];
            char[] charLine = line.toCharArray();
            for (int i = 0; i < charLine.length; i++){
                for (int j = 0; j < ALPHABET.length; j++) {
                    if (charLine[i] == ALPHABET[j]){
                        if (j - key < 0){
                            temp[i] = ALPHABET[ALPHABET.length + (j - key)];
                            break;
                        } else {
                            temp[i] = ALPHABET[j - key];
                            break;
                        }
                    }
                    if (j == ALPHABET.length - 1)
                        temp[i] = charLine[i];
                }
            }
            result.add(index, String.valueOf(temp));
            index++;
        }
        return result;
    }

    public List <String> decryptBrutForce(List<String> toDecryptText, String example){
        int brutCount = ALPHABET.length;
        int brutKey = 0;
        char[] temp = new char[toDecryptText.getFirst().length()];
        char[] charLine = toDecryptText.getFirst().toCharArray();
        List<String> result = new ArrayList<>();
        for (int k = 0; k < brutCount; k++){
            for (int i = 0; i < charLine.length; i++){
                for (int j = 0; j < ALPHABET.length; j++) {
                    if (charLine[i] == ALPHABET[j]){
                        if (j - brutKey < 0){
                            temp[i] = ALPHABET[ALPHABET.length + (j - brutKey)];
                            break;
                        } else {
                            temp[i] = ALPHABET[j - brutKey];
                            break;
                        }
                    }
                    if (j == ALPHABET.length - 1)
                        temp[i] = charLine[i];
                }
            }
            System.out.println(temp);
            if (example.equals(String.valueOf(temp))){
                result = decrypt(toDecryptText, brutKey);
                break;
            }
            brutKey++;
        }
        return result;
    }
}
