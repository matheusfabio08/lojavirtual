package br.com.exemplo.lojavirtual.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.exemplo.lojavirtual.dto.RelatorioClienteDTO;
import br.com.exemplo.lojavirtual.dto.RelatorioDTO;
import br.com.exemplo.lojavirtual.model.Categoria;
import br.com.exemplo.lojavirtual.model.Cliente;
import br.com.exemplo.lojavirtual.model.ItemPedido;
import br.com.exemplo.lojavirtual.model.Pedido;
import br.com.exemplo.lojavirtual.repository.CategoriaRepository;
import br.com.exemplo.lojavirtual.repository.ClienteRepository;
import br.com.exemplo.lojavirtual.repository.PedidoRepository;

@Service
public class RelatorioService {

    private final CategoriaRepository categoriaRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public RelatorioService(CategoriaRepository categoriaRepository, ClienteRepository clienteRepository,
            PedidoRepository pedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<RelatorioDTO> totalVendidoPorCategoria() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        List<RelatorioDTO> relatorio = categoriaRepository.findAll().stream()
                .map(categoria -> new RelatorioDTO(
                        categoria.getId(),
                        categoria.getNome(),
                        totalDaCategoria(pedidos, categoria)))
                .collect(Collectors.toList());

        relatorio.sort(Comparator.comparing(RelatorioDTO::getTotalVendido).reversed());

        return relatorio;
    }

    public List<RelatorioClienteDTO> totalComprasPorCliente() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        List<RelatorioClienteDTO> relatorio = clienteRepository.findAll().stream()
                .map(cliente -> new RelatorioClienteDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        totalDoCliente(pedidos, cliente)))
                .collect(Collectors.toList());

        relatorio.sort(Comparator.comparing(RelatorioClienteDTO::getTotalComprado).reversed());

        return relatorio;
    }

    private BigDecimal totalDaCategoria(List<Pedido> pedidos, Categoria categoria) {
        return pedidos.stream()
                .flatMap(pedido -> pedido.getItens().stream())
                .filter(item -> pertenceACategoria(item, categoria))
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal totalDoCliente(List<Pedido> pedidos, Cliente cliente) {
        return pedidos.stream()
                .filter(pedido -> pedido.getCliente().getId().equals(cliente.getId()))
                .flatMap(pedido -> pedido.getItens().stream())
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean pertenceACategoria(ItemPedido item, Categoria categoria) {
        Categoria categoriaDoProduto = item.getProduto().getCategoria();
        return categoriaDoProduto != null && categoriaDoProduto.getId().equals(categoria.getId());
    }
}
