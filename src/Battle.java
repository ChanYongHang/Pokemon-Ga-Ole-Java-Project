import java.util.Random;
import java.util.Scanner;

public class Battle {
	
	private Pokemon playerPokemon1;
	private Pokemon playerPokemon2;
	private Pokemon opponentPokemon1;
	private Pokemon opponentPokemon2;
	private Score score;
	private Booster booster; 
	
	public Battle() {
		this.score = score; 
	}
	
	public Battle(Pokemon playerPokemon1, Pokemon playerPokemon2, Pokemon opponentPokemon1, Pokemon opponentPokemon2
			, Score score, Booster booster) {
		this.playerPokemon1 = playerPokemon1;
		this.playerPokemon2 = playerPokemon2; 
		this.opponentPokemon1 = opponentPokemon1;
		this.opponentPokemon2 = opponentPokemon2;
		this.score = score;
		this.booster = booster; 
	}

	public Pokemon getPlayerPokemon1() {
		return playerPokemon1;
	}

	public void setPlayerPokemon1(Pokemon playerPokemon1) {
		this.playerPokemon1 = playerPokemon1;
	}

	public Pokemon getPlayerPokemon2() {
		return playerPokemon2;
	}

	public void setPlayerPokemon2(Pokemon playerPokemon2) {
		this.playerPokemon2 = playerPokemon2;
	}

	public Pokemon getOpponentPokemon1() {
		return opponentPokemon1;
	}

	public void setOpponentPokemon1(Pokemon opponentPokemon1) {
		this.opponentPokemon1 = opponentPokemon1;
	}

	public Pokemon getOpponentPokemon2() {
		return opponentPokemon2;
	}

	public void setOpponentPokemon2(Pokemon opponentPokemon2) {
		this.opponentPokemon2 = opponentPokemon2;
	}
	
	//determine attack turn using rock, paper, scissor
	public Boolean attackMove() {
		Random rand = new Random(); 
		Scanner input = new Scanner(System.in); 
		
		Boolean playerWin = false; 
		Boolean isTie = true; 
		
		while (isTie) {
			System.out.println("\nSelect your move by entering a number: \n"
					+ "1. Rock\n"
					+ "2. Paper\n"
					+ "3. Scissor");
			int playerMove = input.nextInt(); 
			int randNum = 1 + rand.nextInt(3);
			
			switch(playerMove) {
			case 1: 
				System.out.println("\nPlayer chose Rock");
				if(randNum == 1) {
					System.out.println("Opponent chose Rock");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Tie");
				}
				else if (randNum == 2) {
					System.out.println("Opponent chose Paper");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Player lose! \n"
							+ "Opponent attack first!");
					playerWin = false; 
					isTie = false;
					score.addDefenseTimes();
					
				}
				else {
					System.out.println("Opponent chose Scissor");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Opponent lose! \n"
							+ "Player attack first!");
					playerWin = true; 
					isTie = false;
					score.addAttackTimes();
					
				}
				break; 
			case 2: 
				System.out.println("\nPlayer chose Paper");
				if(randNum == 1) {
					System.out.println("Opponent chose Rock");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Opponent lose! \n"
							+ "Player attack first!");
					playerWin = true; 
					isTie = false;
					score.addAttackTimes();
				}
				else if (randNum == 2) {
					System.out.println("Opponent chose Paper");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Tie");
				}
				else {
					System.out.println("Opponent chose Scissor");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Player lose! \n"
							+ "Opponent attack first!");
					playerWin = false; 
					isTie = false;
					score.addDefenseTimes();
				}
				break; 
			case 3: 
				System.out.println("\nPlayer chose Scissor");
				if(randNum == 1) {
					System.out.println("Opponent chose Rock");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Player lose! \n"
							+ "Opponent attack first!");
					playerWin = false; 
					isTie = false;
					score.addDefenseTimes();
				}
				else if (randNum == 2) {
					System.out.println("Opponent chose Paper");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Opponent lose! \n"
							+ "Player attack first!");
					playerWin = true; 
					isTie = false;
					score.addAttackTimes();
				}
				else {
					System.out.println("Opponent chose Scissor");
					System.out.println("\n--------------------------------------------------\n");
					System.out.println("Tie");
				}
				break; 
			}
		}
		
		return playerWin; 
	}
	
	//start the battle
	public void startBattle(Boolean attackTurn) {
		if (attackTurn) {
			if (playerPokemon1.isFainted()) {
				System.out.printf("Attacker pokemon %s could not attack as it has fainted%n", playerPokemon1.getName());
				System.out.printf("\nPokemon %s is attacking!", playerPokemon2.getName());
				
				int playerattack1 = attack(playerPokemon2, opponentPokemon1);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon1.getName(), opponentPokemon1.getHP());
				
				score.calculateDamageDealt(playerattack1);
				
				System.out.printf("\nPokemon %s is attacking!", playerPokemon2.getName());
				
				int playerattack2 = attack(playerPokemon2, opponentPokemon2); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon2.getName(), opponentPokemon2.getHP());
				
				score.calculateDamageDealt(playerattack2);
			
			}
			else if (playerPokemon2.isFainted()) {
				System.out.printf("Attacker pokemon %s could not attack as it has fainted%n", playerPokemon2.getName());
				System.out.printf("\nPokemon %s is attacking!", playerPokemon1.getName());
				
				int playerattack1 = attack(playerPokemon1, opponentPokemon1);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon1.getName(), opponentPokemon1.getHP());
				
				score.calculateDamageDealt(playerattack1);
				
				System.out.printf("\nPokemon %s is attacking!", playerPokemon1.getName());
				
				int playerattack2 = attack(playerPokemon1, opponentPokemon2);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon2.getName(), opponentPokemon2.getHP());
				
				score.calculateDamageDealt(playerattack2);
			}
			else {
				System.out.printf("\nPokemon %s is attacking!", playerPokemon1.getName());
				
				int playerattack1 = attack(playerPokemon1, opponentPokemon1);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon1.getName(), opponentPokemon1.getHP());
				
				score.calculateDamageDealt(playerattack1);
				
				System.out.printf("\nPokemon %s is attacking!", playerPokemon2.getName());
				
				int playerattack2 = attack(playerPokemon2, opponentPokemon2);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", opponentPokemon2.getName(), opponentPokemon2.getHP());
				
				score.calculateDamageDealt(playerattack2);
			}
			 
		}
		else {
			if (opponentPokemon1.isFainted()) {
				System.out.printf("Attacker pokemon %s could not attack as it has fainted%n", opponentPokemon1.getName());
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon2.getName());
				
				attack(opponentPokemon2, playerPokemon1); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon1.getName(), playerPokemon1.getHP());
				
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon2.getName());
				
				attack(opponentPokemon2, playerPokemon2); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon2.getName(), playerPokemon2.getHP());
			}
			else if (opponentPokemon2.isFainted()) {
				System.out.printf("Attacker pokemon %s could not attack as it has fainted%n", opponentPokemon2.getName());
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon1.getName());
				
				attack(opponentPokemon1, playerPokemon1); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon1.getName(), playerPokemon1.getHP());
				
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon1.getName());
				
				attack(opponentPokemon1, playerPokemon2);
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon2.getName(), playerPokemon2.getHP());
			}
			else {
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon1.getName());
				
				attack(opponentPokemon1, playerPokemon1); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon1.getName(), playerPokemon1.getHP());
				
				System.out.printf("\nPokemon %s is attacking!", opponentPokemon2.getName());
				
				attack(opponentPokemon2, playerPokemon2); 
				System.out.printf("Defender Pokemon %s remaining HP is %d%n", playerPokemon2.getName(), playerPokemon2.getHP());
			}
			
		}
	} 
	
	//handle the attack
	public int attack(Pokemon attacker, Pokemon defender) {
		if (attacker.isFainted()) {
			System.out.printf("Attacker pokemon %s could not attack as it has fainted%n", attacker.getName());
			return 0; 
		}
		
		 if (defender.isFainted()) {
			 System.out.printf("%nDefender pokemon %s could not be attacked as it is already fainted%n", defender.getName());
		    return 0;
		 }
		 
		int additionalAttack = booster.attackRose() + booster.generateRandomBooster("attack"); 
		int additionalDefense = booster.defenseRose() + booster.generateRandomBooster("defense"); 
		int damage = calDamage(attacker, additionalAttack, additionalDefense); 
		
		defender.takeDamage(damage);
		System.out.printf("%nDefender pokemon %s is taking %d damage%n", defender.getName(), damage);
		
		if (defender.isFainted()) {
			System.out.printf("Defender pokemon %s is fainted%n", defender.getName());
		}
		
		return damage;
	}
	
	//calculate and return the total damage
	public int calDamage(Pokemon pokemon, int attackRose, int defenseRose) {
		int totalDamage = pokemon.calculateDamage(pokemon, attackRose, defenseRose);
		
		System.out.println("\nCalculating damage...");
		System.out.println("--------------------------------------------------\n");
		System.out.printf("Total damage: %d%n", totalDamage);
		return totalDamage; 
	}
	
	//determine if the battle should continue or not
	public boolean continueGame(Pokemon p1, Pokemon p2, Pokemon p3, Pokemon p4) {
		return !allFainted(p1, p2) && !(allFainted(p3, p4));
	}
	
	public boolean allFainted(Pokemon pokemon1, Pokemon pokemon2) {
		return pokemon1.isFainted() && pokemon2.isFainted(); 
	}
	
	//determine the winner
	public boolean playerWon(Pokemon pk1, Pokemon pk2) {
		if(allFainted(pk1, pk2)) {
			score.setWinLose(false);
			return false; 
		}
		else {
			score.setWinLose(true);
			return true; 
		}
	}

	@Override
	public String toString() {
		return String.format("Battle [playerPokemon1=%s, playerPokemon2=%s, opponentPokemon1=%s, opponentPokemon2=%s]",
				playerPokemon1, playerPokemon2, opponentPokemon1, opponentPokemon2);
	}

}
