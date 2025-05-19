public class Area {
    private int valor;
    private boolean fixo;

    public Area(int valor, boolean fixo) {
        this.valor = valor;
        this.fixo = fixo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isFixo() {
        return fixo;
    }

    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }
}