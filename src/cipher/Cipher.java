package cipher;


import org.omg.PortableInterceptor.INACTIVE;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

public class Cipher {

    private final String key;


    public Cipher(String key) {
        this.key = key;
    }


    public int crypt(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                byte[] bytesKey = DatatypeConverter.parseHexBinary(key);
                int symbol = reader.read();
                int newSymbol = 0;
                int i = 0;
                while (symbol != -1) {
                    newSymbol = symbol ^ bytesKey[i % bytesKey.length];
                    i++;
                    writer.write(newSymbol);
                    symbol = reader.read();
                }
                return i;
            }
        }
    }


    public int crypt(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return crypt(inputStream, outputStream);
            }
        }
    }
}

