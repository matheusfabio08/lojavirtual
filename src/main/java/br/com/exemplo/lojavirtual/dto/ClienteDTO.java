package br.com.exemplo.lojavirtual.dto;

import java.time.LocalDateTime;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataHoraAtualizacao;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String email, LocalDateTime dataHoraAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }
}
