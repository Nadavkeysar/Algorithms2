package algorithms_1.exercise9;

import java.util.Arrays;

/**
 * 
 * This is a game simulation program that 
 * calculates the probability of each player/soldier to stay alive in the battle.
 * Each player/soldier has his strategy.
 *
 */
public class Duel3D2 {

	public static void duel3d(int num){ // number of simulations
		double pC = 0.8, pD = 0.5; // pB=1 - probability of hitting the target
		int countB = 0, countC = 0, countD = 0;//an amount wins
		int B=1, C=2, D=3;
		double cChance=0, dChance=0;
		boolean flag = true;
		for (int i=1; i<=num; i++){
			int[] q = getQueuq3();
			int first = q[1];
			switch (first){
			case 1: // B is the first and B kills C
				dChance = Math.random(); // D fire
				if (dChance < pD) countD++; // D kills B
				else countB++; // B kills D
				break;
			case 2: // C is the first, C try to kill B
				cChance = Math.random(); // C fire
				if (cChance < pC){// C kills B, duel between C and D
					flag = true;
					while (flag){
						dChance = Math.random();// D fire
						if (dChance < pD){ // C is killed
							countD++;
							flag = false;
						}
						else{
							cChance = Math.random();
							if (cChance < pC){ //D is killed
								countC++;
								flag = false;
							}
						}
					}
				}
				// C does not kill B,  the triple duel B, C, D
				else if (q[2]==B){ // B is the second, B kills C 
					dChance = Math.random(); // D fire, try to kill B
					if (dChance < pD) countD++;// D kills B
					else countB++; // B kills D
				}
				else if (q[2]==D){ // D is the second, D does not fire
					// B kills C
					dChance = Math.random();// D fire, try to kill B
					if (dChance < pD) countD++;// D kills B
					else countB++; // B kills D
				}
				break;
			case 3: // D is the first, D does not fire
				if (q[2] == B){ // B is the second, B kills C
					dChance = Math.random(); // D fire
					if (dChance < pD) countD++; // D kills B
					else countB++; // B kills D
				}
				else{
					if (q[2]==C){ // C is the second, C try to kill B
						cChance = Math.random();
						if (cChance < pC){ // C kills B, duel between C and D
							flag = true;
							while (flag){
								dChance = Math.random(); // D fire
								if (dChance < pD){ //C is killed
									countD++;
									flag = false;
								}
								else{
									cChance = Math.random(); //C fire
									if (cChance < pC){ //D is killed
										countC++;
										flag = false;
									}
								}
							}
						}
						else{
							//C does not kill B, B kills C
							dChance = Math.random(); // D fire
							if (dChance < pD) countD++;// D kills B
							else countB++; // B kills D
						}
					}
				}
				break;
			}
		}
		double probB = (double)countB/num;
		double probC = (double)countC/num;
		double probD = (double)countD/num;
		System.out.println("probB = " + probB + ",  probC = " + probC + ",  probD = " + probD); 
		System.out.println("summa = " + (probB+probC+probD)); 
	}
	/**
	 * 
	 * @return array of shooter order for 
	 */
	public static int [] getQueuq3(){
		/*
		int[]q = {0,1,2,3};
		for (int i = 1; i < q.length; i++) {
			int j = (int)(Math.random()*3) + 1; 
			//System.out.print(j+"-> ");
			int t = q[i];
			q[i] = q[j];
			q[j] = t;
		}
		//System.out.println(Arrays.toString(q));
		return q;
		 */
		int rand = (int) (Math.random()*6);
		int q[][] = {{0,1,2,3},{0,1,3,2},{0,3,1,2},
				{0,3,2,1},{0,2,1,3},{0,2,3,1}};
		return q[rand];		
	}

	public static void duel2d(int num){ // number of simulations
		double pC = 0.8; // pB=1 - probability of hitting the target
		int countB = 0, countC = 0;//an amount wins
		//	int B=1, C=2;
		double cChance=0;

		for (int i=1; i<=num; i++){			
			int first = (int)(Math.random() * 2) + 1;
			switch (first){
			case 1: // B is the first and B kills C
				countB++; 
				break;
			case 2: // C is the first, C try to kill B
				cChance = Math.random(); // C fire
				if (cChance < pC){// C kills B					
					countC++;
				}
				else
					//C does not kill B, B kills C
					countB++; 
				break;			
			}
		}
		double probB = (double)countB/num;
		double probC = (double)countC/num;		
		System.out.println("probB = " + probB + ",  probC = " + probC); 
		System.out.println("summa = " + (probB+probC)); 
	}

	public static void main(String[] args) {
		int num = 5000000;
		duel2d(num);
		duel3d(num);
	}
}
