public class Detalles {
    int indice;
    String detalle;

    public Detalles() {
        indice=0;
        detalle="";
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return getDetalle();
    }
}
