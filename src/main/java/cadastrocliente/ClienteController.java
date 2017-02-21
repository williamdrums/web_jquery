package cadastrocliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		// captura da tela
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

		//stringbuffer é uma string com uma performace mais rapida 
		StringBuffer json= new StringBuffer("{\"clientes\": [");
		for(int i=0;i<listaCliente.size();i++){
			Cliente c = listaCliente.get(i);
			
			
			
			String id= c.getId().toString();
			String nome = c.getNome();
			String email = c.getEmail();
			
			//string de jason
			//esta concatenando atraves do metodo do stringbuffer append
			json.append( "{\"id\": \""+id+"\", \"nome\": \""+nome+"\", \"email\": \""+email+"\"}");

			
			
			//a cada vez que for inserido um objeto vai ser adicionado
			//ao final do objeto uma virgular
            if((i+1)!=listaCliente.size()){
				json.append(",");
			}
          
		}
		
		json.append("]}");
		
		resp.getWriter().print(json);
	}

}
