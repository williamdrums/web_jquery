package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import model.Cliente;

//mapeamento de url
@WebServlet(value = "/clientes")
public class ClienteController extends HttpServlet {

	private List<Cliente> listaCliente = new ArrayList<Cliente>();

	int idGerado = 1;

	private Cliente cadastrar(Cliente cli) {

		cli.setId(idGerado++);
		listaCliente.add(cli);
		return cli;
	}

	// metodo do controller
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// captura dados vindos da tela html
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		// instancia objeto
		Cliente cli = new Cliente();
		cli.setNome(nome);
		cli.setEmail(email);

		// Grava os dados
		cli = cadastrar(cli);

		// retorna pra tela os dados inseridos
		resp.getWriter().println(cli.getId() + " " + cli.getNome() + " " + cli.getEmail());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(listaCliente);
		
		json = "{\"clientes\":" + json + "}";

		resp.getWriter().println(json);
	}

}
