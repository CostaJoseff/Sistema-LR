package tests;

import org.junit.jupiter.api.BeforeEach;
import Administrador.ControllerAdministrador;
import Administrador.ServiceAdministrador;
import Funcionario.ControllerFuncionario;
import Funcionario.ServiceFuncionario;
import Login.ControllerLogin;
import Login.RepositoryContas;
import Login.ServiceLogin;
import Produtos.DepositoDeProdutos;
import Vendas.HistoricoDeVendas;

class TestesGerais {
	
	ControllerLogin controllerLogin;
	ControllerAdministrador controllerAdministrador;
	ControllerFuncionario controllerFuncionario;
	DepositoDeProdutos depositoDeProdutos;
	HistoricoDeVendas historicoDeVendas;
	
	String idAdm = "IDADM";
	String idFuncionario = "IDFUNCIONARIO";
	String senhaADM = "SENHAADM";
	String senhaFuncionario = "SENHAFUNCIONARIO";
	
	String nulo = "Era esperado uma Exception - parâmetros NULOS";
	String vazio = "Era esperado uma Exception - marâmetros VAZIOS";
	String rte = "Era esperado uma Runtime Exception";
	String iae = "Era esperado uma IllegalArgumentException";
	String nome = "Era esperado uma Exception ao passar um nome com números";
	String positivo = "Era esperado uma Exception - números negativos";
	String idInexistente = "Era espreado uma Excepption - ID inexistente";
	String concluido = "Era esperado uma Runtime - venda concluída";
	
	@BeforeEach
	void setUp(){
		historicoDeVendas = new HistoricoDeVendas();
		depositoDeProdutos = new DepositoDeProdutos();
		controllerFuncionario = new ControllerFuncionario(new ServiceFuncionario(historicoDeVendas, depositoDeProdutos));
		controllerAdministrador = new ControllerAdministrador(new ServiceAdministrador(historicoDeVendas, depositoDeProdutos));
		controllerLogin = new ControllerLogin(new ServiceLogin(new RepositoryContas()));
		
		controllerLogin.adicionaADM(idAdm, senhaADM, "ADM");
		controllerLogin.adicionaFuncionario(idFuncionario, senhaFuncionario, "FUNCIONARIO");
	}
}
