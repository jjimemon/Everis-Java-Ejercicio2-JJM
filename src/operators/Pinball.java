package operators;

import java.util.Arrays;
import java.util.Scanner;

public class Pinball extends Valores {

	/** Variable declarations */
	private String playerName;
	private static int punctuation = 0;
	private int listLaunch[] = new int[0];

	/** Create object StringBuilder */
	StringBuilder chain = new StringBuilder();

	/** Constructor method that creates the player by receiving a name. */
	private Pinball(String playerName) {
		super();
		this.playerName = playerName;
	}

	/** Getters and Setters */

	private String getPlayerName() {
		return playerName;
	}

	private static int getPunctuation() {
		return punctuation;
	}

	/** Method that creates the player object */
	public static Pinball playerCreate(String name) {

		Pinball jugador1;

		if (name.equals("")) {
			name = "Sujeto de prueba 1";
			jugador1 = new Pinball(name);

		} else {

			jugador1 = new Pinball(name);

		}
		return jugador1;

	}

	/** Method that starts the game and contains most of the game logic */
	public void startMatch() {

		Scanner sc = new Scanner(System.in);

		int playerDecision;
		int randomBreak = 0;
		boolean exitGameForced = Boolean.FALSE;
		boolean bonusActive = Boolean.FALSE;
		int lastLaunch;

		do {

			//menu
			System.out.println("Empieza el juego!!");
			System.out.println("-------------------Menu-------------------");
			chain.append("Escriba 1 si desea lanzar la bola ");
			chain.append("\n");
			chain.append("Escriba 2 si desea terminar el juego");
			System.out.println(chain);
			chain.delete(0, chain.length());
			playerDecision = sc.nextInt();

		} while (playerDecision != 1 && playerDecision != 2);

		// bucle que se encarga de controlar los lanzamientos
		do {
			if (playerDecision == 1) {
				System.out.println("Bola lanzada!!");
				// probabilidad de que la bola se pierda.
				randomBreak = (int) Math.floor(Math.random() * 10 + 1);
				if (randomBreak == 1) {
					System.out.println("Mierda, la bola se ha perdido!!!");
					exitGameForced = Boolean.TRUE;
				} else {
					if (bonusActive) {
						lastLaunch = this.launchBall();
						//redimension del array.
						listLaunch = Arrays.copyOf(listLaunch, listLaunch.length + 1);
						listLaunch[listLaunch.length - 1] = lastLaunch * 2;
						addPunctuation(lastLaunch);
						bonusActive = Boolean.FALSE;
						chain.append("Ha caido en el ").append(lastLaunch).append(" con el bonos x2 se convierte en un ").append(lastLaunch*2);
						if (lastLaunch == 50) {
							System.out.println("Bono x2 en la siguiente tirada!!");
							bonusActive = Boolean.TRUE;
						}

					} else {
						lastLaunch = this.launchBall();
						//redimension del array.
						listLaunch = Arrays.copyOf(listLaunch, listLaunch.length + 1);
						listLaunch[listLaunch.length - 1] = lastLaunch;
						chain.append("Ha caido en el ").append(lastLaunch);
						if (lastLaunch == HOLLOWF) {
							System.out.println("Bonus obtenido : x2 en la siguiente tirada.");
							bonusActive = Boolean.TRUE;
						}
					}
					System.out.println(chain);
					// Limpia el contenido de la cadena.
					chain.delete(0, chain.length());
					chain.append("Vuelva a escribir 1 si desea lanzar la bola ").append("\n").append("Escriba 2 si desea terminar el juego");
					System.out.println(chain);
					chain.delete(0, chain.length());
					playerDecision = sc.nextInt();
				}

			}
		} while (playerDecision == 1 && exitGameForced == false);

		sc.close();
		//muestra la puntuacion final del jugador
		System.out.println(this.showPunctuation());
		//muestra la tirada mayor del jugador
		System.out.println("La tirada de mayor puntuacion ha sido de: "+this.highestRoll(listLaunch));
	}

	/** Method that will allow you to toss the bowl */
	private int launchBall() {

		return addPunctuation(randomSelection());

	}

	/** method that selects a random hole for each spin */
	private int randomSelection() {

		int numberRandom = (int) Math.floor(Math.random() * 7 + 1);
		int result = 0;

		switch (numberRandom) {
		case 1:
			result = HOLLOW1;
			break;
		case 2:
			result = HOLLOW2;
			break;
		case 3:
			result = HOLLOW3;
			break;
		case 4:
			result = HOLLOW4;
			break;
		case 5:
			result = HOLLOW5;
			break;
		case 6:
			result = HOLLOWSF;
			break;
		case 7:
			result = HOLLOWF;
			break;
		}

		return result;

	}

	/** Method that adds punctuation to the user. */
	private int addPunctuation(int amount) {

		punctuation += amount;

		return amount;

	}

	/** Method that shows the current score of the user. */
	private StringBuilder showPunctuation() {

		StringBuilder chain2 = new StringBuilder();

		chain2.append("El jugador ").append(this.getPlayerName()).append(" ha sacado una puntuación de: ").append(getPunctuation());

		return chain2;

	}

	/** method that returns the highest ranking roll. */
	private int highestRoll(int[] array) {
		int resulAlto = 0;
		
		
		//Mismo recorrido pero con un for.
		
//		for (int i = 0; i < array.length; i++) {
//
//			//operador ternario que compara el valor de la posicion actual del array con la variable resulAlto.
//			resulAlto=(listLaunch[i] > resulAlto)?resulAlto=listLaunch[i]:resulAlto;
//
//		}
		
		for(int number : array) {
			
			resulAlto=(number> resulAlto)?resulAlto=number:resulAlto;
		}

		return resulAlto;

	}
	

}
