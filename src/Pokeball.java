import java.util.ArrayList;
import java.util.Random;

public class Pokeball extends Item{

	//attributes
	private Pokemon pokemon; 
	
	//empty constructor
	public Pokeball() {
		super(); 
	}
	
	//complete constructor
	public Pokeball(String name, int quantity) {
		super(name, quantity); 
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	//this is use to determine if the pokeball players use
	//can succesfully catch the pokemon players want to catch
	//based on pokemons' grade
	public double catchProbability(String pokeball) {
		String pokeballType = pokeball; 
		
		switch(pokeballType) {
		case "Master Ball": 
			return 0.95; 
		case "Ultra Ball": 
			return 0.8; 
		case "Great Ball": 
			return 0.7; 
		case "Poke Ball": 
			return 0.5; 
		default: 
			return 0.0; 
		}
	}

	@Override
	public String toString() {
		return String.format("%s %d quantity", super.getName(), super.getQuantity());
	}

//	public static void main(String[] args) {
//		Pokemon pokemon1 = new FireTypePokemon("Flareon", 100, 60, "Fire", "Grass", 'B'); 
//		String pokeball = "Poke Ball"; 
//		
//		Pokeball pkb = new Pokeball(pokemon1);
//		pkb.determineSuccessCatch(pokemon1, pokeball);
//	}
}
