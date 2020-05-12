package lunarserver;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class LunarServer {
  LunarServer(Socket socket) throws Exception {}

  public static void main(String args[]) throws Exception {
    InetAddress localHost = InetAddress.getLocalHost();

    System.out.println("Host address: " + localHost.getHostAddress());
    System.out.println("Hostname: " + localHost.getHostName());

    ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(21370);
      System.out.println("Port: " + serverSocket.getLocalPort());
    } catch (Exception e) {
      System.err.println("Create server socket: " + e);
      return;
    }

    System.out.println("Quit with CTRL-C");
    while(true) {
      try {
        // Accept incoming call
        Socket socket = serverSocket.accept();
        // Stream of data on socket
        InputStream is = socket.getInputStream();
        // Stream reader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //  File file = new File("src/main/resources/lunarlander/configuration.json");
        //  CharSource source = Files.asCharSource(file, Charsets.UTF_8);
        //  String result = "Papież polak.";
        //  try {
        //    result = source.read();
        //  } catch (IOException e) {
        //    System.out.println("Something went wrong. Possible reasons: ");
        //    System.out.println("1) Folder your are trying to open does not exist.");
        //    System.out.println("2) You don't have permissions to open that file.");
        //  }

        // Data stream we are getting from the server
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        String test = "Papiez polak wita cie smiertelniku";
        oos.writeObject(test);
        System.out.println("Downloaded from server maps configuration file");

        // Stream info writer
        PrintWriter pw = new PrintWriter(os, true);
        String clientRequest = br.readLine();
        System.out.println("Client request: '" + clientRequest + "'");
        // Response info
        pw.println("Server response: Request was: '" + clientRequest + "'");
        // Closing socket
        socket.close();
        // Closing all streams
        br.close();
        pw.close();
        is.close();
        os.close();
      } catch (Exception e) {
        System.err.println("Server exception: " + e);
      }
    }
  }
}
