package org.pagos.service;

import org.pagos.model.DeudaModel;
import org.pagos.model.SaldoModel;
import org.pagos.repository.DeudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DeudaService {

    @Autowired
    private DeudaRepository deudaRepository;

    public DeudaService() { //Constructor
    }

    //Listar las deudas por usuario de acuerdo a cada servicio
    public List<DeudaModel> obtenerDeudaPorUsuarioYServicio(Long idUsuario, Long idServicio){
        return deudaRepository.findByUsuarioIdUsuarioAndServicioIdServicioAndStatus(idUsuario, idServicio,true);
    }

    //Luego de realizar el pago se reduce la deuda
    public void reducirDeudaPorPago(Long idUsuario, Long idServicio, String montoReducir ) {
        List<DeudaModel> deuda = obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio);
        deuda.get(0).setMontoDeuda(Integer.toString(Integer.parseInt(deuda.get(0).getMontoDeuda()) - Integer.parseInt(montoReducir)));
        deudaRepository.save(deuda.get(0));
    }

    //Se controla si el usuario cuenta con deuda en el servicio a pagar
    public Boolean verificarExistenciaDeuda(Long idUsuario, Long idServicio)   {
        List<DeudaModel> deuda = obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio);
        return !deuda.isEmpty() && Integer.parseInt(deuda.get(0).getMontoDeuda()) > 0;
    }

    //Verifica si la deuda es menor o igual al monto que se quiere pagar
    public Boolean verificarMontoDeuda(Long idUsuario, Long idServicio, String montoPagar)   {
        List<DeudaModel> deuda = obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio);
        return !deuda.isEmpty() && Integer.parseInt(montoPagar) >= Integer.parseInt(deuda.get(0).getMontoDeuda()) ;
    }

}
