package exceptions;

import services.Cipher;

public class KeyOutOfRange extends RuntimeException {
    public KeyOutOfRange() {
        Cipher count = new Cipher();
        int a = count.getAlhabet().length - 1;
        super("Ключ не подходит по условию. Введите ключ шифрования от 1 до " + a + "!");
    }
}
