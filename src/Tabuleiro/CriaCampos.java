package Tabuleiro;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class CriaCampos extends JPanel
{
	//Matriz das armas do jogador
	private boolean[][] coordenadas = new boolean[15][15];
	//Matrizes de tiros do jogador
	private boolean[][] tirosEnviados = new boolean[15][15];
	private boolean[][] tirosRecebidos = new boolean[15][15];

	int coordX = 0;
	int coordY = 0;
	static int direcaoArma = 1;

	/* BLOCO DE INICIALIZAÇÃO DE MATRIZES */
	{
		int lin, col;
		for(lin=0;lin<15;lin++)
		{
			for(col=0;col<15;col++)
			{
				coordenadas[lin][col]=false;
				tirosEnviados[lin][col]=false;
				tirosRecebidos[lin][col]=false;
			}
		}
	}

	//Coordenadas de construção dos tabuleiros
	final double leftX = 35.0;
	final double topY = 50.0;
	final double larg = 500.0;
	final double alt = 500.0;
	final double leftX2 = leftX + 550;

	//Variável de controle de inicialização
	private static boolean controle = false;

	public void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		String caracter = new String();

		/* NOMEIA  */
		g.drawString(this.getName(), (int)(larg + leftX + 40), (int)(alt + topY + 25));

		/* DESENHA CAMPO 'MINHAS FORÇAS' */
		//Desenha retângulo
		g2d.setColor(Color.BLACK);
		Rectangle2D rt=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.draw(rt);		
		//Desenha linhas verticais
		final double larglinha = larg/15;
		for(double i = larglinha; i<larg; i+=larglinha)
		{
			Point2D p1 = new Point2D.Double(leftX + i, topY);
			Point2D p2 = new Point2D.Double(leftX + i, alt + topY);
			g2d.draw(new Line2D.Double(p1,p2));
		}
		//Desenha linhas horizontais
		final double altlinha = alt/15;
		for(double i = 0.0; i<larg; i+=altlinha)
		{
			Point2D p1 = new Point2D.Double(leftX, topY + i);
			Point2D p2 = new Point2D.Double(leftX + larg, topY + i);
			g2d.draw(new Line2D.Double(p1,p2));
		}	

		/* DESENHA CAMPO 'FORÇAS INIMIGAS' */
		//Desenha retângulo
		g2d.setColor(Color.BLACK);
		Rectangle2D rt2 = new Rectangle2D.Double(leftX2,topY,larg,alt);
		g2d.draw(rt2);		
		//Desenha linhas verticais
		for(double i = larglinha; i<larg; i+=larglinha)
		{
			Point2D p1 = new Point2D.Double(leftX2 + i, topY);
			Point2D p2 = new Point2D.Double(leftX2 + i, alt + topY);
			g2d.draw(new Line2D.Double(p1,p2));
		}
		//Desenha linhas horizontais
		for(double i = 0.0; i<larg; i+=altlinha)
		{
			Point2D p1 = new Point2D.Double(leftX2, topY + i);
			Point2D p2 = new Point2D.Double(leftX2 + larg, topY + i);
			g2d.draw(new Line2D.Double(p1,p2));
		}

		/* DESENHA CARACTERES 'MINHAS FORÇAS' */
		//Desenha título
		caracter = "- MINHAS FORÇAS -";
		g.drawString(caracter, (int)(larg/2 - leftX), 20);

		//Desenha os caracteres horizontais
		caracter = "    1    "+"     2    "+"     3    "+"     4    "+"     5    "+"    6    "
		+"     7    "+"     8    "+"    9    "+"    10   "+"   11   "+"    12   "+"    13   "+
		"   14    "+"  15    ";
		g.drawString(caracter, (int)(leftX), (int)(topY-5));

		//Desenha os caracteres verticais
		double distcarac = (alt/15);
		caracter = "A"+"B"+"C"+"D"+"E"+"F"+"G"+"H"+"I"+"J"+"L"+"M"+"N"+"O"+"P";
		String caracteraux = new String();
		int j=0;
		for(double i = 70.0; i<alt+70; i+=distcarac)
		{
			caracteraux = caracter.substring(j, j+1);			
			g.drawString(caracteraux, (int)leftX-15, (int)i);
			j++;
		}

		/* DESENHA CARACTERES 'FORÇAS INIMIGAS' */
		//Desenha título
		caracter = "- FORÇAS INIMIGAS -";
		g.drawString(caracter, (int)(3*larg/2 + leftX), 20);
		//Desenha os caracteres horizontais
		caracter = "    1    "+"     2    "+"     3    "+"     4    "+"     5    "+"    6    "
		+"     7    "+"     8    "+"    9    "+"    10   "+"   11   "+"    12   "+"    13   "+
		"   14    "+"  15    ";
		g.drawString(caracter, (int)(2.5*leftX + larg), (int)(topY-5));

		//Desenha os caracteres verticais
		caracter = "A"+"B"+"C"+"D"+"E"+"F"+"G"+"H"+"I"+"J"+"L"+"M"+"N"+"O"+"P";
		j=0;
		for(double i = 70.0; i<alt+70; i+=distcarac)
		{
			caracteraux = caracter.substring(j, j+1);			
			g.drawString(caracteraux, (int)(2.5*leftX-15+larg), (int)i);
			j++;
		}

		/* DESENHA ARMAS JÁ POSICIONADAS E TIROS RECEBIDOS */
		int lin,col;
		for(lin=0; lin<15; lin++)
		{
			for(col=0; col<15; col++)
			{
				//Pinta arma posicionada
				if(coordenadas[lin][col]==true && tirosRecebidos[lin][col]==false)
				{
					Rectangle2D arma = new Rectangle2D.Double(leftX+((lin)*(larg/15)),topY+((col)*(alt/15)),larg/15,alt/15);
					g2d.setPaint(Color.BLACK);
					g2d.fill(arma);
				}
				//Pinta arma posicionada e atingida
				else if(coordenadas[lin][col]==true && tirosRecebidos[lin][col]==true)
				{
					Rectangle2D tiroEmArma = new Rectangle2D.Double(leftX+((lin)*(larg/15)),topY+((col)*(alt/15)),larg/15,alt/15);
					g2d.setPaint(Color.RED);
					g2d.fill(tiroEmArma);
				}
				//Pinta tiro na água
				else if(coordenadas[lin][col]==false && tirosRecebidos[lin][col]==true)
				{
					Rectangle2D tiroEmAgua = new Rectangle2D.Double(leftX+((lin)*(larg/15)),topY+((col)*(alt/15)),larg/15,alt/15);
					g2d.setPaint(Color.BLUE);
					g2d.fill(tiroEmAgua);
				}	
			}
		}
		
		/* DESENHA TIROS ENVIADOS */
		for(lin=0; lin<15; lin++)
		{
			for(col=0; col<15; col++)
			{
				//Pinta tiro bem sucedido
				if(CriaTabuleiro.isShotBullseye(lin, col)==true && tirosEnviados[lin][col]==true)
				{
					Rectangle2D tiroarma = new Rectangle2D.Double(leftX2+((lin)*(larg/15)),topY+((col)*(alt/15)),larg/15,alt/15);
					g2d.setPaint(Color.RED);
					g2d.fill(tiroarma);
				}
				//Pinta tiro mal sucedido
				else if(CriaTabuleiro.isShotBullseye(lin, col)==false && tirosEnviados[lin][col]==true)
				{
					Rectangle2D tiroagua = new Rectangle2D.Double(leftX2+((lin)*(larg/15)),topY+((col)*(alt/15)),larg/15,alt/15);
					g2d.setPaint(Color.BLUE);
					g2d.fill(tiroagua);
				}				
			}
		}	

		/* EVENTOS ONCLICK */
		final JPopupMenu popup = new JPopupMenu();	

		MouseListener ClickEvent = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				coordX = e.getX();
				coordY = e.getY();
				int X = 0, Y = 0;

				//Tratamentos de click
				if(SwingUtilities.isLeftMouseButton(e)){
										
					//Tratamento de click no campo 'Minhas Forças'
					if((coordX>=leftX&&coordX<=leftX+larg)&&(coordY>=topY&&coordY<=topY+alt))
					{
						
						//Reconhecimento da coordenada x do campo Minhas Forças
						X = calculateCoordX(coordX, leftX, larglinha);
						
						//Reconhecimento da coordenada y do campo Minhas Forças
						Y = calculateCoordY(coordY, topY, altlinha);

						//Inserção de arma se botão de inserção estiver pressionado
						int pressedButton = ifPressedButton();
						if(pressedButton!=0)
						{
							insereArma(pressedButton, X, Y);
							if(CriaArmas.checkAllButtons()==false)
								CriaTabuleiro.enableBotao();
							repaint();
						}

					}
					
					//Tratamento de click no campo 'Forças Inimigas'
					else if((coordX>=leftX2&&coordX<=leftX2+larg)&&(coordY>=topY&&coordY<=topY+alt))
					{
						//Reconhecimento da coordenada x do campo Forças Inimigas
						X = calculateCoordX(coordX, leftX2, larglinha);
						
						//Reconhecimento da coordenada y do campo Forças Inimigas
						Y = calculateCoordY(coordY, topY, altlinha);			
						
						//Realização da jogada se jogo tiver começado
						if(CriaTabuleiro.hasPrepEnded()==true && CriaTabuleiro.hasTurnEnded()==false)
						{
							//Tratando tiro em casa ainda sem tiro
							if(tirosEnviados[X][Y]==false)
							{
								tirosEnviados[X][Y] = true;
								CriaTabuleiro.marcarTiroRecebido(X, Y);
								if(CriaTabuleiro.isShotBullseye(X, Y)==true)
									CriaTabuleiro.checaSquares();
								CriaTabuleiro.endTurn();
								repaint();
								CriaTabuleiro.enableBotao();
								CriaTabuleiro.labelTiro(X,Y);
							}
						}
					}

					popup.show(e.getComponent(), coordX, coordY);
				}
				
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		addMouseListener(ClickEvent);							

	}
	
	private int calculateCoordX(int coordX, double leftX, double larglinha)
	{		
		if(coordX>leftX&&coordX<leftX+larglinha) 					return 0;
		if(coordX>leftX+larglinha&&coordX<leftX+2*larglinha)		return 1;
		if(coordX>leftX+2*larglinha&&coordX<leftX+3*larglinha)		return 2;
		if(coordX>leftX+3*larglinha&&coordX<leftX+4*larglinha)		return 3;
		if(coordX>leftX+4*larglinha&&coordX<leftX+5*larglinha)		return 4;
		if(coordX>leftX+5*larglinha&&coordX<leftX+6*larglinha)		return 5;
		if(coordX>leftX+6*larglinha&&coordX<leftX+7*larglinha)		return 6;
		if(coordX>leftX+7*larglinha&&coordX<leftX+8*larglinha)		return 7;
		if(coordX>leftX+8*larglinha&&coordX<leftX+9*larglinha)		return 8;
		if(coordX>leftX+9*larglinha&&coordX<leftX+10*larglinha)		return 9;
		if(coordX>leftX+10*larglinha&&coordX<leftX+11*larglinha)	return 10;
		if(coordX>leftX+11*larglinha&&coordX<leftX+12*larglinha)	return 11;
		if(coordX>leftX+12*larglinha&&coordX<leftX+13*larglinha)	return 12;
		if(coordX>leftX+13*larglinha&&coordX<leftX+14*larglinha)	return 13;
		if(coordX>leftX+14*larglinha&&coordX<leftX+15*larglinha)	return 14;
		return 15;
	}
	
	private int calculateCoordY(int coordY, double topY, double altlinha)
	{
		if(coordY>topY&&coordY<topY+altlinha) 						return 0;
		if(coordY>topY+altlinha&&coordY<topY+2*altlinha)			return 1;
		if(coordY>topY+2*altlinha&&coordY<topY+3*altlinha)			return 2;
		if(coordY>topY+3*altlinha&&coordY<topY+4*altlinha)			return 3;
		if(coordY>topY+4*altlinha&&coordY<topY+5*altlinha)			return 4;
		if(coordY>topY+5*altlinha&&coordY<topY+6*altlinha)			return 5;
		if(coordY>topY+6*altlinha&&coordY<topY+7*altlinha)			return 6;
		if(coordY>topY+7*altlinha&&coordY<topY+8*altlinha)			return 7;
		if(coordY>topY+8*altlinha&&coordY<topY+9*altlinha)			return 8;
		if(coordY>topY+9*altlinha&&coordY<topY+10*altlinha)			return 9;
		if(coordY>topY+10*altlinha&&coordY<topY+11*altlinha)		return 10;
		if(coordY>topY+11*altlinha&&coordY<topY+12*altlinha)		return 11;
		if(coordY>topY+12*altlinha&&coordY<topY+13*altlinha)		return 12;
		if(coordY>topY+13*altlinha&&coordY<topY+14*altlinha)		return 13;
		if(coordY>topY+14*altlinha&&coordY<topY+15*altlinha)		return 14;
		return 15;
	}

	private int ifPressedButton()
	{
		return CriaArmas.checkButton();
	}

	public static void mudaDirecao()
	{
		direcaoArma++;
		if(direcaoArma==5)
			direcaoArma = 1;					
	}
	
	public static void resetaDirecao()
	{
		direcaoArma = 1;
	}

	private void insereArma(int arma, int X, int Y)
	{
		boolean casaProibida = false;

		if(arma == 1)
		{			
			//Checa se a casa está ocupada
			casaProibida = isOcupada(X,Y);

			//Checa se há outra arma adjacente
			casaProibida = hasAdjacente(X,Y);

			//Marca a casa se não for proibida
			if(casaProibida==false)
			{
				coordenadas[X][Y]=true;
				CriaArmas.buttonPressTrue(arma);
				CriaArmas.unpressButton();
			}

		}
		if(arma == 2)
		{
			int X2 = 0, Y2 = 0;
			//Verifica direção de inserção da arma
			//1~down, 2~left, 3~upper, 4~right
			if(direcaoArma == 1)
			{
				Y2 = Y+1;
				X2 = X;
			}
			if(direcaoArma == 2)
			{
				Y2 = Y;
				X2 = X-1;
			}
			if(direcaoArma == 3)
			{
				Y2 = Y-1;
				X2 = X;
			}
			if(direcaoArma == 4)
			{
				Y2 = Y;
				X2 = X+1;
			}

			//Verifica se pode introduzir
			if((direcaoArma==1 && Y!=14)||
					(direcaoArma==2 && X!=0)||
					(direcaoArma==3 && Y!=0)||
					(direcaoArma==4 && X!=14))
			{

				//Checa se as casas estão ocupadas
				if(isOcupada(X,Y)==false)
					casaProibida = isOcupada(X2,Y2);
				else
					casaProibida = true;

				//Checa se há outra arma adjacente
				if(casaProibida != true)
				{
					if(hasAdjacente(X,Y)==false)
						casaProibida = hasAdjacente(X2,Y2);
					else
						casaProibida = true;
				}
			}
			else casaProibida = true;


			//Marca a casa se não for proibida
			if(casaProibida==false)
			{
				coordenadas[X][Y]=true;
				coordenadas[X2][Y2]=true;
				CriaArmas.buttonPressTrue(arma);
				CriaArmas.unpressButton();
			}

		}
		if(arma == 3)
		{
			int X2 = 0, Y2 = 0;
			int X3 = 0, Y3 = 0;
			int X4 = 0, Y4 = 0;
			//Verifica direção de inserção da arma
			//1~down, 2~left, 3~upper, 4~right
			if(direcaoArma == 1)
			{
				X4 = X3 = X2 = X;
				Y2 = Y+1;
				Y3 = Y+2;
				Y4 = Y+3;
			}
			if(direcaoArma == 2)
			{
				Y4 = Y3 = Y2 = Y;
				X2 = X-1;
				X3 = X-2;
				X4 = X-3;
			}
			if(direcaoArma == 3)
			{
				X4 = X3 = X2 = X;
				Y2 = Y-1;
				Y3 = Y-2;
				Y4 = Y-3;
			}
			if(direcaoArma == 4)
			{
				Y4 = Y3 = Y2 = Y;
				X2 = X+1;
				X3 = X+2;
				X4 = X+3;
			}

			//Verifica se pode introduzir
			if((direcaoArma==1 && Y!=14 && Y!=13 && Y!=12)||
					(direcaoArma==2 && X!=0 && X!=1 && X!=2)||
					(direcaoArma==3 && Y!=0 && Y!=1 && Y!=2)||
					(direcaoArma==4 && X!=14 && X!=13 && X!=12))
			{

				//Checa se as casas estão ocupadas
				if(isOcupada(X,Y)==false)
				{
					if(isOcupada(X2,Y2)==false)
					{
						if(isOcupada(X3,Y3)==false)
						{
							casaProibida = isOcupada(X4,Y4);
						}
						else casaProibida = true;
					}
					else casaProibida = true;
				}
				else casaProibida = true;

				//Checa se há outra arma adjacente
				if(casaProibida != true)
				{
					if(hasAdjacente(X,Y)==false)
					{
						if(hasAdjacente(X2,Y2)==false)
						{
							if(hasAdjacente(X3,Y3)==false)
							{
								casaProibida = hasAdjacente(X4,Y4);
							}
							else casaProibida = true;
						}
						else casaProibida = true;
					}
					else casaProibida = true;
				}
			}

			else casaProibida = true;

			//Marca a casa se não for proibida
			if(casaProibida==false)
			{
				coordenadas[X][Y]=true;
				coordenadas[X2][Y2]=true;
				coordenadas[X3][Y3]=true;
				coordenadas[X4][Y4]=true;
				CriaArmas.buttonPressTrue(arma);
				CriaArmas.unpressButton();
			}
		}
		if(arma == 4)
		{
			int X2 = 0, Y2 = 0;
			int X3 = 0, Y3 = 0;
			//Verifica direção de inserção da arma
			//1~down, 2~left, 3~upper, 4~right
			if(direcaoArma == 1)
			{
				Y2 = Y+1;
				X2 = X+1;
				Y3 = Y+1;
				X3 = X-1;
			}
			if(direcaoArma == 2)
			{
				X2 = X-1;
				Y2 = Y+1;
				X3 = X-1;
				Y3 = Y-1;
			}
			if(direcaoArma == 3)
			{
				Y2 = Y-1;
				X2 = X-1;
				Y3 = Y-1;
				X3 = X+1;
			}
			if(direcaoArma == 4)
			{
				X2 = X+1;
				Y2 = Y-1;
				X3 = X+1;
				Y3 = Y+1;
			}

			//Verifica se pode introduzir
			if((direcaoArma==1 && Y!=14 && X!=0 && X!=14)||
					(direcaoArma==2 && X!=0 && Y!=0 && Y!=14)||
					(direcaoArma==3 && Y!=0 && X!=0 && X!=14)||
					(direcaoArma==4 && X!=14 && Y!=0 && Y!=14))
			{

				//Checa se as casas estão ocupadas
				if(isOcupada(X,Y)==false)
				{
					if(isOcupada(X2,Y2)==false)
					{
						casaProibida = isOcupada(X3,Y3);
					}
					else casaProibida = true;
				}
				else casaProibida = true;

				//Checa se há outra arma adjacente
				if(casaProibida != true)
				{
					if(hasAdjacente(X,Y)==false)
					{
						if(hasAdjacente(X2,Y2)==false)
						{
							casaProibida = hasAdjacente(X3,Y3);
						}
						else casaProibida = true;
					}
					else casaProibida = true;
				}
			}

			else casaProibida = true;

			//Marca a casa se não for proibida
			if(casaProibida==false)
			{
				coordenadas[X][Y]=true;
				coordenadas[X2][Y2]=true;
				coordenadas[X3][Y3]=true;
				CriaArmas.buttonPressTrue(arma);
				CriaArmas.unpressButton();
			}
		}

		if(arma == 5)
		{			
			{
				int X2 = 0, Y2 = 0;
				int X3 = 0, Y3 = 0;
				int X4 = 0, Y4 = 0;
				int X5 = 0, Y5 = 0;
				//Verifica direção de inserção da arma
				//1~down, 2~left, 3~upper, 4~right
				if(direcaoArma == 1)
				{
					X5 = X4 = X3 = X2 = X;
					Y2 = Y+1;
					Y3 = Y+2;
					Y4 = Y+3;
					Y5 = Y+4;
				}
				if(direcaoArma == 2)
				{
					Y5 = Y4 = Y3 = Y2 = Y;
					X2 = X-1;
					X3 = X-2;
					X4 = X-3;
					X5 = X-4;
				}
				if(direcaoArma == 3)
				{
					X5 = X4 = X3 = X2 = X;
					Y2 = Y-1;
					Y3 = Y-2;
					Y4 = Y-3;
					Y5 = Y-4;
				}
				if(direcaoArma == 4)
				{
					Y5 = Y4 = Y3 = Y2 = Y;
					X2 = X+1;
					X3 = X+2;
					X4 = X+3;
					X5 = X+4;
				}

				//Verifica se pode introduzir
				if((direcaoArma==1 && Y!=14 && Y!=13 && Y!=12 && Y!=11)||
						(direcaoArma==2 && X!=0 && X!=1 && X!=2 && X!=3)||
						(direcaoArma==3 && Y!=0 && Y!=1 && Y!=2 && Y!=3)||
						(direcaoArma==4 && X!=14 && X!=13 && X!=12 && X!=11))
				{

					//Checa se as casas estão ocupadas
					if(isOcupada(X,Y)==false)
					{
						if(isOcupada(X2,Y2)==false)
						{
							if(isOcupada(X3,Y3)==false)
							{
								if(isOcupada(X4, Y4)==false)
								{
									casaProibida = isOcupada(X5,Y5);
								}
								else casaProibida = true;
							}
							else casaProibida = true;
						}
						else casaProibida = true;
					}
					else casaProibida = true;

					//Checa se há outra arma adjacente
					if(casaProibida != true)
					{
						if(hasAdjacente(X,Y)==false)
						{
							if(hasAdjacente(X2,Y2)==false)
							{
								if(hasAdjacente(X3,Y3)==false)
								{
									if(hasAdjacente(X4, Y4)==false)
									{
										casaProibida = hasAdjacente(X5,Y5);
									}
									else casaProibida = true;
								}
								else casaProibida = true;
							}
							else casaProibida = true;
						}
						else casaProibida = true;
					}
				}
				else casaProibida = true;

				//Marca a casa se não for proibida
				if(casaProibida==false)
				{
					coordenadas[X][Y]=true;
					coordenadas[X2][Y2]=true;
					coordenadas[X3][Y3]=true;
					coordenadas[X4][Y4]=true;
					coordenadas[X5][Y5]=true;
					CriaArmas.buttonPressTrue(arma);
					CriaArmas.unpressButton();
				}
			}

		}
	}

	public boolean isOcupada(int X, int Y)
	{
		return coordenadas[X][Y];		
	}

	private boolean hasAdjacente(int X, int Y)
	{
		if(X!=14&&X!=0&&Y!=14&&Y!=0)
		{
			if(coordenadas[X+1][Y]==true||coordenadas[X][Y+1]==true||coordenadas[X-1][Y]==true||coordenadas[X][Y-1]==true)
				return true;
		}
		else
		{
			if(X==14)
			{
				System.out.printf("Entrei aqui!\n");
				if(Y==14)
				{
					if(coordenadas[X-1][Y]==true||coordenadas[X][Y-1]==true)
					{
						return true;
					}
				}
				else if(Y==0)
				{
					if(coordenadas[X-1][Y]==true||coordenadas[X][Y+1]==true)
					{
						return true;
					}
				}
				else
				{
					if(coordenadas[X][Y+1]==true||coordenadas[X-1][Y]==true||coordenadas[X][Y-1]==true)
						return true;
				}

			}
			else if(X==0)
			{
				if(Y==0)
				{
					if(coordenadas[X+1][Y]==true||coordenadas[X][Y+1]==true)
						return true;
				}
				else if(Y==14)
				{
					if(coordenadas[X+1][Y]==true||coordenadas[X][Y-1]==true)
						return true;
				}
				else
				{
					if(coordenadas[X][Y-1]==true||coordenadas[X+1][Y]==true||coordenadas[X][Y+1]==true)
						return true;
				}
			}
			else if(Y==14)
			{
				if(coordenadas[X+1][Y]==true||coordenadas[X-1][Y]==true||coordenadas[X][Y-1]==true)
					return true;
			}
			else if(Y==0&&(X!=0&&X!=14))
			{
				if(coordenadas[X+1][Y]==true||coordenadas[X-1][Y]==true||coordenadas[X][Y+1]==true)
					return true;
			}
		}

		return false;
	}

	public void recebeTiro(int X, int Y)
	{
		this.tirosRecebidos[X][Y]=true;
	}

}



