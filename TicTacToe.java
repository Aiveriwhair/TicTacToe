package oop.games;

import java.io.IOException;

public class TicTacToe {
	private static char[][] grid = { { '-', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };
	private static char P1;
	private static char P2;

	public static void initGame(char first) {
		if (first != 'O' && first != 'X') {
			throw new IllegalArgumentException("The players must be either X or O");
		}
		
		resetGrid();
		
		// set players
		P1 = first;
		if (first == 'X') {
			P2 = 'O';
		} else
			P2 = 'X';
	}

	public static void playGame() throws IOException {

		char CurrentPlayer;
		int round = 1;

		while (true) {

			if (round > 9)
			{
				System.out.println("DRAW"); 
				break;
			}
			
			
			// Determiner quel joueur joue
			if (round % 2 == 1)
				CurrentPlayer = P1;
			else
				CurrentPlayer = P2;

			
			while(true)
			{
				// Demander les coordonnées auxquelles il veut jouer
				System.out.println("Player " + CurrentPlayer + ": row, cow?");
				
				// Récuperer les coordonnées saisies par l'utilisateur
				char[] Coords = Utils.readLine().replaceAll(" ", "").replaceAll(",", "").toCharArray();
				
				if (!Character.isDigit(Coords[0])||!Character.isDigit(Coords[1])) {
					continue;
				}
				
				int playedx = Character.getNumericValue(Coords[0]);
				int playedy = Character.getNumericValue(Coords[1]);
				
				// Vérifier les données saisis par l'utilisateur
				if ((playedx < 0)||(playedx > 2)) {
					continue;
				}
				else if ((playedy < 0)||(playedy > 2)) {
					continue;
				}
	
				// Mettre à jour la grille
				grid[playedx][playedy] = CurrentPlayer;
				
				// Continuer le jeu
				break;
			}
			

			
			// Afficher la grille
			PrintGrid();

			// Checker si il y a un gagnant, on sort de la boucle
			if (isWinner()) {
				System.out.println("Player " + CurrentPlayer + " won !");
				break;
			}
			// Incrémenter le nombre du round
			round++;
		}

		System.out.println();
		System.out.println();
	}

	private static boolean isWinner() {
		// Check les horizontales
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) 
			{
				if (grid[i][0] == 'X') {
					return true;
				}
				if (grid[i][0] == 'O') {
					return true;
				}
			}
		}

		// Check les verticales
		for (int j = 0; j < 3; j++) {
			if (grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
				if (grid[0][j] == 'X') {
					return true;
				}
				if (grid[0][j] == 'O') {
					return true;
				}
			}
		}

		// Check les diagonales
		if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
			if (grid[0][0] == 'X') {
				return true;
			}
			if (grid[0][0] == 'O') {
				return true;
			}
		}
		if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
			if (grid[1][1] == 'X') {
				return true;
			}
			if (grid[1][1] == 'O') {
				return true;
			}
		}
		
		return false;
	}

	private static void PrintGrid() {
		System.out.println("-----");
		for (int i = 0; i < 3; i++) {
			System.out.println("|" + grid[i][0] + grid[i][1] + grid[i][2] + "|");
		}
		System.out.println("-----");
	}
	
	private static void resetGrid()
	{
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = '-';
			}
		}
	}
}
