package org.pagos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="servicio")
public class ServicioModel implements Serializable {

    private static final long serialVersionUID = -3776566456060519355L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_servicio")
    private Long idServicio;
    @Column(name="nombre")
    private String nombre;
    @Column(name="status")
    private Boolean status;

    public ServicioModel() {
        //Contructor
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}