
import java.util.Random;

public class Booster {	
	
    private final String attackBooster = "Super Potion"; 
    private final String hpBooster = "Rare Candy";
    private int additionalAttack; 
    private int additionalDefense; 
    
    public Booster() {
    	
    }

    //getters & setters
    public String getAddAttackBooster() {
		return attackBooster;
	}

	public String getAddHPBooster() {
		return hpBooster;
	}
	
	public int getAdditionalAttack() {
		return additionalAttack;
	}

	public void setAdditionalAttack(int additionalAttack) {
		this.additionalAttack = additionalAttack;
	}

	public int getAdditionalDefense() {
		return additionalDefense;
	}

	public void setAdditionalDefense(int additionalDefense) {
		this.additionalDefense = additionalDefense;
	}

	//randomly choosing an attack rose
	//will be added to attacker's attack power in each attack turn 
	public int attackRose() {
		Random rand = new Random(); 
		int randomNumber = 1 + rand.nextInt(4);
		System.out.println("\nAttack rose chosen: " + randomNumber);
		
		switch(randomNumber) {
		case 1: 
			additionalAttack = 0; 
			System.out.printf("Attacker gets an additional attack of: %d%n", additionalAttack);
			break; 
		case 2: 
			additionalAttack = 20; 
			System.out.printf("Attacker gets an additional attack of: %d%n", additionalAttack);
			break; 
		case 3: 
			additionalAttack = 50; 
			System.out.printf("Attacker gets an additional attack of: %d%n", additionalAttack);
			break;  
		case 4: 
			additionalAttack = 100; 
			System.out.printf("Attacker gets an additional attack of: %d%n", additionalAttack);
			break; 
		}
		
		return additionalAttack; 
	}
	
	//randomly choosing a defense rose
	//will be added to defender's defense power in each attack turn 
	public int defenseRose() {
		Random rand = new Random(); 
		int randomNumber = 1 + rand.nextInt(4);
		System.out.println("\nDefense rose chosen: " + randomNumber);
		
		switch(randomNumber) {
		case 1: 
			additionalDefense = 0; 
			System.out.printf("Defender gets an additional defense of: %d%n", additionalDefense);
			break; 
		case 2: 
			additionalDefense = 20; 
			System.out.printf("Defender gets an additional defense of: %d%n", additionalDefense);
			break; 
		case 3: 
			additionalDefense = 50; 
			System.out.printf("Defender gets an additional defense of: %d%n", additionalDefense);
			break;  
		case 4: 
			additionalDefense = 100; 
			System.out.printf("Defender gets an additional defense of: %d%n", additionalDefense);
			break; 
		}
		
		return additionalDefense; 
	}
	
	//these random boosters will only be generated in rare occasions unlike attack rose and defense rose
	public int generateRandomBooster(String boosterType) {
		Random rand = new Random(); 
		int boosterProbability = 1 + rand.nextInt(10); 
		
		if (boosterProbability == 5) {
			
			if(boosterType.equals("attack")) {
				System.out.println("Player received a " + attackBooster +  " booster, adding additional 100 attack damage for player's pokemons");
				return 100;
			}
			else if (boosterType.equals("defense")) {
				System.out.println("Player received a " + hpBooster + " booster, adding additional 100 defense power for player's pokemons");
				return 100; 
			}
		}
		
		return 0; 
	}
}
