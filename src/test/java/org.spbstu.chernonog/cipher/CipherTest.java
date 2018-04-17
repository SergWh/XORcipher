package org.spbstu.chernonog.cipher;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CipherTest {

    boolean contentEquals(String first, String second) throws IOException {
        try (FileInputStream firstStream = new FileInputStream(first);
             FileInputStream secondStream = new FileInputStream(second)) {
            int byte1 = firstStream.read();
            int byte2 = secondStream.read();
            while ((byte1 != -1) && (byte2 != -1)) {
                if (byte1 != byte2) return false;
                byte1 = firstStream.read();
                byte2 = secondStream.read();
            }
            return true;
        }
    }

    void encryptAndDecrypt(String original) throws IOException {
        String encrypted = original + "Enc.xor";
        String decrypted = original + "Dec.xor";
        Cipher cipher = new Cipher("FF");
        cipher.crypt(original, encrypted);
        cipher.crypt(encrypted, decrypted);
        assertTrue(contentEquals(original, decrypted));
        new File(encrypted).delete();
        new File(decrypted).delete();
    }

    @Test
    void encryptAndDecryptPicture() throws IOException {
        encryptAndDecrypt("src/test/resources/picExample.jpg");
    }

    @Test
    void encryptAndDecryptTxt() throws IOException {
        encryptAndDecrypt("src/test/resources/hello.txt");
    }
}