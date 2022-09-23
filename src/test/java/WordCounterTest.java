import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import wordcounter.WordCounter;
import wordcounter.WordCounterImpl;
import wordcounter.translator.EnglishTranslator;
import wordcounter.translator.Translator;

public class WordCounterTest {

    private static Translator translator;
    private static WordCounter wordCounter;

    @BeforeClass
    public static void initialise() {
        translator = new EnglishTranslator();
        wordCounter = new WordCounterImpl(translator);
    }

    @Test
    public void checkNull() {
        wordCounter.addWord(null);
        Assert.assertEquals(0, wordCounter.getCount(null));
    }

    @Test
    public void checkEmptyString() {
        wordCounter.addWord("");
        Assert.assertEquals(0, wordCounter.getCount(""));
    }

    @Test
    public void checkNonAlpha() {
        wordCounter.addWord("Check123");
        Assert.assertEquals(0, wordCounter.getCount("Check123"));
    }

    @Test
    public void checkMultipleWordsAndTranslation() {
        wordCounter.addWord("flower");
        wordCounter.addWord("flower");
        wordCounter.addWord("flor");
        wordCounter.addWord("blume");
        wordCounter.addWord("petals");

        Assert.assertEquals(4, wordCounter.getCount("flower"));
        Assert.assertEquals(0, wordCounter.getCount("flor"));
        Assert.assertEquals(0, wordCounter.getCount("blume"));
        Assert.assertEquals(1, wordCounter.getCount("petals"));
    }
}
