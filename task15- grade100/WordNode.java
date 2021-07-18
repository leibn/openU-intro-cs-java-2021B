/**
 * WordNode.java is a class for mmn 15 Ex' in "intro to cs and java" course in the open university.
 * the class represents one member(node) of the list.
 * The overall goal is to analyze text.
 *
 *
 * @author : Daniel Leibner
 * @class: Ex15
 * @authorId : #########
 * @version: 11/06/21
 */
public class WordNode {
    private static WordNode mostFrequentWord;
    private static int differentWordsInList;
    private static int totalWordsInList;
    private String _word;
    private WordNode _next;
    private int _numberOfTimesInText;


    /**
     * An empty constructor that initializes the node
     * complexity O(1)
     */
    public WordNode() {
        _word = null;
        _next = null;
        _numberOfTimesInText = 0;
    }

    /**
     * A constructor that receives:
     * string that represents text,
     * WordNode object represents next node.
     * complexity O(1)
     *
     * @param word string that represents text
     * @param next WordNode object represents next node
     */
    public WordNode(String word, WordNode next) {
        _word = word;
        plusOneToTimesInText();
        setNext(next);
        differentWordsInList++;
        setMostFrequentWord();//will check if it is the most frequent word
    }

    /**
     * A constructor that receives:
     * string that represents text,
     * WordNode object represents next node.
     * int represent the times the word was already shown in the text
     * complexity O(1)
     *
     * @param word  string that represents text
     * @param next  WordNode object represents next node
     * @param times int represent the times the word was already shown in the text
     */
    public WordNode(String word, WordNode next, int times) {
        _word = word;
        _next = next;
        _numberOfTimesInText = times;
        differentWordsInList++;
        totalWordsInList += times;
        setMostFrequentWord();//will check if it is the most frequent word
    }

    /**
     * A constructor that receives as a parameter a string that represents text
     * and input the word as a node that not have next value.
     * complexity O(1)
     *
     * @param firstWord the word to make the node for
     */
    public WordNode(String firstWord) {
        _word = firstWord;
        _next = null;
        _numberOfTimesInText = 1;
        differentWordsInList++;
        totalWordsInList++;
        setMostFrequentWord();//will check if it is the most frequent word
    }

    /**
     * static method that restarts all the static values in the word node class
     * complexity O(1)
     */
    public static void restartStaticsParameters() {
        mostFrequentWord = null;
        differentWordsInList = 0;
        totalWordsInList = 0;
    }

    /**
     * static method that gets the most Frequent Word that was insert as node in that list.
     * complexity O(1)
     *
     * @return the most Frequent Word that entered
     */
    public static WordNode getMostFrequentWord() {
        return mostFrequentWord;
    }

    /**
     * getter to get num of different words in list
     * complexity O(1)
     *
     * @return num of different words in list
     */
    public static int getNumOfDifferentWordsInList() {
        return differentWordsInList;
    }
//    }

    /**
     * getter to get total num of words in list
     * complexity O(1)
     *
     * @return total num of words in list
     */
    public static int getTotalWordsInList() {
        return totalWordsInList;
    }

    /**
     * setter to the most Frequent Word.
     * the setter will check the state and will change the field as expected.
     * complexity O(1)
     */
    public void setMostFrequentWord() {
//        if (this != null) {
        if (null == mostFrequentWord) {
            mostFrequentWord = this;
        } else if (getNumberOfTimesInText() > mostFrequentWord._numberOfTimesInText) {
            mostFrequentWord = this;
        } else if (_numberOfTimesInText == mostFrequentWord._numberOfTimesInText) {
            if (0 < mostFrequentWord._word.compareTo(this._word)) {
                mostFrequentWord = this;
            }
        }
    }

    /**
     * method that gives +1 to the Times In Text of that node.
     * complexity O(1)
     */
    public void plusOneToTimesInText() {
        _numberOfTimesInText++;
        totalWordsInList++;
        setMostFrequentWord();//will check if it is the most frequent word
    }

    /**
     * getter to get the word of the node
     * complexity O(1)
     *
     * @return the word of the node
     */
    public String getWord() {
        return this._word;
    }

    /**
     * setter the _Word value.
     * complexity O(1)
     * @param word the word to change the node to
     */
    public void setWord(String word) {
        this._word = word;
    }

    /**
     * getter to get the next node of this node
     * complexity O(1)
     *
     * @return the next node of this node
     */
    public WordNode getNext() {
        return this._next;
    }

    /**
     * setter to the WordNode next.
     * the setter will set the next value of this node to next .
     * complexity O(1)
     *
     * @param next the next value to set for this node
     */
    public void setNext(WordNode next) {
        if (null != next && _word.equals(next._word)) {
            _numberOfTimesInText += next._numberOfTimesInText;
            differentWordsInList--;//the words was equals.
            setMostFrequentWord();//will check if it is the most frequent word
            setNext(next._next);
        } else {
            _next = next;
        }
    }

    /**
     * getter to get number of this word of the node was shown in text
     * complexity O(1)
     *
     * @return number of this word of the node was shown in text
     */
    public int getNumberOfTimesInText() {
        return this._numberOfTimesInText;
    }

    /**
     * setter to the WordNode nodeToAdd attribute not by the alphabetic order.
     * after using this setter most to sort the list.
     * the setter will set:
     * nodeToAdd._next = _head .
     * _head = nodeToAdd .
     * complexity O(1)
     *
     * @param nodeToAdd the nodeToAdd value to set for this node
     */
    public void addAsHead(WordNode nodeToAdd) {
        _next = nodeToAdd;
    }

    /**
     * getter to get the start char of this word of the node
     * complexity O(1)
     *
     * @return the start char of this word of the node
     */
    public char getStartChar() {
        return this._word.charAt(0);
    }

    /**
     * to string method will return "word +tab + number Times The Word Mention in given string"
     *
     * @return string represent the node
     */
    @Override
    public String toString() {
        return this._word + '\t' + this._numberOfTimesInText + '\n';
    }
}