package campeonato;

import java.io.Serializable;
import java.util.Objects;

public class Partido implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Equipo equipo1;
    private Equipo equipo2;
    private Integer arbitro;
    
    // Constructor
    public Partido(Equipo equipo1, Equipo equipo2)
    {
    	if(equipo1.equals(equipo2))
    	{
    		throw new IllegalArgumentException("Los equipos no pueden ser iguales: " + equipo1 + ", " + equipo2);
    	}
    	this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }
    
    // Obtiene el equipo1
    public Equipo getEquipo1()
    {	
        return this.equipo1;
    }
    
    // Obtiene el equipo2
    public Equipo getEquipo2()
    {
        return this.equipo2;
    }
    
    // Obtiene el arbitro
    public Integer getArbitro()
    {
    	Integer r = this.arbitro;
		return r;
	}
    
    public void setEquipo1(Equipo e)
    {
    	this.equipo1 = e;
    }
    
    public void setEquipo2(Equipo e)
    {
    	this.equipo2 = e;
    }
    
    // Setea el arbitro que estara en el partido
    public void setArbitro(Integer arbitro)    
    {
    	if (this.arbitro == null)
    	{
    		this.arbitro = arbitro;
    		asignarArbitroAEquipos(arbitro);      
		}
    	else
    	{
    		throw new IllegalArgumentException("El partido ya tiene un arbitro asignado: " + this.arbitro + ", " + arbitro);
		}		
    }
    
    // Asigna el arbitro a los equipos que jugaran el partida
    private void asignarArbitroAEquipos(Integer arbitro)
    {
		equipo1.addArbitroQueLoDirigio(arbitro);
		equipo2.addArbitroQueLoDirigio(arbitro);
	}
    
    
    private void restarArbitroAEquipos(Integer arbitro)
    {
		equipo1.restarArbitroQueLoDirigio(arbitro);
		equipo2.restarArbitroQueLoDirigio(arbitro);
	}
    
   
    public void quitarArbitro(Integer arbitro)    
    {
    	if (this.arbitro != null)
    	{
    		this.arbitro = null;
    		restarArbitroAEquipos(arbitro);      
		}
    	else
    	{
    		throw new IllegalArgumentException("El arbitro no existe: " + this.arbitro + ", " + arbitro);
		}		
    }
    
    @Override
	public int hashCode()
    {
		return Objects.hash(equipo1, equipo2);
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
		Partido other = (Partido) obj;
		return Objects.equals(equipo1, other.equipo1) && Objects.equals(equipo2, other.equipo2) || 
				Objects.equals(equipo2, other.equipo1) && Objects.equals(equipo2, other.equipo1);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("'");
		builder.append(equipo1.getNombre());
		builder.append(" v ");
		builder.append(equipo2.getNombre());
		builder.append("'");
		builder.append(", Arb: ");
		builder.append(arbitro);
		builder.append("\n");
		return builder.toString();
	}
}
