package campeonato;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class Fecha implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer numeroFecha;
    private HashSet<Partido> partidos;
    
    // Constructor
    public Fecha(Integer fecha)
    {
    	if(fecha == 0) 
    	{
    		throw new IllegalArgumentException("La fecha debe ser mayor a 0: " + fecha);
    	}
        this.numeroFecha = fecha;
        this.partidos = new HashSet<Partido>();
    }
    
    // Obtiene el numeroFecha
    public Integer getNumeroFecha()
    {
        return this.numeroFecha;
    }
    
    // Obtiene los partidos que se juegan en la fecha
    public HashSet<Partido> getPartidos()
    {
        return this.partidos;
    }
    
    
    public Partido getPartidoEspecifico(int nropartido)
    {
    	int cont = 0;
    	for (Partido partido : partidos)
		{	
			if (cont == nropartido)
			{
				return partido;
			}
			cont++;
		}
    	throw new IllegalArgumentException("Solo se juegan " + this.cantPartidos());
    }
    
    // Obtiene la cant. de partidos que se juegan en la fecha
    public int cantPartidos()
    {
        return partidos.size();
    }
    
    // Obtiene la cant. de partidos que se juegan en la fecha
    public boolean existePartido(Partido partido)
    {
        return partidos.contains(partido);
    }
    
    // Agrega el partido que se juega en esa fecha
    public void addPartido(Partido partido)
    {
        this.partidos.add(partido);
    }
    
	@Override
	public int hashCode()
	{
		return Objects.hash(numeroFecha, partidos);
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
		Fecha other = (Fecha) obj;
		return Objects.equals(numeroFecha, other.numeroFecha) || Objects.equals(partidos, other.partidos);
	}
    
    @Override
	public String toString()
    {
		StringBuilder builder = new StringBuilder();
		builder.append("Fecha: ");
		builder.append(numeroFecha);
		builder.append("\n");
		builder.append(partidos);
		builder.append("\n");
		return builder.toString();
	}
    
}
