import java.lang.reflect.Array;
import java.util.Arrays;

public class Levenshtein_Distance {
	
	//print matrix as grid (for debugging)
	static void printMatrix(int[][] grid) {
	    for(int r=0; r<grid.length; r++) {
	       for(int c=0; c<grid[r].length; c++)
	           System.out.print(grid[r][c] + " ");
	       System.out.println();
	    }
	    System.out.println();
	}
	
	//iterative DP solution wrapper
	public static int iterativeWrapper(String string1, String string2) {
			//initialize cache w/ default values of -1
			int[][] cache = new int[string1.length()+1][string2.length()+1];
			for (int[] row: cache){
				 Arrays.fill(row, -1);
			}
			//set top row, set left column
			for (int i = 0; i < cache.length; i++) {
				cache[i][0] = i;
			}
			for (int j = 0; j < cache[0].length; j++) {
				cache[0][j] = j;
			}
			for (int a = 0; a < cache.length; a++) {
				for (int b = 0; b < cache[0].length; b++) {
					//[][0] & [0][] values already set
					//b = row value, aka column
					//a = row 'number'
					if(a != 0 && b != 0) {
						String s1 = string1.substring(0, a);
						String s2 = string2.substring(0, b);
						if(s2.length() <= s1.length()) {
							if(s2.charAt(s2.length() - 1) == s1.charAt(s1.length() - 1)) {
								cache[a][b] = cache[a][b-1] - 1;
							}
						}
					}
				}
			}
			printMatrix(cache);
			return 0;
	}
	
	
	
	
	//memoized DP solution, prints cache as well
	public static int DPwrapper(String string1, String string2) {
		//initialize cache w/ default values of -1
		int[][] cache = new int[string1.length()+1][string2.length()+1];
		for (int[] row: cache){
			 Arrays.fill(row, -1);
		}
		int answer = Levenshtein(string1, string2, cache);
		printMatrix(cache);
		return answer;
	}
	
	public static int Levenshtein(String string1, String string2, int[][] cache) {
		int cost = 0;
		int s1_len = string1.length();
		int s2_len = string2.length();
		
		//cache lookup
		if(cache[s1_len][s2_len] != -1) {
			return cache[s1_len][s2_len];
		}
		
		//if one of the strings.length() = 0, the distance is the other string
		if(s1_len == 0) {
			cache[s1_len][s2_len] = s2_len;
			return s2_len;
		}
		if(s2_len == 0) {
			cache[s1_len][s2_len] = s1_len;
			return s1_len;
		}
		
		//determine whether the last char of s1 & s2 are a swap or a match
		if(string1.charAt(s1_len - 1) == string2.charAt(s2_len - 1)){
			cost = 0; //last char = match
		} else {
			cost = 1; //last char needs to be swapped
		}
		
		//recurse on match/swap, add, remove decisions, find the minimum value
		int total =  Math.min(
				//1: match (+0) or swap (+1 via int cost) ... remove last char from both string1/string2
				Levenshtein(string1.substring(0, s1_len - 1), string2.substring(0, s2_len - 1), cache) + cost,
				Math.min(
						//2: remove last char from string1 && return int + 1 (add)
						Levenshtein(string1.substring(0, s1_len - 1), string2, cache) + 1,
						//2: remove last char from string2 && return int + 1 (subtract)
						Levenshtein(string1, string2.substring(0, s2_len - 1), cache) + 1
						)
				);
		cache[s1_len][s2_len] = total;
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(DPwrapper("mzzzm", "mmmmm"));
		iterativeWrapper("mzzzm", "mmmmm");
	}
}
