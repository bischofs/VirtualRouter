import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrieTest extends TestCase {
    Trie target;
    public void setUp() throws Exception {
        target = new Trie(1);

    }

    public void tearDown() throws Exception {

    }

    public void testInsertCanGetNextHopOfLeaf() throws Exception {
        target.setStrides(1);
        target.insert("100", "95.123.12.1");
        TrieNode rootNode = target.getRootNode();

        assertThat(rootNode.getChildren().size(), equalTo(1));
        assertThat(rootNode.getChild("1").getChild("0").getChild("0").getNextHop(), equalTo("95.123.12.1"));

        target.insert("011", "92.432.4");
        assertThat(rootNode.getChildren().size(), equalTo(2));
        assertThat(rootNode.getChild("0").getChild("1").getChild("1").getNextHop(), equalTo("92.432.4"));

    }

    public void testInsertGetsTheCorrectNumberOfChildren() throws Exception {
        TrieNode rootNode = target.getRootNode();
        target.insert("10", "2");

        target.insert("11", "3");
        assertThat(rootNode.getChild("1").getChildren().size(), equalTo(2));

    }

    public void testInsertCanUseMultipleStrideLength() throws Exception {
        target.setStrides(2);

        target.insert("1011", "7");
        target.insert("1111", "15");
        target.insert("0011", "3");

        assertThat(target.getRootNode().getChildren().size(), equalTo(3));

    }

    public void testInsertCanUseMultipleStrideLengthWithNotNiceIPLengths() throws Exception {
        target.setStrides(2);

        target.insert("10110", "7");
        target.insert("1111101", "15");
        target.insert("001100", "3");
        target.insert("0", "0");

        assertThat(target.getRootNode().getChildren().size(), equalTo(4));

    }
}
