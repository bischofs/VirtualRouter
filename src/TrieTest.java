import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrieTest extends TestCase {
    Trie target;
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testInsertCanGetNextHopOfLeaf() throws Exception {
        target = new Trie(1);

        target.insert("100", "95.123.12.1");
        TrieNode rootNode = target.getRootNode();

        assertThat(rootNode.getChildren().size(), equalTo(2));
        assertThat(rootNode.getChild("1").getChild("0").getChild("0").getNextHop(), equalTo("95.123.12.1"));

        target.insert("011", "92.432.4");
        assertThat(rootNode.getChildren().size(), equalTo(2));
        assertThat(rootNode.getChild("0").getChild("1").getChild("1").getNextHop(), equalTo("92.432.4"));

    }


    public void testInsertSetsHeadNodeIfNoIPLength() throws Throwable {
        target = new Trie(1);
        TrieNode rootNode = target.getRootNode();
        target.insert("", "1");

        assertThat(rootNode.getNextHop(), equalTo("1"));

    }

    public void testInsertGetsTheCorrectNumberOfChildren() throws Exception {
        target = new Trie(1);
        TrieNode rootNode = target.getRootNode();
        target.insert("10", "2");

        target.insert("11", "3");
        assertThat(rootNode.getChild("1").getChildren().size(), equalTo(2));

    }

    public void testInsertCanUseMultipleStrideLength() throws Exception {
        target = new Trie(2);

        target.insert("1011", "7");
        target.insert("1111", "15");
        target.insert("0011", "3");

        assertThat(target.getRootNode().getChildren().size(), equalTo(4));
        assertThat(target.getRootNode().getChild("10").getChild("11").getNextHop(), equalTo("7"));

    }

    public void testInsertCanUseMultipleStrideLengthWithNotNiceIPLengths() throws Exception {
        target = new Trie(2);

        target.insert("10110", "7");
        target.insert("1111101", "15");
        target.insert("001100", "3");
        target.insert("0", "0");

        assertThat(target.getRootNode().getChildren().size(), equalTo(4));

    }

    public void testfindNextHopRouter() throws Exception {
        target = new Trie(1);

        target.insert("101", "2");
        target.insert("111", "3");

        assertThat(target.findNextHopRouter("101"), equalTo("2"));
        assertThat(target.findNextHopRouter("111"), equalTo("3"));

    }

    public void testFindNextHopRouterReturnsClosetMatch() throws Exception {
        target = new Trie(1);

        target.insert("10000", "32");
        target.insert("1", "1");

        assertThat(target.findNextHopRouter("100"), equalTo("1"));
    }

    public void testFindNextHopRouterCanUseMultipleStrides() throws Exception {
        target = new Trie(2);

        target.insert("1010", "42");
        target.insert("1011", "48");

        assertThat(target.findNextHopRouter("1010"), equalTo("42"));
        assertThat(target.findNextHopRouter("1011"), equalTo("48"));
    }

    public void testFindNextHopRouterReturnsClosestMatchWithMultipleStrides() throws Exception {
        target = new Trie(3);

        target.insert("101010", "50");
        target.insert("101", "51");

        assertThat(target.findNextHopRouter("1010"), equalTo("51"));
        assertThat(target.findNextHopRouter("1"), equalTo("No Prefix found"));

    }
}
