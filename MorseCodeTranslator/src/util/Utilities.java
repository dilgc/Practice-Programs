package util;
/**
 * Utilities class for storing methods
 */
public class Utilities {
    //Create the morse and letter arrays to correlate to one another for easy translation
    static final private String[] MORSE_CODE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    static final private String[] LETTERS = "abcdefghijklmnopqrstuvwxyz".split("");

    /**
     * Determines the inputted language (English, Morse, or other.)
     * @return 1 if alphanumeric, -1 if morse, and 0 if neither.
     */
    public static int getLanguage(String str){

        if(str.matches("[a-zA-Z ]+")) { //If English
            return 1;
        }
        else if(str.matches("^[\\.\\-/ ]*$")) { //If Morse
            return -1;
        }
        else {
            return 0;
        }
    }

    /**
     * Translates a word to morse
     * @param word a word to be translated
     * @return the morse translation of the word
     */
    public static String TranslateWordToMorse(String word) {

        StringBuilder translation =  new StringBuilder();

        //For every letter, search for a corresponding morse code sequence. Then append it and add a space
        for(int i = 0; i < word.length(); i++){
            for(int j = 0; j < LETTERS.length; j++){
                if(Character.toString(word.charAt(i)).equalsIgnoreCase(LETTERS[j])){
                    translation.append(MORSE_CODE[j]);
                    translation.append(" ");
                }
            }
        }
        return translation.toString();
    }

    /**
     * Translates morse into a word
     * @param morse to be translated
     * @return a translated word
     */
    public static String TranslateMorseToWord(String morse){

        String[] lettersToTranslate = morse.split(" ");
        StringBuilder translation = new StringBuilder();

        //For every morse sequence, search for a corresponding letter. Then append it and add a "/"
        for(int i = 0; i < lettersToTranslate.length; i++){
            for(int j = 0; j < LETTERS.length; j++){
                if(lettersToTranslate[i].equals(MORSE_CODE[j])){
                    translation.append(LETTERS[j]);
                }
            }
        }
        return translation.toString();
    }

    /**
     * Translates the entire text, depending on the language
     * @param text The text, either in morse or in English
     * @return The text, but in the opposite option
     */
    public static String Translate(String text){
        int language = getLanguage(text); //Is the user writing in Morse or English?
        String[] words;
        StringBuilder translation = new StringBuilder();

        //If English, translate to Morse.
        if(language == 1){
            words = text.split(" ");
            for(String word : words){
                translation.append(TranslateWordToMorse(word));
                translation.append(" / ");
            }
            translation.delete(translation.length()-3, translation.length());
        }
        //If Morse, translate to English
        else if(language == -1){
            words = text.split(" / ");
            for(String word : words){
                translation.append(TranslateMorseToWord(word));
                translation.append(" ");
            }
        }
        //Otherwise, warn the user about their input in the output box.
        else{
            translation.append("Error: Invalid input. Please enter letters for English or -./ for morse.");
        }
        return translation.toString();
    }
}
