package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame{
	
	private Font fonte;
	
	public TelaPrincipal(int tamanho) {
		setTitle("Javalar Parte 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setResizable(true);
		
		setIconImage(new ImageIcon("imagens/javalar.png").getImage());
		JLabel fundo = new JLabel(new ImageIcon("imagens/fundo.gif"));
		fundo.setLayout(new BorderLayout());
		setContentPane(fundo);
		
		JPanel Principal = new JPanel();
		JPanel Opcoes = new JPanel();
		Principal.setOpaque(false);
		Opcoes.setOpaque(false);
		Opcoes.setPreferredSize(new Dimension((int) (getWidth() * 0.2), getHeight()));
		
		Principal.setLayout(new GridLayout(tamanho, tamanho));
		Opcoes.setLayout(new GridLayout(3, 1));
		for(int i = 0; i < tamanho*tamanho; i++) {
			JLabel espaco = new JLabel();
			espaco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Principal.add(espaco);
		}
		
		try {
			Font Fonte = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Trajan.ttf"));
			fonte = Fonte.deriveFont(18f);	
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel Titulo = new JLabel(new ImageIcon("imagens/titulo.png"));
        Opcoes.add(Titulo);
        
        JPanel BotoesPrincipais = new JPanel(new GridLayout(3, 1));
        BotoesPrincipais.setOpaque(false);
        JPanel BotoesBanco = new JPanel(new GridLayout(3, 1));
        BotoesBanco.setOpaque(false);
        
        BotoesPrincipais.add(new Botao(fonte, "Processar Novo Instante"));
        BotoesPrincipais.add(new Botao(fonte, "Ler Arquivo de Entrada"));
        BotoesPrincipais.add(new Botao(fonte, "Gravar Novo Relatório"));
        BotoesBanco.add(new Botao(fonte, "Ler os Dados do Banco"));
        BotoesBanco.add(new Botao(fonte, "Gravar Arquivo de Saída"));
        
        
        Opcoes.add(BotoesPrincipais);
        Opcoes.add(BotoesBanco);
        
		add(Principal, BorderLayout.CENTER);
		add(Opcoes, BorderLayout.EAST);
		
		setVisible(true);
	}
}
