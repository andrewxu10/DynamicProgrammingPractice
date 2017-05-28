
public class Levenshtein_Distance {
	
	public static int Levenshtein(String string1, String string2) {
		if(string1.length() == 0) {
			return string2.length();
		}
		if(string2.length() == 0) {
			return string2.length();
		}
		int cost = 0;
//		System.out.println(string1.substring(0, string1.length() - 1));
//		System.out.println(string2.substring(0, string2.length() - 1));
//		System.out.println(string2.charAt(string2.length() - 1));
//		System.out.println(string2.length() - 1);
		if(string1.charAt(string1.length() - 1) == string2.charAt(string2.length() - 1)){
		//if(string1.charAt(string1.length() - 1) == string2.charAt(string2.length()) - 1) {
			cost = 0; //last char = match
		} else {
			cost = 1; //last char needs to be swapped
		}
		
		return Math.min(//get the min return value of the 3 scenarios below:
				//1: match (+0) or swap (+1 via int cost) ... remove last char from both string1/string2
				Levenshtein(string1.substring(0, string1.length() - 1), string2.substring(0, string2.length() - 1)) + cost,
				Math.min(
						//2: remove last char from string1 && return int + 1 (add)
						Levenshtein(string1.substring(0, string1.length() - 1), string2) + 1,
						//2: remove last char from string2 && return int + 1 (subtract)
						Levenshtein(string2, string2.substring(0, string2.length() - 1)) + 1
						)
				);
	}
	
	public static void main(String[] args) {
		//System.out.println("billy".charAt("billy".length() - 1));
		System.out.println(Levenshtein("billy", "bill"));
	}
}
