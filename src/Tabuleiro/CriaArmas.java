package Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriaArmas extends JToolBar
{
	static int numSub = 0;
	static int numCruz = 0;
	static int numEnc = 0;
	static int numHid = 0;
	static int numPor = 0;

	private static boolean buttonSubPressed = false;
	private static boolean buttonCruzPressed = false;
	private static boolean buttonEncPressed = false;
	private static boolean buttonHidPressed = false;
	private static boolean buttonPorPressed = false;
	
	static private JButton botao_submarino;
	static private JButton botao_cruzador;
	static private JButton botao_encouracado;
	static private JButton botao_hidroaviao;
	static private JButton botao_portaaviao;
	
	static int numRotate = 1;
	static private JButton botao_rotate;
	

	public CriaArmas()
	{
		//Criando a toolbar
		this.setOrientation(JToolBar.VERTICAL);
		this.setFloatable(false);
		this.setVisible(true);

		//Inicializando botão 'inserir submarino'
		this.add(Box.createVerticalStrut(49));
		botao_submarino = new JButton("Adicionar Submarino", new ImageIcon("img\\submarino.gif"));
		botao_submarino.setMargin(new Insets(9, 10, 9, 10));
		botao_submarino.setHorizontalTextPosition(JButton.CENTER);
		botao_submarino.setVerticalTextPosition(JButton.BOTTOM);
		this.add(botao_submarino);
		this.add(Box.createVerticalStrut(5));

		//Inicializando botão 'inserir cruzador'
		botao_cruzador = new JButton("Adicionar Cruzador", new ImageIcon("img\\cruzador.gif"));
		botao_cruzador.setMargin(new Insets(7, 15, 7, 15));
		botao_cruzador.setHorizontalTextPosition(JButton.CENTER);
		botao_cruzador.setVerticalTextPosition(JButton.BOTTOM);
		this.add(botao_cruzador);
		this.add(Box.createVerticalStrut(5));

		//Inicializando botão 'inserir encouraçado'
		botao_encouracado = new JButton("Adicionar Encouraçado", new ImageIcon("img\\encouracado.gif"));
		botao_encouracado.setMargin(new Insets(7, 4, 7, 3));
		botao_encouracado.setHorizontalTextPosition(JButton.CENTER);
		botao_encouracado.setVerticalTextPosition(JButton.BOTTOM);
		botao_encouracado.setIconTextGap(5);
		this.add(botao_encouracado);
		this.add(Box.createVerticalStrut(5));

		//Inicializando botão 'inserir hidroavião'
		botao_hidroaviao = new JButton("Adicionar Hidroavião", new ImageIcon("img\\hidroaviao.gif"));
		botao_hidroaviao.setMargin(new Insets(8, 11, 8, 11));
		botao_hidroaviao.setHorizontalTextPosition(JButton.CENTER);
		botao_hidroaviao.setVerticalTextPosition(JButton.BOTTOM);
		this.add(botao_hidroaviao);
		this.add(Box.createVerticalStrut(5));
		
		//Inicializando botão 'inserir porta-avião'
		botao_portaaviao = new JButton("Adicionar Porta-avião", new ImageIcon("img\\portaaviao.gif"));
		botao_portaaviao.setMargin(new Insets(15, 8, 15, 9));
		botao_portaaviao.setHorizontalTextPosition(JButton.CENTER);
		botao_portaaviao.setVerticalTextPosition(JButton.BOTTOM);
		this.add(botao_portaaviao);
		this.add(Box.createVerticalStrut(40));
		
		//Inicializando botão 'rotate'
		botao_rotate = new JButton("Direção de inserção", new ImageIcon("img\\downarrow.gif"));
		botao_rotate.setMargin(new Insets(12, 12, 12, 12));
		botao_rotate.setHorizontalTextPosition(JButton.CENTER);
		botao_rotate.setVerticalTextPosition(JButton.BOTTOM);
		this.add(botao_rotate);
		this.addSeparator();
		
		//Tratando inserção de submarino
		botao_submarino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numSub==3 && checkButton()==0)
				{
					botao_submarino.setEnabled(false);
					buttonSubPressed = true;
				}
				else if(checkButton()==0)
				{
					buttonSubPressed = true;
				}
			}
		}); 
		//Tratando inserção de cruzador
		botao_cruzador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numCruz==2 && checkButton()==0)
				{
					botao_cruzador.setEnabled(false);
					buttonCruzPressed = true;
				}
				else if(checkButton()==0)
				{
					buttonCruzPressed = true;
				}
			}
		}); 
		//Tratando inserção de encouracado
		botao_encouracado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numEnc==1 && checkButton()==0)
				{
					botao_encouracado.setEnabled(false);
					buttonEncPressed = true;
				}
				else if(checkButton()==0)
				{
					buttonEncPressed = true;
				}
			}
		}); 
		//Tratando inserção de hidroaviao
		botao_hidroaviao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numHid==4 && checkButton()==0)
				{
					botao_hidroaviao.setEnabled(false);
					buttonHidPressed = true;
				}
				else if(checkButton()==0)
				{
					buttonHidPressed = true;
				}
			}
		});
		//Tratando inserção de porta-aviões
		botao_portaaviao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numPor==0 && checkButton()==0)
				{
					botao_portaaviao.setEnabled(false);
					buttonPorPressed = true;
				}
				else if(checkButton()==0)
				{
					buttonPorPressed = true;
				}
			}
		}); 
		//Tratando rotação
		botao_rotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numRotate == 1)
				{
					botao_rotate.setIcon(new ImageIcon("img\\leftarrow.gif"));
					CriaCampos.mudaDirecao();
					numRotate++;
				}
				else if(numRotate == 2)
				{
					botao_rotate.setIcon(new ImageIcon("img\\uparrow.gif"));
					CriaCampos.mudaDirecao();
					numRotate++;
				}
				else if(numRotate == 3)
				{
					botao_rotate.setIcon(new ImageIcon("img\\rightarrow.gif"));
					CriaCampos.mudaDirecao();
					numRotate++;
				}
				else if(numRotate == 4)
				{
					botao_rotate.setIcon(new ImageIcon("img\\downarrow.gif"));
					CriaCampos.mudaDirecao();
					numRotate = 1;
				}
			}
		}); 

	}
	
	public static int checkButton()
	{
		if(buttonSubPressed == true)
			return 1;
		if(buttonCruzPressed == true)
			return 2;
		if(buttonEncPressed == true)
			return 3;
		if(buttonHidPressed == true)
			return 4;
		if(buttonPorPressed == true)
			return 5;
		return 0;
	}
	
	public static boolean checkAllButtons()
	{
		if(numSub == 4 && numCruz == 3 && numEnc == 2 && numHid == 5 && numPor == 1)
		{
			botao_rotate.setEnabled(false);
			return false;
		}
		else return true;
	}
	
	public static void setinvisibleAllButtons()
	{
		botao_submarino.setVisible(false);
		botao_cruzador.setVisible(false);
		botao_encouracado.setVisible(false);
		botao_hidroaviao.setVisible(false);
		botao_portaaviao.setVisible(false);
		botao_rotate.setVisible(false);
	}
	
	public static void setvisibleAllButtons()
	{
		botao_submarino.setVisible(true);
		botao_cruzador.setVisible(true);
		botao_encouracado.setVisible(true);
		botao_hidroaviao.setVisible(true);
		botao_portaaviao.setVisible(true);
		botao_rotate.setVisible(true);
	}
	
	public static void restartAllButtons()
	{
		botao_submarino.setEnabled(true);
		botao_cruzador.setEnabled(true);
		botao_encouracado.setEnabled(true);
		botao_hidroaviao.setEnabled(true);
		botao_portaaviao.setEnabled(true);
		numSub = 0;
		numCruz = 0;
		numEnc = 0;
		numHid = 0;
		numPor = 0;
		
		numRotate = 1;
		CriaCampos.resetaDirecao();
		botao_rotate.setIcon(new ImageIcon("img\\downarrow.gif"));
		botao_rotate.setEnabled(true);
	}
	
	public static void unpressButton()
	{
		if(buttonSubPressed == true)
			buttonSubPressed = false;
		if(buttonCruzPressed == true)
			buttonCruzPressed = false;
		if(buttonEncPressed == true)
			buttonEncPressed = false;
		if(buttonHidPressed == true)
			buttonHidPressed = false;
		if(buttonPorPressed == true)
			buttonPorPressed = false;
	}
	
	public static void buttonPressTrue(int arma)
	{
		if(arma==1) numSub++;
		if(arma==2) numCruz++;
		if(arma==3) numEnc++;
		if(arma==4) numHid++;
		if(arma==5) numPor++;
	}

}