import junit.framework.TestCase;
import virtualrouter.helpers.BinaryHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BinaryHelperTest extends TestCase {
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testConvertToBinaryWithImportantBits() throws Exception {
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("4.0/14"), equalTo("00000100000000"));
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("0.8/12"), equalTo("000000000000"));
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("2.1/8"), equalTo("00000010"));
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("37.4/4"), equalTo("0010"));
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("0.0/0"), equalTo(""));
        assertThat(BinaryHelper.convertToBinaryWithImportantBits("65.72.0.0/16"), equalTo("0100000101001000"));

    }

    public void testConvertToBinary() throws Exception {
        assertThat(BinaryHelper.convertToBinary("8.0"), equalTo("0000100000000000"));
    }
}
