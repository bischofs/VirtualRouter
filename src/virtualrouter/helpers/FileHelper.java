package virtualrouter.helpers;
import java.io.*;


public class FileHelper {

    public static int getStrideLength() {
        int stride = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter stride length (1 - 4): ");
        try {
            String line = reader.readLine();
            stride = Integer.parseInt(line);
			if (stride > 4 || stride < 1){
                System.out.println("Stride of ("+ stride + ") is not between 1 and 4, setting stride to 1.");
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

    public static PrintWriter openWriteFile(String file) {
        File writeFile = new File(file);
        try {
            return new PrintWriter(new FileWriter(writeFile), true);
        } catch (IOException e) {
            System.out.println("Caught IO Exception while opening write file, whoopsies");
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToFile(String data, String toFile) {
        PrintWriter writer = openWriteFile(toFile);
        writer.write(data);
    }
}
