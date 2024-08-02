package programaJogoDaVelha;

import java.util.Scanner;

public class ProgramaJogoDaVelha {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println("     # JOGO DA VELHA #     \n");
		
		String nome;
		System.out.println("Digite o nome do jogador 1: ");
		nome = sc.nextLine();
		
		Jogador j1 = new Jogador(nome, 'X', 0);
		
		System.out.println("\nDigite o nome do jogador 2: ");
		nome = sc.nextLine();
		Jogador j2 = new Jogador(nome, 'O', 0);
		
		String jogar = "1";
		
		while (jogar.equals("0") || jogar.equals("1")) {
			if (jogar.equals("1")) {
				tabuleiro.partida(tabuleiro, j1, j2);
			}
			
			System.out.println("\nDeseja jogar novamente? 1 - Continuar | 2 - Parar");
			jogar = sc.nextLine();

			if (!jogar.equals("1")  && !jogar.equals("2")) {
				System.out.println("\nOpção inválida!");
				jogar = "0";
			}
		}
		
		System.out.println("\nEncerrando...");
		
		

		sc.close();
	}
}
