package cipher;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    boolean contentEquals(String first, String second) throws IOException {
        try (FileInputStream firstStream = new FileInputStream(first); FileInputStream secondStream = new FileInputStream(second)) {
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
    void encryptAndDecryptSameText() throws IOException {
        Cipher cipher = new Cipher("FF");
        cipher.crypt("input/hello.txt", "input/helloEncrypted.xor");
        cipher.crypt("input/helloEncrypted.xor", "input/helloDecrypted.xor");
        assertTrue(contentEquals("input/hello.txt", "input/helloDecrypted.xor"));
        new File("input/helloEncrypted.xor").delete();
        new File("input/helloDecrypted.xor").delete();
    }

    @Test
    void encryptAndDecryptSamePic() throws IOException {
        Cipher cipher = new Cipher("FA");
        cipher.crypt("input/picExample.jpg", "input/picEncrypted.xor");
        cipher.crypt("input/picEncrypted.xor", "input/picDecrypted.xor");
        assertTrue(contentEquals("input/picExample.jpg", "input/picDecrypted.xor"));
        new File("input/picEncrypted.xor").delete();
        new File("input/picDecrypted.xor").delete();
    }

}