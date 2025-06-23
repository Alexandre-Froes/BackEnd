package br.edu.iftm.tspi.porm.sistema_jpa.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "ClienteID", nullable = false, columnDefinition = "CHAR(5)")
    private String id;

    @Column(name = "nome", nullable=false)
    private String nome;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "endereco", nullable=false)
    private String endereco;

    @Column(name = "cidade", nullable=false)
    private String cidade;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "Pais", nullable=false)
    private String pais;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Fax")
    private String fax;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
