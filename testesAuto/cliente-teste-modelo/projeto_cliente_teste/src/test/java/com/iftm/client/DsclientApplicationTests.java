package com.iftm.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iftm.client.entities.Client;
import com.iftm.client.repositories.ClientRepository;

import java.util.List;


@SpringBootTest
class DsclientApplicationTests {

	@Autowired
    private ClientRepository clientRepository;

	@BeforeEach
	@Transactional
    public void setUp() {
        Client c1 = new Client();
        c1.setName("Joao Silva");
        c1.setCpf("123.456.789-01");
        c1.setIncome(3000.00);
        c1.setBirthDate(Instant.parse("1990-05-15T00:00:00Z"));
        c1.setChildren(2);

        Client c2 = new Client();
        c2.setName("Maria Oliveira");
        c2.setCpf("987.654.321-00");
        c2.setIncome(4500.00);
        c2.setBirthDate(Instant.parse("1985-09-10T00:00:00Z"));
        c2.setChildren(1);

        Client c3 = new Client();
        c3.setName("Carlos Santos");
        c3.setCpf("111.222.333-44");
        c3.setIncome(12000.00);
        c3.setBirthDate(Instant.parse("1980-02-20T00:00:00Z"));
        c3.setChildren(3);

        Client c4 = new Client();
        c4.setName("Fernanda Costa");
        c4.setCpf("555.666.777-88");
        c4.setIncome(3500.00);
        c4.setBirthDate(Instant.parse("1993-11-05T00:00:00Z"));
        c4.setChildren(0);
    }

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientByNomeValido() {
		String nomeValido = "Joao Silva";

		Client clienteNovo = clientRepository.findByName(nomeValido);

		assertNotNull(clienteNovo);
		assertEquals(nomeValido, clienteNovo.getName());
	}

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientByNomeInvalido() {
		String nomeValido = "João Silva Inexistente";

		Client clienteNovo = clientRepository.findByName(nomeValido);
		assertNull(clienteNovo);
	}

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientsByParamVariosClients() {
		String paramGeral = "a";

		List<Client> clients = clientRepository.findClientsByParam(paramGeral);
		assertNotNull(clients);
		assertTrue(clients.stream()
        		.allMatch(client -> client.getName().contains(paramGeral)));
	}

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientsByParamInvalido() {
		String paramGeral = "ddadadadadadadada";

		List<Client> clients = clientRepository.findClientsByParam(paramGeral);
		boolean vazio = clients.isEmpty();

		assertTrue(vazio);
	}

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientsByParamVazio() {
		String paramGeral = "";

		List<Client> clientsListaParam = clientRepository.findClientsByParam(paramGeral);
		List<Client> clientsListaReal = clientRepository.findAll();

		assertEquals(clientsListaParam, clientsListaReal);
	}

	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientWithIncomeLessThanValor() {
		Double valor = 100.00;

		List<Client> clients = clientRepository.findClientsWithIncomeLessThanValor(valor);
		List<Client> clientsLista = clientRepository.findAll();

		assertNotNull(clients);
		assertTrue(clients.stream()
				.allMatch(client -> client.getIncome() < valor));	
	}
}
