package Task2.First;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor extends Thread{
    private final Socket socket;
    private final HttpRequest request;
    private int number;

    public Processor(Socket socket, HttpRequest request, int number) {
        this.socket = socket;
        this.request = request;
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();
        String st = request.toString();
        System.out.println(number);
        try {
            // To send response back to the client.
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            if (st.length() > 10 && st.substring(0, 12).equals("GET /compute")) {
                Compute com = new Compute();
                com.solution(7);
                output.println("<body><p>" + com.ans + "</p></body>");
            } else if (st.length() > 22 && st.substring(0, 24).equals("GET /write/data.txt/data")) {
                output.println("<body><p>File created</p></body>");
            } else if (st.length() > 23 && st.substring(0, 25).equals("GET /delete/data.txt/data")) {
                output.println("<body><p>File deleted</p></body>");
            } else {
                output.println("<body><p>Hello, World!</p></body>");
            }
            output.println("</html>");
            output.flush();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}