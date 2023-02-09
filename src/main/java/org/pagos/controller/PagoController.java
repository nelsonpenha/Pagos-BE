package org.pagos.controller;
import org.pagos.model.PagoModel;
import org.pagos.service.DeudaService;
import org.pagos.service.PagoService;
import org.pagos.service.SaldoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final PagoService pagoService;
    private final DeudaService deudaService;
    private final SaldoService saldoService;

    public PagoController(PagoService pagoService, DeudaService deudaService, SaldoService saldoService) {
        this.pagoService = pagoService;
        this.deudaService = deudaService;
        this.saldoService = saldoService;
    }

    @PostMapping("/")
    public @ResponseBody
    Object registrarPago(@RequestBody PagoModel pagoModel) {
        if(!deudaService.verificarExistenciaDeuda(pagoModel.getUsuario().getIdUsuario(), pagoModel.getServicio().getIdServicio()))
            return new ResponseEntity<>("No existe deuda para este servicio", HttpStatus.BAD_REQUEST);
        if(!saldoService.verificarExistenciaMontoSuficiente(pagoModel.getUsuario().getIdUsuario(), pagoModel.getMontoAbonado()))
            return new ResponseEntity<>("El monto que se desea abonar excede el saldo disponible", HttpStatus.BAD_REQUEST);
        if(deudaService.verificarMontoDeuda(pagoModel.getUsuario().getIdUsuario(), pagoModel.getServicio().getIdServicio(),
                                            pagoModel.getMontoAbonado()) )
            return new ResponseEntity<>("El monto a pagar excede la deuda", HttpStatus.BAD_REQUEST);
        if(!saldoService.verificarSaldoMayorDeuda(pagoModel.getUsuario().getIdUsuario(), pagoModel.getServicio().getIdServicio()))
            return new ResponseEntity<>("No se cuenta con el saldo suficiente", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(pagoService.registrarPago(pagoModel), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public Object obtenerPagosPorRangoFechaPaginado(@RequestParam Integer pagina, @RequestParam Integer tamanhoPagina,
                                         @RequestParam String ordenColumn, @RequestParam String direccion,
                                         @RequestParam(required = false) String idUsuario,
                                         @RequestParam(required = false) String fechaInicio,
                                         @RequestParam(required = false) String fechaFin) {
        try {
            Page<PagoModel> pago = pagoService.obtenerPagosPorRangoFechaPaginado(pagina, tamanhoPagina,
                    ordenColumn, direccion, idUsuario, fechaInicio, fechaFin);
            HashMap<String, Object> response = new HashMap<>();
            response.put("total", pago.getTotalElements());
            response.put("results", pago.getContent());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/servicio/")
    public Object obtenerPagosPorServicioPorRangoFechaPaginado(@RequestParam Integer pagina, @RequestParam Integer tamanhoPagina,
                                         @RequestParam String ordenColumn, @RequestParam String direccion,
                                         @RequestParam(required = false) String idUsuario,
                                         @RequestParam(required = false) String idServicio,
                                         @RequestParam(required = false) String fechaInicio,
                                         @RequestParam(required = false) String fechaFin) {
        try {
            Page<PagoModel> pago = pagoService.obtenerPagosPorServicioPorRangoFechaPaginado(pagina, tamanhoPagina,
                    ordenColumn, direccion, idUsuario, idServicio, fechaInicio, fechaFin);
            HashMap<String, Object> response = new HashMap<>();
            response.put("total", pago.getTotalElements());
            response.put("results", pago.getContent());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
