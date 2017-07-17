package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by CodeAcademy on 2017.07.17.
 */
public class ClientData {
    public static void main(String[] args) {

        System.out.println("veikia");

        try {
            Socket socket = new Socket("0.0.0.0", 8886);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String atsakymas = bufferedReader.readLine();
            System.out.println("atsakymas is serverio " + atsakymas);

        } catch (Exception ex) {
            System.out.println("klaida " + ex);
        }
    }
}
