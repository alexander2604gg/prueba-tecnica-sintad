package com.alexandersaul.pruebatecnica.service;

import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;

public interface EntidadValidatorService {
    TipoDocumento validateTipoDocumento(Integer idTipoDocumento);
    TipoContribuyente validateTipoContribuyente(Integer idTipoContribuyente);
}
