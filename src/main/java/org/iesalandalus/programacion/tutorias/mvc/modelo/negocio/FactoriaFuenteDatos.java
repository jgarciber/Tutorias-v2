package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria.FactoriaFuenteDatosMemoria;

public enum FactoriaFuenteDatos {
	MEMORIA {
		@Override
		public IFuenteDatos crear() {
			// TODO Auto-generated method stub
			return new FactoriaFuenteDatosMemoria();
		}
	};
	
	public abstract IFuenteDatos crear();
	
}
