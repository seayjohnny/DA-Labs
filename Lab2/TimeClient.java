
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {
        if (args.length < 2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String time = reader.readLine();
            System.out.println(time);
        }
        catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
