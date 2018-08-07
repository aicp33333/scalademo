package nioTest;

import java.io.*;


/**
 * Created by 2018/4/17.
 *
 * @author rongpei
 */
public class CharsetDemo {

    public static void main( String[] args ) throws IOException {

        File file=new File("/Users/rongpei/Downloads/rp.rp");
        BufferedReader in = new BufferedReader(new FileReader(file));

        String tmp =in.readLine();



        tmp = new String(tmp.getBytes("iso-8859-1"),"gbk");
        System.out.println(getEncoding(tmp));
        System.out.println(tmp);

        System.out.println(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(tmp));

        if(!java.nio.charset.Charset.forName("utf-8").newEncoder().canEncode(tmp)){
            System.out.println(new String(tmp.getBytes("iso-8859-1"),"utf-8"));
        }



//        System.out.println(new String("????".getBytes("utf-8"),"iso-8859-1"));
//
//
//        String str = new String("测试".getBytes("utf-8"),"iso-8859-1");
//
//
//
//        System.out.println(str);
//
//        System.out.println(getEncoding(str));
//
//        System.out.println(java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(str));
//
//        System.out.println(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(str));
//
//        System.out.println(new String(str.getBytes("iso-8859-1"),"utf-8"));
//
//        System.out.println(java.nio.charset.Charset.forName("utf-8").newEncoder().canEncode(str));
//
//
//        System.out.println(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode("测试"));

    }

    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode[] = new String[]{
                "UTF-8",
                "ISO-8859-1",
                "GB2312",
                "GBK",
                "GB18030",
                "Big5",
                "Unicode",
                "ASCII"
        };
        for (int i = 0; i < encode.length; i++){
            try {
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
                    return encode[i];
                }
            } catch (Exception ex) {
            }
        }

        return "";
    }

}
