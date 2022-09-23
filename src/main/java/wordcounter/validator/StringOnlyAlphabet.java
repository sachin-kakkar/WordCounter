package wordcounter.validator;

/**
 * Check for only alpha
 * If present return false i.e. check failed.
 */
public class StringOnlyAlphabet implements Validator {

    private static final String regex = "^[a-zA-Z]*$";

    @Override
    public boolean doCheck(String word) {
        boolean result = (word.matches("^[a-zA-Z]*$"));
        if (!result) {
            System.out.print(" StringOnlyAlphabet validation failed on " + word);
        }
        return result ;
    }
}
