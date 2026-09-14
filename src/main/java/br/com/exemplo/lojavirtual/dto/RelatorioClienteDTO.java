package br.com.exemplo.lojavirtual.dto;

import java.math.BigDecimal;

public class RelatorioClienteDTO {

    private Long clienteId;
    private String clienteNome;
    private BigDecimal totalComprado;

    public RelatorioClienteDTO() {
    }

    public RelatorioClienteDTO(Long clienteId, String clienteNome, BigDecimal totalComprado) {
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.totalComprado = totalComprado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public BigDecimal getTotalComprado() {
        return totalComprado;
    }

    public void setTotalComprado(BigDecimal totalComprado) {
        this.totalComprado = totalComprado;
    }
}
