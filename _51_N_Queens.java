package com.javadwarf.backtracking.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _51_N_Queens {

	public static void main(String[] args) {
		System.out.println(new _51_N_Queens().solveNQueens(4));
	}
	
	
	
	
	/*************************** Actual Brute Force Approach *************************/
	/**
	 * time  : N^N + N*N
	 * space  : N^N + N*N
	 * intuition : create all possible boards with queen placement. Lets say there is a board of length 4 , so 4* 4 , 16 cells and we have to place 4 queens. Now toatla poosible combination of 
	 * placing 4 queens in 16 cells in 16C4 , check each combination for to be valid, if valid add to output.
	 */

	List<List<String>> output;
	String[][] board;

	public List<List<String>> solveNQueens(int n) {
		output = new ArrayList<List<String>>(n);
		initialiseBoard(n);
		placeQueens(n,0);
		return output;
	}


	private void placeQueens(int n, int currQueen) {
		if(currQueen == n) {
			if(isValidBoard(n)) {
				output.add(copyBoard(n));
			}
			return;
		}
		for(int i=0;i<n; i++) {
			board[currQueen][i] = "q";
			placeQueens(n, currQueen+1);
			board[currQueen][i] = ".";
		}
	}

	private void initialiseBoard(int n) {
		board = new String[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				board[i][j] = ".";
			}
		}
	}

	private boolean isValidBoard(int n) {

		Set<Integer> rows  = new HashSet<>();
		Set<Integer> cols  = new HashSet<>();
		Set<Integer> diagonals  = new HashSet<>();
		Set<Integer> oppDiagonals  = new HashSet<>();

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(board[i][j].equalsIgnoreCase("q")) {
					if(rows.contains(i) || cols.contains(j) || diagonals.contains(i+j) || oppDiagonals.contains(i-j)) {
						return false;
					}
					rows.add(i);
					cols.add(j);
					diagonals.add(i+j);
					oppDiagonals.add(i-j);
				}
			}
		}
		return true;
	}

	private List<String> copyBoard(int n){
		List<String> serialisedBoard = new ArrayList<>();
		for(int i=0; i<n; i++) {
			String row = "";
			for(int j=0; j<n; j++) {
				row += board[i][j];
			}
			serialisedBoard.add(row);
		}
		return serialisedBoard;
	}
	
	/******************* Optimized brute force approach by backtracking and pruning using Bounding Function(prune if two queens in same, row, column, or diagonal or opposite diagonal)*******/
	/**
	 * 
	 */
	
	

}
