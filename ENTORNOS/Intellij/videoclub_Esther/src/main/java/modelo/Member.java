package modelo;

import java.util.Objects;

public class Member {

  private String nif;
  private String nombre;
  private String direccion;
  private String telefono;
  private int edad;
  private boolean sancionado;


  public Member(String nif, String nombre, String direccion, String telefono, int edad) {
    this.nif = nif;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.edad = edad;
    this.sancionado = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return edad == member.edad &&
            sancionado == member.sancionado &&
            Objects.equals(nif, member.nif) &&
            Objects.equals(nombre, member.nombre) &&
            Objects.equals(direccion, member.direccion) &&
            Objects.equals(telefono, member.telefono);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nif, nombre, direccion, telefono, edad, sancionado);
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public boolean isSancionado() {
    return sancionado;
  }

  public void setSancionado(boolean sancionado) {
    this.sancionado = sancionado;
  }
}