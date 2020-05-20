package com.desenvolvimento.bets4you.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "usuario_plano")
public class UsuarioPlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plano")
    private Plano plano;

    @Column(name="inicio_plano")
    @NotNull
    private LocalDate inicioPlano = LocalDate.now();

    @Column(name="termino_plano")
    @NotNull
    private LocalDate terminoPlano = LocalDate.now();

    public LocalDate getInicioPlano() {
        return inicioPlano;
    }

    public void setInicioPlano(LocalDate inicioPlano) {
        this.inicioPlano = inicioPlano;
    }

    public LocalDate getTerminoPlano() {
        return terminoPlano;
    }

    public void setTerminoPlano(LocalDate terminoPlano) {
        this.terminoPlano = terminoPlano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}
