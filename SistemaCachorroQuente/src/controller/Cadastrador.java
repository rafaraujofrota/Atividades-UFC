package controller;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Aluno;
import classes.Cliente;
import classes.Funcionarios;

public class Cadastrador {
	private static int totalCadastrado = 0;
	private static ArrayList<Cliente> cadastrados = new ArrayList<>(); 
	
	public Cliente PegarCliente(int indentificador) {
		for(Cliente c: cadastrados) {
			if(c.PegarIndentificador() == indentificador) return c;
		}
		return null;
	}
	
	public int PegarTotal() {
		return totalCadastrado;
	}
	
	public ArrayList<Cliente> PegarClientes() {
		return cadastrados;
	}
	
	public void Cadastrar(Scanner Entrada) {
		System.out.println("Insira seu nome: ");
		String nome = Entrada.nextLine();
		
		System.out.println("Insira o identificador: ");
		int id = Entrada.nextInt();
		int tamanhoId = Integer.toString(id).length();
		
		Cliente c;
		
		if(tamanhoId == 7) c = new Funcionarios(nome, id);
		else if(tamanhoId == 6) c = new Aluno(nome, id);
		else {
			System.out.println("Indentificador Inválido");
			return;
		}
			
		if(totalCadastrado == 0 || PegarCliente(id) != null) {
			cadastrados.add(c);
			totalCadastrado++;
		} else {
			System.out.println("Cliente já existe, tente novamente");
		}
	}
}
