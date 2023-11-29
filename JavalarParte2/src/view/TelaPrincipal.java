package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Plano;
import classes.Relatorio;
import controllers.GerenciadorSimulacao;
import controllers.RelatorioDAO;
import utils.Astro;
import utils.AnalisadorDados;
import utils.Vetor;

public class TelaPrincipal extends JFrame{
	
	private Font fonte;
	private JLabel[][] espacos;
	private GerenciadorSimulacao controle;
	private JLabel loading;
	
	public TelaPrincipal(GerenciadorSimulacao controle) {
		setTitle("Javalar Parte 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setResizable(true);
		
		this.controle = controle;
		int tamanho = controle.PegarPlano().PegarTamanho();
		espacos = new JLabel[tamanho][tamanho];
		
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
		for(int i = 0; i < tamanho; i++) {
			for(int j = 0; j < tamanho; j++) {
				JLabel espaco = new JLabel();
				espaco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				espaco.setHorizontalAlignment(JLabel.CENTER);
				espaco.setVerticalAlignment(JLabel.CENTER);
				espacos[i][j] = espaco;
				Principal.add(espaco);
			}
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
        
        loading = new JLabel(new ImageIcon("imagens/loading.gif"));
        loading.setVisible(false);
        
        BotoesPrincipais.add(new Botao(fonte, "Processar Novo Instante", () -> BotaoProcessar()));
        BotoesPrincipais.add(new Botao(fonte, "Ler Arquivo de Entrada", () -> BotaoLer()));
        BotoesPrincipais.add(new Botao(fonte, "Gravar Novo Relatório", () -> BotaoGravar()));
        BotoesBanco.add(new Botao(fonte, "Ler os Dados do Banco", () -> BotaoBuscar()));
        BotoesBanco.add(new Botao(fonte, "Gravar Arquivo de Saída", () -> BotaoSalvar()));
        BotoesBanco.add(loading);
        
        Opcoes.add(BotoesPrincipais);
        Opcoes.add(BotoesBanco);
        
		add(Principal, BorderLayout.CENTER);
		add(Opcoes, BorderLayout.EAST);
		
		AtualizarPlano();
		
		setVisible(true);
	}
	
	private JLabel pegarEspaco(Vetor vet) {
		return espacos[vet.y][vet.x];
	}
	
	private void AtualizarPlano() {
		Plano plano = controle.PegarPlano();
		int tamanho = plano.PegarTamanho();
		
		for(int i = 0; i < tamanho; i++) {
			for(int j = 0; j < tamanho; j++) {
				Vetor pos = new Vetor(i, j);
				JLabel espaco = pegarEspaco(pos);
				Astro astro = plano.PegarAstro(pos);
				
				if(espaco.getIcon() == null) {
					if(astro != null) espaco.setIcon(new ImageIcon(astro.pegarImagem()));
				}
				else if(astro == null) {
					espaco.setIcon(null);
				}
				else if(espaco.getIcon().toString() != astro.pegarImagem()) {
					espaco.setIcon(new ImageIcon(astro.pegarImagem()));
				}
				
			}
		}
		
		repaint();
		
	}
	
	private void BotaoProcessar() {
		try {
			controle.PassaTempoArquivo();
			AtualizarPlano();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fica de Olho", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void BotaoLer() {
		JFileChooser JanelaArquivo = new JFileChooser();
		JanelaArquivo.setFileFilter(new FileNameExtensionFilter("Arquivos CSV", "csv"));
		
		int resultado = JanelaArquivo.showOpenDialog(null);
		if( resultado == JFileChooser.APPROVE_OPTION) {
			File arquivo = JanelaArquivo.getSelectedFile();
			controle.DefinirArquivo(arquivo.getAbsolutePath());
			controle.ResetarPlano();
			AtualizarPlano();
		} 
	}
	
	private void BotaoGravar() {
		if(controle.PegarNomeArquivo() == null) {
			JOptionPane.showMessageDialog(null, "Você ainda não iniciou o programa" ,"Sem Arquivo", JOptionPane.INFORMATION_MESSAGE);
			return;
		} 
		
		loading.setVisible(true);
		
		Thread requisicao = new Thread(() -> {
			try {
				RelatorioDAO relatorioDAO = new RelatorioDAO();
				relatorioDAO.Criar(controle.PegarPlanetasAtivos(), controle.PegarPlano(), controle.PegarNomeArquivo());
				loading.setVisible(false);
				JOptionPane.showMessageDialog(null, "Deu Bom", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Deu Ruim", JOptionPane.ERROR_MESSAGE);
			} finally {
				loading.setVisible(false);
			}
		});
			
		requisicao.start();
	}
	
	private void BotaoBuscar() {
		loading.setVisible(true);
		
		Thread requisicao = new Thread(() -> {
			try {
				RelatorioDAO relatorioDAO = new RelatorioDAO();
				relatorioDAO.PegarTodos();
				loading.setVisible(false);
				JOptionPane.showMessageDialog(null, "Deu Bom", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Deu Ruim", JOptionPane.ERROR_MESSAGE);
			} finally {
				loading.setVisible(false);
			}
		});
		
		requisicao.start();
	}
	
	private void BotaoSalvar() {
		loading.setVisible(true);
		
		Thread salvar = new Thread(() -> {
			try {
				AnalisadorDados Gerador = new AnalisadorDados(Relatorio.ListaRelatorios);
				JFileChooser JanelaArquivo = new JFileChooser();
				JanelaArquivo.setFileFilter(new FileNameExtensionFilter("Saida", "txt"));
				
				int selecao = JanelaArquivo.showSaveDialog(this);
				
				if(selecao == JFileChooser.APPROVE_OPTION) {
					String local = JanelaArquivo.getSelectedFile().getAbsolutePath() + ".txt";
					
					BufferedWriter criador = new BufferedWriter(new FileWriter(local));
					criador.write(Gerador.PegarResumo());
					criador.close();
					JOptionPane.showMessageDialog(null, "Deu Bom", "Arquivo Salvo", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Deu Ruim", JOptionPane.ERROR_MESSAGE);
			} finally {
				loading.setVisible(false);
			}
		});
		
		salvar.start();
	}
	
}
