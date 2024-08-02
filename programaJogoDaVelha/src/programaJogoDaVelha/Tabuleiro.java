package programaJogoDaVelha;
import java.util.Scanner;

public class Tabuleiro {
	Scanner sc = new Scanner(System.in);
	
	public char[][] hashtag = new char[5][5];
	public boolean posicaoDisponivel;
	public char escolha;
	public String nome;
	public char simbolo;
	public int linha;
	public int coluna;
	public int xLinha;
	public int xColuna;
	public int xDiagonal1;
	public int xDiagonal2;
	public int oLinha;
	public int oColuna;
	public int oDiagonal1;
	public int oDiagonal2;
	public String vencedor = "none";
	
	//Criar tabuleiro
	public Tabuleiro() {	
		hashtag = new char[][] {
				{'a', '|', 'b', '|', 'c'},
				{'_', '_', '_', '_', '_'},
				{'d', '|', 'e', '|', 'f'},
				{'_', '_', '_', '_', '_'},
				{'g', '|', 'h', '|', 'i'}
		};
	}
	
	//Novo Tabuleiro
	public void novoTabuleiro() {
		hashtag = new char[][] {
			{'a', '|', 'b', '|', 'c'},
			{'_', '_', '_', '_', '_'},
			{'d', '|', 'e', '|', 'f'},
			{'_', '_', '_', '_', '_'},
			{'g', '|', 'h', '|', 'i'}
		};
	}
	
	//Mostrar tabuleiro
	public void exibirTabuleiro() {
		System.out.println();
		for (char row[] : hashtag) {
			for (char i : row) {
				System.out.print(i);
			}
			System.out.println();
		}

	}
	
	//Preecher posição e mostrar resultado
	public String partida(Tabuleiro tabuleiro, Jogador j1, Jogador j2) {
		
		tabuleiro.novoTabuleiro();
		for (int rodada = 1; rodada <= 9; rodada++) {
			System.out.println();
			tabuleiro.exibirTabuleiro();
			nome = (rodada % 2 != 0) ? j1.nome : j2.nome;
			System.out.printf("\n> Jogador(a): %s\nInsira a posição que deseja jogar: ", nome);
			escolha = sc.nextLine().charAt(0);
			posicaoDisponivel = posicaoDisponivel(escolha);
			if (posicaoDisponivel == true) {
				simbolo = (rodada % 2 != 0) ? j1.simbolo : j2.simbolo;
				hashtag[linha][coluna] = simbolo; 
			} else {
				System.out.println("Opção inválida!");
				rodada--;
			}
			vencedor = verificarJogo(rodada);
			
			if (!vencedor.equals("none")) {
				tabuleiro.exibirTabuleiro();
				String resultado = exibirResultado(vencedor, j1, j2);
				System.out.println(resultado);
				return vencedor;
			}
		}
		return vencedor;
	}
	
	//Conferindo posição está disponível
	public boolean posicaoDisponivel(char escolha) {
	    for (int i = 0; i < hashtag.length; i++) {
	        for (int j = 0 ; j < hashtag[i].length; j++) {
	            if (hashtag[i][j] == escolha) {
	                linha = i;
	                coluna = j;
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	//Verificar se ouve ganhador ou ocorreu empate
	public String verificarJogo(int rodada) {
	    for (int i = 0; i < hashtag.length; i++) {
	    	int xLinha = 0, oLinha = 0, xColuna = 0, oColuna = 0;
	    	int xDiagonal1 = 0, xDiagonal2 = 0, oDiagonal1 = 0, oDiagonal2 = 0;
	        
	        for (int j = 0; j < hashtag.length; j++) {
	        	if (hashtag[i][j] == 'X') {
	                xLinha++;
	        	}
	        	if (hashtag[i][j] == 'O') {
	                oLinha++;
	        	}
	        	if (hashtag[j][i] == 'X') {
	                xColuna++;
	        	}
	        	if (hashtag[j][i] == 'O') {
	                oColuna++;
	        	}
	        }
	        
	        if (xLinha == 3 || xColuna == 3) {
                return vencedor = "j1";
            }
	        if (oLinha == 3 || oColuna == 3) {
	        	return vencedor = "j2";
            }
	        
	        for (int d = 0; d < hashtag.length; d++) {
	        	if (hashtag[d][d] == 'X') {
	        		xDiagonal1++;
	        	} else if (hashtag[d][d] == 'O') {
	        		oDiagonal1++;
	        	}
	        	if (hashtag[d][hashtag.length -1 -d] == 'X') {
	        		xDiagonal2++;
	        	} else if (hashtag[d][hashtag.length -1 -d] == 'O') {
	        		oDiagonal2++;
	        	}
	        	if (xDiagonal1 == 3 || xDiagonal2 == 3) {
	        		return vencedor = "j1";
	        	}
	        	if (oDiagonal1 == 3 || oDiagonal2 == 3) {
	        		return vencedor = "j2";
	        	}
	        }
	    }
	    
	    
	    if (rodada == 9 && vencedor.equals("none")) {
	    	return vencedor = "Empate";
	    }
	    
	    return vencedor = "none";
	    
	}

	//Exibir resultado
	public String exibirResultado(String vencedor, Jogador j1, Jogador j2) {
		String resultado = vencedor;
		if (!vencedor.equals("Empate")) {
			if (vencedor.equals("j1")) {
				j1.pontos++;
				resultado = ("\n\n> " + j1.nome + " Venceu!");
			} else if (vencedor.equals("j2")) {
				j2.pontos++;
				resultado = "\n\n> " + j2.nome + " Venceu!";
			}
		} else {
			resultado = "\n\n> O jogo terminou em EMPATE!";
		}
		return resultado += "\n\n-> " + j1.nome + ": " + j1.pontos + " ponto(s)\n-> " + j2.nome + ": " + j2.pontos + " ponto(s)\n";
	}
	
}
