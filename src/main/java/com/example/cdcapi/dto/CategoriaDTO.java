package com.example.cdcapi.dto;

public class CategoriaDTO {
    private Long id;
    private String descricao;
    private Boolean ativo;

    public CategoriaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public static final class CategoriaDTOBuilder {
        private CategoriaDTO categoriaDTO;

        private CategoriaDTOBuilder() {
            categoriaDTO = new CategoriaDTO();
        }

        public static CategoriaDTOBuilder aCategoriaDTO() {
            return new CategoriaDTOBuilder();
        }

        public CategoriaDTOBuilder comId(Long id) {
            categoriaDTO.setId(id);
            return this;
        }

        public CategoriaDTOBuilder comDescricao(String descricao) {
            categoriaDTO.setDescricao(descricao);
            return this;
        }

        public CategoriaDTOBuilder comAtivo(Boolean ativo) {
            categoriaDTO.setAtivo(ativo);
            return this;
        }

        public CategoriaDTO build() {
            return categoriaDTO;
        }
    }
}
