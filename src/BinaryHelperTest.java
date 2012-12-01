import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BinaryHelperTest extends TestCase {
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testConvertToBinary() throws Exception {
        assertThat(BinaryHelper.convertToBinary("4.0"), equalTo("0000010000000000"));
        assertThat(BinaryHelper.convertToBinary("0.8"), equalTo("0000000000001000"));
        assertThat(BinaryHelper.convertToBinary("2.1"), equalTo("0000001000000001"));
        assertThat(BinaryHelper.convertToBinary("37.4"), equalTo("0010010100000100"));

    }
}
