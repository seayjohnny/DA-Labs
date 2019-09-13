import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
	    int exp = Integer.parseInt(args[0]);
            BigInteger p = new BigInteger("2").pow(exp).subtract(BigInteger.ONE);
            Integer cert = Integer.parseInt(args[1]);

	    TimeUnit.SECONDS.sleep(5);

            writer.println("2^" + exp + " with certainty " + cert + ": " + p.isProbablePrime(cert));
            socket.close();
	    System.out.println("Connection closed");

        }
        catch (Exception e)
        {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
