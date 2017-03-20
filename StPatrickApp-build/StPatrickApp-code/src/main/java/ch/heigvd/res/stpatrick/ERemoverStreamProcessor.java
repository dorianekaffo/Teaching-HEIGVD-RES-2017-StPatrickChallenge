package ch.heigvd.res.stpatrick;

import java.io.*;

/**
 * Created by sydney on 20.03.17.
 */
public class ERemoverStreamProcessor implements IStreamProcessor {
    public void process(Reader in, Writer out) throws IOException {
        BufferedReader bin = new BufferedReader(in);
        BufferedWriter bout = new BufferedWriter(out);

        int c = bin.read();
        while(c != -1) {
            if(!Character.toString((char)c).equalsIgnoreCase("e")) {
                bout.write(c);
            }

            c = bin.read();
        }

        bout.flush();
    }
}
