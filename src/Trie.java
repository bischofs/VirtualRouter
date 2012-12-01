import java.util.ArrayList;

public class Trie
{
    private TrieNode rootNode;
    private int strides;
    private TrieNode headNode;
    private String longestPrefixMatch;

    public Trie(int strides) {
        rootNode = new TrieNode("-1");
        headNode = rootNode;
        this.strides = strides;
    }

    public TrieNode getRootNode() {
        return rootNode;
    }

    public String findNextHopRouter(String binaryIp){
        String nextHop = "";
        String bit = "";
        if(binaryIp.length() >= strides) {
            bit = binaryIp.substring(0, strides);
        } else {
            bit = binaryIp;
        }

        if(!rootNode.hasChildForKey(bit)) {
            setLongestPrefixMatch();
            rootNode = headNode;
        } else {
            setLongestPrefixMatch();
            rootNode = rootNode.getChild(bit);
            if(binaryIp.length() == strides) {
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
        if(!rootNode.getNextHop().equals("-1")) {
            longestPrefixMatch = rootNode.getNextHop();
        }
    }

    public void insert(String binaryIp, String nextHop) {
        String bit = "";
        String binarySub = "";
        if(binaryIp.length() >= strides) {
            bit = binaryIp.substring(0, strides);
            binarySub = binaryIp.substring(strides);
        } else {
            bit = binaryIp;
            binarySub = binaryIp;
        }

        if(!rootNode.hasChildForKey(bit)) {
            System.out.println("No child found");
            TrieNode newChild = new TrieNode("-1");

            if(binaryIp.length() <= strides) {
                System.out.println("Adding child with next hop: " + nextHop);
                newChild.setNextHop(nextHop);
                rootNode.addChild(bit, newChild);
                rootNode = headNode;
            } else {
                System.out.println("Adding new empty child");
                rootNode.addChild(bit, newChild);
                rootNode = newChild;
                insert(binarySub, nextHop);
            }

        } else {
            TrieNode node = rootNode.getChild(bit);
            System.out.println("Found child");
            rootNode = node;
            if(binaryIp.length() <= strides) {
                System.out.println("Setting next hop: " + nextHop +" in found child" );
                rootNode.setNextHop(nextHop);
                rootNode = headNode;
            } else {
                System.out.println("Binary of: " + binaryIp + " not done traversing");
                insert(binarySub, nextHop);
            }
        }
    }

    public int getStrides() {
        return strides;
    }

    public void setStrides(int strides) {
        this.strides = strides;
    }
}