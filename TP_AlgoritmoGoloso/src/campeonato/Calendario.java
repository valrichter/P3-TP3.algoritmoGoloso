package campeonato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Calendario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Fecha> campeonatoFechas;
	
	// Constructor
	public Calendario() 
	{
		this.campeonatoFechas = new ArrayList<Fecha>();
	}

	public void setFechas(ArrayList<Fecha> fechas)
	{
		this.campeonatoFechas = fechas;
	}
	
	// Obtiene el calendario completo
	public ArrayList<Fecha> getCampeonatoFechas()
	{
		return this.campeonatoFechas;
	}
	
	// Obtiene una fecha especificada
	public Fecha getFechaEspecifica(Integer fecha)
	{
		if(fecha < campeonatoFechas.size() && fecha > 0) 
		{
			return campeonatoFechas.get(fecha-1);
		}
		throw new IllegalArgumentException("La fecha no existe: " + fecha);
	}
	
	
	public HashSet<Equipo> getEquipos()
	{
		HashSet<Equipo> equipos = new HashSet<Equipo>();
		for(Fecha f : this.campeonatoFechas) 
		{
			for(Partido partido : f.getPartidos()) 
			{
				if(equipos.contains(partido.getEquipo1()) || equipos.contains(partido.getEquipo2()) ) 
				{
					for(Equipo e : equipos) 
					{
						if(e.equals(partido.getEquipo1())) 
						{
							partido.setEquipo1(e);
						}
						if(e.equals(partido.getEquipo2())) 
						{
							partido.setEquipo2(e);
						}
					}
				}
				equipos.add(partido.getEquipo1());
				equipos.add(partido.getEquipo2());
			}
		}
		return equipos;
	}
	
	// Genera su JSON
    public String generarJSON()
    {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(this);
        return json;
    }
	
 	// Genera su JSON Pretty
	public String generarJSONPretty()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        String json = gson.toJson(this);
        return json;
    }
	
	// Crea un archivo.JSON
    public void crearJSONfile(String json, String dirArchivo)
    {
        try
        {
            FileWriter writer = new FileWriter(dirArchivo);
            writer.write(json);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }   
	
    
	public static Calendario leerJSON(String archivo)
    {
        Gson gson = new Gson();
        Calendario ret = null;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            ret = gson.fromJson(br, Calendario.class);
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return ret;
    }

	@Override
	public int hashCode() {
		return Objects.hash(campeonatoFechas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calendario other = (Calendario) obj;
		return Objects.equals(campeonatoFechas, other.campeonatoFechas);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("---------------CALENDARIO---------------");
		builder.append("\n");
		builder.append(campeonatoFechas);
		builder.append("\n");
		return builder.toString();
	}
}
