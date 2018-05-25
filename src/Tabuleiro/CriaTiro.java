package Tabuleiro;

import javax.swing.*;

public class CriaTiro extends JLabel {

	public CriaTiro(int X, int Y)
	{
		super();
		
		if(CriaTabuleiro.isShotBullseye(X, Y)==true)
		{
			this.setText("Tiro no alvo!");
			this.setIcon(new ImageIcon("img\\shot_bang.jpg"));
		}
		else
		{
			this.setText("Tiro na água...");
			this.setIcon(new ImageIcon("img\\shot_splash.jpg")); 
		}
	}
}
