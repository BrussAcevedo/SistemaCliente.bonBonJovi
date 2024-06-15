package cl.bonBonJovi.modelo;

public enum CategoriaEnum {

	ACTIVO("Activo"),
	INACTIVO("Inactivo"),
	SC("SinCategoria");
	
	private final String descripcion;

	private CategoriaEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}
	
}
