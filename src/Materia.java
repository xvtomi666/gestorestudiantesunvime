public class Materia {
    private int Id;
    private String Nombre;
    private int Codigo;
    private int Credito;
    private float Calif;

    public Materia() {
    }

    public Materia(int id ,String nombre, int codigo, int credito, float calif) {
        this.Id = id;
        this.Nombre = nombre;
        this.Codigo = codigo;
        this.Credito = credito;
        this.Calif = calif; 
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getCredito() {
        return Credito;
    }

    public void setCredito(int credito) {
        Credito = credito;
    }

    public float getCalif() {
        return Calif;
    }

    public void setCalif(float calif) {
        Calif = calif;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    
}
