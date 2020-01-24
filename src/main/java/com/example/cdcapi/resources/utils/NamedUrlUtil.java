package com.example.cdcapi.resources.utils;

import org.springframework.stereotype.Controller;

@Controller
public class NamedUrlUtil {
    public static final String AUTORES_URL_DEFAULT = "autores";
    public static final String CATEGORIA_URL_DEFAULT = "categorias";
    public static final String LIVROS_URL_DEFAULT = "livros";
    public static final String USUARIOS_URL_DEFAULT = "usuarios";


    public static final String NAME_URL_SEARCH = "buscar";
    public static final String NAME_URL_SAVE = "cadastrar";
    public static final String NAME_URL_SAVE_REAL_TIME = "realtime/cadastrar";
    public static final String NAME_URL_UPDATE = "atualizar";
    public static final String NAME_URL_DELETE = "deletar";
}
