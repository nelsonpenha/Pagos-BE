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
@Table(name="deuda")
public class DeudaModel implements Serializable {

    private static final long serialVersionUID = 5088054738483705875L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_deuda")
    private Long idDeuda;
    @Column(name="monto_deuda")
    private String montoDeuda;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="id_servicio")
    private ServicioModel servicio;
    @Column(name="status")
    private Boolean status;

    public DeudaModel() {
        //Constructor
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(Long idDeuda) {
        this.idDeuda = idDeuda;
    }

    public String getMontoDeuda() {
        return montoDeuda;
    }

    public void setMontoDeuda(String montoDeuda) {
        this.montoDeuda = montoDeuda;
    }

    public Usuario getUsuarioModel() {
        return usuario;
    }

    public void setUsuarioModel(Usuario usuario) {
        this.usuario = usuario;
    }

    public ServicioModel getServicioModel() {
        return servicio;
    }

    public void setServicioModel(ServicioModel servicio) {
        this.servicio = servicio;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}