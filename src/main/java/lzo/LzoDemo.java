package lzo;

import org.anarres.lzo.LzoAlgorithm;
import org.anarres.lzo.LzoDecompressor;
import org.anarres.lzo.LzoInputStream;
import org.anarres.lzo.LzoLibrary;

import java.io.*;

/**
 * Created by rongpei on 2017/7/10.
 */
public class LzoDemo {

    public static void main(String[] args ){
        InputStream in = null;
        try {
            in = new FileInputStream(new File("/Users/rongpei/data/part-00000.lzo_deflate"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LzoAlgorithm algorithm = LzoAlgorithm.LZO1X;
        LzoDecompressor decompressor = LzoLibrary.getInstance().newDecompressor(algorithm, null);
        LzoInputStream stream = new LzoInputStream(in, decompressor);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("/Users/rongpei/data/aa.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            while ((read = stream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
