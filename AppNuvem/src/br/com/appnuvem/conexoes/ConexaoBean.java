package br.com.appnuvem.conexoes;

import javax.faces.bean.ManagedBean;

import br.com.appnuvem.model.Usuario;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@ManagedBean(name="conexaoBean")
public class ConexaoBean {
	public String getListar() {
	    Client c = Client.create();
	    Gson gson = new Gson();
	    Usuario usuario = new Usuario("Erick Bhrener","erick@mail.com","1234");
	    String json = gson.toJson(usuario);
	    System.out.println(json);
	    WebResource wr = c.resource("http://localhost:8080/WebServiceREST/rest/server/inserir");
	    ClientResponse response = wr.type("application/json")
	 		   .post(ClientResponse.class, json);
	  
	 	if (response.getStatus() != 201) {
	 		throw new RuntimeException("Failed : HTTP error code : "
	 		     + response.getStatus());
	 	}
	    return response.getEntity(String.class);
	  }
}
