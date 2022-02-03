package model;

import java.util.ArrayList;

import campeonato.Calendario;
import campeonato.Equipo;
import campeonato.Fecha;
import campeonato.Partido;

public class Model
{
	private static Solucion solucion = new Solucion();
	private static Calendario calendario = new Calendario();
	
	public static void generarSolucion()
	{	
		/* Generamos un calendario.json para simular una instancia */
		Calendario calendarioGenerado = new Calendario();
		calendarioGenerado.setFechas(generarCalendario());
		setCalendario(calendarioGenerado);
		String calendarioJson = calendarioGenerado.generarJSONPretty();
		calendarioGenerado.crearJSONfile(calendarioJson, "Calendario.JSON");
		
		/* Instancia de datos */
		Calendario calendario = Calendario.leerJSON("Calendario.JSON");
		Integer arbitros = 3;
		Instancia instancia = new Instancia(calendario, arbitros);
		Solver solver = new Solver(instancia);
		Solucion solucion = solver.resolver();
		setSolucion(solucion);	
		
		/* Imprime la solucion por consola */
//		System.out.println(calendario);
//		System.out.println(solucion);
//		System.out.println("PROMEDIO PEOR ARBITRO: " + solver.promedioPeorArbitro());
		
		/* Genera un JSON con la solucion */
//		String calendarioArbitrosJson = solver.getInstancia().getCalendario().generarJSONPretty();
//		calendario.crearJSONfile(calendarioArbitrosJson, "CalendarioConArbitros.json");
//		System.out.println("PROMEDIO PEOR ARBITRO: " + solver.promedioPeorArbitro());
	}
	
	public static Solucion getSolucion()
	{
		return solucion;
	}

	private static void setSolucion(Solucion s)
	{
		solucion = s;
	}

	public static Calendario getCalendario()
	{
		return calendario;
	}

	public static void setCalendario(Calendario c)
	{
		calendario = c;
	}

	private static ArrayList<Partido> partidos()
	{
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		
		Equipo riv = new Equipo("River");
		Equipo boc = new Equipo("Boca");
		Equipo ind = new Equipo("Independiente");
		Equipo rac = new Equipo("Racing");
		Equipo slo = new Equipo("San_Lorenzo");
		Equipo hur = new Equipo("Huracan");
	
		//Fecha 1
		Partido p1 = new Partido(riv, boc);
		Partido p2 = new Partido(ind, rac);
		Partido p3 = new Partido(slo, hur);
		
		//Fecha 2
		Partido p4 = new Partido(riv, ind);
		Partido p5 = new Partido(slo, boc);
		Partido p6 = new Partido(hur, rac);
		
		//Fecha 3
		Partido p7 = new Partido(riv, slo);
		Partido p8 = new Partido(hur, ind);
		Partido p9 = new Partido(rac, boc);
		
		//Fecha 4
		Partido p10 = new Partido(riv, hur);
		Partido p11 = new Partido(rac, slo);
		Partido p12 = new Partido(boc, ind);
		
		//Fecha 5
		Partido p13 = new Partido(riv, rac);
		Partido p14 = new Partido(boc, hur);
		Partido p15 = new Partido(ind, slo);
		
		//Fecha 1
		partidos.add(p1);
		partidos.add(p3);
		partidos.add(p2);
		
		//Fecha 2
		partidos.add(p6);
		partidos.add(p5);
		partidos.add(p4);
		
		//Fecha 3
		partidos.add(p9);
		partidos.add(p7);
		partidos.add(p8);
		
		//Fecha 4
		partidos.add(p12);
		partidos.add(p11);
		partidos.add(p10);
		
		//Fecha 5
		partidos.add(p14);
		partidos.add(p13);		
		partidos.add(p15);
		
		return partidos;
	}
	
	private static ArrayList<Fecha> fechas(int cantfechas)
	{		
		ArrayList<Fecha> fechas = new ArrayList<Fecha>();
		
		for (int i = 1; i <= cantfechas; i++) 
		{
			Fecha f = new Fecha(i);
			fechas.add(f);
		}
		return fechas;
	}
	
	private static ArrayList<Fecha> generarCalendario()
	{	
		ArrayList<Fecha> fechas = fechas(5);
		ArrayList<Partido> partidos = partidos();
		int partidosXfecha = 3;
		ArrayList<Fecha> calendario = new ArrayList<Fecha>();
		
		int nrofecha = 0;
		for (int i = 0; i < partidos.size(); i++) 
		{
			Fecha f = fechas.get(nrofecha);
			Partido p = partidos.get(i);
			f.addPartido(p);
			
			if(esMultiplo(i+1, partidosXfecha)) 
			{
				calendario.add(f);
				nrofecha++;
			}
		}
		return calendario;
	}
	
	private static boolean esMultiplo(int n1,int n2)
	{
		if (n1%n2==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
