package Login;

import Validador.ValidadorEntradas;

public class ControllerLogin {

    private ServiceLogin serviceLogin;
    private ValidadorEntradas validador;
    
    public ControllerLogin(ServiceLogin servicelogin) {
    	this.serviceLogin = servicelogin;
    	this.validador = new ValidadorEntradas();
    }

    public boolean validaLoginFuncionario(String ID, String senha) {
    	this.validador.valida(new String[] {ID, senha});
    	return this.serviceLogin.validaLoginFuncionario(ID, senha);
    }

    public boolean validaLoginAdm(String ID, String senha) {
    	this.validador.valida(new String[] {ID, senha});
    	return this.serviceLogin.validaLoginAdm(ID, senha);
    }

    public void adicionaFuncionario(String idFuncionario, String senha, String nome) {
    	this.validador.valida(new String[] {idFuncionario, senha, nome});
    	this.validador.validaNome(nome);
    	this.validador.validaIDExistente(this.serviceLogin.getChavesFuncionario(), idFuncionario);
    	this.serviceLogin.adicionaFuncionario(idFuncionario, senha, nome);
    }

    public void removeFuncionario(String idFuncionario) {
    	this.validador.valida(idFuncionario);
    	this.serviceLogin.removeFuncionario(idFuncionario);
    }

    public void adicionaADM(String ID, String senha, String nome) {
    	this.validador.valida(new String[] {ID, senha, nome});
    	this.validador.validaNome(nome);
    	this.validador.validaIDExistente(this.serviceLogin.getChavesFuncionario(), ID);
    	this.serviceLogin.adicionaADM(ID, senha, nome);
    }

    public void removeADM(String ID) {
    	this.validador.valida(ID);
    	this.serviceLogin.removeADM(ID);
    }

}