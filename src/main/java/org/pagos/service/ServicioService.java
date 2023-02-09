package org.pagos.service;

import org.pagos.model.ServicioModel;
import org.pagos.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public ServicioService() {
        //Constructor
    }

    public List<ServicioModel> getServicios(){
        return servicioRepository.findAll();
    }

}
