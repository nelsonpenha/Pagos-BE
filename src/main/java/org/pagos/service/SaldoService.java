package org.pagos.service;

import org.pagos.model.DeudaModel;
import org.pagos.model.SaldoModel;
import org.pagos.repository.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    private final DeudaService deudaService;

    public SaldoService(DeudaService deudaService) {
        this.deudaService = deudaService;
    }

    //Se controla que el usuario cuente con el saldo suficiente para cubrir la deuda
    public Boolean verificarSaldoMayorDeuda(Long idUsuario, Long idServicio)   {
        List<SaldoModel> saldo = obtenerSaldoPorUsuario(idUsuario);
        if(verificarExistenciaSaldo(idUsuario))
        {
            List<DeudaModel> deuda = deudaService.obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio);
            return deuda.isEmpty() || Integer.parseInt(saldo.get(0).getMontoSaldo()) >= Integer.parseInt(deuda.get(0).getMontoDeuda());
        }
        else return false;
    }

    //Se controla si el usuario cuenta con saldo disponible en su cuenta
    public Boolean verificarExistenciaSaldo(Long idUsuario)   {
        List<SaldoModel> saldo = obtenerSaldoPorUsuario(idUsuario);
        return !saldo.isEmpty() && Integer.parseInt(saldo.get(0).getMontoSaldo()) > 0;
    }

    //Se verifica si el usuario cuenta con el monto que desea pagar
    public Boolean verificarExistenciaMontoSuficiente(Long idUsuario, String montoPagar)   {
        List<SaldoModel> saldo = obtenerSaldoPorUsuario(idUsuario);
        return !saldo.isEmpty() && Integer.parseInt(saldo.get(0).getMontoSaldo()) >= Integer.parseInt(montoPagar) ;
    }

    //se lista el disponible por cada usuario
    public List<SaldoModel> obtenerSaldoPorUsuario(Long idUsuario){
        return saldoRepository.findByUsuarioIdUsuarioAndStatus(idUsuario, true);
    }

    //Luego de realizar el pago se reduce el saldo del usuario
    public void reducirSaldoPorPago(Long idUsuario, String montoReducir ) {
        List<SaldoModel> saldo = obtenerSaldoPorUsuario(idUsuario);
        saldo.get(0).setMontoSaldo(Integer.toString(Integer.parseInt(saldo.get(0).getMontoSaldo()) - Integer.parseInt(montoReducir)));
        saldoRepository.save(saldo.get(0));
    }


}