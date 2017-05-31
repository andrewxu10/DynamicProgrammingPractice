import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Word_Break {

	public static void main (String[] args) {
		String[] strDict = new String[]{"apple", "pie", "albino", "juice", "ass", "assan", "an"};
		wordBreak("applepiealbinojuiceassan", strDict);	
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
			return answers;
		}
		if(string.length() > 1) {
			if(findNextShortest(string, strDict) == null) { //if the last substring isn't flush with the string's suffix:
				return wordBreak(string, strDict.remove(answers[answers.size()])); //recurse, keep popping the last word
			} else {
			String nextString = findNextShortest(string, strDict);
			answers.add(nextString);
			string = string.substring(nextString.length(), string.length()); //string = string - nextString (a prefix)
			wordBreak(string, strDict, answers); //recursion: substring, same dict, keep adding to answers
			}
		}
	}
	
	//a null answers list should be empty!
		
	//handler : run wordBreak, then run wordBreak - last answer
	public static void wordBreakHandler(String string, HashMap<String, String> strDict) {
		ArrayList<String> emptyAnswersList = new ArrayList<String>(); //dummy list for initializing recursion etc
		ArrayList<String> answersList = wordBreak(string, strDict, emptyAnswersList);
		if(!answersList.isEmpty()) { 
			System.out.println(answersList); //print answers, if any
			
			while(!answersList.isEmpty()) { //while there are still answers in the array 'answersList'
				strDict.remove(answersList.get(answersList.size())); //pop last answer from the dictionary
				ArrayList<String> otherAnswersList = wordBreak(string, strDict, emptyAnswersList); //get result without the popped dictionary item
				if(!answersList.isEmpty()) { //if it can run flush, it won't be empty
					System.out.println(answersList); //so print them out. Otherwise run through the while loop again, and pop out another dictionary item
				}
			}
	}
	
	
	
	
	
	
}
