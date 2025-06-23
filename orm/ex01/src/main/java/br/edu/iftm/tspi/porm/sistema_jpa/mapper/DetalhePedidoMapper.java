package br.edu.iftm.tspi.porm.sistema_jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedido;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedidoId;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.Pedido;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.DetalhePedidoDto;

@Mapper(componentModel = "spring")
public interface DetalhePedidoMapper {

    @Mapping(target = "id", expression = "java(mapToDetalhePedidoId(dto))")
    @Mapping(source = "pedidoId", target = "pedido")
    @Mapping(source = "produtoId", target = "produto")
    DetalhePedido toEntity(DetalhePedidoDto dto);

    @Mapping(source = "id.pedidoId", target = "pedidoId")
    @Mapping(source = "id.produtoId", target = "produtoId")
    DetalhePedidoDto toDto(DetalhePedido entity);

    List<DetalhePedido> toEntityList(List<DetalhePedidoDto> dtos);
    List<DetalhePedidoDto> toDtoList(List<DetalhePedido> entities);

    default DetalhePedidoId mapToDetalhePedidoId(DetalhePedidoDto dto) {
        if (dto == null) return null;
        return new DetalhePedidoId(dto.getPedidoId(), dto.getProdutoId());
    }

    default Pedido mapPedido(Integer id) {
        if (id == null) return null;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        return pedido;
    }

    default Produto mapProduto(Integer id) {
        if (id == null) return null;
        Produto produto = new Produto();
        produto.setId(id);
        return produto;
    }
}


