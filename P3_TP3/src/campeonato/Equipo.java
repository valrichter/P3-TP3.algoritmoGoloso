package campeonato;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Equipo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nombre;
    private Map<Integer, Integer> arbitros; // Map<arbitro, cant. veces que lo dirigio>

    // Constructor
    public Equipo(String nombre)
    {
        this.nombre = nombre;
        this.arbitros = new HashMap<Integer, Integer>();
    }
    
    // Obtiene el nombre
    public String getNombre()
    {
		return this.nombre;
	}

    // Obtiene todos los arbitrajes
    public Map<Integer, Integer> getArbitraje()
    {
		return this.arbitros;
	}
    
    // Returna cuantas veces lo dirigio un arbitro en particular
	public Integer vecesQueLoDirigioUnArbitro(Integer arbitro)
    {
		if(arbitros.get(arbitro) == null)
		{
			return 0;
		}
		else
		{
			return arbitros.get(arbitro);
		}
    }
	
	// Obtiene el arbitro que menos veces lo dirigio
	public Integer arbitroMejor()
	{
		Integer arbitroMinimo = null;
		if(arbitros.size() == 0)
		{
			return null;
		}
		
		for (Integer arbitro : arbitros.keySet())
		{
			// Es '<=' para que tome el ultimo de todos si dos o mas arbitros tienen las mismas cant de arbitraje
			if (arbitroMinimo == null || arbitros.get(arbitro) <= arbitros.get(arbitroMinimo))
			{
				arbitroMinimo = arbitro;
			}
		}
		return arbitroMinimo;
	}
	
	
	public Integer arbitroPeor()
	{
		Integer arbitroMaximo = null;
		if(arbitros.size() == 0)
		{
			return null;
		}
		
		for (Integer arbitro : arbitros.keySet())
		{
			if (arbitroMaximo == null || arbitros.get(arbitro) >= arbitros.get(arbitroMaximo))
			{
				arbitroMaximo = arbitro;
			}
		}
		return arbitroMaximo;
	}
	
	
	public void setArbitrosDelCampeonato(Integer cantArbitros)
	{	
		Integer cont = 1;
		while(cont <= cantArbitros) 
		{
			this.arbitros.put(cont, 0);
			cont++;
		}
	}
	
	// Agrega el arbitro que lo dirigio a 'arbitros'
	public void addArbitroQueLoDirigio(Integer arbitro)
	{
		if(arbitros.get(arbitro) == null)
		{		
    		throw new IllegalArgumentException("El arbitro no existe: " + arbitro + " : " + "null");
		}
		Integer cantAnterior = this.arbitros.get(arbitro);
		arbitros.put(arbitro, cantAnterior + 1);
	}
	
	
	public void restarArbitroQueLoDirigio(Integer arbitro)
	{
		Integer cantAnterior = this.arbitros.get(arbitro);
		arbitros.put(arbitro, cantAnterior - 1);
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(nombre);
		builder.append(", Arbs: ");
		builder.append(arbitros);
		builder.append("\n");
		return builder.toString();
	}
}
