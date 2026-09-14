package br.com.exemplo.lojavirtual.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.lojavirtual.dto.ItemPedidoRequestDTO;
import br.com.exemplo.lojavirtual.dto.PedidoDTO;
import br.com.exemplo.lojavirtual.dto.PedidoRequestDTO;
import br.com.exemplo.lojavirtual.exception.RecursoNaoEncontradoException;
import br.com.exemplo.lojavirtual.mapper.PedidoMapper;
import br.com.exemplo.lojavirtual.model.Cliente;
import br.com.exemplo.lojavirtual.model.ItemPedido;
import br.com.exemplo.lojavirtual.model.Pedido;
import br.com.exemplo.lojavirtual.model.Produto;
import br.com.exemplo.lojavirtual.repository.PedidoRepository;

@Service
@Transactional(readOnly = true)
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository repository, ClienteService clienteService, ProdutoService produtoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public List<PedidoDTO> listarTodos() {
        return repository.findAll().stream().map(PedidoMapper::paraDTO).collect(Collectors.toList());
    }

    public List<PedidoDTO> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId).stream().map(PedidoMapper::paraDTO).collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(Long id) {
        return PedidoMapper.paraDTO(buscarEntidade(id));
    }

    @Transactional
    public PedidoDTO criar(PedidoRequestDTO dto) {
        Cliente cliente = clienteService.buscarEntidade(dto.getClienteId());

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Produto produto = produtoService.buscarEntidade(itemDto.getProdutoId());

            if (itemDto.getQuantidade() > produto.getQuantidadeEmEstoque()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o produto '" + produto.getNome() + "': "
                                + "disponível " + produto.getQuantidadeEmEstoque()
                                + ", solicitado " + itemDto.getQuantidade());
            }

            ItemPedido item = new ItemPedido(produto, itemDto.getQuantidade(), produto.getPreco());
            pedido.adicionarItem(item);

            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - itemDto.getQuantidade());
        }

        return PedidoMapper.paraDTO(repository.save(pedido));
    }

    @Transactional
    public void deletar(Long id) {
        Pedido pedido = buscarEntidade(id);
        repository.delete(pedido);
    }

    private Pedido buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: id " + id));
    }
}
