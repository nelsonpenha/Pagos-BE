package org.pagos.repository;

import org.pagos.model.PagoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PagoRepository extends JpaRepository<PagoModel, Long> {

    Page<PagoModel> findByUsuarioIdUsuarioAndStatusAndFechaRegistroBetween
            (Long idUsuario, Boolean status, Date fechaInicio, Date fechaFin, Pageable pageable);

    Page<PagoModel> findByStatusAndFechaRegistroBetween
            (Boolean status, Date fechaInicio, Date fechaFin, Pageable pageable);

    Page<PagoModel> findByStatusAndUsuarioIdUsuarioAndServicioIdServicioAndFechaRegistroBetween
            (Boolean status, Long idUsuario, Long idServicio, Date fechaInicio, Date fechaFin, Pageable pageable);

    Page<PagoModel> findByStatusAndServicioIdServicioAndFechaRegistroBetween
            (Boolean status, Long idServicio, Date fechaInicio, Date fechaFin, Pageable pageable);


}
