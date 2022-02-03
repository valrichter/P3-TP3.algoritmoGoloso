package tests;

import static org.junit.Assert.*;
import java.util.HashSet;

import org.junit.Test;

import campeonato.Equipo;
import campeonato.Fecha;
import campeonato.Partido;

public class FechaTest
{
	@Test(expected = IllegalArgumentException.class)
	public void fechaCero()
	{
		@SuppressWarnings("unused")
		Fecha f = new Fecha(0);
	}
	
	@Test
	public void fechaCorrecta()
	{
		Fecha f = new Fecha(3);
		Integer r = 3;
		
		assertEquals(f.getNumeroFecha(), r);
	}

	@Test
	public void fechaIncorrecta()
	{
		Fecha f = new Fecha(3);
		Integer r = 2;
		
		assertFalse(f.getNumeroFecha().equals(r));
	}
	
	@Test
	public void partidosVacio()
	{
		Fecha f = new Fecha(3);
		HashSet<Partido> r = new HashSet<Partido>();
		
		assertEquals(f.getPartidos(), r);
	}
	
	@Test
	public void partidosCorrecto()
	{
		Fecha f = new Fecha(3);
		Partido p = partido();
		f.addPartido(p);
		HashSet<Partido> r = new HashSet<Partido>();
		r.add(p);
		
		assertEquals(f.getPartidos(), r);
		assertEquals(f.cantPartidos(), 1);
	}
	
	@Test
	public void partidoYaExiste()
	{
		Fecha f = new Fecha(3);
		Partido p1 = partido();
		f.addPartido(p1);
		
		Equipo e1 = new Equipo("Boca");
		Equipo e2 = new Equipo("River");
		Partido p2 = new Partido(e1, e2);

		assertTrue(f.existePartido(p2));
	}
	
	private Partido partido()
	{
		Equipo e1 = new Equipo("Boca");
		Equipo e2 = new Equipo("River");
		Partido p = new Partido(e1, e2);
		return p;
	}
}
