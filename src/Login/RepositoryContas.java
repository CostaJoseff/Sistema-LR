package Login;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RepositoryContas {

    private HashMap<String, Funcionario> funcionarios;
    private HashMap<String, Administrador> administradores;

    public RepositoryContas () {
    	this.funcionarios = new HashMap<>();
    	this.administradores = new HashMap<>();
    }
    
    public Funcionario getFuncionario(String ID) {
    	return this.funcionarios.get(ID);
    }

    public void putFuncionario(String idFuncionario, String senha, String nome) {
    	this.funcionarios.put(idFuncionario, new Funcionario(idFuncionario, montar(senha), nome));
    }

    public void removeFuncionario(String ID) {
    	this.funcionarios.remove(ID);
    }

    public Administrador getAdministrador(String ID) {
    	return this.administradores.get(ID);
    }

    public void putAdministrador(String ID, String senha, String nome) {
    	this.administradores.put(ID, new Administrador(ID, montar(senha), nome));
    }

    public void removeADM(String ID) {
    	this.administradores.remove(ID);
    }
    
    public Set<String> getChaveFuncionario() {
    	return this.funcionarios.keySet();
    }
    
    public Set<String> getChaveADM(){
    	return this.administradores.keySet();
    }
    
    public boolean contemFuncionario(String iD, String mascara) {
		return this.funcionarios.get(iD).getSenha().equals(montar(mascara));
	}

	public boolean contemAdministrador(String iD, String mascara) {
		return this.administradores.get(iD).getSenha().equals(montar(mascara));
	}

	public void alteraSenhaFuncionario(String ID, String novaSenha) {
		this.funcionarios.get(ID).setSenha(montar(novaSenha));
	}

	public void alteraSenhaAdminsitrador(String ID, String novaSenha) {
		this.administradores.get(ID).setSenha(montar(novaSenha));
	}
    
    private String montar(String textoQuebrado) {
    	BigInteger d = new BigInteger("10800334039651574463254576618197122983390635841698433474900758978970544732316357580072484160820148863973097601402476620174039585617819420916769787937910711");
    	BigInteger n = new BigInteger("125538307429523777402676048037744338792992662167799127768228678790350902176133709612762050147990404185582222244446954062989274309523715895280982268207411192832732866364551997975600990897540455695751718217656678343456984422855629847284030859458823063246234088264116492127298949204666274427547116571344016496151");
    	return new String(new BigInteger(textoQuebrado).modPow(d, n).toByteArray());
    }
}