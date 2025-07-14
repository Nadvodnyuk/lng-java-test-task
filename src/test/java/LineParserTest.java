import org.junit.jupiter.api.Test;
import org.task.LineParser;

import static org.junit.jupiter.api.Assertions.*;

public class LineParserTest {

    @Test
    public void validLineTest() {
        String line1 = "\"a\";\"b\";\"c\"";
        String line2 = "\"a\"     ;\"b\";\"c\"";
        String[] result1 = LineParser.parseLineOrReturnNull(line1);
        String[] result2 = LineParser.parseLineOrReturnNull(line2);
        assertArrayEquals(new String[]{"a", "b", "c"}, result1);
        assertArrayEquals(new String[]{"a", "b", "c"}, result2);
    }

    @Test
    public void validLineEmptyTest() {
        String line1 = ";;\"b\";\"c\"";
        String line2 = "\"a\";\"b\";";
        String line3 = "\"a\";\"b\";;";

        String[] result1 = LineParser.parseLineOrReturnNull(line1);
        String[] result2 = LineParser.parseLineOrReturnNull(line2);
        String[] result3 = LineParser.parseLineOrReturnNull(line3);
        assertArrayEquals(new String[]{"","", "b", "c"}, result1);
        assertArrayEquals(new String[]{"a", "b", ""}, result2);
        assertArrayEquals(new String[]{"a","b", "", ""}, result3);
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
