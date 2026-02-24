package com.brunofragadev.projetos.entitys;

import com.brunofragadev.security.auditoria.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String video;
    private String status;
    private String papel;
    private String dataProjeto;
    private String repositorioUrl;
    private String liveUrl;

    // 👇 O Hibernate vai pegar esses campos e criar colunas na própria tabela tb_projetos
    @Embedded
    private FichaTecnica techs;

    // 👇 O Hibernate gerencia a tabela auxiliar de passos do setup automaticamente
    @Embedded
    private SetupProjeto setup;

    // 👇 O Relacionamento 1 para N com a Galeria, já trazendo na ordem correta
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("ordemExibicao ASC")
    private List<ImagemProjeto> galeria = new ArrayList<>();

    // MÉTODOS AUXILIARES: Mantém a consistência bidirecional ao salvar do React
    public void setGaleria(List<ImagemProjeto> imagens) {
        this.galeria.clear();
        if (imagens != null) {
            imagens.forEach(img -> img.setProjeto(this)); // Carimba o ID do projeto na imagem
            this.galeria.addAll(imagens);
        }
    }
}