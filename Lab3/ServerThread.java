import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String[] args = reader.readLine().split(" ");
            BigInteger exp = new BigInteger(args[0]);
            Integer cert = Integer.parseInt(args[1]);

            writer.println(exp.toString() + " with certainty " + cert + ": " + exp.isProbablePrime(cert));
            socket.close();

        }
        catch (Exception e)
        {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
