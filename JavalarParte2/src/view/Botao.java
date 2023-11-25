package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Botao extends JPanel implements MouseListener {

	JLabel seta_esquerda;
	JLabel seta_direita;
	Runnable funcao;
	
	public Botao(Font fonte, String texto, Runnable funcao) {
		this.funcao = funcao;
		setLayout(new BorderLayout());
		setOpaque(false);
		
		JLabel Texto = new JLabel(texto);
		seta_esquerda = new JLabel(new ImageIcon("imagens/seta_esquerda.png"));
		seta_direita = new JLabel(new ImageIcon("imagens/seta_direita.png"));
		seta_esquerda.setVisible(false);
		seta_direita.setVisible(false);
		
		Texto.setFont(fonte);
		Texto.setForeground(Color.WHITE);
		Texto.setHorizontalAlignment(JLabel.CENTER);
		
		addMouseListener(this);
		add(seta_esquerda, BorderLayout.WEST);
		add(seta_direita, BorderLayout.EAST);
		add(Texto, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		funcao.run();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		seta_esquerda.setVisible(true);
		seta_direita.setVisible(true);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		seta_esquerda.setVisible(false);
		seta_direita.setVisible(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
	}
}
