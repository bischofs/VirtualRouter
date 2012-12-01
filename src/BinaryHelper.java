public class BinaryHelper {

    public static String convertToBinary(String toConvert) {
        StringBuilder builder = new StringBuilder();
        String[] ipFragments = toConvert.split("\\.");
        for(String fragment: ipFragments) {
            String binary = Integer.toBinaryString(Integer.parseInt(fragment));
            builder.append(addLeadingZeros(binary));
        }

        return builder.toString();
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
