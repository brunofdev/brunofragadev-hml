package com.brunofragadev.module.project.mapper;

import com.brunofragadev.module.project.dto.request.ProjectRequest;
import com.brunofragadev.module.project.dto.response.*;
import com.brunofragadev.module.project.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequest dto, Project project) {
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setVideo(dto.video());
        project.setStatus(dto.status());
        project.setPapel(dto.papel());
        project.setDataProjeto(dto.dataProjeto());
        project.setRepositorioUrl(dto.repositorioUrl());
        project.setLiveUrl(dto.liveUrl());

        if (dto.techs() != null) {
            TechnicalSheet ficha = new TechnicalSheet();
            ficha.setLinguagem(dto.techs().linguagem());
            ficha.setParadigma(dto.techs().paradigma());
            ficha.setFramework(dto.techs().framework());
            ficha.setBibliotecas(dto.techs().bibliotecas());
            ficha.setInfraestrutura(dto.techs().infraestrutura());
            project.setTechs(ficha);
        }

        if (dto.setup() != null) {
            ProjectSetup setup = new ProjectSetup();
            setup.setObs(dto.setup().obs());
            if (dto.setup().steps() != null) {
                List<SetupStep> passos = dto.setup().steps().stream().map(p -> {
                    SetupStep passo = new SetupStep();
                    passo.setNum(p.num());
                    passo.setText(p.text());
                    passo.setCmd(p.cmd());
                    return passo;
                }).collect(Collectors.toList());
                setup.setSteps(passos);
            }
            project.setSetup(setup);
        }
        if (dto.galeria() != null) {
            List<ProjectImage> imagens = dto.galeria().stream().map(imgDto -> {
                ProjectImage img = new ProjectImage();
                img.setUrlImagem(imgDto.urlImagem());
                img.setOrdemExibicao(imgDto.ordemExibicao());
                img.setIsCapa(imgDto.isCapa());
                img.setLegenda(imgDto.legenda());

                return img;
            }).collect(Collectors.toList());
            project.setGaleria(imagens);
        }

        return project;
    }

    public ProjectResponse toDTO(Project project) {
        TechnicalSheetDTO techsDTO = project.getTechs() != null ? new TechnicalSheetDTO(
                project.getTechs().getLinguagem(), project.getTechs().getParadigma(),
                project.getTechs().getFramework(), project.getTechs().getBibliotecas(),
                project.getTechs().getInfraestrutura()
        ) : null;

        ProjectSetupDTO setupDTO = null;
        if (project.getSetup() != null) {
            List<SetupStepDTO> passosDTO = project.getSetup().getSteps().stream()
                    .map(p -> new SetupStepDTO(p.getNum(), p.getText(), p.getCmd()))
                    .collect(Collectors.toList());
            setupDTO = new ProjectSetupDTO(project.getSetup().getObs(), passosDTO);
        }

        List<ProjectImageDTO> galeriaDTO = project.getGaleria().stream()
                .map(img -> new ProjectImageDTO(
                        img.getId(),
                        img.getUrlImagem(),
                        img.getOrdemExibicao(),
                        img.getIsCapa(),
                        img.getLegenda()
                ))
                .collect(Collectors.toList());

        return new ProjectResponse(
                project.getId(), project.getTitle(), project.getDescription(),
                project.getVideo(), project.getStatus(), project.getPapel(),
                project.getDataProjeto(), project.getRepositorioUrl(), project.getLiveUrl(),
                techsDTO, setupDTO, galeriaDTO
        );
    }
}