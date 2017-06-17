import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class wordBreakAttemptTwo {
	
	public static boolean wordBreak(String toProcess, ArrayList<String> answersList, HashMap<String, String> strDict, ArrayList<String> ignoreList, ArrayList<ArrayList<String>> allAnswers) {
		if(toProcess.length() == 0) {
			System.out.print("Solution: ");
			printList(answersList);
			allAnswers.add(answersList);
			return true;
		}
		for(int i = 2; i <= toProcess.length(); i++) {
			if(strDict.containsKey(toProcess.substring(0,i)) && !ignoreList.contains(toProcess.substring(0,i))) { //hello.(0,2) = he ... if in dict !in ignorelist
				ArrayList<String> newLocalAnswersList1 = new ArrayList<String>();
				ArrayList<String> newLocalAnswersList2 = new ArrayList<String>();
				String firstHalf = toProcess.substring(0,i); //1st half (found in strDict)
				String secondHalf = toProcess.substring(i, toProcess.length()); //2nd half of string
				System.out.println("1st: " + firstHalf);
				System.out.println("2nd: " + secondHalf);

				////code here
					//ignoreList.add(firstHalf);
					//wordBreak(toProcess, newLocalAnswersList1, strDict, ignoreList, allAnswers); //recurse on original string + firstHalf on ignoreList
					//System.out.println("removed: " + ignoreList.get(ignoreList.size() - 1));
					//ignoreList.remove(ignoreList.size() - 1); //prepare ignoreList for next recursive call
				answersList.add(firstHalf); //prepare answersList for next recursive call
				printList(answersList);
				
				
				
				
				
				if(wordBreak(secondHalf, answersList, strDict, ignoreList, allAnswers)) {
					answersList.remove(answersList.size() - 1);
					answersList.remove(answersList.size() - 1);
				}
				
				
				
				
				//answersList.remove(firstHalf);
			}
		}
		return false;
	}
	
	////
//	if(secondHalf.length() == 0) {
//	printList(answersList);
//	allAnswers.add(answersList);
//
//System.out.println("firsthalf: " + firstHalf);
//System.out.println("secondhalf: " + secondHalf);
	
	public static void main (String args[]) {
		//System.out.println("an".substring(2, "an".length()));
		
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
        
        ArrayList<String> answersList = new ArrayList<String>();
        ArrayList<String> ignoreList = new ArrayList<String>();
        ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
        wordBreak("applepieassananalbinojuice", answersList, strDict, ignoreList, answers);
        //wordBreak("an", answersList, strDict, ignoreList, answers);
        System.out.println("applepiealbinojuiceassanan");
//        printList(answers.get(0));
//        printList(answers.get(1));
//        printList(answers.get(2));
//        printList(answers.get(3));
        //printList(answers.get(0));
	}
	
	public static void printList(ArrayList<String> arrayList) {
		System.out.println(Arrays.deepToString(arrayList.toArray()));
	}
	
	public void handler(String input, HashMap<String, String> strDict) {
		//initial declaration + call/processing (1st solution)
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
		ArrayList<String> localAnswers = new ArrayList<String>();
		ArrayList<String> ignore = new ArrayList<String>();
		wordBreak(input, localAnswers, strDict, ignore, answers);
		
		ArrayList<String> btIgnore = new ArrayList<String>(); //ignore list for the sequence
		Queue<String> queue = new LinkedList<String>(); //queue to mediate btIgnore & answers.(i).(0)
		queue.add(answers.get(0).get(0)); //add 1st String to queue
		int iterator = 1;
		while(queue.peek() != null) { //sequence continues as long as queue has Strings
			ArrayList<String> btLocalAnswers = new ArrayList<String>(); //new localAnswers list each iter
			btIgnore.add(queue.poll()); //add to sequence's ignore list, remove from queue
			wordBreak(input, btLocalAnswers, strDict, btIgnore, answers); //orig input, empty answers list, orig dict, ignore 1 more word, global answers list of lists
			queue.add(answers.get(iterator).get(0)); //add from new answers.(i).(0) value
		}
	}

}
