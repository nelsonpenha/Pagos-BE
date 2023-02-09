package org.pagos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="saldo")
public class SaldoModel implements Serializable {

    private static final long serialVersionUID = -8134865159676458187L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_saldo")
    private Long idSaldo;
    @Column(name="monto_saldo")
    private String montoSaldo;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @Column(name="status")
    private Boolean status;

    public SaldoModel() {//Constructor
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Long idSaldo) {
        this.idSaldo = idSaldo;
    }

    public String getMontoSaldo() {
        return montoSaldo;
    }

    public void setMontoSaldo(String montoSaldo) {
        this.montoSaldo = montoSaldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}