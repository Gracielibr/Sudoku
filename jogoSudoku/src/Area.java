public class Area {
    private int valor;
    private boolean fixo;
    private boolean valorUsuario;  

    public Area(int valor, boolean fixo) {
        this.valor = valor;
        this.fixo = fixo;
        this.valorUsuario = false;
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

    public boolean isValorUsuario() {
        return valorUsuario;
    }

    public void setValorUsuario(boolean valorUsuario) {
        this.valorUsuario = valorUsuario;
    }
}