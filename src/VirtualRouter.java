import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

public class VirtualRouter {
    Trie trie;
    public VirtualRouter(String file, int strides) {
        BufferedReader reader = FileHelper.openFile(file);
        String line = null;
        trie = new Trie(strides);
        try {
            while((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                String ipAddress = splitLine[0];
                String nextHop = splitLine[1];
                String binaryIp = convertIPtoBinary(ipAddress);
                System.out.println("Binary IP Address: " + binaryIp);
                trie.insert(binaryIp, nextHop);
            }
        } catch (IOException e) {
            System.out.println("Couldn't read line :(");
            e.printStackTrace();
        }

    }

    private String convertIPtoBinary(String ipAddress) {
        String importantBitsString = ipAddress.split("/")[1];
        int importantBitsInt = Integer.parseInt(importantBitsString);
        ipAddress = stripPeriodsFromIP(ipAddress);
        String fullAddress = new BigInteger(ipAddress.getBytes()).toString(2);
        return fullAddress.substring(0,importantBitsInt);
    }

    private String stripPeriodsFromIP(String ipAddress) {
        return ipAddress.replace(".","");
    }

    public static void main(String[] args) {
		int stride = FileHelper.getStrideLength();
        VirtualRouter vr = new VirtualRouter(args[0], stride);
    }
}