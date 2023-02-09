package org.pagos.repository;

import org.pagos.model.DeudaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeudaRepository extends JpaRepository<DeudaModel, Long> {

    List<DeudaModel> findByUsuarioIdUsuarioAndServicioIdServicioAndStatus(Long idUsuario, Long idServicio, Boolean status);
}
