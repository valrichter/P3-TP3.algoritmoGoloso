package model;

import campeonato.Calendario;

public class Instancia
{
    private Calendario calendario;
    private Integer cantArbitros;
    
    public Instancia(Calendario calendario, Integer arbitros)
    {
        this.calendario = calendario;
        this.cantArbitros = arbitros;
    }
    
    public Calendario getCalendario()
    {
        return this.calendario;
    }
    
    public Integer getArbitros()
    {
    	Integer r = this.cantArbitros;
        return r;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Instancia: \n");
		builder.append(calendario);
		builder.append("\nArbitros: ");
		builder.append(cantArbitros);
		return builder.toString();
	}
     
}
