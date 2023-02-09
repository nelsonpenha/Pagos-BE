package org.pagos.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -6731479269087455007L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    @NotNull
    @Column(name="nombre")
    private String nombre;
    @NotNull
    @Column(unique = true, name = "numero_documento")
    private String documentoNumero;
    @NotNull
    @Column(unique = true, name = "email")
    private String email;
    @NotNull
    @Column(name="password")
    private String password;

    public Usuario() {
        //Constructor
    }

    public Usuario(@NotNull String nombre, @NotNull String documentoNumero, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.documentoNumero = documentoNumero;
        this.email = email;
        this.password = password;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
