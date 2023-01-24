package tests;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;

import campeonato.Equipo;

public class EquipoTest
{
	@Test
	public void equiposIguales()
	{
		Equipo e1 = new Equipo("Boca");
		Equipo e2 = new Equipo("Boca");
		assertTrue(e1.equals(e2));
	}
	
	@Test
	public void equiposDistintos()
	{
		Equipo e1 = new Equipo("Boca Juniors");
		Equipo e2 = new Equipo("Boca");
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void arbitrajeCorrecto()
	{
		Equipo e1 = new Equipo("Boca");
		e1.setArbitrosDelCampeonato(4);
		e1.addArbitroQueLoDirigio(1);
		e1.addArbitroQueLoDirigio(2);
		e1.addArbitroQueLoDirigio(3);
		e1.addArbitroQueLoDirigio(3);
		e1.addArbitroQueLoDirigio(4);
		e1.addArbitroQueLoDirigio(4);

		HashMap<Integer, Integer> r = new HashMap<Integer, Integer>();
		r.put(1, 1);
		r.put(2, 1);
		r.put(3, 2);
		r.put(4, 2);

		assertEquals(e1.getArbitraje().equals(r), true);
	}
	
	@Test
	public void arbitrajeVacio()
	{
		Equipo e1 = new Equipo("Boca");
		HashMap<Integer, Integer> r = new HashMap<Integer, Integer>();

		assertEquals(e1.getArbitraje().equals(r), true);
	}
	
	@Test
	public void arbitroDirigioCeroVeces()
	{
		Equipo e1 = new Equipo("Boca");
		Integer r = 0;
		
		assertEquals(e1.vecesQueLoDirigioUnArbitro(1), r);
	}
	
	@Test
	public void menorArbitro()
	{
		Equipo e1 = new Equipo("Boca");
		e1.setArbitrosDelCampeonato(5);
		e1.addArbitroQueLoDirigio(1);
		e1.addArbitroQueLoDirigio(1);
		e1.addArbitroQueLoDirigio(1);
		e1.addArbitroQueLoDirigio(2);
		e1.addArbitroQueLoDirigio(2);
		e1.addArbitroQueLoDirigio(3);
		e1.addArbitroQueLoDirigio(4);
		e1.addArbitroQueLoDirigio(4);
		e1.addArbitroQueLoDirigio(5);
		
		Integer r = 5;
		System.out.println(e1.getArbitraje());
		assertEquals(e1.arbitroMejor(), r);
	}
	
	@Test
	public void menorArbitroCantidaIguales()
	{
		Equipo e1 = new Equipo("Boca");
		e1.setArbitrosDelCampeonato(4);
		e1.addArbitroQueLoDirigio(1);
		e1.addArbitroQueLoDirigio(2);
		e1.addArbitroQueLoDirigio(3);
		e1.addArbitroQueLoDirigio(4);
		
		Integer r = 4;
		assertEquals(e1.arbitroMejor(), r);
	}
}
