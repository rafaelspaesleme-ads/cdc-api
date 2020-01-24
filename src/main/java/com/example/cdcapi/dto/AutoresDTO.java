package com.example.cdcapi.dto;

public class AutoresDTO {
    private Long id;
    private String nome;
    private String email;

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

    public static final class AutoresDTOBuilder {
        private AutoresDTO autoresDTO;

        private AutoresDTOBuilder() {
            autoresDTO = new AutoresDTO();
        }

        public static AutoresDTOBuilder anAutoresDTO() {
            return new AutoresDTOBuilder();
        }

        public AutoresDTOBuilder comId(Long id) {
            autoresDTO.setId(id);
            return this;
        }

        public AutoresDTOBuilder comNome(String nome) {
            autoresDTO.setNome(nome);
            return this;
        }

        public AutoresDTOBuilder comEmail(String email) {
            autoresDTO.setEmail(email);
            return this;
        }

        public AutoresDTO build() {
            return autoresDTO;
        }
    }
}
