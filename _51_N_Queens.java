package com.javadwarf.backtracking.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _51_N_Queens {

	public static void main(String[] args) {
		System.out.println(new _51_N_Queens().solveNQueens1(4));
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
	Set<Integer> rows;
	Set<Integer> cols;
	Set<Integer> diagonals;
	Set<Integer> oppDiagonals;

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

		rows  = new HashSet<>();
		cols  = new HashSet<>();
		diagonals  = new HashSet<>();
		oppDiagonals  = new HashSet<>();

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



	/********************* Optimized brute force approach by backtracking and pruning using Bounding Function
	 *  i.e prune the recursion branch if two queens in same, row, column, or diagonal or opposite diagonal
	 * 
	 */

	public List<List<String>> solveNQueens1(int n) {
		output = new ArrayList<List<String>>(n);
		rows  = new HashSet<>();
		cols  = new HashSet<>();
		diagonals  = new HashSet<>();
		oppDiagonals  = new HashSet<>();
		initialiseBoard(n);
		placeQueens1(n,0);
		return output;
	}


	private void placeQueens1(int n, int currQueenRow) {
		if(currQueenRow == n) {
			output.add(copyBoard(n));
			return;
		}
		for(int col=0;col<n; col++) {
			// prune branch here, don't go further down in the recursion tree if the bounding function is violated
			if(!(rows.contains(currQueenRow) || cols.contains(col) || diagonals.contains(currQueenRow+col) || oppDiagonals.contains(currQueenRow-col))) {

				rows.add(currQueenRow);
				cols.add(col);
				diagonals.add(currQueenRow+col);
				oppDiagonals.add(currQueenRow-col);

				board[currQueenRow][col] = "q";

				placeQueens1(n, currQueenRow+1);

				board[currQueenRow][col] = ".";

				rows.remove(currQueenRow);
				cols.remove(col);
				diagonals.remove(currQueenRow+col);
				oppDiagonals.remove(currQueenRow-col);
			}
		}
	}


}
