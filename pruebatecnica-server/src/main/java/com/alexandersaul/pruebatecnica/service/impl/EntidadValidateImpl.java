package com.alexandersaul.pruebatecnica.service.impl;

import com.alexandersaul.pruebatecnica.entity.TipoContribuyente;
import com.alexandersaul.pruebatecnica.entity.TipoDocumento;
import com.alexandersaul.pruebatecnica.exception.ResourceNotFoundException;
import com.alexandersaul.pruebatecnica.repository.TipoDocumentoRepository;
import com.alexandersaul.pruebatecnica.repository.TipoContribuyenteRepository;
import com.alexandersaul.pruebatecnica.service.EntidadValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntidadValidateImpl implements EntidadValidatorService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private TipoContribuyenteRepository tipoContribuyenteRepository;


    @Override
    public TipoDocumento validateTipoDocumento(Integer idTipoDocumento) {
        return tipoDocumentoRepository.findById(idTipoDocumento)
                .orElseThrow(() -> new ResourceNotFoundException("TipoDocumento" , "idTipoDocumento" ,idTipoDocumento.toString() ));
    }

    @Override
    public TipoContribuyente validateTipoContribuyente(Integer idTipoContribuyente) {
        return tipoContribuyenteRepository.findById(idTipoContribuyente)
                .orElseThrow(() -> new ResourceNotFoundException("TipoContribuyente" , "idTipoDocumento" , idTipoContribuyente.toString()));
    }

}
