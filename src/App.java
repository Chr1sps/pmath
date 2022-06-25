import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        /*
         * All primitive data types:
         * byte (8 bits)
         * short (16 bits)
         * int (32 bits)
         * long (64 bits)
         * float (32 bits)
         * double (64 bits)
         * char (16 bits) (????)
         * boolean (1 bit, padded).
         */
        Scanner myScanner = new Scanner(System.in);
        Segment s = new Segment(new Point(1, 2), new Point(3, 4));
        System.out.println(s.getLength());
        myScanner.close(); // closes the stream
    }
}
