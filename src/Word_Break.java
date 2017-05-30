import java.util.Arrays;

public class Word_Break {

	public static void main (String[] args) {
		String[] strDict = new String[]{"apple", "pie", "albino", "juice", "ass", "assan", "an"};
		wordBreak("assan", strDict);
		
	}
	
	public static void wordBreak(String string, String[] strDict) {
		if (Arrays.asList(strDict).contains(string)) {
			System.out.println(string);
			
		}
		
		for(int i = 1; i < string.length(); i++) {
			String a = string.substring(0, i);
			String b = string.substring(i, string.length());
//			System.out.println(a);
//			System.out.println(b);
//			if(wordBreak(a, strDict) && wordBreak(b, strDict)) {
//				System.out.println(a);
//				System.out.println(b);
//				System.out.println();
//				
//				return true;
			
			wordBreak(a, strDict);
			wordBreak(b, strDict);
			}
	}
}
