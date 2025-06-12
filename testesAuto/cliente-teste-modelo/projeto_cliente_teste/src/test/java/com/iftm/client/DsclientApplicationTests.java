package com.iftm.client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.iftm.client.dto.ClientDTO;
import com.iftm.client.entities.Client;
import com.iftm.client.repositories.ClientRepository;
import com.iftm.client.services.ClientService;
import com.iftm.client.services.exceptions.ResourceNotFoundException;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class DsclientApplicationTests {

	@Autowired
    private ClientRepository clientRepository;

	@InjectMocks
    private ClientService servico;

    @Mock
    private ClientRepository repositorio;

    /** Testar o metodo delete da classe service, verificando se o método não retorna nada e exclui o registro quando o id existe.
     * idExistente = 1;
     * resultado esperado = não há retorno de dados ou exceptions.
     */
    @Test
    public void testarApagarClienteQuandoIDExisteNaoRetornandoNada(){
        //assign
        Long idExistente = 1L;
        Mockito.doNothing().when(repositorio).deleteById(idExistente);
        //act e assert
        assertDoesNotThrow(()->{servico.delete(idExistente);});
        Mockito.verify(repositorio, Mockito.times(1)).deleteById(idExistente);
    }

    /** Testar o metodo delete da classe service, verificando se o método retorna exception quando o id não existe.
     * idExistente = 100;
     * resultado esperado = ResourceNotFoundException
     */
    @Test
    public void testarApagarClienteQuandoIDNaoExisteRetornaException(){
        //assign
        Long idNaoExistente = 100L;        
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repositorio).deleteById(idNaoExistente);

        //act e assert
        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, ()->{servico.delete(idNaoExistente);});
        assertEquals("Id not found " + idNaoExistente, e.getMessage());
        Mockito.verify(repositorio, Mockito.times(1)).deleteById(idNaoExistente);
    }

    @Test
    @DisplayName("Teste para buscar todos os clientes com paginação")
    public void TesteBuscarTodosOsClientesComPaginacao() {

        Client cliente1 = new Client(1L, "Xande", "12345678910", 1000.00, null, null);
        Client cliente2 = new Client(2L, "Matheus", "98765432110", 20000.00, null, null);

        List<Client> listaClientes = Arrays.asList(cliente1, cliente2);
        PageRequest requisicao = PageRequest.of(0, 10);

        PageImpl<Client> implementacao = new PageImpl<>(listaClientes, requisicao, listaClientes.size());

        Mockito.when(repositorio.findAll(any(Pageable.class))).thenReturn(implementacao);

        Page<ClientDTO> resultado = servico.findAllPaged(requisicao);

        verify(repositorio, times(1)).findAll(requisicao);

        Assertions.assertEquals(2, resultado.getTotalElements());
        Assertions.assertEquals(1, resultado.getTotalPages());
    }


	// A5
    @Test
    @DisplayName("Teste para buscar cliente por ID existente")
    public void TesteBuscarClientePorIdExistente() {
        Long idExistente = 1L;

        Mockito.when(repositorio.findById(idExistente)).thenReturn(Optional.of(new Client()));

        Assertions.assertDoesNotThrow(() -> { servico.findById(idExistente); });
    }

    @Test
    @DisplayName("Teste para buscar cliente por ID inexistente e lançar exceção")
    public void TesteBuscarClientePorIdInexistenteDeveLancarExcecao() {
        Long idNaoExistente = 100L;
        Mockito.when(repositorio.findById(idNaoExistente)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> { servico.findById(idNaoExistente); });
    }

    @Test
    @DisplayName("Teste para atualizar cliente com ID existente")
    public void TesteAtualizarClienteComIdExistente() {
        Long idExistente = 1L;

        Client cliente = new Client(idExistente, "Fred", "12345678910", 10000.00, null, null);
        ClientDTO clienteDTO = new ClientDTO(idExistente, "Xande", "12345678910", 3000.00, null, null);

        Mockito.when(repositorio.getOne(idExistente)).thenReturn(cliente);
        Mockito.when(repositorio.save(Mockito.any(Client.class))).thenReturn(cliente);

        ClientDTO resultado = servico.update(idExistente, clienteDTO);

        Assertions.assertEquals(idExistente, resultado.getId());
        Assertions.assertEquals("Xande", resultado.getName());
    }

    @Test
    @DisplayName("Teste para atualizar cliente com ID inexistente e lançar exceção")
    public void TesteAtualizarClienteComIdInexistenteDeveLancarExcecao() {
        Long idNaoExistente = 100L;
        Long idExistente = 1L;

        Client cliente = new Client(idExistente, "Fred", "12345678910", 10000.00, null, null);
        ClientDTO clienteDTO = new ClientDTO(idExistente, "Xande", "12345678910", 3000.00, null, null);

        Mockito.when(repositorio.getOne(idExistente)).thenReturn(cliente);
        Mockito.when(repositorio.save(Mockito.any(Client.class))).thenReturn(cliente);
        Mockito.when(repositorio.getOne(idNaoExistente)).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> { servico.update(idNaoExistente, clienteDTO); });
    }

    @Test
    @DisplayName("Teste para inserir um novo cliente com sucesso")
    public void TesteInserirNovoClienteComSucesso() {
        Long idExistente = 1L;
        ClientDTO clienteDTOSucesso = new ClientDTO(null, "Xande", "12345678910", 3000.00, null, null);

        Client clienteQueOrepositorioVaiRetornar = new Client(idExistente, "Xande", "12345678910", 3000.00, null, null);
        Mockito.when(repositorio.save(Mockito.any(Client.class))).thenReturn(clienteQueOrepositorioVaiRetornar);

        ClientDTO resultado = servico.insert(clienteDTOSucesso);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Xande", resultado.getName());
    }


	// A4
	@Test
	@DisplayName("Testa se o método retorna o cliente com o nome desejado.")
	void TestarMetodoFindClientByNomeValido() {
		String nomeValido = "Conceição Evaristo";

		Client clienteNovo = clientRepository.findByName(nomeValido);

		assertNotNull(clienteNovo);
		assertEquals(nomeValido, clienteNovo.getName());
	}

	@Test
	@DisplayName("Testa se o método retorna null se o cliente não existir.")
	void TestarMetodoFindClientByNomeInvalido() {
		String nomeValido = "João Silva Inexistente";

		Client clienteNovo = clientRepository.findByName(nomeValido);
		assertNull(clienteNovo);
	}

	@Test
	@DisplayName("Testa se o método retorna todos os clientes com um parametro")
	void TestarMetodoFindClientsByParamVariosClients() {
		String paramGeral = "a";

		List<Client> clients = clientRepository.findClientsByParam(paramGeral);
		assertNotNull(clients);
		assertTrue(clients.stream()
        	.allMatch(client -> client.getName().contains(paramGeral)));
	}

	@Test
	@DisplayName("Testa se o método uma lista vazia com um parametro inválido")
	void TestarMetodoFindClientsByParamInvalido() {
		String paramGeral = "ddadadadadadadada";

		List<Client> clients = clientRepository.findClientsByParam(paramGeral);
		boolean vazio = clients.isEmpty();

		assertTrue(vazio);
	}

	@Test
	@DisplayName("Testa se o método retorna a lista com todos os clientes com o param vazio")
	void TestarMetodoFindClientsByParamVazio() {
		String paramGeral = "";

		List<Client> clientsListaParam = clientRepository.findClientsByParam(paramGeral);
		List<Client> clientsListaReal = clientRepository.findAll();

		assertEquals(clientsListaParam, clientsListaReal);
	}

	@Test
	@DisplayName("Testa se o método retorna clientes que ganham menos que um valor")
	void TestarMetodoFindClientWithIncomeLessThanValor() {
		Double valor = 100.00;

		List<Client> clients = clientRepository.findClientsWithIncomeLessThanValor(valor);

		assertNotNull(clients);
		assertTrue(clients.stream()
				.allMatch(client -> client.getIncome() < valor));	
	}
}
