package model;

import java.util.ArrayList;

import campeonato.Fecha;

public class Solucion
{
    private ArrayList<Fecha> calendarioConArbitros;
    
    public Solucion()
    {
        this.calendarioConArbitros = new ArrayList<Fecha>();
    } 
    
    public ArrayList<Fecha> getCalendarioConArbitros()
    {
    	ArrayList<Fecha> r = this.calendarioConArbitros;
		return r;
	}

    public void addFecha(Fecha fecha)
    {
        this.calendarioConArbitros.add(fecha);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Solucion: Calendario con arbitros\n");
		builder.append("---------------CALENDARIO---------------\n");
		builder.append(calendarioConArbitros);
		builder.append("\n");
		return builder.toString();
	}
    
}
