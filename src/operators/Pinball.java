package operators;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class that contains all of the application logic
 * 
 * @author jjimemon
 *
 */

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

	/**
	 * Method that creates the player object
	 * 
	 * @param name
	 * @return Pinball player1 object
	 */
	public static Pinball playerCreate(String name) {

		Pinball player1;

		// condicional que realiza su contenido si no establece ningún nombre.
		if (name.equals("")) {
			name = "Sujeto de prueba 1";
			player1 = new Pinball(name);

		} else {

			player1 = new Pinball(name);

		}
		return player1;

	}

	/** Method that starts the game */
	public void startMatch() {
		// Creación del objeto Scanner.
		Scanner sc = new Scanner(System.in);
		// Declaración de variables del método.
		int playerDecision;
		boolean exitGameForced = Boolean.FALSE;
		boolean bonusActive = Boolean.FALSE;

		// llamamiento al método decisionControl, guarda en la variable playerDecision lo que devuelve dicho método.
		playerDecision = decisionControl(sc);

		// llamamiento al método launchControl.
		launchControl(sc, playerDecision, exitGameForced, bonusActive);

		sc.close();
		// muestra la puntuacion final del jugador
		System.out.println(this.showPunctuation());
		
		// muestra la tirada mayor del jugador
		System.out.println("La tirada de mayor puntuacion ha sido de: " + this.highestRoll(listLaunch));
	}

	private int decisionControl(Scanner sc) {
		int playerDecision;
		do {

			// menu
			System.out.println("Empieza el juego!!");
			System.out.println("-------------------Menu-------------------");
			chain.append("Escriba 1 si desea lanzar la bola ");
			chain.append("\n");
			chain.append("Escriba 2 si desea terminar el juego");
			System.out.println(chain);
			chain.delete(0, chain.length());

			// condicional que realiza su contenido cuando el usuario no introduce un entero.
			if (!sc.hasNextInt()) {
				System.out.println("¿Qué pasa que no sabes que es un número ENTERO?");
				System.out.print("Ve a esta dirección y aprende que es un entero anda:");
				System.out.println(" https://es.wikipedia.org/wiki/Número_entero");
				System.out.println("¿O Quizás me has intentado joder la app? pues ahora no juegas listillo!.");
				playerDecision = 2;

			} else {
				playerDecision = sc.nextInt();

			}

		} while (playerDecision != 1 && playerDecision != 2); // condicion de realización
		return playerDecision;
	}

	/**
	 * Method that controls the launch of the ball.
	 * 
	 * @param sc
	 * @param playerDecision
	 * @param exitGameForced
	 * @param bonusActive
	 */
	private void launchControl(Scanner sc, int playerDecision, boolean exitGameForced, boolean bonusActive) {
		int randomBreak;
		do {
			if (playerDecision == 1) {
				System.out.println("Bola lanzada!!");
				// probabilidad de que la bola se pierda.
				randomBreak = (int) Math.floor(Math.random() * 10 + 1);
				if (randomBreak == 1) {
					System.out.println("Mierda, la bola se ha perdido!!!");
					exitGameForced = Boolean.TRUE;
				} else {
					// Condicional que controla si el bonus está activo, si lo está realiza su contenido.
					bonusActive = bonusReviewer(bonusActive);
					System.out.println(chain);
					// Limpia el contenido de la cadena.
					chain.delete(0, chain.length());
					chain.append("Vuelva a escribir 1 si desea lanzar la bola ").append("\n").append("Escriba 2 si desea terminar el juego");
					System.out.println(chain);
					chain.delete(0, chain.length());
					playerDecision = sc.nextInt();
				}

			}
		} while (playerDecision == 1 && !exitGameForced); // condicion a cumplir.
	}

	/**
	 * Method that checks whether or not the bonus is active.
	 * 
	 * @param bonusActive
	 * @return bonusActive
	 */
	private boolean bonusReviewer(boolean bonusActive) {
		int lastLaunch;
		if (bonusActive) {
			lastLaunch = this.launchBall();
			// redimension del array.
			listLaunch = Arrays.copyOf(listLaunch, listLaunch.length + 1);
			listLaunch[listLaunch.length - 1] = lastLaunch * 2;
			addPunctuation(lastLaunch);
			bonusActive = Boolean.FALSE;
			chain.append("Ha caido en el ").append(lastLaunch).append(" con el bonos x2 se convierte en un ").append(lastLaunch * 2);
			// Condicional que controla si la tirada ha sido de 50, si es así realiza su contenido.
			if (lastLaunch == HOLLOWF) {
				System.out.println("Bono x2 en la siguiente tirada!!");
				bonusActive = Boolean.TRUE;
			}

		} else {
			lastLaunch = this.launchBall();
			// redimension del array.
			listLaunch = Arrays.copyOf(listLaunch, listLaunch.length + 1);
			listLaunch[listLaunch.length - 1] = lastLaunch;
			chain.append("Ha caido en el ").append(lastLaunch);
			// Condicion que revisa si el último lanzamiento ha sido de 50, si es así realiza su contenido.
			if (lastLaunch == HOLLOWF) {
				System.out.println("Bonus obtenido : x2 en la siguiente tirada.");
				bonusActive = Boolean.TRUE;
			}
		}
		return bonusActive;
	}

	/**
	 * Method that will allow you to toss the bowl
	 * 
	 * @return a whole number
	 */
	private int launchBall() {

		return addPunctuation(randomSelection());

	}

	/**
	 * method that selects a random hole for each spin
	 * 
	 * @return result
	 */
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

	/**
	 * Method that adds punctuation to the user.
	 * 
	 * @param amount
	 * @return amount
	 */
	private int addPunctuation(int amount) {

		punctuation += amount;

		return amount;

	}

	/**
	 * Method that shows the current score of the user.
	 * 
	 * @return chain2
	 */
	private StringBuilder showPunctuation() {

		StringBuilder chain2 = new StringBuilder();

		chain2.append("El jugador ").append(this.getPlayerName()).append(" ha sacado una puntuación de: ").append(getPunctuation());

		return chain2;

	}

	/**
	 * Method that returns the highest ranking roll.
	 * 
	 * @param array
	 * @return highResul
	 */
	private int highestRoll(int[] array) {
		int highResul = 0;

		// Mismo recorrido pero con un for.

		// for (int i = 0; i < array.length; i++) {
		//
		// highResul=(listLaunch[i] > highResul)?highResul=listLaunch[i]:highResul;
		//
		// }

		for (int number : array) {

			// operador ternario que compara el valor de la posicion actual del array con la variable highResul.
			highResul = (number > highResul) ? highResul = number : highResul;
		}

		return highResul;

	}

}
