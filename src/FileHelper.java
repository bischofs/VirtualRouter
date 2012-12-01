import java.io.*;
import java.util.ArrayList;

public class FileHelper {
    public static ArrayList<String> readFile(String file) {
        ArrayList<String> ipAddresses = new ArrayList<String>();

    return ipAddresses;
    }

    public static int getStrideLength() {
        int stride = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter stride length (1 - 4): ");
        try {
            String line = reader.readLine();
            stride = Integer.parseInt(line);
			if (stride > 4 || stride < 1){ 
				stride = 1;
			}
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
            System.out.println("Caught IO Exception while opening the read file, whoopsies");
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    public static BufferedWriter openWriteFile(String file) {
        BufferedWriter writer = null;
        try {
            FileWriter output = new FileWriter(file);
            writer = new BufferedWriter(output);
        } catch (IOException e) {
            System.out.println("Caught IO Exception while opening write file, whoopsies");
            e.printStackTrace();
            return null;
        }
        return writer;
    }

    public static void writeToFile(String router, String ipAddress, String toFile) {
        BufferedWriter writer = openWriteFile(toFile);
        try {
            writer.write(ipAddress + " " + router + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
            e.printStackTrace();
        }
    }
}
