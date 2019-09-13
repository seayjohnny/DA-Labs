
import java.io.*;
import java.net.Socket;

public class ThreadedTimeClient {

    public static void main(String[] args) {
        if (args.length < 4) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);


        try (Socket socket = new Socket(hostname, port)) {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(args[2] + " " + args[3]);
            String response = reader.readLine();
            do
            {
                if(response != null) {
                    System.out.println(response);
                    break;
                }
                response = reader.readLine();
            } while (response == null);

        }
        catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
