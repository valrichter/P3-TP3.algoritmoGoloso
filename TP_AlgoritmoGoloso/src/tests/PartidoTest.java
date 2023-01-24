package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import campeonato.Equipo;
import campeonato.Partido;

public class PartidoTest
{
	@Test
	public void asignarNingunArbitroAEquipo()
	{
		Partido p = partido();
		assertEquals(p.getArbitro(), null);
	}
	
	@Test
	public void asignarArbitro()
	{
		Partido p = partido();
		Integer r = 1;
		
		p.setArbitro(1);
		assertEquals(p.getArbitro(), r);
	}

	@Test(expected = IllegalArgumentException.class)
	public void asignarMismoArbitro()
	{
		Partido p = partido();
		
		p.setArbitro(1);
		p.setArbitro(1);
	}
	
	@Test
	public void ambosEquiposConMismoArbitro()
	{
		Partido p = partido();
		p.setArbitro(1);
		Integer r = p.getArbitro();
		
		assertTrue(p.getEquipo1().arbitroPeor().equals(r));
		assertTrue(p.getEquipo2().arbitroPeor().equals(r));
	}
	
	private Partido partido()
	{
		Equipo e1 = new Equipo("Boca");
		Equipo e2 = new Equipo("River");
		e1.setArbitrosDelCampeonato(2);
		e2.setArbitrosDelCampeonato(2);
		Partido p = new Partido(e1, e2);
		return p;
	}
}
