package Client;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class Client {

  
    public static void main(String[] args) throws IOException , SocketException, InvalidKeyException,IllegalBlockSizeException, NoSuchAlgorithmException, Exception{
        File f1=new File("F:\\DES\\input.txt");
        FileReader fr=new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);
        Socket s=new Socket("127.0.0.1",6482);
        DataInputStream sc1;
        sc1 = new DataInputStream(s.getInputStream());
        String no=br.readLine();
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DesEncrypter  encrypter = new DesEncrypter (key);
        String encrypted = encrypter.encrypt(no);

        
        PrintStream p=new PrintStream(s.getOutputStream());
        p.println(encrypted);
        String temp;
        temp = sc1.readLine();
        String s3=encrypter.decrypt(temp);
        
        File file2=new File("F:\\DES\\output.txt");
        FileWriter fw=new FileWriter(file2);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(s3);
        bw.close();
        
        
    }
    
}

class DesEncrypter {
  Cipher ecipher;

  Cipher dcipher;

  DesEncrypter(SecretKey key) throws Exception {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }

  public String encrypt(String str) throws Exception {
    byte[] utf8 = str.getBytes("UTF8");

    
    byte[] enc = ecipher.doFinal(utf8);

    return new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String str) throws Exception {
    
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

    byte[] utf8 = dcipher.doFinal(dec);

    
    return new String(utf8, "UTF8");
  }
}