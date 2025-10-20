import java.util.Objects;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;
    private String documento;

    public Persona(String nombre, String apellido, Integer edad, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Integer getEdad() {
        return edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    @Override
    public String toString() {
        return String.format("Persona[nombre='%s', apellido='%s', edad=%s, documento='%s']",
                nombre, apellido, edad, documento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        // Se considera igual por documento (atributo único)
        return Objects.equals(documento, persona.documento);
    }

    @Override
    public int hashCode() {
        // Basado en el documento (atributo único)
        return Objects.hash(documento);
    }
}