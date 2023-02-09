package org.pagos.controller;
import org.pagos.service.DeudaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deuda")
public class DeudaController {

    private final DeudaService deudaService;

    public DeudaController(DeudaService deudaService) {
        this.deudaService = deudaService;
    }

    @GetMapping("/")
    public Object getDeudas(@RequestParam Long idUsuario, @RequestParam Long idServicio) {
        try {
            return new ResponseEntity<>(deudaService.obtenerDeudaPorUsuarioYServicio(idUsuario, idServicio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verificar/")
    public Object verificarExistenciaDeuda(@RequestParam Long idUsuario, @RequestParam Long idServicio) {
        try {
            return new ResponseEntity<>(deudaService.verificarExistenciaDeuda(idUsuario, idServicio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
