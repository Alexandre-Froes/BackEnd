package com.iftm.client;

import com.iftm.client.dto.ClientDTO;
import com.iftm.client.repositories.ClientRepository;
import com.iftm.client.services.ClientService;
import com.iftm.client.services.exceptions.ResourceNotFoundException;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.data.domain.*;
import javax.transaction.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class A6Tests {

    @Autowired
    private ClientService servico;

    @Autowired
    private ClientRepository repositorio;

    private Long idExistente;
    private Long idNaoExistente;

    @BeforeEach
    void setUp() {
        idExistente = 1L;
        idNaoExistente = 100L;
    }

    @Test
    void deleteDeveRemoverClienteComIdExistente() {
        assertDoesNotThrow(() -> servico.delete(idExistente));
        assertFalse(repositorio.findById(idExistente).isPresent());
    }

    @Test
    void deleteDeveLancarExcecaoSeIdNaoExistir() {
        assertThrows(ResourceNotFoundException.class,
            () -> servico.delete(idNaoExistente));
    }

    @Test
    void findAllPagedDeveRetornarPaginaComRegistros() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ClientDTO> resultado = servico.findAllPaged(pageRequest);

        assertNotNull(resultado);
        assertTrue(resultado.getTotalElements() > 0, 
            "Deveria retornar registros para a primeira página.");
    }


    @Test
    void findByIdDeveRetornarClienteExistente() {
        ClientDTO dto = servico.findById(idExistente);
        assertNotNull(dto);
        assertEquals(idExistente, dto.getId());
    }

    @Test
    void findByIdDeveLancarExcecaoSeIdNaoExistir() {
        assertThrows(ResourceNotFoundException.class, 
            () -> servico.findById(idNaoExistente));
    }

    @Test
    void updateDeveAlterarClienteExistente() {
        ClientDTO dto = new ClientDTO();
        dto.setName("Novo nome");
        ClientDTO atualizada = servico.update(idExistente, dto);
        assertEquals("Novo nome", atualizada.getName());
    }

    @Test
    void updateDeveLancarExcecaoSeIdNaoExistir() {
        ClientDTO dto = new ClientDTO();
        assertThrows(ResourceNotFoundException.class, 
            () -> servico.update(idNaoExistente, dto));
    }

    @Test
    void insertDevePersistirNovoCliente() {
        ClientDTO novo = new ClientDTO(null, "Teste", "99988877766", 5000.0, null, null);
        ClientDTO resultado = servico.insert(novo);
        assertNotNull(resultado.getId());
        assertEquals("Teste", resultado.getName());
    }
}
