package cipher;


import javax.xml.bind.DatatypeConverter;
import java.io.*;


public class Cipher {

    private final byte[] key;


    public Cipher(String key) {
        this.key = DatatypeConverter.parseHexBinary(key);
    }

    public Cipher(byte[] key) {
        this.key = key;
    }


    public int crypt(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                int currentByte = inputStream.read();
                int i = 0;
                while (currentByte != -1) {
                    int newByte = currentByte ^ key[i % key.length];
                    i++;
                    outputStream.write(newByte);
                    currentByte = inputStream.read();
                }
                return i;
            }
        }
    }
}

