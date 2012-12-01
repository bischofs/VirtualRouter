import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

public class VirtualRouter {
    Trie trie;
    public VirtualRouter(String buildFile, String testFile, int strides) {
        createTrieFromFile(buildFile, strides);
        writeRoutingTableToFile(testFile);

    }

    private void writeRoutingTableToFile(String testFile) {
        BufferedReader reader = FileHelper.openFile(testFile);
        String line = null;

        try {
            while((line = reader.readLine()) != null) {
                String ipAddress = BinaryHelper.convertToBinary(line);

                String router = trie.findNextHopRouter(ipAddress);
                FileHelper.writeToFile(router, line, "routing.txt");
            }
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
            while((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                String ipAddress = splitLine[0];
                String nextHop = splitLine[1];
                String binaryIp = BinaryHelper.convertToBinary(ipAddress);
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