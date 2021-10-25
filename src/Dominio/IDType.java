package Dominio;

public class IDType {
    public String getTipoDeID() {
        return TipoDeID;
    }

    public void setTipoDeID(String tipoDeID) {
        TipoDeID = tipoDeID;
    }

    private String TipoDeID;


    //Constructor
    public IDType(String Type) {
    this.TipoDeID = Type;
    }
}