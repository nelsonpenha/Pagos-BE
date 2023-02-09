package org.pagos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="pago")
public class PagoModel implements Serializable {

    private static final long serialVersionUID = -7148768989981739988L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pago")
    private Long idPago;
    @Column(name="numero_referencia_comprobante")
    private String numeroReferenciaComprobante;
    @Column(name="monto_deuda_total")
    private String montoDeudaTotal;
    @Column(name="monto_abonado")
    private String montoAbonado;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="id_servicio")
    private ServicioModel servicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @Column(name="status")
    private Boolean status;

    public PagoModel() { //Constructor
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public String getNumeroReferenciaComprobante() {
        return numeroReferenciaComprobante;
    }

    public void setNumeroReferenciaComprobante(String numeroReferenciaComprobante) {
        this.numeroReferenciaComprobante = numeroReferenciaComprobante;
    }

    public String getMontoDeudaTotal() {
        return montoDeudaTotal;
    }

    public void setMontoDeudaTotal(String montoDeudaTotal) {
        this.montoDeudaTotal = montoDeudaTotal;
    }

    public String getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(String montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ServicioModel getServicio() {
        return servicio;
    }

    public void setServicio(ServicioModel servicio) {
        this.servicio = servicio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}