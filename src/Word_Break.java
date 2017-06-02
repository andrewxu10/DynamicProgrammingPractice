import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Word_Break {

	public static void main (String[] args) {
		HashMap<String, String> strDict = new HashMap<String, String>();
        strDict.put("assan", "assan");
        strDict.put("assanan", "assanan");
        strDict.put("an", "an");
        strDict.put("anan", "anan");
        strDict.put("ass", "ass");
        strDict.put("apple", "apple");
        strDict.put("pie", "pie");
        strDict.put("albino", "albino");
        strDict.put("juice", "juice");
        //wordBreakHandler("applepiealbinojuiceassan", strDict); //stress test
		wordBreakHandler("applepiealbinojuiceassanan", strDict); //ultimate stress test
		//wordBreakHandler("appleassanalbinojuiceassanan", strDict); //now this is what i call a stress test - not working
	}
	
	public static ArrayList<String> wordBreak(String string, HashMap<String, String> strDict, ArrayList<String> answers) {
		if(string.length() == 0) {
			return answers; //to do: add to list of answers
		}
		if(string.length() > 1) {
			if(findNextShortest(string, strDict) == null) { //if we're in the middle of a non-answer, backtrack one word (remove from dictionary, answers list, and un-process input string) and re-try:
					//no need for loop. Recurse with the full state in the parameters.
					String locallyUnmatched = string; //unprocessed string that indicates we are in a non-answer
					String toBePoppedLocally = answers.get(answers.size() - 1);
					System.out.println("remaining string: " + locallyUnmatched + " has no dict match '" + toBePoppedLocally + "' was removed from answers, dict, readded to the string to process");
					strDict.remove(toBePoppedLocally); //pop the last answer from the dict
					answers.remove(answers.size() - 1); //and also pop it from the answers list
					String letsProcessThisNow = toBePoppedLocally + locallyUnmatched;
					//ArrayList<String> value = wordBreak(string, strDict, answers); //recurse, keep popping the last word if necessary
					//return value;
					
					//below and above - need to implement this within handler
					
					//wordBreakHandler(string, strDict);
					return wordBreak(letsProcessThisNow, strDict, answers); // might just need empty answers array
				//}
			} else {
			String nextString = findNextShortest(string, strDict);
			answers.add(nextString);
			string = string.substring(nextString.length(), string.length()); //string = string - nextString (a prefix)
			wordBreak(string, strDict, answers); //recursion: substring, same dict, keep adding to answers
			}
		}
		return answers;
	}
		
	//handler : run wordBreak, then run wordBreak - last answer
	public static void wordBreakHandler(String string, HashMap<String, String> strDict) {
		ArrayList<String> emptyAnswersList = new ArrayList<String>(); //dummy list for initializing recursion etc
		ArrayList<String> answersList = wordBreak(string, strDict, emptyAnswersList);
		if(!answersList.isEmpty()) {
			printList(answersList);
			
			//currently this starts from the back, just like the recursive solution. lets try going from the front of the list to detect solution variations!
//			String popped = "";
//			for(int i = answersList.size() - 1; i >= 0; i--) {
//				
//				if(!answersList.isEmpty()) { //while there are still answers in the array 'answersList'
//					String pop = answersList.get(i);
//					popped = pop + "" + popped;
//					System.out.println("removed from dict: " + pop);
//					strDict.remove(pop); //pop last answer from the dictionary
//					System.out.println("string left: " + popped);
//					if(findNextShortest(popped, strDict) != null) {
//						System.out.println("popped string has match");
//						ArrayList<String> newEmptyAnswersList = new ArrayList<String>();
//						ArrayList<String> otherAnswersList = wordBreak(string, strDict, newEmptyAnswersList); //get result without the popped dictionary item
//						printList(otherAnswersList);
//					}
//				}
//			}
		}
	}
	
	//2 helper functions below:
	public static String findNextShortest(String string, HashMap<String, String> strDict) {
		for (int i = 1; i <= string.length(); i++) {
			if(strDict.containsKey(string.substring(0, i))) {
				return string.substring(0,i);
			}
		}
		return null;
	}
	public static void printList(ArrayList<String> arrayList) {
		System.out.println(Arrays.deepToString(arrayList.toArray()));
	}
}
