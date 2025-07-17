public class RockTypePokemon extends Pokemon{
	
	public RockTypePokemon(String name, int HP, int attackPower, String moveType, 
			String defenderType, char grade, double catchProbability) {
		super(name, HP, attackPower, moveType, defenderType, grade, catchProbability); 
	}
	
	@Override
	public int calculateDamage(Pokemon opponent, int attackRose, int defenseRose) {
		int basicDamage = super.calculateDamage(opponent, attackRose, defenseRose);
		String opponentDefenseType = opponent.getDefenderType(); 
		
		if(opponentDefenseType.equals("Fire")) {
			System.out.println("Super Effective Attack!");
			basicDamage *= 1.85;
		}
		
		if (basicDamage < 0) {
			return basicDamage = 0; 
		}
		else {
			return (int) basicDamage; 
		}
	}
}
