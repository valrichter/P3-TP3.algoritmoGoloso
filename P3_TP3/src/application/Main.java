package application;

import java.awt.EventQueue;

import model.Model;
import view.View;

public class Main
{

	public static void main(String[] args)
	{
		Model.generarSolucion();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					View window = new View(Model.getCalendario().toString());
					window.frame.setVisible(true);
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
