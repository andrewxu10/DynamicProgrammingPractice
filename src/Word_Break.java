import java.util.Arrays;

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
	
	public static boolean wordBreak(String string, String[] strDict) {
		boolean flag = false;
		if(Arrays.asList(strDict).contains(string)) {
			System.out.println(string);
			flag = true;
		}
		for(int i=1; i<string.length(); i++) {
			String a = string.substring(0,i);
			String b = string.substring(i, string.length());
//			System.out.println(a);
//			System.out.println(b);
//			System.out.println();
			if(Arrays.asList(strDict).contains(a)) {
				if(wordBreak(b, strDict)) {
					System.out.println(a);
					return true;
				}
			}
		}
		return flag;
	}
}
