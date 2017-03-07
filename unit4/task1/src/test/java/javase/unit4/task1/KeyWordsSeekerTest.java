package javase.unit4.task1;

import javase.unit4.task1.KeyWordsSeeker;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public abstract class KeyWordsSeekerTest {

    String testFileName = "TestJavaCode.java";
    String emptyFileName = "TestEmptyFile.java";
    String outputFileName;
    Map<String, Integer> expectedKeyWordsWithCount = new HashMap<>(); //TODO
    KeyWordsSeeker seeker;

    @Before
    public void setUp() {
        expectedKeyWordsWithCount.put("package", 1);
        expectedKeyWordsWithCount.put("import", 1);
        expectedKeyWordsWithCount.put("public", 1);
        expectedKeyWordsWithCount.put("interface", 1);
        expectedKeyWordsWithCount.put("void", 1);
        expectedKeyWordsWithCount.put("default", 1);
        expectedKeyWordsWithCount.put("super", 1);
        expectedKeyWordsWithCount.put("return", 1);
        expectedKeyWordsWithCount.put("this", 1);

        seeker = new KeyWordsByteSeeker(testFileName);
    }

    @Test
    public void testGetAllKeyWords() {
        Set<String> keywords = seeker.getKeyWords();

        assertEquals(expectedKeyWordsWithCount.keySet(), keywords);
    }

    @Test
    public abstract void testGetAllKeyWordsIfWhereIsNoKeyWords();

    @Test
    public void testGetKeyWordsWithCount() {
        Map<String, Integer> keywordsWithCount = seeker.getKeyWordsWithCount();

        assertEquals(expectedKeyWordsWithCount, keywordsWithCount);
    }

    @Test
    public void testPrintInto() throws IOException {
        File file = new File(outputFileName);
        file.delete();

        seeker.printInto(outputFileName);

        file = new File(outputFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        Pattern pattern = Pattern.compile("\\w+ \\d+");

        String line;
        while ((line = br.readLine()) != null) {
            assertTrue(pattern.matcher(line).find());
        }
    }
}
