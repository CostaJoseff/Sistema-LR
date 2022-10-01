package Login;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ServiceLogin {

    private RepositoryContas repositoryContas;
    private Cripto cripto;
    
    public ServiceLogin(RepositoryContas repositoryContas) {
    	this.repositoryContas = repositoryContas;
    	this.cripto = new Cripto();
    }

    public boolean validaLoginFuncionario(String ID, String senha) {
    	Funcionario funcionario = this.repositoryContas.getFuncionario(ID);
    	if (funcionario == null) {
    		return false;
    	}
    	String descriptSenha = cripto.Descriptografar(funcionario.getSenha());
    	if (descriptSenha.equals(senha)) {
    		return true;
    	}
    	return false;
    }

    public boolean validaLoginAdm(String ID, String senha) {
    	Administrador administrador = this.repositoryContas.getAdministrador(ID);
    	if (administrador == null) {
    		return false;
    	}
    	String descriptSenha = cripto.Descriptografar(administrador.getSenha());
    	if (administrador != null && descriptSenha.equals(senha)) {
    		return true;
    	}
    	return false;
    }

    public void adicionaFuncionario(String idFuncionario, String senha, String nome){
    	this.repositoryContas.putFuncionario(idFuncionario, new Funcionario(idFuncionario, quebrar(senha), nome));
    }

    public void removeFuncionario(String idFuncionario) {
    	this.repositoryContas.removeFuncionario(idFuncionario);
    }

    public void adicionaADM(String ID, String senha, String nome) {
    	this.repositoryContas.putAdministrador(ID, new Administrador(ID, quebrar(senha), nome));
    }

    public void removeADM(String ID) {
    	this.repositoryContas.removeADM(ID);
    }
    
    public Set<String> getChavesFuncionario() {
    	return this.repositoryContas.getChaveFuncionario();
    }
    
    public Set<String> getChavesADM() {
    	return this.repositoryContas.getChaveADM();
    }

	public void alteraSenha(String ID, String novaSenha) {
		this.repositoryContas.getFuncionario(ID).setSenha(quebrar(novaSenha));
	}

	private String quebrar(String senha) {
		String[] grupoSoma7  = {"q", "e", "t", "u", "o", "l"};
		String[] grupoSoma12 = {"w", "r", "y", "i", "p", "k"};
		String[] grupoSoma20 = {"j", "g", "d", "a", "x", "v", "n"};
		String[] grupoSoma9  = {"h", "f", "s", "z", "c", "b", "m"};
		String[] alfabeto = {"w", "r", "y", "i", "p", "k", "h", "f", "s", "z", "c", "b", "m", "q", "e", "t", "u", "o", "l", "j", "g", "d", "a", "x", "v", "n"};
		HashSet<String> setSoma7 = new HashSet<>();
		HashSet<String> setSoma12 = new HashSet<>();
		HashSet<String> setSoma20 = new HashSet<>();
		HashSet<String> setSoma9 = new HashSet<>();
		Random gerador = new Random();
		
		for (String letra : grupoSoma7) {
			setSoma7.add(letra);
		}
		for (String letra : grupoSoma12) {
			setSoma12.add(letra);
		}
		for (String letra : grupoSoma20) {
			setSoma20.add(letra);
		}
		for (String letra : grupoSoma9) {
			setSoma9.add(letra);
		}
		
		String senhaInvertida = "";
		String retorno = "";
		int soma;
		char[] letras = senha.toCharArray();
		for (int i = letras.length - 1; i > -1 ; i--) {
			senhaInvertida += letras[i];
		}

		for (char letra : senhaInvertida.toCharArray()) {
			String letraAleatoria = alfabeto[gerador.nextInt(26)];
			if (setSoma7.contains(letraAleatoria)) { 
				soma = 7;
			} else if (setSoma12.contains(letraAleatoria)) {
				soma = 12;
			} else if (setSoma20.contains(letraAleatoria)) {
				soma = 20;
			} else {
				soma = 9;
			}

			String hexadecimal = String.format("%x", ((int) letra + soma));
			retorno += hexadecimal + letraAleatoria;
		}
		return retorno;
	}
	
}