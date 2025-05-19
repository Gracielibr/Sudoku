import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
  
  
  public class JanelaNivel {
  
  
  public static void JanelaPrincipal() {
        
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 100);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel painel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Escolha o nível de dificuldade:");

        JButton botaoFacil = new JButton("Fácil");
        JButton botaoMedio = new JButton("Médio");
        JButton botaoDificil = new JButton("Difícil");

        botaoFacil.addActionListener(_ -> iniciarJogo(frame, 1));
        botaoMedio.addActionListener(_ -> iniciarJogo(frame, 2));
        botaoDificil.addActionListener(_ -> iniciarJogo(frame, 3));

        painel.add(label);
        painel.add(botaoFacil);
        painel.add(botaoMedio);
        painel.add(botaoDificil);

        frame.add(painel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

     private static void iniciarJogo(JFrame frame, int dificuldade) {
        frame.dispose();
        new Painel(dificuldade);
    }
}