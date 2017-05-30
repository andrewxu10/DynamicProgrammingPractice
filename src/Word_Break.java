import java.util.Arrays;

public class Word_Break {

	public static void main (String[] args) {
		String[] strDict = new String[]{"apple", "pie", "applepie"};
		wordBreak("applepie", strDict);
		
	}
	
	public static void wordBreak(String string, String[] strDict) {
		if (Arrays.asList(strDict).contains(string)) {
			System.out.println(string);
		}
		for(int i = 0; i < string.length(); i++) {
			String a = string.substring(0, i);
			String b = string.substring(i, string.length());
			wordBreak(a, strDict);
			wordBreak(b, strDict);
		}
	}
}
