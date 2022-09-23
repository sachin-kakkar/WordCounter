package wordcounter.validator;

/**
 * For a given work check if validation rule(s) are satisfied.
 */
public interface Validator {
    boolean doCheck(String word);
}
