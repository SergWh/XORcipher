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

    @Test
    void encryptAndDecryptSameFile() throws IOException {
        Cipher cipher = new Cipher("FF");

        cipher.crypt("src/test/resources/hello.txt", "src/test/resources/helloEncrypted.xor");
        cipher.crypt("src/test/resources/helloEncrypted.xor", "src/test/resources/helloDecrypted.xor");
        assertTrue(contentEquals("src/test/resources/hello.txt", "src/test/resources/helloDecrypted.xor"));
        new File("src/test/resources/helloEncrypted.xor").delete();
        new File("src/test/resources/helloDecrypted.xor").delete();

        cipher.crypt("src/test/resources/picExample.jpg", "src/test/resources/picEncrypted.xor");
        cipher.crypt("src/test/resources/picEncrypted.xor", "src/test/resources/picDecrypted.xor");
        assertTrue(contentEquals("src/test/resources/picExample.jpg", "src/test/resources/picDecrypted.xor"));
        new File("src/test/resources/picEncrypted.xor").delete();
        new File("src/test/resources/picDecrypted.xor").delete();
    }
}