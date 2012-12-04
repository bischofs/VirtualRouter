import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Trie {
    private TrieNode rootNode;
    private int strides;
    private TrieNode headNode;
    private String longestPrefixMatch;

    public Trie(int strides) {
        rootNode = new TrieNode("-1");
        this.strides = strides;
        rootNode = createChildrenForNode(rootNode);
        headNode = rootNode;
    }

    public TrieNode getRootNode() {
        return rootNode;
    }

    public String findNextHopRouter(String binaryIp) {
        String bit = "";

        if (binaryIp.length() >= strides) {
            bit = binaryIp.substring(0, strides);
        } else {
            bit = binaryIp;
        }
        if (binaryIp.equals("")) {
            setLongestPrefixMatch();
            return longestPrefixMatch;
        }

        if (!rootNode.hasChildForKey(bit)) {
            setLongestPrefixMatch();
            rootNode = headNode;
        } else {
            setLongestPrefixMatch();
            rootNode = rootNode.getChild(bit);
            if (binaryIp.length() == strides) {
                setLongestPrefixMatch();
                rootNode = headNode;
            } else {
                return findNextHopRouter(binaryIp.substring(strides));
            }
        }
        String rtn = longestPrefixMatch;
        longestPrefixMatch = null;
        return rtn == null ? "No Prefix found" : rtn;
    }

    private void setLongestPrefixMatch() {
        if (!rootNode.getNextHop().equals("-1")) {
            longestPrefixMatch = rootNode.getNextHop();
        }
    }

    public void insert(String binaryIp, String nextHop) {
        String bit = "";
        String binarySub = "";

        if(binaryIp.length() == 0) {
            rootNode.setNextHop(nextHop);
            return;
        }

        if (binaryIp.length() >= strides) {
            bit = binaryIp.substring(0, strides);
            binarySub = binaryIp.substring(strides);
        } else {
            if(rootNode.getChildren().size() == 0) {
                createChildrenForNode(rootNode);
            }
            insertNaughtyBit(binaryIp, nextHop);
            rootNode = headNode;
            return;
        }

        if(rootNode.getChildren().size() == 0) {
            createChildrenForNode(rootNode);
        }
        rootNode = rootNode.getChild(bit);

        if (binaryIp.length() == strides) {
            rootNode.setNextHop(nextHop);
            rootNode = headNode;
        } else {
            insert(binarySub, nextHop);
        }

    }

    private TrieNode createChildrenForNode(TrieNode node) {
        String[] connections;

        if (strides == 2) {
            connections = new String[]{"00", "01", "10", "11"};
            return createChildrenWithConnection(connections, node);

        } else if (strides == 3) {
            connections = new String[]{"000", "001", "010", "011", "100", "101", "110", "111"};
            return createChildrenWithConnection(connections, node);

        } else if (strides == 4) {
            connections = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001",
                    "1010", "1011", "1100", "1101", "1110", "1111"};
            return createChildrenWithConnection(connections, node);

        } else {
            connections = new String[]{"0", "1"};

            return createChildrenWithConnection(connections, node);
        }
    }

    private TrieNode createChildrenWithConnection(String[] connections, TrieNode node) {
        for (String connection : connections) {
            TrieNode newChild = new TrieNode("-1");
            node.addChild(connection, newChild);
        }
        return node;
    }

    public void insertNaughtyBit(String toMatch, String nextHop) {
        Iterator it = rootNode.getChildren().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String key = pairs.getKey().toString();
            if (key.startsWith(toMatch) && rootNode.getChild(key).getNextHop().equals("-1")) {
                rootNode.getChild(key).setNextHop(nextHop);
            }
        }
    }


    public void setStrides(int strides) {
        this.strides = strides;
    }
}