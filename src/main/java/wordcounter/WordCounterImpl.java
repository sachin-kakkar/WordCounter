package wordcounter;

import wordcounter.translator.EnglishTranslator;
import wordcounter.translator.Translator;
import wordcounter.validator.StringOnlyAlphabet;
import wordcounter.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounterImpl implements WordCounter {

    private Map<String, AtomicInteger> words = new ConcurrentHashMap<>();
    private Translator translator;

    // List of validators to perform validations. This can be added.
    // I have not added unit test for validators but it will be easy to add
    // and debug when any issue.
    private static List<Validator> validators = new ArrayList<>();
    static {
        validators.add(new StringOnlyAlphabet());
    }

    public WordCounterImpl(Translator translator) {
        this.translator = translator;
    }

    @Override
    public boolean addWord(String word) {
        if (checkNonNullOrEmptyString(word)) return false;

        if (!validate(word)) {
            System.out.println(" .Cannot add word " + word);
            return false;
        }
        String wordToAdd = translator.getWord(word);
        AtomicInteger currentCount = words.get(wordToAdd);
        if (currentCount == null) {
            words.put(word,new AtomicInteger(1));
        } else {
            currentCount.getAndIncrement();
            words.put(wordToAdd,currentCount);
        }

        return true;
    }

    private boolean checkNonNullOrEmptyString(String word) {
        if ((word == null) || word.equals("")) {
            System.out.println("Cannot perform operation as empty string or null");
            return true;
        }
        return false;
    }

    @Override
    public int getCount(String word) {
        if (checkNonNullOrEmptyString(word)) return 0;

        AtomicInteger currentCount = words.get(word);
        return (currentCount == null) ? 0 : currentCount.get();
    }

    private boolean validate(String word) {
        for (Validator validator :validators) {
            if (!validator.doCheck(word)) {
                return false;
            }
        }
        return true;
    }
}
