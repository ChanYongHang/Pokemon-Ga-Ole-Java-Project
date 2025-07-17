
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private Stage s; 
	private Battle b;
	private Player p; 
	//this array list stores the randomly appeared wild pokemons
	//for players to catch when the game first started 
	//after player selected a stage to embark on
	private ArrayList<Pokemon> wildPokemonToCatch; 
	private Score score;
	private Booster booster; 
	
	public Game() {
		this.s = new Stage();
		this.p = new Player(); 
		this.wildPokemonToCatch = new ArrayList<Pokemon>(); 
		this.score = new Score();
		this.booster = new Booster(); 
		
		score.loadScoresFromTextFile();
	}
	
	public void startGame() {
		Scanner input = new Scanner(System.in); 
		
		//generate 2 random pokeball for player at the start of each game
		p.generateRandomPokeball();
		
		s.restoreStagePokemonsHP();
				
		System.out.println("Enter a number to choose a stage: \n"
				+ "1. Forest\n"
				+ "2. Lake\n"
				+ "3. Grassland");
		int choice = input.nextInt();
		
		//display pokemons player might encounter in a particular stage
		displayPotentialOpponent(choice); 
		
		System.out.println("\nThree wild pokemon appeared");
		System.out.println("Catch one out of three");
		System.out.println("--------------------------------------------------\n");
		
		//generate wild pokemon and add it to an array
		//then display random pokemon 
		displayWildPokemons(generateWildPokemons()); 
		
		//prompt player to choose one pokemon to catch 
		System.out.println("\nEnter a number to catch: ");
		int catchChoice = input.nextInt();
		
		//use to clear buffer
		input.nextLine(); 
		
		//handle catching details
		handleCatch(getWildPokemon(catchChoice));
		
		//generate rental pokemon for player
		//if their pokemon is lesser than 2
		if(p.getPlayerPokemons().size() < 2) {
			System.out.println("\nPlayer's pokemon is not enough\n"
					+ "Generating rental pokemon...\n");
			while(p.getPlayerPokemons().size() < 2) {
				p.addPokemon(s.generateRandomPokemon(1, 15));
			}
		}
		
		//initialise player selected pokemons for battling
		ArrayList<Pokemon> selectedPokemon = selectBattlePokemons(); 
		Pokemon playerPokemon1 = selectedPokemon.get(0); 
		Pokemon playerPokemon2 = selectedPokemon.get(1); 
		
		//initialise opponent pokemons for battling
		ArrayList<Pokemon> opponentPokemons = opponentPokemonsList(playerPokemon1, playerPokemon2, choice);
		Pokemon opponentPokemon1 = opponentPokemons.get(0); 
		Pokemon opponentPokemon2 = opponentPokemons.get(1); 
		
		//creating and initialising battle class
		b = new Battle(playerPokemon1, playerPokemon2, opponentPokemon1, opponentPokemon2, score, booster); 
		
		//display opponents pokemons
		System.out.println("\nOpponent's Pokemons: ");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Pokemon 1\n" + b.getOpponentPokemon1());
		System.out.println("Pokemon 2\n" + b.getOpponentPokemon2());
		
		System.out.println("\nGame Start");
		System.out.println("--------------------------------------------------");
		
		//starting the battle
		while (b.continueGame(opponentPokemon1, opponentPokemon2, playerPokemon1, playerPokemon2)) {
			Boolean attackTurn = b.attackMove();
			b.startBattle(attackTurn);
		}
		
		//display winner, scores and provide catch chances after battle ended
		System.out.println("\nBattle has ended!");
		System.out.println("--------------------------------------------------");
		//display winner
		if(b.playerWon(playerPokemon1, playerPokemon2)) {
			System.out.println("Player Won");
			
			System.out.println("\nChoose one opponent pokemon to catch [Enter Pokemon Name]: ");
			System.out.println(opponentPokemon1);
			System.out.println(opponentPokemon2);
			String catchOpponentPokemonChoice = input.nextLine();
			
			handleCatch(s.getStagePokemon(catchOpponentPokemonChoice)); 
		}
		else {
			System.out.println("Opponent Won");
		}
		
		displayScores();
	}
	
	//display pokemons that might appear in each stage
	public void displayPotentialOpponent(int choice) {
		System.out.println("\nPokemons might appear in this stage");
		System.out.println("--------------------------------------------------\n");
		switch(choice) {
		case 1: 
			s.displayStagePokemon(1, 5);
			break; 
		case 2: 
			s.displayStagePokemon(6, 10);
			break; 
		case 3: 
			s.displayStagePokemon(11, 15);
			break; 
		default: 
			System.out.println("Wrong selection");
			break; 
		}
	}
	
	//use to calculate catch probability and determine success
	public boolean determineCatchSuccess(Pokemon pokemon, String pokeball) {
		Inventory inventory = p.getPlayerInventory(); 
		Item playerPokeball = inventory.getItem(pokeball); 
		
		double pokeballCatchProbability = ((Pokeball) playerPokeball).catchProbability(pokeball); 
		double pokemonCatchProbability = pokemon.getCatchProbability(); 
		
		if(Math.random() <= pokeballCatchProbability * pokemonCatchProbability) {
			return true; 
		}
		else {
			return false; 
		}
	}
	
	//handle catch, add caught pokemon to player's inventory if caught successfully
	public void handleCatch(Pokemon pokemon) {
		Scanner input = new Scanner(System.in); 
		
		//display player's inventory
		System.out.println("\nPlayer's Inventory");
		System.out.println("--------------------------------------------------\n");
		System.out.println(p.getPlayerInventory() + "\n"); 
		
		//prompt player to choose a pokeball for catching 
		System.out.println("Choose a pokeball to catch [Enter Pokeball Name]: ");
		String pokeballChoice = input.nextLine(); 
		
		//reduce player's inventory after using one
		p.reducePlayerInventory(pokeballChoice);
		
		boolean isCaught = determineCatchSuccess(pokemon, pokeballChoice); 
		
		if(isCaught) {
			p.addPokemon(pokemon);
			System.out.printf("%n%s is caught successfully!%n", pokemon.getName());
			score.addWildPokemonCaught();
			System.out.println("\nUpdated Player's Inventory");
			System.out.println("--------------------------------------------------\n");
			System.out.println(p.getPlayerInventory());
			
			System.out.println("\nCaught Pokemon Details");
			System.out.println("--------------------------------------------------\n");
			System.out.println(p.getPlayerPokemon(pokemon.getName()));
		}
		else {
			System.out.println("Catch fails\n");
		}
	}
	
	//get the pokemon from wild pokemon array list
	//for adding into player's pokemon array list
	public Pokemon getWildPokemon(int index) {
		index -= 1; 
		if(index >= 0 && index < wildPokemonToCatch.size()) {
			return wildPokemonToCatch.get(index); 
		}
		else {
			throw new IndexOutOfBoundsException("Invalid index: " + index); 
		}
	}
	
	//display wild pokemons in wildPokemonToCatch array list
	public void displayWildPokemons(ArrayList<Pokemon> wildPokemonToCatch) {
		int count = 1; 
		for (Pokemon p : wildPokemonToCatch) {
			System.out.printf("%d. Pokemon Name: %s, Move Type: %s, Defense Type: %s, Grade: %s%n"
					, count, p.getName(), p.getMoveType(), p.getDefenderType(), p.getGrade());
			count++; 
		}
	}
	
	//allow players to choose 2 pokemons from player's pokemons inventory for battle
	public ArrayList<Pokemon> selectBattlePokemons() {
		Scanner input = new Scanner(System.in); 
		
		ArrayList<Pokemon> selectedBattlePokemons = new ArrayList<Pokemon>(); 
		
		//display player's pokemon details
		p.displayPlayerPokemon();
		
		//player selecting pokemons to battle
		int playerSelection; 
		System.out.println("\nSelect 2 pokemon to battle: ");
		
		System.out.println("Pokemon 1: ");
		playerSelection = input.nextInt(); 
		selectedBattlePokemons.add(p.getPlayerPokemon(playerSelection));
		
		System.out.println("Pokemon 2: ");
		playerSelection = input.nextInt(); 
		selectedBattlePokemons.add(p.getPlayerPokemon(playerSelection));
		
		return selectedBattlePokemons; 
	}
	
	//create 2 opponent pokemons from 5 pokemons that might appear in the particular stage
	public ArrayList<Pokemon> opponentPokemonsList(Pokemon pk1, Pokemon pk2, int choice){
		ArrayList<Pokemon> opponentPokemonsList = new ArrayList<Pokemon>(); 
		
		Pokemon opponentPokemon1 = null;
		Pokemon opponentPokemon2 = null;
		switch (choice) {
		case 1: 
			//ensure opponent pokemons are unique, not the same as player pokemon
			do {
				opponentPokemon1 = s.generateRandomPokemon(0, 4);
			}while(opponentPokemon1.equals(pk1) || opponentPokemon1.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon1); 
			
			do {
				opponentPokemon2 = s.generateRandomPokemon(0, 4);
			}while(opponentPokemon2.equals(pk1) || opponentPokemon2.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon2);
			
			break; 
		case 2: 
			//ensure opponent pokemons are unique, not the same as player pokemon
			do {
				opponentPokemon1 = s.generateRandomPokemon(5, 9);
			}while(opponentPokemon1.equals(pk1) || opponentPokemon1.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon1);
			
			do {
				opponentPokemon2 = s.generateRandomPokemon(5, 9);
			}while(opponentPokemon2.equals(pk1) || opponentPokemon2.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon2);
			
			break; 
		case 3: 
			//ensure opponent pokemons are unique, not the same as player pokemon
			do {
				opponentPokemon1 = s.generateRandomPokemon(10, 14); 
			}while(opponentPokemon1.equals(pk1) || opponentPokemon1.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon1);
			
			do {
				opponentPokemon2 = s.generateRandomPokemon(10, 14);
			}while(opponentPokemon2.equals(pk1) || opponentPokemon2.equals(pk2)); 
			
			opponentPokemonsList.add(opponentPokemon2);
			
			break; 
		}
		
		return opponentPokemonsList;
	}
	
	//generating and adding random wild pokemons to array list 
	public ArrayList<Pokemon> generateWildPokemons() {
		//clear remaining pokemons in the array list if there's more than 1 round of game
		wildPokemonToCatch.clear(); 
		
		for(int i = 0; i < 3; i++) {
			wildPokemonToCatch.add(s.generateRandomPokemon(1, 15));
		}
		return wildPokemonToCatch; 
	}
	
	//for displaying score
	public void displayScores() {
		System.out.println("\nScore List");
		System.out.println("--------------------------------------------------\n");
		System.out.printf("Your total score is : %d%n", score.calculateTotalScore());
		 
		System.out.printf("Win lose score : %d%n", score.getWinLose());
		System.out.printf("Damage dealt score : %d%n",score.getDamageDealt());
		System.out.printf("Wild pokemon caught score : %d%n", score.getWildPokemonCaught());
		System.out.printf("Attack score : %d%n",score.getAttackScore());
		System.out.printf("Defense score : %d%n",score.getDefenseScore());
		 
		System.out.println("\n--------------------------------------------------\n");
		
		score.displayTop5Scores();
		 
		score.saveScoresToTextFile();
	}
	
	
	//setters & getters
	public Stage getS() {
		return s;
	}

	public void setS(Stage s) {
		this.s = s;
	}

	public Battle getB() {
		return b;
	}

	public void setB(Battle b) {
		this.b = b;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public ArrayList<Pokemon> getWildPokemonToCatch() {
		return wildPokemonToCatch;
	}

	public void setWildPokemonToCatch(ArrayList<Pokemon> wildPokemonToCatch) {
		this.wildPokemonToCatch = wildPokemonToCatch;
	}
}
