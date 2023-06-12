package br.com.alura.gerenciador.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> empresas = new ArrayList<>();
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa1 = new Empresa("Alura");
		empresa1.setId(Banco.chaveSequencial++);
		Empresa empresa2 = new Empresa("Caelum");
		empresa2.setId(Banco.chaveSequencial++);
		empresas.add(empresa1);
		empresas.add(empresa2);
	}

	public void adicionar(Empresa empresa) {
		Banco.empresas.add(empresa);
		empresa.setId(Banco.chaveSequencial++);
	}
	
	public List<Empresa> getEmpresas(){
		return Banco.empresas;
	}

	public void removerEmpresa(Integer id) {
		
		Iterator<Empresa> it = empresas.iterator();
		
		while(it.hasNext()) {
			Empresa empresa = it.next();
			
			if(empresa.getId() == id) {
				it.remove();
			}
		}
	}

	public Empresa buscarEmpresa(Integer id) {
		for(Empresa empresa : empresas) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

}
