public class StringActions {

    public StringActions() {

    }
    public int wordCount(String sentence){

        // Splits sentence into constituent words
        String[] words = sentence.split(" ");

        // Returns the length of the "words" array (Equating to the number of words in the sentence)
        return words.length;

    }

    public String reverseSentence(String sentence){

        // Reversing string manually (I am choosing not to use the inbuilt "reverse" method)
        StringBuilder reversedSentenceBuilder = new StringBuilder();

        for (int i = sentence.length()-1; i >= 0; i--){
            reversedSentenceBuilder.append(sentence.charAt(i));
        }

        // Converts Stringbuilder to String
        String reversedSentence = reversedSentenceBuilder.toString();
        return reversedSentence;
    }

    public Boolean containsSubString(String subString, String sentence){
        // Checks whether sentence contains substring
        return sentence.toLowerCase().contains(subString.toLowerCase());
    }

    public String replaceSpaces(char replacementChar, String sentence){
        // Replaces spaces in string with an underscore
        return sentence.replace(" ", String.valueOf(replacementChar));
    }
}
