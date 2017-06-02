import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Word_Break {

	public static void main (String[] args) {
		//String[] strDict = new String[]{"apple", "pie", "albino", "juice", "ass", "assan", "an"};
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

		
	}
	
//	public static boolean wordBreak(String string, String[] strDict) {
//		if (Arrays.asList(strDict).contains(string)) {
//			System.out.println(string);
//		}
//		
//		for(int i = 1; i < string.length(); i++) {
//			String a = string.substring(0, i);
//			String b = string.substring(i, string.length());
//			if(wordBreak(a, strDict) && wordBreak(b, strDict)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	
//	public static boolean wordBreak(String string, String[] strDict) {
//		boolean flag = false;
//		if(Arrays.asList(strDict).contains(string)) {
//			System.out.println(string);
//			flag = true;
//		}
//		for(int i=1; i<string.length(); i++) {
//			String a = string.substring(0,i);
//			String b = string.substring(i, string.length());
//			if(Arrays.asList(strDict).contains(a)) {
//				if(wordBreak(b, strDict)) {
//					System.out.println(a);
//					return true;
//				}
//			}
//		}
//		return flag;
//	}
	
	
	
//	public static void wordBreak(String string, String[] strDict, boolean recursed) {
//		boolean present = false;
//		boolean flush = false;
//		if(Arrays.asList(strDict).contains(string) || string == "") {
//			System.out.println(string);
//			flush = true;
//			if(string.length() > 1) {
//				strDict.remove(string); //create .remove for strDict
//				//if (wordBreak(string, modifiedStrDict).length() > 1) {
//					//the zeroith field in the return array can be a flag to retry the entire recursion
//					//if you find flag in array && recursed == false, 
//					//redo the whole thing w/o the string in dict
//			}
//		}
	
	public static ArrayList<String> wordBreak(String string, HashMap<String, String> strDict, ArrayList<String> answers) {
		if(string.length() == 0) {
			return answers; //should be- add to list of answers
		}
		if(string.length() > 1) {
			if(findNextShortest(string, strDict) == null) { //if the last substring isn't flush with the string's suffix:
				//for(int i = answers.size() - 1; i >= 0; i--) { //everything in this loop is broken
					//System.out.println(string);
					//System.out.println("hit");
					//System.out.println("answers before removal: " + answers);
					String locallyUnmatched = string;
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
	
	//a null answers list should be empty!
		
	//handler : run wordBreak, then run wordBreak - last answer
	public static void wordBreakHandler(String string, HashMap<String, String> strDict) {
		ArrayList<String> emptyAnswersList = new ArrayList<String>(); //dummy list for initializing recursion etc
		ArrayList<String> answersList = wordBreak(string, strDict, emptyAnswersList);
		if(!answersList.isEmpty()) {
			printList(answersList);
			//System.out.println(answersList); //print answers, if any
			
			String popped = "";
			for(int i = answersList.size() - 1; i >= 0; i--) {
				
				if(!answersList.isEmpty()) { //while there are still answers in the array 'answersList'
					String pop = answersList.get(i);
					popped = pop + "" + popped;
					System.out.println("removed from dict: " + pop);
					strDict.remove(pop); //pop last answer from the dictionary
					System.out.println("string left: " + popped);
					if(findNextShortest(popped, strDict) != null) {
						System.out.println("popped string has match");
						ArrayList<String> newEmptyAnswersList = new ArrayList<String>();
						ArrayList<String> otherAnswersList = wordBreak(string, strDict, newEmptyAnswersList); //get result without the popped dictionary item
						printList(otherAnswersList);
					}
				}
			}
		}
	}
	
	
	
	
	
	
}
