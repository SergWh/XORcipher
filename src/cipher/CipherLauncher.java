package cipher;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.nio.charset.Charset;

public class CipherLauncher {


    @Option(name = "-c", required = true, aliases = "-d", metaVar = "Key", usage = "Key of cipher")
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


        Cipher cipher = new Cipher(key);

        try {
            int result = cipher.crypt(inputFileName, outputFileName);
            System.out.println("Total of " + result + " bytes XORed");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
