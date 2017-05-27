
public class maxPath {
	public static void main (String[] args) {	
		int[][]  grid  =  {{  4,  2,  1,  1 },
					   	   {  1,  9,  9,  5 },
					   	   {  1,  1,  1,  8 },
					   	   {  1,  1,  1,  8 }};
		int recursiveTotal = maxPathWrapper(grid);
		System.out.println(recursiveTotal);
		
		int iterativeTotal = iterativeMPWrapper(grid);
		System.out.println(iterativeTotal);
	}
	
	public static int findMaxPath(int[][] grid, int x, int y, int[][] cache) {
		//base case: if you're at the end, add the end to it
		if(cache[x][y] != 0) {
			return cache[x][y];
		}
		if (x == grid.length - 1 && y == grid.length - 1) {
			return grid[x][y];
		}
		int right = 0, up = 0;
		if(x != grid.length - 1) { //if(x < grid.length) { doesn't work!
			up = findMaxPath(grid, x+1, y, cache);
		}
		if(y != grid.length - 1) {
			right = findMaxPath(grid, x, y+1, cache);
		}
		cache[x][y] = grid[x][y] + Math.max(right, up);
		return grid[x][y] + Math.max(right, up);
	}
	
	public static int[][] iterativeMP(int[][] grid, int[][] cache) {
		cache[grid.length - 1][grid.length - 1] = grid[grid.length - 1][grid.length - 1];
		for(int i = grid.length - 1; i >= 0; i--) {
			for(int j = grid.length - 1; j >= 0; j--) {
				//if in bottom row, just add the one to the right
				if(i == grid.length - 1) {
					if(j != grid.length - 1) {
						cache[i][j] = cache[i][j+1] + grid[i][j];
					}
					cache[grid.length - 1][grid.length - 1] = grid[grid.length - 1][grid.length - 1];
				} else {
					if(j == grid.length - 1) { //if on right column, just add from cache below
						cache[i][j] = cache[i+1][j] + grid[i][j];
					} else {
						cache[i][j] = grid[i][j] + Math.max(cache[i+1][j], cache[i][j+1]);
//						System.out.println(cache[i+1][j]);
//						System.out.println(cache[i][j+1]);
//						System.out.println("hit");
					}
				}	
			}
		}
//		System.out.print(cache[0][3]);
//		System.out.print(cache[0][2]);
//		System.out.print(cache[0][1]);
//		System.out.println(cache[0][0]);
//		System.out.println();
//		
//		System.out.print(cache[1][3]);
//		System.out.print(cache[1][2]);
//		System.out.print(cache[1][1]);
//		System.out.println(cache[1][0]);
//		System.out.println();
//		
//		System.out.print(cache[2][3]);
//		System.out.print(cache[2][2]);
//		System.out.print(cache[2][1]);
//		System.out.println(cache[2][0]);
//		System.out.println();
//		
//		System.out.print(cache[3][3]);
//		System.out.print(cache[3][2]);
//		System.out.print(cache[3][1]);
//		System.out.println(cache[3][0]);
//		System.out.println();
		return cache;
	}
	
	//wrapper for iterative function
	public static int iterativeMPWrapper(int[][] grid) {
		int[][]  cache  =  {{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 }};
		int total = iterativeMP(grid, cache)[0][0];
		return total;
	}
	
	//wrapper for recursive function
	public static int maxPathWrapper(int[][] grid) {
		int[][]  cache  =  {{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 },
			   	   			{  0,  0,  0,  0 }};
		int total = findMaxPath(grid, 0, 0, cache);
		return total;
	}
} 

