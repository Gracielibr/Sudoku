import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Grade grade;
    Scanner scanner = new Scanner(System.in);

    public void iniciarJogo() {
        System.out.println("Escolha o nível de dificuldade:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");

        int dificuldade = executarAteObterNumeroValido(1, 3);
        gerarTabuleiroInicial(dificuldade);
        mostrarTabuleiro();
        menuJogo();
    }

    public void menuJogo() {
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Inserir número");
            System.out.println("2 - Limpar tabuleiro");
            System.out.println("3 - Limpar cédula");
            System.out.println("4 - Novo Jogo");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> {
                    inserirNumero();
                    mostrarTabuleiro();
                }
                case 2 -> {
                    limparTabuleiro();
                    System.out.println("Tabuleiro limpo (somente valores editáveis).");
                     mostrarTabuleiro();
                }

                case 3 -> {
                 limparCelula();
                    mostrarTabuleiro();
                }
                case 4 -> {
                    novoJogo();
                   System.out.println("Iniciando um novo jogo...");
                    
                }
                case 5 -> {
                    System.out.println("Saindo do jogo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
     }
    

    public void gerarTabuleiroInicial(int dificuldade) {
        Random random = new Random();
        List<List<Area>> areas = gerarTabuleiroCompleto();

        if (areas == null) {
            System.out.println("Erro ao gerar uma solução válida.");
            return;
        }

        int numerosFixos = switch (dificuldade) {
            case 1 -> 78;
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
                    areas.get(i).get(j).setFixo(true);

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

    public boolean gerarSolucao(List<List<Area>> areas, int linha, int coluna, Random random) {
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
   
    public boolean tabuleiroCorreto() {
        List<List<Area>> areas = grade.getAreas();
        for (int linha = 0; linha < 9; linha++) {
        for (int coluna = 0; coluna < 9; coluna++) {
            int valor = areas.get(linha).get(coluna).getValor();
            if (valor == 0) return false;

            areas.get(linha).get(coluna).setValor(0); 
            boolean valido = podeInserir(areas, linha, coluna, valor);
            areas.get(linha).get(coluna).setValor(valor); 

            if (!valido) return false;
            }
        }
        return true;
    }

    public void mostrarTabuleiro() {
        System.out.println("\n    1  2  3    4  5  6    7  8  9");
        System.out.println("  +----------+----------+----------+");
        for (int i = 0; i < 9; i++) {
        System.out.print((i + 1) + " | ");
        for (int j = 0; j < 9; j++) {
            int valor = grade.getArea(i, j).getValor();
            System.out.print((valor == 0 ? "·" : valor) + "  ");
            if ((j + 1) % 3 == 0) System.out.print("| ");
        }
        System.out.println();
        if ((i + 1) % 3 == 0)
            System.out.println("  +----------+----------+----------+");
        }
    }

    public int executarAteObterNumeroValido(int min, int max) {
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num < min || num > max) {
                    System.out.println("Número fora do intervalo. Tente novamente.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.next();
            }
        }
        return num;
    }


     public void inserirNumero() {
        if (grade == null) {
            System.out.println("Inicie o jogo primeiro.");
            return;
        }

        System.out.println("Digite a linha (1-9): ");
        int linha = executarAteObterNumeroValido(1, 9) - 1;

        System.out.println("Digite a coluna (1-9): ");
        int coluna = executarAteObterNumeroValido(1, 9) - 1;

        System.out.println("Digite o número (1-9): ");
        int valor = executarAteObterNumeroValido(1, 9);

        if (grade.getArea(linha, coluna).isFixo()) {
           System.out.println("Não é possível alterar um número fixo.");
            return;
        }

        grade.alterarValor(linha, coluna, valor);        
            System.out.println("Número inserido com sucesso!");
       

         if (jogoConcluido()) {
        if (tabuleiroCorreto()) {
            System.out.println("\nParabéns! Você completou o Sudoku com sucesso!!!!!!\n");
        } else {
            System.out.println("\nTabuleiro completo, mas o jogo não está correto. Tente novamente.\n");
        }
    }
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

public void limparCelula() {
    System.out.println("Digite a linha da cédula a limpar (1-9):");
    int linha = executarAteObterNumeroValido(1, 9) - 1;

    System.out.println("Digite a coluna da cédula a limpar (1-9):");
    int coluna = executarAteObterNumeroValido(1, 9) - 1;

    if (grade.getArea(linha, coluna).isFixo()) {
        System.out.println("Não é possível limpar uma célula fixa.");
        return;
    }

    grade.alterarValor(linha, coluna, 0);
    System.out.println("Célula limpa com sucesso.");
}


    private void novoJogo(){
        iniciarJogo();     // Inicia o novo jogo
        return; 
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