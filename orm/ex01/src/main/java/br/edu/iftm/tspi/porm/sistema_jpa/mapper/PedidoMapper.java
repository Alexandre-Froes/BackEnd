package br.edu.iftm.tspi.porm.sistema_jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Cliente;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.PedidoDto;

@Mapper(componentModel = "spring", uses = {DetalhePedidoMapper.class})
public interface PedidoMapper {

    @Mapping(source = "clienteId", target = "cliente")
    Pedido toEntity(PedidoDto dto);

    @Mapping(source = "cliente.id", target = "clienteId")
    PedidoDto toDto(Pedido entity);

    List<PedidoDto> toDtoList(List<Pedido> entities);

    List<Pedido> toEntityList(List<PedidoDto> dtos);

    default Cliente map(String clienteId) {
        if (clienteId == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        return cliente;
    }
}