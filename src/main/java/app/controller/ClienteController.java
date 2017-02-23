package app.controller;

import java.util.ArrayList;
import java.util.List;



import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;

//mapeamento de url
@RestController(value = "/clientes")
public class ClienteController {

	private List<Cliente> listaCliente = new ArrayList<Cliente>();

	int idGerado = 1;

	private Cliente cadastrar(Cliente cli) {

		cli.setId(idGerado++);
		listaCliente.add(cli);
		return cli;
	}
	
    //metodo controller post
	@RequestMapping(method = RequestMethod.POST)
	public Cliente salvar(@RequestBody Cliente cliente) {

		cliente = cadastrar(cliente);
		return cliente;
	}

	//metodo controller get
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		return listaCliente;
	}

}
