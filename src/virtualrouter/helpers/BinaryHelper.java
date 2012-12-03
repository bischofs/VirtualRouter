package virtualrouter.helpers;
public class BinaryHelper {

    public static String convertToBinaryWithImportantBits(String toConvert) {
        StringBuilder builder = new StringBuilder();

        String[] ipAndBits = toConvert.split("/");
        int importantBits = Integer.parseInt(ipAndBits[1]);

        builder = getBinaryFromFragments(builder, ipAndBits[0]);

        return builder.toString().substring(0, importantBits);
    }

    private static StringBuilder getBinaryFromFragments(StringBuilder builder, String ipAddress) {
        String[] ipFragments = ipAddress.split("\\.");

        for(String fragment: ipFragments) {
            String binary = Integer.toBinaryString(Integer.parseInt(fragment));
            builder.append(addLeadingZeros(binary));
        }
        return builder;
    }

    public static String convertToBinary(String toConvert) {
        return getBinaryFromFragments(new StringBuilder(), toConvert).toString();
    }

    private static String addLeadingZeros(String binary) {
        int count = binary.length();
        while(count < 8) {
            binary = "0" + binary;
            count++;
        }
        return binary;
    }
}
