import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHelper {
    public static ArrayList<String> readFile(String file) {
        ArrayList<String> ipAddresses = new ArrayList<String>();

    return ipAddresses;
    }

    public static int getStrideLength() {
        int stride = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter stride length (1 - 4): ");
        try {
            String line = reader.readLine();
            stride = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println("Couldn't parse value into an integer, setting stride length to default (1)" );
        }
        return stride;
    }

    public static BufferedReader openFile(String file) {
        BufferedReader reader = null;
        try {
            FileReader input = new FileReader(file);
            reader = new BufferedReader(input);
        } catch (IOException e) {
            System.out.println("Caught IO Exception, whoopsies");
            e.printStackTrace();
            return null;
        }
        return reader;
    }
}
