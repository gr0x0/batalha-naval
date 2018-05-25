package Tabuleiro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  

public class CriaTabuleiro extends JFrame
{
	static private CriaCampos tabuleiro1 = new CriaCampos();
	static private CriaCampos tabuleiro2 = new CriaCampos();
	public final int largura_armastab = 125;
	public final int LARGURA = 1125 + largura_armastab;
	public final int ALTURA = 700;
	private static boolean showTabuleiro1 = true;
	private static boolean showTabuleiro2 = false;
	private static boolean showEntreJogadas = false;	
	private static boolean gameBegins = true;
	private static boolean turnEnd = false;
	private static int p1Squares = 0;
	private static int p2Squares = 0;
	
	static private final JButton botao = new JButton("Armas Posicionadas");
	
	public CriaTabuleiro()
	{
		/* CRIANDO O FRAME */
		//Definindo limites de acordo com a resolução original
		int sl = 1366;
		int sa = 768;
		//Posicionando frame no centro da tela
		final int x = sl/2 - LARGURA/2;
		final int y = sa/2 - ALTURA/2;
		setBounds(x, y, LARGURA, ALTURA);
		//Definindo forma de fechamento
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		//Retirando bordas e decorando janela
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

		/* CRIANDO A TOOLBAR DE ARMAS */
		// Inicializando a barra e adicionais
		CriaArmas armastoolbar = new CriaArmas();
		//Adicionando ao frame
		this.getContentPane().add(armastoolbar,BorderLayout.EAST);
		armastoolbar.setVisible(true);
				
		/* CRIANDO PAINÉIS DE TABULEIROS */
		tabuleiro1.setName("- JOGADOR 1 -");
		tabuleiro2.setName("- JOGADOR 2 -");
		add(tabuleiro2);
		add(tabuleiro1);
		
		//Criando painel 'entre-jogadas'
		final JPanel entrejogadas = new JPanel();	

		/* CRIANDO BOTÃO DE FINALIZAR JOGADA */ 
		//Inicializando botão
		botao.setMargin(new Insets(26, 30, 26, 30));
		JPanel LocalBotao = new JPanel( new FlowLayout(FlowLayout.CENTER) );
		LocalBotao.add(botao);
		botao.setVisible(true);
		add(LocalBotao, BorderLayout.SOUTH);
		botao.setEnabled(false);
		//Criando evento do botão
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameBegins == true)
				{
					if(showTabuleiro1 && !showTabuleiro2 && !showEntreJogadas)
					{//Saindo do tabuleiro 1 e entrando no modo espera
						add(entrejogadas);
						entrejogadas.setVisible(true);
						remove(tabuleiro1);
						tabuleiro1.setVisible(false);
						showEntreJogadas = true;
						showTabuleiro1 = false;
						showTabuleiro2 = true;
						botao.setText("Posicionar Armas do Jogador 2");
						CriaArmas.setinvisibleAllButtons();
						System.out.printf("Posicionamento de armas finalizado: waiting for player 2\n");
					}
					else if(!showTabuleiro1 && showTabuleiro2 && showEntreJogadas)
					{//Saindo do modo espera e entrando no tabuleiro 2
						add(tabuleiro2);
						tabuleiro2.setVisible(true);
						remove(entrejogadas);
						entrejogadas.setVisible(false);
						showEntreJogadas = false;
						botao.setText("Armas Posicionadas");
						CriaArmas.restartAllButtons();
						CriaArmas.setvisibleAllButtons();
						disableBotao();
						System.out.printf("Player 1 switch to player 2\n");
					}
					else if(!showTabuleiro1 && showTabuleiro2 && !showEntreJogadas && CriaArmas.checkAllButtons()==false)
					{//Saindo do tabuleiro 2 e entrando no modo espera
						add(entrejogadas);
						entrejogadas.setVisible(true);
						remove(tabuleiro2);
						tabuleiro2.setVisible(false);
						showEntreJogadas = true;
						showTabuleiro1 = true;
						showTabuleiro2 = false;
						botao.setText("Turno do Jogador 1");
						CriaArmas.setinvisibleAllButtons();
						gameBegins = false;
						System.out.printf("Posicionamento de armas finalizado: waiting for player 1\n");
					}
				}
				else
				{
					if(showTabuleiro1 && !showTabuleiro2 && !showEntreJogadas)
					{//Saindo do tabuleiro 1 e entrando no modo espera
						add(entrejogadas);
						entrejogadas.setVisible(true);
						remove(tabuleiro1);
						tabuleiro1.setVisible(false);
						showEntreJogadas = true;
						showTabuleiro1 = false;
						showTabuleiro2 = true;
						botao.setText("Turno do Jogador 2");
						CriaArmas.setinvisibleAllButtons();
						System.out.printf("Jogada finalizada: waiting for player 2\n");
					}
					else if(!showTabuleiro1 && showTabuleiro2 && showEntreJogadas)
					{//Saindo do modo espera e entrando no tabuleiro 2
						add(tabuleiro2);
						tabuleiro2.setVisible(true);
						remove(entrejogadas);
						entrejogadas.setVisible(false);
						showEntreJogadas = false;
						botao.setText("Finalizar Jogada");
						CriaArmas.setvisibleAllButtons();
						beginTurn();
						disableBotao(); 
						System.out.printf("Player 1 switch to player 2\n");
					}
					else if(!showTabuleiro1 && showTabuleiro2 && !showEntreJogadas)
					{//Saindo do tabuleiro 2 e entrando no modo espera
						add(entrejogadas);
						entrejogadas.setVisible(true);
						remove(tabuleiro2);
						tabuleiro2.setVisible(false);
						showEntreJogadas = true;
						showTabuleiro1 = true;
						showTabuleiro2 = false;
						botao.setText("Turno do Jogador 1");
						CriaArmas.setinvisibleAllButtons();
						System.out.printf("Jogada finalizada: waiting for player 1\n");
					}
					else if(showTabuleiro1 && !showTabuleiro2 && showEntreJogadas)
					{//Saindo do modo de espera e entrando no tabuleiro 1
						add(tabuleiro1);
						tabuleiro1.setVisible(true);
						remove(entrejogadas);
						entrejogadas.setVisible(false);
						showEntreJogadas = false;
						botao.setText("Finalizar Jogada");
						CriaArmas.setvisibleAllButtons();
						beginTurn();
						disableBotao(); 
						System.out.printf("Player 2 switch to player 1\n");
					}
				}				
				invalidate();
				validate();

			}
		});      

	}
	
	static public void enableBotao()
	{
		botao.setEnabled(true);
	}
	
	static public void disableBotao()
	{
		botao.setEnabled(false);
	}
	
	static public boolean hasPrepEnded()
	{
		if(gameBegins == false)
			return true;
		else return false;
	}
	
	static public boolean hasTurnEnded()
	{
		return turnEnd;
	}
	
	static private void beginTurn()
	{
		turnEnd = false;
	}
	
	static public void endTurn()
	{
		turnEnd = true;
	}

	static public void marcarTiroRecebido(int X, int Y)
	{
		//Se for jogador 1 enviando info
		if(showTabuleiro1 == true)
		{
			tabuleiro2.recebeTiro(X,Y);
		}
		//Se for jogador 2 enviando info
		else
		{
			tabuleiro1.recebeTiro(X,Y);
		}
	}
	
	static public boolean isShotBullseye(int X, int Y)
	{
		//Se for jogador 1 pedindo info
		if(showTabuleiro1 == true)
		{
			return tabuleiro2.isOcupada(X,Y);
		}
		//Se for jogador 2 pedindo info
		else
		{
			return tabuleiro1.isOcupada(X,Y);
		}
	}
	
	static public void labelTiro(int X, int Y)
	{
		CriaTiro label = new CriaTiro(X, Y);
		JFrame labelFrame = new JFrame("Resultado do tiro");
		labelFrame.setUndecorated(true);
		labelFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		labelFrame.setContentPane(label);
		labelFrame.pack();
		labelFrame.setVisible(true);
		labelFrame.setBounds(560, 250, 200, 100);
		labelFrame.setResizable(false);
		labelFrame.toFront();
		labelFrame.setAlwaysOnTop(true);
	}

	static public void checaSquares()
	{
		//Se for jogador 2 que recebeu o tiro
		if(showTabuleiro1 == true)
		{
			p2Squares++;
			if(p2Squares==38)
			{
				JFrame endFrame = new JFrame("Fim de jogo");
				JOptionPane.showMessageDialog(endFrame, "Fim de jogo: Jogador 1 vence!");
			}
		}
		//Se for jogador 1 que recebeu o tiro
		else
		{
			p1Squares++;
			if(p1Squares==38)
			{
				JFrame endFrame = new JFrame("Fim de jogo");
				JOptionPane.showMessageDialog(endFrame, "Fim de jogo: Jogador 2 vence!");
			}
		}
	}
	public static void main(String[] args)
	{
		CriaTabuleiro f1 = new CriaTabuleiro();
		f1.setVisible(true);	
	}

}
