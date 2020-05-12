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

        String clientRequest = br.readLine();
        RequestType requestType = getTypeFromRequest(clientRequest);

        String response;

        switch (requestType) {
          case GET_MAP:
            response = getConfigJson();
            break;
          case PUSH_SCORE:
            response = "OK";
            break;
          default:
            response = "Invalid request";
            break;
        }

        // Data stream we are getting from the server
        OutputStream os = socket.getOutputStream();

        // Stream info writer
        PrintWriter pw = new PrintWriter(os, true);
        System.out.println("Client request: `" + clientRequest + "'");
        // Response info
        pw.print(response);
        pw.println();
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

  private static enum RequestType {
    GET_MAP,
    PUSH_SCORE,
    INVALID
  }

  private static RequestType getTypeFromRequest(String request) {
    if (request.matches("(?i)^GET.*")) {
      return RequestType.GET_MAP;
    } else if (request.matches("(?i)^PUSH.*")) {
      return RequestType.PUSH_SCORE;
    } else {
      return RequestType.INVALID;
    }
  }

  private static String getConfigJson() {
    File configFile = new File("src/main/resources/lunarserver/configuration.json");
    CharSource source = Files.asCharSource(configFile, Charsets.UTF_8);
    String configJson = "";
    try {
      configJson =  source.read();
    } catch (IOException e) {
      System.out.println("Something went wrong. Possible reasons: ");
      System.out.println("1) Folder your are trying to open does not exist.");
      System.out.println("2) You don't have permissions to open that file.");
    }

    return configJson;
  }
}
