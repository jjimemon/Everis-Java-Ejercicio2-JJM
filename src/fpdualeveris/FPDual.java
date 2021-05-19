package fpdualeveris;

import operators.Pinball;

/**
 * Dual-Ejercicio1-Java.
 * 
 * @author jjimemon
 *
 */

public class FPDual {

	/**
	 * Main Class
	 * 
	 * @param
	 */

	public static void main(String[] args) {

		// Llamamiento al método estático operatorsChallenge.
		operatorsChallenge();

	}

	/**
	 * Method that initializes the application.
	 */

	private static void operatorsChallenge() {

		// entre los "" puedes poner el nombre del jugador.
		Pinball.playerCreate("").startMatch();

	}
}
