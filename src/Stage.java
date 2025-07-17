import java.util.ArrayList;
import java.util.Random;

public class Stage {

	private ArrayList<Pokemon> stagePokemons; 
	
	public Stage() {
		this.stagePokemons = new ArrayList<Pokemon>(); 
		stagePokemons.add(new FireTypePokemon("Flareon", 100, 60, "Fire", "Grass", 'B', 0.85)); 
		stagePokemons.add(new WaterTypePokemon("Vaporeon", 110, 55, "Water", "Rock", 'C', 1.0)); 
		stagePokemons.add(new GrassTypePokemon("Bulbasaur", 45, 49, "Grass", "Water", 'A', 0.7)); 
		stagePokemons.add(new ElectricTypePokemon("Jolteon", 95, 70, "Electric", "Water", 'A', 0.7)); 
		stagePokemons.add(new GrassTypePokemon("Leafeon", 90, 50, "Grass", "Water", 'B', 0.85)); 
		stagePokemons.add(new RockTypePokemon("Rhyperior", 120, 75, "Rock", "Electric", 'A', 0.7)); 
		stagePokemons.add(new FireTypePokemon("Magmar", 85, 60, "Fire", "Grass", 'B', 0.85)); 
		stagePokemons.add(new WaterTypePokemon("Blastoise", 130, 65, "Water", "Fire", 'S', 0.5)); 
		stagePokemons.add(new ElectricTypePokemon("Raichu", 95, 60, "Electric", "Rock", 'B', 0.85)); 
		stagePokemons.add(new GrassTypePokemon("Torterra", 125, 70, "Grass", "Electric", 'A', 0.7)); 
		stagePokemons.add(new RockTypePokemon("Golem", 110, 80, "Rock", "Fire", 'C', 1.0)); 
		stagePokemons.add(new FireTypePokemon("Charizard", 120, 85, "Fire", "Grass", 'S', 0.5)); 
		stagePokemons.add(new WaterTypePokemon("Gyarados", 115, 75, "Water", "Rock", 'S', 0.5)); 
		stagePokemons.add(new ElectricTypePokemon("Zapdos", 100, 85, "Electric", "Grass", 'C', 1.0)); 
		stagePokemons.add(new WaterTypePokemon("Lanturn", 105, 60, "Water", "Electric", 'B', 0.85)); 
	}

	//setters & getters
	public ArrayList<Pokemon> getStagePokemons() {
		return stagePokemons;
	}

	public void setStagePokemons(ArrayList<Pokemon> stagePokemons) {
		this.stagePokemons = stagePokemons;
	}
	
	public Pokemon getStagePokemon(String pokemonName) {
		for(Pokemon p : stagePokemons) {
			if(p.getName().equals(pokemonName)) {
				return p; 
			}
		}
		throw new IllegalArgumentException("Pokemon " + pokemonName + " is not a valid pokemon"); 
	}
	
	//display pokemons in each stage
	public void displayStagePokemon(int startValue, int endValue) {
		int count=1;
		for (int i=startValue; i <= endValue && i < stagePokemons.size(); i++) {
			System.out.printf("%d. Pokemon Name: %s, Move Type: %s, Defense Type: %s%n"
					, count, stagePokemons.get(i).getName(), stagePokemons.get(i).getMoveType()
					, stagePokemons.get(i).getDefenderType());
			count++; 
		}
	}
	
	//generate random pokemon
	public Pokemon generateRandomPokemon(int startValue, int endValue) {
		int arrayListSize = stagePokemons.size(); 
		
		if (endValue > arrayListSize) 
			endValue = arrayListSize; 
		
		Random rand = new Random(); 
		int randIndex = startValue + rand.nextInt(endValue - startValue);
//		//debug purpose
//		System.out.println(randIndex);
		return stagePokemons.get(randIndex); 
	}
	
	public void restoreStagePokemonsHP() {
		for(Pokemon p : stagePokemons) {
			p.restoreHP(); 
		}
	}
	

	@Override
	public String toString() {
		return String.format("Stage [stagePokemons=%s]", stagePokemons);
	}
	
	
	//testing purposes
	public static void main(String[] args) {
		Stage s = new Stage();
	}
	
	
}
