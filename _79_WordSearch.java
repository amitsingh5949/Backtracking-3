package com.javaDwarf.graphs.leetcode.dfs;

public class _79_WordSearch {

	public static void main(String[] args) {
		char[][] arr = {
				  {'A','B','C','E'},
				  {'S','F','C','S'},
				  {'A','D','E','E'}
				};
		
		System.out.println(new _79_WordSearch().exist(arr, "ABCCED"));

	}

	public boolean exist(char[][] board, String word) {
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(word.charAt(0) == board[i][j] && 
						dfs(0, i,j,word, board, new boolean[board.length][board[0].length], false)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs(int pos, int x, int y,  String word, char[][] board, boolean[][] visited, boolean result) {

		if(result || pos == word.length()-1) {
			return true;
		}

		visited[x][y] = true;

		int[] xArr = {0,-1,1,0};
		int[] yArr = {-1,0,0,1};

		for(int i=0; i<xArr.length; i++) {

			int xNew = x + xArr[i];
			int yNew = y + yArr[i];

			if(xNew >= 0 && xNew < board.length && yNew >=0 && yNew < board[0].length
					&& !visited[xNew][yNew] && pos < word.length()-1 && board[xNew][yNew] == word.charAt(pos+1)) {
				pos += 1;
				visited[xNew][yNew] = true;
				result = result || dfs(pos, xNew, yNew, word, board, visited, result);
				pos -= 1;
				visited[xNew][yNew] = false;
			}

		}
		return result;
	}
}
