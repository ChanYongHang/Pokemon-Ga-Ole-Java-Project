import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Score {

	//attributes
	private ArrayList<Integer> total_Score;
	private int wildpokemoncaught;
	private int attacktimes;
	private int defensetimes;
	private int winlose;
	private int damagedealt;
	
	//initialize set scores with values
	private static final int wildpokemonscore = 1500; 
	private static final int attackscore = 1500;
	private static final int defensescore = 500;
	private static final int damagedealtscore = 10;

	//constructor
	public Score() {
		this.total_Score = new ArrayList<>();
		wildpokemoncaught = 0;
		attacktimes = 0;
		defensetimes = 0;
		winlose = 0;
		damagedealt = 0;
	}
	
//	public Score(int wildpokemoncaught, int attacktimes, int defensetimes,
//			 int winlose) {
//		this.wildpokemoncaught = wildpokemoncaught;
//		this.attacktimes = attacktimes;
//		this.defensetimes = defensetimes;
//		this.winlose = winlose;
//	}
	
	//count wild pokemon caught
	public void addWildPokemonCaught() {
		wildpokemoncaught++;
		
	}
	
	//get wild pokemon caught
	public int getWildPokemonCaught() {
		return wildpokemoncaught * wildpokemonscore;
	}
	
	//count how many times player attacks 
	public void addAttackTimes() {
		attacktimes++;
	}
	
	//get the score for player attacks
	public int getAttackScore() {
		return attacktimes * attackscore;
	}
	
	//count how many times player defends
	public void addDefenseTimes() {
		defensetimes++;
	}
	
	//get the score for player defenses
	public int getDefenseScore() {
		return defensetimes * defensescore;
	}
	
	//setting win or lose 
	public void setWinLose(boolean won) {
		if (won) {
			winlose = 5000;
		}
		else {
			winlose = 1000;
		}
	}
	
	//getting win or lose score
	public int getWinLose() {
		return winlose;
	}
	
	//calculating damage dealt
	public void calculateDamageDealt(int damage) {
		damagedealt += damage;
	}
	
	//get damage dealt score
	public int getDamageDealt() {
		return damagedealt * damagedealtscore;
	}
	
	//calculate total score
	public int calculateTotalScore() {
		int total = getWinLose() + getWildPokemonCaught() + getAttackScore() + getDefenseScore() + getDamageDealt();
		total_Score.add(total);
		return total;
	}
	
	//arraylist for storing total scores
	public ArrayList<Integer> getTotal_Score() {
		return total_Score;
	}
	
	//display top 5 scores from the array list
	 public void displayTop5Scores() {
	       
	        Collections.sort(total_Score, Collections.reverseOrder()); 

	        System.out.println("Top 5 Scores (highest to lowest): ");
	        for (int i = 0; i < 5; i++) { 
	        	if (i < total_Score.size()) {
	               
	                System.out.println("Score " + (i + 1) + ": " + total_Score.get(i)); 
	            } else {
	                
	                System.out.println("Score " + (i + 1) + ": No record found"); //display no record found for scores without values added yet
	            }
	        }
	 }
	
	 //saves scores from arraylist to text file when the program is closed
	public void saveScoresToTextFile() {
		String filename = "scores.txt"; 
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			
			Collections.sort(total_Score, Collections.reverseOrder());
			
			for (int i = 0; i < Math.min(5, total_Score.size()); i++) {
	            writer.write(total_Score.get(i) + "\n");
	        }
			
		} catch (IOException e) {
			System.out.println("The scores could not be saved due to an error: " + e.getMessage());
			
		}
	}
	
	//load scores from text file into arraylist
	public void loadScoresFromTextFile() {
		String filename = "scores.txt";
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line;
			while ((line = reader.readLine())!= null) {
				total_Score.add(Integer.parseInt(line));
			}
		} catch(IOException e) {
			System.out.println("No scores can be found: " + e.getMessage());
		
		}
	}

	@Override
	public String toString() {
		return String.format(
			"Score [getWildPokemonCaught()=%s, getAttackScore()=%s, getDefenseScore()=%s, getWinLose()=%s, getDamageDealt()=%s, getTotal_Score()=%s]",
				getWildPokemonCaught(), getAttackScore(), getDefenseScore(), getWinLose(), getDamageDealt(),
				 getTotal_Score());
	}
	
	
	
}
