import org.junit.jupiter.api.Test;
import org.task.LineParser;

import static org.junit.jupiter.api.Assertions.*;

public class LineParserTest {

    @Test
    public void validLineTest() {
        String line = "\"a\";\"b\";\"c\"";
        String[] result = LineParser.parseLineOrReturnNull(line);
        assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    @Test
    public void invalidQuotesTest() {
        String line = "\"a\";\"b\";c";
        assertNull(LineParser.parseLineOrReturnNull(line));
    }

    @Test
    public void invalidLineTest() {
        String line = "\"a\"b\";c";
        assertNull(LineParser.parseLineOrReturnNull(line));
    }

    @Test
    public void emptyLineTest() {
        assertNull(LineParser.parseLineOrReturnNull(""));
    }
}
