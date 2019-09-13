
import java.io.*;
import java.net.Socket;

public class ThreadedTimeClient {

    public static void main(String[] args) {
        if (args.length < 4) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String response;

        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(args[2] + " " + args[3]);
            response = reader.readLine();
            do
            {
                System.out.println(response);
                response = reader.readLine();
            } while (response.isEmpty());

        }
        catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
