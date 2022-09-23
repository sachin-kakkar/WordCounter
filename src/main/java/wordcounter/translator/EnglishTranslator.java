package wordcounter.translator;

import java.util.HashMap;
import java.util.Map;

public class EnglishTranslator implements Translator {

    public static final Map<String, String> directorOfWords = new HashMap<>();

    // Initialise this from a repo ideally.
    static {
        directorOfWords.put("flower", "flower");
        directorOfWords.put("flor", "flower");
        directorOfWords.put("blume", "flower");
    }


    @Override
    public String getWord(String word) {
        String wordInEnglish = directorOfWords.get(word);
        if (wordInEnglish == null) {
            wordInEnglish = word;
        }

        return wordInEnglish;
    }
}
