package org.pagos.controller;
import org.pagos.service.SaldoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/saldo")
public class SaldoController {

    private final SaldoService saldoService;

    public SaldoController(SaldoService saldoService) {
        this.saldoService = saldoService;
    }

    @GetMapping("/")
    public Object getSaldo(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(saldoService.obtenerSaldoPorUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verificar/")
    public Object verificarExistenciaSaldo(@RequestParam Long idUsuario) {
        try {
            return new ResponseEntity<>(saldoService.verificarExistenciaSaldo(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deuda/")
    public Object verificarSaldoMayorDeuda(@RequestParam Long idUsuario, @RequestParam Long idServicio) {
        try {
            return new ResponseEntity<>(saldoService.verificarSaldoMayorDeuda(idUsuario, idServicio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
