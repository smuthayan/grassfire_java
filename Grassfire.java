
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Grassfire {

	/*
	 Here are the steps to generate the search map based on the inputs from the user:
a.	Step1: divide the search region in grid cells: 
User enters the size of the region as the dimensions of the grid. This means user should provide the number of rows and columns. The minimum size should be 8x8.

b.	Step 2: identify the obstacle cells
User provides a random number as the percentage of the obstacle cells. 
This can be any percentage in the range of 10% to 20%. In your program, you can round up the user input to make it an integer number. 
Based on this number, generate a random list of pairs representing the row and column indices of the obstacle cells.

c.	Step 3: initialize the starting cell
User enters a random number less than the number of columns which represents a cell in the first row 
as the starting cell/node.

d.	Step 4: initialize the destination cell
User provides the index of row and column for the destination node. 
To this end, the row index should be a random number greater than half of the number of rows
(i.e., if the number of rows is 10, the row index for the destination cell should be greater than 5) \
and the column index is greater than 2/3 of the number of columns (i.e., if the number of columns is 10, 
the column index for the destination cell should be greater than 6.6 which should be round up to 7).

	 */
	
	static int destination_row;
	static int destination_column;
	static int starting_row = 0;
	static int starting_column;	
	
	public static int find_shortest_steps(int grassfire_grid[][], int ROWS, int COLS) {
		int currow = starting_row;
		int curcol = starting_column;
		int[] possible_indices = {0, 0, 0, 0,};
		if (currow < ROWS-1) {
			possible_indices[0] = grassfire_grid[currow+1][curcol];
		}
		if (curcol != 0) {
			possible_indices[1] = grassfire_grid[currow][curcol-1];
		
		}
		if (curcol < COLS-1) {
			possible_indices[2] = grassfire_grid[currow][curcol+1];
		}
		if (currow != 0) {
			possible_indices[3] = grassfire_grid[currow-1][curcol];
		}
		
		int max_val = possible_indices[0];
		for (int x = 0; x < possible_indices.length; ++x) {
			if (max_val < possible_indices[x]) {
				max_val = possible_indices[x];
			}
		}
		return max_val+1; // We add +1 due to our implementation of grassfire algorithm as it doesn't overwrite the starting node
	}
	
	public static ArrayList<Pair> find_shortest_path(int grassfire_grid[][], int ROWS, int COLS) {
		ArrayList<Pair> indices = new ArrayList<Pair>();
		int currow = starting_row;
		int curcol = starting_column;
		int current_value = find_shortest_steps(grassfire_grid, ROWS, COLS); 
		
		
		
		
		
		while (true) {
			
			
			if (currow < ROWS-1) {
				if (grassfire_grid[currow+1][curcol] == -3)
				return indices;
				
				
				if (grassfire_grid[currow+1][curcol] == current_value - 1) {
					
					currow += 1;
					Pair p = new Pair();
					p.row = currow;
					p.column = curcol;
					indices.add(p);
				
				
				}
			}
			
			if (curcol != 0) {
				if (grassfire_grid[currow][curcol-1] == -3)
					return indices;
			
			
				if (grassfire_grid[currow][curcol-1] == current_value - 1) {
					
					curcol -= 1;
					Pair p = new Pair();
					p.row = currow;
					p.column = curcol;
					indices.add(p);
				
				
				}
			
			
			}
			
			
			if (curcol < COLS-1) {
				
				
				
				if (grassfire_grid[currow][curcol+1] == -3) {
					return indices;
				}
				
				if (grassfire_grid[currow][curcol+1] == current_value - 1) {
					
					curcol += 1;
					Pair p = new Pair();
					p.row = currow;
					p.column = curcol;
					indices.add(p);
					
				}
				
				
				
				
			}
			
			
			
			if (currow != 0) {
				
				if (grassfire_grid[currow-1][curcol] == -3) {
					
					return indices;
				}
				
				if (grassfire_grid[currow-1][curcol] == current_value - 1) {
					currow -= 1;
					Pair p = new Pair();
					p.row = currow;
					p.column = curcol;
					indices.add(p);
						
				}
				
			}
			
			--current_value;
			
		}
		
	}
	
	public static int[][] grassfire(int[][] matrix, int ROWS, int COLUMNS) {
		ArrayList<Pair> marked_indices=new ArrayList<Pair>();
		ArrayList<Pair> nodes = new ArrayList<Pair>();
		Pair pairobj = new Pair();
		
		
		int count = 1;
		pairobj.row = destination_row;
		pairobj.column = destination_column;
		nodes.add(pairobj);
		
		while (true) {
		
			
			for (int y = 0; y < nodes.size(); ++y) {
				
				
		if (nodes.get(y).row < ROWS-1) {
			if (matrix[nodes.get(y).row+1][nodes.get(y).column] == -2) {
				
				return matrix;
			}
			
			if (matrix[nodes.get(y).row+1][nodes.get(y).column] == 0) {
				
				
					Pair temp = new Pair();
					temp.row = nodes.get(y).row+1;
					temp.column = nodes.get(y).column;
					marked_indices.add(temp);
				
				
				
			}
			
			
			
		}
		if (nodes.get(y).column != 0) {
			if (matrix[nodes.get(y).row][nodes.get(y).column-1] == -2) {
				
				return matrix;
			}
			
			if (matrix[nodes.get(y).row][nodes.get(y).column-1] == 0) {
				
				
					Pair temp = new Pair();
					temp.row = nodes.get(y).row;
					temp.column = nodes.get(y).column-1;
					marked_indices.add(temp);
			
				
			}
			
		}
		
		if (nodes.get(y).column < COLUMNS-1) {
			
			if (matrix[nodes.get(y).row][nodes.get(y).column+1] == -2) {
				return matrix;
			}
			
			if (matrix[nodes.get(y).row][nodes.get(y).column+1] == 0) {
				

					Pair temp = new Pair();
					temp.row = nodes.get(y).row;
					temp.column = nodes.get(y).column+1;
					marked_indices.add(temp);
					
				
			}
			
			
		}
		
		if (nodes.get(y).row != 0) {
			
			if (matrix[nodes.get(y).row-1][nodes.get(y).column] == -2) {
				
				return matrix;
			}
			
			if (matrix[nodes.get(y).row-1][nodes.get(y).column] == 0) {
					Pair temp = new Pair();
					temp.row = nodes.get(y).row-1;
					temp.column = nodes.get(y).column;
					marked_indices.add(temp);
					
					
			}
			
		}
			
			}
		
		nodes.clear();
	
		
		for (int x = 0; x < marked_indices.size(); ++x) {
			Pair temp = new Pair();
			temp.row = marked_indices.get(x).row;
			temp.column = marked_indices.get(x).column;
			matrix[temp.row][temp.column] = count;
			nodes.add(temp);
			
		}
		
		marked_indices.clear();
		++count;
		
		
		
	}
		
		
		
		
	}
		
	
	
	
	public static void print_matrix(int[][] matrix, int ROWS, int COLUMNS) {
		
		for (int r = 0; r < ROWS; ++r) {
			System.out.print("{ ");
			for (int c = 0; c < COLUMNS; ++c) {
			
				System.out.print(matrix[r][c]);
	            System.out.print("\t");
			}
			System.out.print("}");
			System.out.println();
		}
		
	}
	
	public static int[][] init_matrix(int ROWS, int COLUMNS, int PERCENTAGE){
		
		int[][] grid = new int[ROWS][COLUMNS];
		Scanner scan = new Scanner(System.in);
		// First generate indices [i][j] of obstacles
		//Calculate how many obstacles there are
		
		int num_of_obstacles = (int)((ROWS * COLUMNS) * ((float)PERCENTAGE / 100));
		// Now generate random indices 
		int random_row = 0;
		int random_column = 0;
		for (int x = 0; x < num_of_obstacles; ++x) {
			random_row = ThreadLocalRandom.current().nextInt(0, ROWS);
			random_column = ThreadLocalRandom.current().nextInt(0, COLUMNS);
			grid[random_row][random_column] = -1;
			
			
			
		}

		// Now initialize starting cell and destination cell
		
		System.out.println("Enter the column index of the starting cell of the first row");
		while(true) {
			if (scan.hasNextInt()) {
				starting_column = scan.nextInt();
				if (starting_column >= 8) {
					
				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number less than 8");
		}
		
		grid[0][starting_column] = -2;
		
		
		// row index should be a random number greater than half of the number of rows 
		System.out.println("Enter the row index of the destination cell");
		while(true) {
			if (scan.hasNextInt()) {
				destination_row = scan.nextInt();
				if (destination_row <= ROWS / 2) {
					
				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number greater than half of the rows");
		}
		
		
		System.out.println("Enter the column index of the desintation cell of the ");
		while(true) {
			if (scan.hasNextInt()) {
				destination_column = scan.nextInt();
				if (destination_column <= (int)(COLUMNS*(float)(2.0/3.0))) {
					
				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number greater than 2/3 of the columns");
		}
		

		grid[destination_row][destination_column] = -3;
		
		
		
		return grid;
	}
	
	
	
	public static void main(String[] args) {
		
		int rows = 0;
		int columns = 0; 
		int percentage = 0;
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Enter rows of the grid (minimum 8)");
		while(true) {
			if (scan.hasNextInt()) {
				rows = scan.nextInt();
				if (rows < 8) {
					
				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number greater than 8");
		}
		
		
		System.out.println("Enter columns of the grid (minimum 8)");
		while(true) {
			if (scan.hasNextInt()) {
				columns = scan.nextInt();
				if (columns < 8) {
					
				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number greater than 8");
		}
		
		System.out.println("Enter the percent of obstacle cells");
		while(true) {
			if (scan.hasNextInt() || scan.hasNextFloat()) { // if it is float or int in stream
				percentage = scan.nextInt();
				if (percentage < 10 || percentage > 20) {

				}
				else {
					break;
				}
			}
			
			System.out.println("Please enter a number greater than 10 and less than 20");
		}
		
		
		
		
		int[][] grid = init_matrix(rows, columns, percentage);
		
		int[][] grassfire_grid = grassfire(grid, rows, columns);
		
		System.out.println("Legend:	");
		System.out.println("Obstacles: -1");
		System.out.println("Destination node: -3");
		System.out.println("Starting node: -2");
		System.out.println("Our grassfire algorithm goes from destination node to the starting node");
		print_matrix(grassfire_grid, rows, columns);
		System.out.println("Shortest path: ");
		ArrayList<Pair> shortest_path = new ArrayList<Pair>();
		shortest_path = find_shortest_path(grassfire_grid, rows, columns);
		for (Pair p : shortest_path) {
			System.out.println("[" + p.row + "]" + "[" + p.column + "]");
		}
		System.out.println("[" + destination_row + "]" + "[" + destination_column + "]");
		
		System.out.println("Steps taken: " + find_shortest_steps(grassfire_grid, rows, columns));
		
		
		
		
		
	}
	

}
