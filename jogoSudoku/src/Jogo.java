import java.util.*;

import javax.swing.JOptionPane;

public class Jogo {
    private Grade grade;
    private int dificuldade;

    public void iniciarJogo(int dificuldade) {
        this.dificuldade = dificuldade;
        gerarTabuleiroInicial();
    }


    private void gerarTabuleiroInicial() {
        Random random = new Random();
        List<List<Area>> areas = gerarTabuleiroCompleto();


        if (areas == null) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar tabuleiro", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numerosFixos = switch (dificuldade) {
            case 1 -> 65;
            case 2 -> 45;
            default -> 30;
        };

        int removidos = 0;
        while (removidos < (81 - numerosFixos)) {
            int linha = random.nextInt(9);
            int coluna = random.nextInt(9);
            Area area = areas.get(linha).get(coluna);
            if (area.getValor() != 0) {
                area.setValor(0);
                area.setFixo(false);
                removidos++;
            }
        }

        for (int i = 0; i < 9; i++) 
            for (int j = 0; j < 9; j++) 
                if (areas.get(i).get(j).getValor() != 0) 
                    areas.get(i).get(j).setFixo
                    (true);
                    
        grade = new Grade(areas);
        System.out.println("\nJogo Sudoku iniciado.\nBoa sorte!!!");
    }

    private List<List<Area>> gerarTabuleiroCompleto() {
        Random random = new Random();
        List<List<Area>> areas = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            areas.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                areas.get(i).add(new Area(0, false)); 
            }
        }
       
        return gerarSolucao(areas, 0, 0, random) ? areas : null;
    }

    private boolean gerarSolucao(List<List<Area>> areas, int linha, int coluna, Random random) {
        if (linha == 9) {
        return true; 
        }
        if (coluna == 9) {
        return gerarSolucao(areas, linha + 1, 0, random); 
        }
        if (areas.get(linha).get(coluna).getValor() != 0) {
            return gerarSolucao(areas, linha, coluna + 1, random); 
        }
       
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
        numeros.add(i);
        }

        Collections.shuffle(numeros, random);

        for (int num : numeros) {
            if (podeInserir(areas, linha, coluna, num)) {
            areas.get(linha).set(coluna, new Area(num, false)); 
            if (gerarSolucao(areas, linha, coluna + 1, random)) { 
                return true;
            }
            areas.get(linha).set(coluna, new Area(0, false));
            }
        }
        return false; 
    }
   

    public boolean podeInserir(List<List<Area>> areas, int linha, int coluna, int valor) {
        for (int i = 0; i < 9; i++) {
            if (areas.get(linha).get(i).getValor() == valor) return false;
            if (areas.get(i).get(coluna).getValor() == valor) return false;
        }

        int startRow = (linha / 3) * 3;
        int startCol = (coluna / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (areas.get(i).get(j).getValor() == valor) return false;
            }
        }

        return true;
    }



    
    public boolean validarJogo() {
        if (!jogoConcluido()) return false;
        
        List<List<Area>> areas = grade.getAreas();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = areas.get(i).get(j).getValor();
                areas.get(i).get(j).setValor(0);
                boolean valido = podeInserir(areas, i, j, valor);
                areas.get(i).get(j).setValor(valor);
                
                if (!valido) return false;
            }
        }
        return true;
    }
public boolean tentarInserirNumero(int linha, int coluna, int valor) {
        if (grade.getArea(linha, coluna).isFixo()) {
            return false;
        }
        
        grade.getArea(linha, coluna).setValor(valor);
        return true;
    }

    public void limparCelula(int linha, int coluna) {
        if (!grade.getArea(linha, coluna).isFixo()) {
            grade.getArea(linha, coluna).setValor(0);
        }
    }




      public Grade getGrade() {
        return grade;
    }

public void limparTabuleiro() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            if (!grade.getArea(i, j).isFixo()) {
                grade.alterarValor(i, j, 0);
            }
        }
    }
}



 
    public boolean jogoConcluido() {
        if (grade == null) return false;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grade.getArea(i, j).getValor() == 0)
                    return false;
        return true;
    }

    
}