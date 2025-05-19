import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Painel {
     private JFrame frame;
    private JTextField[][] campos;
    private Jogo jogo;   
    private JPanel boardPanel;

    public Painel(int dificuldade) {
        
        jogo = new Jogo();
        jogo.iniciarJogo(dificuldade);
        
        Janela();
        inicializarTabuleiro();
        botoesDoControle();
        configurarEventos();
        frame.setVisible(true);
    }

    private void Janela() {
        frame = new JFrame("Sudoku ");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null); 
    }

    private void inicializarTabuleiro() {
        boardPanel = new JPanel(new GridLayout(9, 9));
        campos = new JTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 final int linha = i; 
                 final int coluna = j; 

                JTextField campo = new JTextField();
                campo.setHorizontalAlignment(JTextField.CENTER);
                campo.setFont(new Font("Arial", Font.BOLD, 18));
                campo.setPreferredSize(new Dimension(40, 40));
                
                int top = 1, left = 1, bottom = 1, right = 1;
                if (i % 3 == 0) top = 3;
                if (i % 3 == 2) bottom = 3;
                if (j % 3 == 0) left = 3;
                if (j % 3 == 2) right = 3; 

                campo.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                int valor = jogo.getGrade().getArea(i, j).getValor();
                if (valor != 0) {
                    campo.setText(String.valueOf(valor));
                    campo.setEditable(false);
                    campo.setBackground(new Color(180, 180, 180));
                    campo.setForeground(Color.BLACK); 
                } else {
                    campo.setForeground(Color.BLUE); 
                    campo.setBackground(Color.WHITE); 
                    campo.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                               if (!(Character.isDigit(c) && c >= '1' && c <= '9')  || campo.getText().length() > 0) {
                                e.consume(); 
                                campo.setText(""); // Limpa a célula
                                campo.requestFocusInWindow(); 
                                JOptionPane.showMessageDialog(
                                    frame,
                                    "Número incorreto! Digite um valor entre 1 e 9.",      
                                    "Entrada inválida",
                                    JOptionPane.WARNING_MESSAGE
                                    );                        

                                } else {
                                    int valor = Character.getNumericValue(c);
                                    jogo.tentarInserirNumero(linha, coluna, valor); 
                                     
                                if (jogo.jogoConcluido()) {
                                    if (jogo.validarJogo()) {
                                        JOptionPane.showMessageDialog(frame, 
                                             "Parabéns! Você completou o Sudoku com sucesso!!!!!!", 
                                            "Vitória", 
                                        JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(frame, 
                                        "Tabuleiro completo, mas o jogo não está correto.", 
                                        "Atenção", 
                                        JOptionPane.WARNING_MESSAGE);
                                  }
                             }
                         }
                        }
                        public void keyPressed(KeyEvent e) {
                            if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                             e.getKeyCode() == KeyEvent.VK_DELETE) && 
                             !campo.getText().isEmpty()) {
                                campo.setText("");
                                jogo.limparCelula(linha, coluna);
                            }
                         }



                    });


                    
                }
                boardPanel.add(campo);
                campos[i][j] = campo;
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);
    }
    
    private void botoesDoControle() {
        JPanel controlPanel = new JPanel(new FlowLayout());       
        
        JButton limparTabuleiroButton = new JButton("Limpar Jogo");
        limparTabuleiroButton.addActionListener(_ ->  {
            jogo.limparTabuleiro();
            atualizarTabuleiro();
        });
        
        JButton novoJogoButton = new JButton("Novo Jogo");
        novoJogoButton.addActionListener(_ -> {
            frame.dispose();
            JanelaNivel.JanelaPrincipal(); 
        });
        
        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(_ -> confirmarSaida());  
        
        controlPanel.add(limparTabuleiroButton);
        controlPanel.add(novoJogoButton);
        controlPanel.add(sairButton);
        
        frame.add(controlPanel, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        frame.addWindowListener(new WindowAdapter() {        
            public void windowClosing(WindowEvent e) {
                confirmarSaida();
            }
        });
    
    }

    private void confirmarSaida() {
        Object[] opcoes = {"SIM", "NÃO"};
        int resposta = JOptionPane.showOptionDialog(
            frame, 
            "Deseja realmente sair do jogo?", 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
            );
    
        if (resposta == JOptionPane.YES_OPTION) {
        frame.dispose();
        System.exit(0);
        }
    }

     private void atualizarTabuleiro() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = jogo.getGrade().getArea(i, j).getValor();
                JTextField campo = campos[i][j];
                campo.setText(valor != 0 ? String.valueOf(valor) : "");
                
                if (jogo.getGrade().getArea(i, j).isFixo()) {
                    campo.setEditable(false);
                    campo.setBackground(new Color(180, 180, 180));
                    campo.setForeground(Color.BLACK);
                } else {
                    campo.setEditable(true);
                    campo.setBackground(Color.WHITE);
                    campo.setForeground(Color.BLUE);
                }
            }
        }
    }
}



