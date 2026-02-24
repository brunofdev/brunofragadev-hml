package com.brunofragadev.projetos.entitys;

import com.brunofragadev.security.auditoria.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_imagens_projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagemProjeto extends Auditable { // 👈 HERDANDO A SUA AUDITORIA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String urlImagem;

    @Column(nullable = false)
    private Integer ordemExibicao;

    private Boolean isCapa = false;

    // 👇 Relacionamento de volta para o Projeto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    @JsonIgnore // Evita loop infinito na hora de devolver o JSON pro Front-end
    private Projeto projeto;
}