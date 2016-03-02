package Chess.Logic;

import java.util.Scanner;

class Player {
	
	private String name;
	private String color;
	private final Scanner scanner = new Scanner(System.in);
	
	Player(String nameIn, String colorIn){
		name = nameIn;
		color = colorIn;
	}
	
	private int convertCharToNum(char charIn){ //if this method returns a -1 the letter is not valid
	//the user will enter a char but it need to be a num for program
		int numOut = -1;
		
		for(int i = 0; i < Board.SIDE_LETTERS.length; i++){
			
			if(Board.SIDE_LETTERS[i] == charIn){
				numOut = i;
			}
		}
		return numOut;
	}
	
	private int convertCharNumtoNum(char charIn){ //convert string number to real int and check that it is less than 8
		int numOut = -1; //if -1 is returned the number is not valid
		int convertedNum = Character.getNumericValue(charIn); 
		
		for(int i: Board.SIDE_NUMS){
			if(i == convertedNum){
				//-1 so that the locations are stored starting from 0 
				numOut = convertedNum;   
			}
		}
		return numOut;
	}
	
	public int[][] getMove(){ //returns array of x and y for the spot to move from and to
		
		int[][] move = new int[2][2];
		for(int runNum = 1; runNum <= 2; runNum++){
			while(true){
				if(runNum == 1){ //on first run ask for location to move from
					System.out.print(name + ", input your location to move from. (EX: E4)\n>> "); //prompt
				}
				else{ //on second run ask pos to move to
					System.out.print(name + ", input you location to move to. (EX: E4)\n>> ");
				}
				String moveIn = scanner.nextLine().trim();
			
				//checking for blank spaces and nothing entered and no more than 2 characters long
				if(!moveIn.isEmpty() && moveIn.length() <= 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))){
				
					if(!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))){
						//making sure first char is a char and making sure the second is a digit
						int x, y;
			
						if((x = convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1){
							if((y = convertCharNumtoNum(moveIn.charAt(1))) != -1){
								y = 8 - y; //flipping value so that 0 index is on top
								int tempArray[] = {x, y};
								if(runNum == 1){
									if(Board.board[y][x].getType() == "blank" || Board.board[y][x].getColor() != color){ 
										
										//returning an array full of -1's if the first location does not point to a piece
										//or if the piece is not of the same color as the player
										tempArray[0] = -1;																
										tempArray[1] = -1;
										int[][] errorArray = {tempArray, tempArray};
										return errorArray;
									}
								}
								
								move[runNum - 1] = tempArray;
								break;
							}
						}
					}
				}
				System.out.println("Invalid location. Try again."); //can only get here if the other possibilities fail
			}		
		}
		return move;
	}
}
