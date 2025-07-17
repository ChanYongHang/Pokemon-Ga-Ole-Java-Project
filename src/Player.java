import java.util.ArrayList;
import java.util.Random;

public class Player {
	private ArrayList<Pokemon> playerPokemons;
	private Inventory playerInventory; 
	
	//constructor
	public Player() {
		this.playerPokemons = new ArrayList<>(); //using player constructor to create playerPokemons array list
		this.playerInventory = new Inventory(); 
	}
	
	//getters & setters
	public ArrayList<Pokemon> getPlayerPokemons() {
		return playerPokemons;
	}
	
	public void setPlayerPokemons(ArrayList<Pokemon> playerPokemons) {
		this.playerPokemons = playerPokemons;
	}
	
	public Inventory getPlayerInventory() {
		return playerInventory;
	}

	public void setPlayerInventory(Inventory playerInventory) {
		this.playerInventory = playerInventory;
	}

	//add pokemons to player's inventory
	public void addPokemon(Pokemon newPokemon) {
		playerPokemons.add(newPokemon);
	}
	
	//display player's pokemons, displaying with numbering 
	public void displayPlayerPokemon() {
		int count = 1;
		System.out.println("\nPlayer's Pokemons");
		System.out.println("--------------------------------------------------\n");
		for (Pokemon p : playerPokemons) {
			System.out.println("Pokemon: " + count + "\n" + p.toString());
			count++; 
		}
	}
	
	//get pokemon for battle
	//find the pokemon player wants from array list
	//array list stores pokemon player has
	public Pokemon getPlayerPokemon(int index) { 
		index -= 1; 
		return playerPokemons.get(index);
	}
	
	public Pokemon getPlayerPokemon(String name) {
		for(Pokemon p : playerPokemons) {
			if(p.getName().equals(name)){
				return p; 
			}
		}
		throw new IllegalArgumentException("Pokemon name is invalid"); 
	}
	
	//this is use to randomly generate 2 pokeballs for players 
	//to catch pokemons at the start of the game
	public void generateRandomPokeball() {
		Random rand = new Random(); 
		
		String[] pokeballs = {"Master Ball", "Ultra Ball", "Great Ball", "Poke Ball"};
		for(int i = 0; i < 2; i++) {
			String randomPokeball = pokeballs[rand.nextInt(pokeballs.length)];
			Pokeball pokeball = new Pokeball(randomPokeball, 1); 
			playerInventory.addItem(pokeball); 
		}
	}
	
	public void reducePlayerInventory(String itemName) {
		playerInventory.reduceQuantity(itemName);
	}
	
}
