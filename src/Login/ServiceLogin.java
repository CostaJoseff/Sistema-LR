package Login;

import java.math.BigInteger;
import java.util.Set;

public class ServiceLogin {

    private RepositoryContas repositoryContas;
    
    public ServiceLogin(RepositoryContas repositoryContas) {
    	this.repositoryContas = repositoryContas;
    }

    public boolean validaLoginFuncionario(String ID, String senha) {
    	return this.repositoryContas.contemFuncionario(ID, quebrar(senha));
    }

    public boolean validaLoginAdm(String ID, String senha) {
    	return this.repositoryContas.contemAdministrador(ID, quebrar(senha));
    }

    public void adicionaFuncionario(String idFuncionario, String senha, String nome){
    	this.repositoryContas.putFuncionario(idFuncionario, quebrar(senha), nome);
    }

    public void removeFuncionario(String idFuncionario) {
    	this.repositoryContas.removeFuncionario(idFuncionario);
    }

    public void adicionaADM(String ID, String senha, String nome) {
    	this.repositoryContas.putAdministrador(ID, quebrar(senha), nome);
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

	public void alteraSenhaFuncionario(String ID, String novaSenha) {
		this.repositoryContas.alteraSenhaFuncionario(ID, quebrar(novaSenha));
	}
	
	public void alteraSenhaAdminstrador(String ID, String novaSenha) {
		this.repositoryContas.alteraSenhaAdminsitrador(ID, quebrar(novaSenha));
	}

	public String quebrar(String texto) {
		BigInteger e = new BigInteger("60958009123833438732118812480122111623580621121904976293863275380408318406251754794498645471703258543751852892572806500665514242002303390991578479237267974594701629328678128034037744949480762453612955352408072168148191290186873981454155705260958265382059539048443903758662975417512486115776458490824189982591");
		BigInteger n = new BigInteger("125538307429523777402676048037744338792992662167799127768228678790350902176133709612762050147990404185582222244446954062989274309523715895280982268207411192832732866364551997975600990897540455695751718217656678343456984422855629847284030859458823063246234088264116492127298949204666274427547116571344016496151");
		return new BigInteger(texto.getBytes()).modPow(e, n).toString();
	}
	
}