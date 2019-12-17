import java.util.Arrays;
import java.util.Scanner;

public class NimGame {
	
  public static boolean isArrayZero(int[] arr) {
	  return Arrays.stream(arr).allMatch(i -> i <= 0);
  }
  
  //Getting the heap position from {0,1,2 and with an exception of -1}.
  public static int useHeap(char heap) {
    if (heap == 'A') {
      return 0; 
	}
	else if (heap == 'B') {
	  return 1;
	}
	else if (heap == 'C') {
	  return 2;
	}
	else {
	  return -1;
    }
  }
  
  //Makes the game able to give turns to each player.
  public static String turn(String firstPlayer, String secondPlayer, int turnCounter) {
    if(turnCounter % 2 == 0)
       return firstPlayer;
       return secondPlayer;
  }
  
  //Starts the operations of getting inputs by the user and also sets many variables as strings or int, depending each case.
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String player_1, player_2, ObjectivePlayer;
	
	//Makes the TagOfHeap which in this case works as the letter of the heaps a character variable.
	char TagOfHeap;
	
	//Stating the main array that has the tokens.
    int[] heap = {9,9,9};
	
	//indexOfHeap is used only for positioning reasons
    int indexOfHeap;
	int heapUsed = 0, turnCounter = 0, amountOfTokens = 0;
	
    //Obtaining user input in which case we obtain their names instead of just printing player_1 and player_2.
    System.out.print("Please enter the name of the first player: ");
    player_1 = input.next();

    System.out.print("Please enter the name of the second player: ");
    player_2 = input.next();


    do {
		
	  //Prints the heaps as columns for better readabillity. Again numbers 0,1 and 2 are used as a reference of the heaps as decleared from "useHeap".
      System.out.println(String.format("\nA:%s\tB:%s\tC: %s", heap[0], heap[1], heap[2]) );
      ObjectivePlayer = turn(player_1, player_2, turnCounter);
	  
      do {   
	      //Getting the exact heap the user wants to take tokens from and then select the heap itself for the upcoming calculations.
          System.out.print(String.format("\n%s, Now you may select your ideal heap/column you want to withdraw tokens/numbers: ", ObjectivePlayer));
          TagOfHeap = Character.toUpperCase(input.next().charAt(0));
          indexOfHeap = useHeap(TagOfHeap);
		  
          if(indexOfHeap == -1)
            continue;
          heapUsed = heap[indexOfHeap];
          if(heapUsed == 0)
            continue;
		
		  //Getting the amount of tokens or numbers the user wants to withdraw from each heap/column.
          System.out.println(String.format("Now you may enter the amount of tokens you want to remove from %s", TagOfHeap));
          amountOfTokens = input.nextInt();
		 		 
	  //Checking for available tokens and then after it makes sure there's enough remaining, it contues with the substraction of them.
	  
      } while((amountOfTokens > heapUsed) || (amountOfTokens <= 0));
	  
      heap[indexOfHeap] = heapUsed - amountOfTokens;
      turnCounter += 1;
    } while (!isArrayZero(heap)); //! used as a negation otherwise the program would terminate by the first time a user withdrew even a single token.
    System.out.println(String.format("%s, apparently you've lost the game...", ObjectivePlayer));
  }
}