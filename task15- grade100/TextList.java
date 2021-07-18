/**
 * TextList.java is a class for mmn 15 Ex' in "intro to cs and java" course in the open university.
 * the class represents list of word's using the WordNode.java class.
 * The overall goal is to analyze text.
 *
 *
 * @author : Daniel Leibner
 * @class: Ex15
 * @authorId : #########
 * @version: 11/06/21
 */
public class TextList {
    private WordNode _head;


    /**
     * An empty constructor that initializes the database
     * complexity O(1)
     */
    public TextList() {
        WordNode.restartStaticsParameters();
        this._head = null;
    }

    /**
     * A constructor that receives as a parameter a string that represents text
     * (a collection of words) and builds list from the string
     * complexity O(n log n )  using the mergeSort algorithm.
     *
     * @param text a string that represents text (a collection of words)
     */
    public TextList(String text) {
        this();
        while (0 < text.length()) {
            int toSpace = text.indexOf(' ');
            int toTab = (-1 == text.indexOf('\t')) ? text.indexOf(' ') : 0;
            int toRowEnter = (-1 == text.indexOf('\n')) ? text.indexOf(' ') : 0;


            int toIndex = Math.min(toSpace, Math.min(toTab, toRowEnter));
            if (0 == toIndex) {
                text = text.substring(1);
            } else if (-1 == toIndex) {//no more space's in text. and the text not empty
                //adding to the list bay the order they where given and not bay alphabetic order.
                this._head = new WordNode(text, this._head);
                text = "";
            } else {//there is a word with space after it in the text.
                //adding to the list bay the order they where given and not bay alphabetic order.
                this._head = new WordNode(text.substring(0, toIndex), this._head);
                text = text.substring(toIndex + 1);
            }
        }
        mergeSort();
    }

    /**
     * complexity O(1)
     * checks if the linked list is empty
     *
     * @return true if the linked list is empty
     */
    private boolean isEmpty() {
        return null == this._head;
    }

    /**
     * A method that receives a word and adds it to the data structure.
     * If the string obtained as an empty parameter nothing is done.
     * complexity O(n)
     *
     * @param word a word to add to the data structure.
     */
    public void addToData(String word) {
        if (null == word || 1 > word.length()) {
        } else if (isEmpty()) {
            _head = new WordNode(word);
        } else {
            WordNode prev = null;
            WordNode current = this._head;
            while (null != current && 0 > current.getWord().compareTo(word)) {
                prev = current;
                current = current.getNext();
            }
            ///the situation they equals will be dissolved in the set next method
            if (null == prev) {//the while loop not fire at all
                this._head = new WordNode(word, current);//the head was alphabetic later than word

            } else {
                prev.setNext(new WordNode(word, current));

            }
        }
    }

    /**
     * A method that returns the total number of words in the text
     * complexity o(1)
     *
     * @return int represent the total number of words in the text
     */
    public int howManyWords() {
        return WordNode.getTotalWordsInList();
    }


    /**
     * A method that returns the number of different words in the text
     * complexity o(1)
     *
     * @return int represent the number of different words in the text
     */
    public int howManyDifferentWords() {
        return WordNode.getNumOfDifferentWordsInList();
    }


    /**
     * A method that returns the most common word in the text.
     * If there is more than one such word, the first word between
     * them will be returned (in alphabetical order)
     * complexity O(1)
     *
     * @return string represent the most common word in the text
     */
    public String mostFrequentWord() {
        if (isEmpty()) {
            return "";
        }
        return WordNode.getMostFrequentWord().getWord();
    }


    /**
     * A method that receives a letter, and returns the number
     * of words in the text that begin with that letter
     * complexity O(n)
     *
     * @param letter the letter to compere
     * @return int represent the number of words start with that letter.
     */
    public int howManyStarting(char letter) {
        int startingCounter = 0;
        if (!isEmpty()) {
            WordNode current = this._head;
            while (null != current && (!(letter < current.getStartChar()))) {
                if (current.getStartChar() == letter) {
                    startingCounter += current.getNumberOfTimesInText();
                }
                current = current.getNext();
            }
        }
        return startingCounter;
    }

    /**
     * a recursive methode that returns the most Frequent Starting Letter in the list.
     *
     * @return char represent the most Frequent Starting Letter.
     */
    public char mostFrequentStartingLetter() {
        return mostFrequentStartingLetter(0, ' ', this._head);
    }


    /**
     * over Loading recursive methode that returns the most Frequent Starting Letter in the list.
     *
     * @return char represent the most Frequent Starting Letter.
     */
    private char mostFrequentStartingLetter(int maxTimes, char charMax, WordNode current) {
        if (null != current) {
            if ((howManyStarting(current.getStartChar()) > maxTimes) ||
                    ((howManyStarting( current.getStartChar()) == maxTimes)) && ( current.getWord().charAt(0) < charMax)) {
                //recursive call with change in the parameters to fit the data
                return mostFrequentStartingLetter(howManyStarting(current.getStartChar()),
                        current.getStartChar(), current.getNext());
            } else
                //recursive call with change in node place so will move on.
                return mostFrequentStartingLetter(maxTimes, charMax, current.getNext());
        }
        return charMax;//current==null mean no moor words in list.
    }

    /**
     * @return a string representation of the list.
     * @override method that returns a string that "textually represents" the list.
     * complexity O(n)
     */
    public String toString() {
        String representOfList = "";
        WordNode current = this._head;
        while (null != current) {
            representOfList = representOfList.concat(current.toString());
            current = current.getNext();
        }
//        }
        return representOfList;
    }


    /**
     * splits list to two lists(until one node size)
     * half list will return from method
     * and second will be the argument was given to split.
     *
     * @param node the list to split and will contain the half list after the method fire.
     * @return second half of the node list.
     */
    private WordNode split(WordNode node) {
        if (null == node || null == node.getNext()) {
            return null;
        }
        WordNode list2 = node.getNext();
        node.addAsHead(list2.getNext());
        list2.addAsHead(split(list2.getNext()));
        return list2;
    }

    /**
     * will merge in alphabetic order two alphabetic ordered lists
     *
     * @param list1 first ordered list
     * @param list2 second ordered list
     * @return sort list contain all nodes from list1+list2
     */
    private WordNode merge(WordNode list1, WordNode list2) {
        if (null == list2)
            return list1;
        if (null == list1)
            return list2;
        if (0 > list1.getWord().compareTo(list2.getWord())) {//alphabetic before.
            list1.addAsHead(merge(list1.getNext(), list2));
            return list1;
        } // end if
        else {
            list2.setNext(merge(list1, list2.getNext()));
            return list2;
        } // end else
    }

    /**
     * method that fire the mergeSort and returns sorted list
     *
     * @param node the node to sort
     * @return the head of the sorted list
     */
    private WordNode mergeSort(WordNode node) {
        if (null == node || null == node.getNext()) {
            return node; // checks for empty or single list
        }
        WordNode list2 = split(node);
        node = mergeSort(node);
        list2 = mergeSort(list2);
        return merge(node, list2);
    } // end merge_sort

    /**
     * methode that fire the merge sort on the list start with  _head without
     * need to deal with arguments and so on.
     */
    private void mergeSort() {
        this._head = mergeSort(this._head);
    }
}

