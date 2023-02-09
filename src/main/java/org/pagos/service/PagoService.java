package org.pagos.service;

import org.pagos.model.DeudaModel;
import org.pagos.model.PagoModel;
import org.pagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    private final DeudaService deudaService;
    private final SaldoService saldoService;

    public PagoService(DeudaService deudaService, SaldoService saldoService) {
        this.deudaService = deudaService;
        this.saldoService = saldoService;
    }

    //Registra el pago y reduce la deuda del usuario en el servicio
    public PagoModel registrarPago(PagoModel pagoModel)   {
        Long idUsuario = pagoModel.getUsuario().getIdUsuario();
        Long idServicio = pagoModel.getServicio().getIdServicio();
        List<DeudaModel> deuda = deudaService.obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio);
        pagoModel.setMontoDeudaTotal(deuda.get(0).getMontoDeuda());
        pagoModel.setStatus(Boolean.TRUE);
        pagoModel.setFechaRegistro(new Date());
        PagoModel pagoRetorno = pagoRepository.save(pagoModel);
        if(pagoRetorno.getIdPago() != null)
        {
            saldoService.reducirSaldoPorPago(idUsuario, pagoModel.getMontoAbonado());
            deudaService.reducirDeudaPorPago(idUsuario, idServicio, pagoModel.getMontoAbonado());
        }
        return pagoRetorno;
    }

    //Lista los pagos realizados durante un rango de fecha seleccionado por el usuario
    public Page<PagoModel> obtenerPagosPorRangoFechaPaginado(Integer pagina, Integer tamanhoPagina, String ordenColumn,
                                                     String direccion, String idUsuario, String fechaInicio, String fechaFin ) {
        Sort.Direction direction = "asc".equalsIgnoreCase(direccion) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort.Order order = new Sort.Order(direction, ordenColumn);
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by(order));

        Date dateStart = stringToDate(fechaInicio);
        Date dateEnd = sumarRestarDias(stringToDate(fechaFin), 1);

        if(idUsuario != null && !idUsuario.equals("null") && !idUsuario.equals(""))
        {
            return pagoRepository.findByUsuarioIdUsuarioAndStatusAndFechaRegistroBetween
                    (parseLong(idUsuario), true, dateStart, dateEnd, pageRequest);
        }
        else {
            return pagoRepository.findByStatusAndFechaRegistroBetween
                    (true, dateStart, dateEnd, pageRequest);
        }
    }

    //Lista los pagos realizados para cada servicio durante un rango de fecha seleccionado por el usuario
    public Page<PagoModel> obtenerPagosPorServicioPorRangoFechaPaginado(Integer pagina, Integer tamanhoPagina, String ordenColumn,
                                                     String direccion, String idUsuario, String idServicio, String fechaInicio, String fechaFin ) {
        Sort.Direction direction = "asc".equalsIgnoreCase(direccion) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort.Order order = new Sort.Order(direction, ordenColumn);
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by(order));

        Date dateStart = stringToDate(fechaInicio);
        Date dateEnd = sumarRestarDias(stringToDate(fechaFin), 1);

        if(idUsuario != null && !idUsuario.equals("null") && !idUsuario.equals(""))
        {
            return pagoRepository.findByStatusAndUsuarioIdUsuarioAndServicioIdServicioAndFechaRegistroBetween
                    (true, parseLong(idUsuario), parseLong(idServicio), dateStart, dateEnd, pageRequest);
        }
        else {
            return pagoRepository.findByStatusAndServicioIdServicioAndFechaRegistroBetween
                    (true, parseLong(idServicio), dateStart, dateEnd, pageRequest);
        }
    }

    //Convierte String a Date
    public Date stringToDate(String fechaConvertir){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(fechaConvertir, new ParsePosition(0));
    }

    //Suma o resta de fechas
    public Date sumarRestarDias(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }

}