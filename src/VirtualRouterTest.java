import junit.framework.TestCase;
import virtualrouter.helpers.BinaryHelper;
import virtualrouter.helpers.FileHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class VirtualRouterTest extends TestCase {
    VirtualRouter target;
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testSmallInputProducesCorrectTrie() {
        String inputFile = "/Users/rabel/git/school/CS_457/VirtualRouter/resources/large_input.txt";
        String ipFile = "/Users/rabel/git/school/CS_457/VirtualRouter/resources/small_test_input.txt";

        target = new VirtualRouter(inputFile, ipFile, 4);

        String ipAddress = BinaryHelper.convertToBinary("65.71.156.183");

        assertThat(target.trie.findNextHopRouter(ipAddress), equalTo("129.250.0.11"));

    }
}
