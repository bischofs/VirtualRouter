import virtualrouter.helpers.BinaryHelper;
import virtualrouter.helpers.FileHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;

public class VirtualRouter {
    Trie trie;
    float averageLookupTime = 0;
    long totalLookupTime = 0;

    public VirtualRouter() {
    }

    public VirtualRouter(String buildFile, String testFile, int strides) {
        createTrieFromFile(buildFile, strides);
        writeRoutingTableToFile(testFile);

    }

    private void writeRoutingTableToFile(String testFile) {
        BufferedReader reader = FileHelper.openFile(testFile);
        String line = null;
        PrintWriter writer;
        float count = 0;

        try {
            writer = FileHelper.openWriteFile("routing.txt");
            while ((line = reader.readLine()) != null) {
                count++;
                String ipAddress = BinaryHelper.convertToBinary(line);
                long startTime = System.nanoTime();
                String nextHop = trie.findNextHopRouter(ipAddress);
                long endTime = System.nanoTime();
                totalLookupTime = totalLookupTime + (endTime - startTime);
                averageLookupTime = totalLookupTime / count;

                String formattedString = String.format("%s   %-20s", line, nextHop);

                System.out.println("Writing " + formattedString);

                writer.write(formattedString + "\n");
            }
            writer.close();
            System.out.println("The Average Lookup Time was: " + averageLookupTime);
        } catch (IOException e) {
            System.out.println("Couldn't read line from test file");
            e.printStackTrace();
        }

    }


    private void createTrieFromFile(String buildFile, int strides) {
        BufferedReader reader = FileHelper.openFile(buildFile);
        String line = null;
        trie = new Trie(strides);
        try {
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                String ipAddress = splitLine[0];
                String nextHop = splitLine[1];
                String binaryIp = BinaryHelper.convertToBinaryWithImportantBits(ipAddress);
                trie.insert(binaryIp, nextHop);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Couldn't read line from builder file");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int stride = FileHelper.getStrideLength();
        VirtualRouter vr = new VirtualRouter(args[0], args[1], stride);
    }
}