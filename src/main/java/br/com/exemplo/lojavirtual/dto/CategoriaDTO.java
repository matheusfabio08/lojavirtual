package br.com.exemplo.lojavirtual.dto;

import java.time.LocalDateTime;

public class CategoriaDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataHoraLancamento;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome, LocalDateTime dataHoraLancamento) {
        this.id = id;
        this.nome = nome;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataHoraLancamento() {
        return dataHoraLancamento;
    }

    public void setDataHoraLancamento(LocalDateTime dataHoraLancamento) {
        this.dataHoraLancamento = dataHoraLancamento;
    }
}
