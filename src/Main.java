import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		Game game = new Game(); 
		char choice = ' ';  
		int count = 1; 
		
		do {
			game.startGame();
			
			System.out.println("\nContinue game [y/n]: ");
			choice = input.next().charAt(0); 
			
			if(choice == 'n') {
				System.out.println("Thanks for playing!");
				break; 
			}
			
			count++; 
			System.out.println("\nRound " + count);
			System.out.println("--------------------------------------------------\n");
		}while(choice == 'y'); 
	}
}
