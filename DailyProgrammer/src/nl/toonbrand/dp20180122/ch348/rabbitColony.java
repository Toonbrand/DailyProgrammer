package nl.toonbrand.dp20180122.ch348;

/**
 * @author Toon Brand
 * 
 * https://www.reddit.com/r/dailyprogrammer/comments/7s888w/20180122_challenge_348_easy_the_rabbit_problem/
 * Assuming this class is executed using three numeric inputs: (Male_rabbits Female_rabbits Rabbits_needed_alive)
 * Inputs from the challenge: (2 4 1000000000) and (2 4 15000000000)
 *
 */
public class rabbitColony {
	int[] males = new int[96], females = new int[96];		//rabbits live to be 96 years old
	static final int FERTILE_AGE = 4;						//rabbits are fertile from 4 months old
	int month, rabbitsNeeded, deadRabbits;

	public static void main(String[] args) {
		rabbitColony colony = new rabbitColony(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

		while(!colony.worldDomination()){
			colony.progress();
			colony.month++;
		}
	}

	public rabbitColony (int maleRabbits, int femaleRabbits, int rabbitsNeeded){
		this.males[2]=maleRabbits;
		this.females[2]=femaleRabbits;
		this.rabbitsNeeded = rabbitsNeeded;
	}

	private void progress(){
		int newMales = 0;
		int newFemales = 0;

		// Calculate and save how many new rabbits are born
		for(int i=0;i<females.length;i++){
			if(i>=FERTILE_AGE&&females[i]>0){
				for(int j=0;j<females[i];j++){
					newMales += 5;
					newFemales += 9;
				}
			}
		}

		// Age all males by one and add the newborns
		deadRabbits += males[males.length-1];
		for(int i=males.length-1;i>0;i--){
			males[i]=males[i-1];
		}
		males[0]=newMales;

		// Age all females by one and add the newborns
		deadRabbits += females[females.length-1];
		for(int i=females.length-1;i>0;i--){
			females[i]=females[i-1];
		}
		females[0]=newFemales;
	}

	public boolean worldDomination(){
		int totalMales = 0;
		int totalFemales = 0;

		for(int alive : males){
			totalMales += alive;
		}
		for(int alive : females){
			totalFemales += alive;
		}

		// Log the progress to world domination
		System.out.println("Month: " + month);
		System.out.println("Total males:   " + totalMales);
		System.out.println("Total females: " + totalFemales);
		System.out.println("Total rabbits: " + (totalFemales+totalMales));
		System.out.println("Total deaths:  " + (deadRabbits));
		System.out.println("===========================");
		
		return (totalMales+totalFemales>rabbitsNeeded);
	}
}
