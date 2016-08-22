package Server;

import java.io.*;
import java.net.*;

/**
 *
 * @author user
 */
public class Server {
    public static void main(String args[]) throws IOException
    {
        ServerSocket s1=new ServerSocket(6482);
        System.out.println("Server Waiting for Connection");
        Socket ss=s1.accept();
        DataInputStream sc=new DataInputStream(ss.getInputStream());
        String s=sc.readLine();
        File f3=new File("F:\\DES\\encrypted.txt");
        FileWriter fw=new FileWriter(f3);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(s);
        bw.close();
        PrintStream p=new PrintStream(ss.getOutputStream());
        p.println(s);
        
        
    }
}
