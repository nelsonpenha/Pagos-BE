package org.pagos.repository;

import org.pagos.model.SaldoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaldoRepository extends JpaRepository<SaldoModel, Long> {
    List<SaldoModel> findByUsuarioIdUsuarioAndStatus(Long idUsuario, Boolean status);
}
