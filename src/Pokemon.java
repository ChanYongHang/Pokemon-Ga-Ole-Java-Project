
public class Pokemon {
	
	private String name;
	private int HP;
	private int maxHP; 
	private int attackPower;
	private String moveType; 
	private String defenderType; 
	private char grade; 
	private double catchProbability; 

	public Pokemon(String name, int HP, int attackPower, String moveType, 
			String defenderType, char grade, double catchProbability) {
		this.name = name;
		this.HP = HP; 
		this.maxHP = HP; 
		this.attackPower = attackPower;
		this.moveType = moveType;
		this.defenderType = defenderType;
		this.grade = grade;
		this.catchProbability = catchProbability; 
	}

	//getters & setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getDefenderType() {
		return defenderType;
	}

	public void setDefenderType(String defenderType) {
		this.defenderType = defenderType;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	public double getCatchProbability() {
		return catchProbability;
	}

	public void setCatchProbability(double catchProbability) {
		this.catchProbability = catchProbability;
	}

	public void takeDamage(int totalDamage) {
		if (totalDamage > HP) {
			HP = 0; 
		}
		else {
			HP -= totalDamage; 
		}
	}
	
	public Boolean isFainted() {
		return HP <= 0; 
	}
	
	public int calculateDamage(Pokemon opponent, int attackRose, int defenseRose) {
		int basicDamage = opponent.getAttackPower() + attackRose - defenseRose; 
		if (basicDamage < 0) {
			return basicDamage = 0; 
		}
		else {
			return basicDamage; 
		}
		
	}
	
	//restore player's pokemons HP starting from the start of second round 
	public void restoreHP() {
		this.HP = maxHP; 
	}

	@Override
	public String toString() {
		return String.format(
				"Pokemon Name: %s, HP: %d, Attack Power: %d, Move Type: %s, Defender Type: %s",
				name, HP, attackPower, moveType, defenderType);
	}
}
