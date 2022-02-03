package model;

import java.util.ArrayList;
import java.util.HashSet;

import campeonato.Equipo;
import campeonato.Fecha;
import campeonato.Partido;

public class Solver
{
	private Instancia instancia;
	
	public Solver(Instancia instancia) 
	{
		this.instancia = instancia;
	}
	
	public Instancia getInstancia()
	{
		Instancia r = this.instancia;
		return r;
	}

	public Solucion resolver()
	{
		Solucion r = new Solucion();
		
		// 1. SETEO DE DATOS
		// Inizializamos para cada equipo los arbitros que habra en el torneo
		setearArbitrosALosEquipos0();
		
		// Creamos un conjunto de arbitros
		HashSet<Integer> arbitrosDisponibles = this.arbitrosConjunto();
		
		// Obtenemos las fechas del calendario
		ArrayList<Fecha> calendario = this.instancia.getCalendario().getCampeonatoFechas();
		
		// 2. ALGORITMO
		// Primero, recorremos todas las fechas
		for (Fecha fecha : calendario) 
		{
			// Obtenemos los partidos que se juegan en esa fecha
			HashSet<Partido> partidos = fecha.getPartidos();
			
			// Segundo, recorremos todos los partidos
			for(Partido partido : partidos)
			{					
				// Para cada partido seleccionamos el mejor arbitro hasta ese momento y lo asignamos al partido
				Integer arbitro = dameArbitro(partido, arbitrosDisponibles);
				partido.setArbitro(arbitro);
				
				// Como el arbitro ya fue asignado, lo sacamo de arbitrosDisponibles
				arbitrosDisponibles.remove(arbitro);
			}
			// Agregamos la fecha con arbitros a la solucion
			r.addFecha(fecha);

			// Volvemos a tener arbitrosDisponibles para la siguente fecha
			arbitrosDisponibles = this.arbitrosConjunto();
		}
		return r;
	}
	
	// Genera un conjunto de arbitros
	private HashSet<Integer> arbitrosConjunto()
	{
		HashSet<Integer> arbitrosConjunto = new HashSet<Integer>();
		Integer arbitros = this.instancia.getArbitros();
		
		for (Integer i=1; i<=arbitros; i++) 
		{
			arbitrosConjunto.add(i);
		}
		return arbitrosConjunto;
	}
	
	// Inserta todo los arbitros que habra en el torneo a los equipos
	private void setearArbitrosALosEquipos0()
	{	
		HashSet<Equipo> equipos = this.instancia.getCalendario().getEquipos();
		for (Equipo equipo : equipos)
		{
			equipo.setArbitrosDelCampeonato(this.instancia.getArbitros());
		}		
	}
	
	// Retorna el mejor arbitro
	private Integer dameArbitro(Partido partido, HashSet<Integer> arbitrosDisponibles)
	{
		// Generamos dos promedios
		double promedioActual = 0;
		double promedioAnterior = 9999999999.9;
		
		Integer mejorArbitro = 0;
		
		// Para cada arbitro disponible hasta el momento
		for(Integer arbitro : arbitrosDisponibles) 
		{	
			// Simulamos que se agrego el arbitro a ese partido
			partido.setArbitro(arbitro);
			
			// Calculamos el promedio que se genero al asignar ese arbitro
			promedioActual = promedioPeorArbitro();
			
			// Si el promedio actual es mejor que el promedio anterior
			if(promedioActual < promedioAnterior) 
			{
				mejorArbitro = arbitro;
				promedioAnterior = promedioActual;
				
				// Quitamos el arbitro simulado
				partido.quitarArbitro(arbitro);
			}
			else 
			{
				// Quitamos el arbitro simulado
				partido.quitarArbitro(arbitro);
			}
		}
		return mejorArbitro;	
	}
	
	// Calcula el promedio del peor arbitro
	public double promedioPeorArbitro()
	{	
		double r = 0;
		HashSet<Equipo> equipos = this.instancia.getCalendario().getEquipos();
		for (Equipo equipo : equipos) 
		{
			Integer arbitro = equipo.arbitroPeor();
			r = r + equipo.vecesQueLoDirigioUnArbitro(arbitro);
		}
		return r/this.instancia.getCalendario().getEquipos().size();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Solver [instancia=");
		builder.append(instancia);
		return builder.toString();
	}

}
