package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class ServerData {
    public static void main(String[] args) {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(8886);

            while (true) {
                Socket socket = listener.accept();
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.print("labas " + new Date().toString());
                pw.flush();
                socket.close();
            }

        } catch (Exception ex) {
            System.out.println("klaida " + ex);
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
