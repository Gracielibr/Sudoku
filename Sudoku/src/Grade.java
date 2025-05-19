import java.util.List;


public class Grade {
    private List<List<Area>> areas;

    public Grade(List<List<Area>> areas) {
        this.areas = areas;
    }

    public Area getArea(int linha, int coluna) {
        return areas.get(linha).get(coluna);
    }

    public void alterarValor(int linha, int coluna, int valor) {
        areas.get(linha).get(coluna).setValor(valor);
    }

    public List<List<Area>> getAreas() {
        return areas;
    }
}