package org.spbstu.chernonog.cipher;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class CipherLauncher {

    @Option(name = "-c", required = true, aliases = "-d", metaVar = "Key", usage = "Key of cipher in hexadecimal")
    private String key;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;


    public static void main(String[] args) {
        new CipherLauncher().launch(args);
    }

    private String getOutputFileName() {
        return inputFileName + ".xor";
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("");
            parser.printUsage(System.err);
            return;
        }

        if (outputFileName == null) {
            outputFileName = getOutputFileName();
        }

        byte[] bytesKey;

        try {
            bytesKey = DatatypeConverter.parseHexBinary(key);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            System.err.println("");
            parser.printUsage(System.err);
            return;
        }

        Cipher cipher = new Cipher(bytesKey);


        try {
            int result = cipher.crypt(inputFileName, outputFileName);
            System.out.println("Total of " + result + " bytes XORed");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


    }
}
