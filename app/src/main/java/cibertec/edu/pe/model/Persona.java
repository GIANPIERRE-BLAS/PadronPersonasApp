package cibertec.edu.pe.model;

public class Persona {
    private int id;
    private String nombreCompleto;
    private String documentoIdentidad;
    private String telefono;
    private String direccion;
    private String distrito;
    private String estadoCivil;
    private String genero;

    public Persona() {}

    public Persona(String nombreCompleto, String documentoIdentidad,
                   String telefono, String direccion, String distrito,
                   String estadoCivil, String genero) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.distrito = distrito;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
