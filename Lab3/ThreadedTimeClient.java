
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedTimeClient {

    public static void main(String[] args) {
        if (args.length < 2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter two integers: ");
	int exp = scanner.nextInt();
	int cert = scanner.nextInt();

	while(true){
        	try (Socket socket = new Socket(hostname, port)) {
	            InputStream input = socket.getInputStream();
        	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

	            OutputStream output = socket.getOutputStream();
        	    PrintWriter writer = new PrintWriter(output, true);

	            writer.println(exp + " " + cert);
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

		System.out.print("Enter two integers: ");
		exp = scanner.nextInt();
		cert = scanner.nextInt();
	}
    }
}
