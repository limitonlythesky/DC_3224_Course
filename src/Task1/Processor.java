package Task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();
        String st = request.getRequestLine();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        if (st.length() > 11 && st.substring(0, 12).equals("GET /compute")) {
            Compute com = new Compute();
            com.solution(7);
            output.println("<body><p>" + com.ans + "</p></body>");
        }else if (st.length() > 23 && st.substring(0, 24).equals("GET /write/data.txt/data")) {
            output.println("<body><p>File created</p></body>");
        }else if (st.length() > 24 && st.substring(0, 25).equals("GET /delete/data.txt/data")){
            output.println("<body><p>File deleted</p></body>");
        } else {
            output.println("<body><p>Hello, World!</p></body>");
        }
        output.println("</html>");
        output.flush();
        socket.close();
    }
}
