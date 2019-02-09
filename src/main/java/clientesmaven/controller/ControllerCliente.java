/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesmaven.controller;

import java.util.List;
import clientesmaven.model.entities.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import clientesmaven.model.ModelCliente;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Roxiis Ulloa
 */
@SessionScoped
@ManagedBean
public class ControllerCliente {
    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String direccion;
    private String telefono;
    private List<Cliente> listaClientes;

    private Cliente cliente;
    @EJB
    ModelCliente modelCliente = new ModelCliente();
    
    public void actionListenerListarClientes(){
        listaClientes = modelCliente.listarClientes();
    }
    
    public void actionListenerInsertarCliente(){
        //llamar ws
        Cliente cli = modelCliente.insertarCliente(cedula, nombre, apellido, contrasenia, direccion, telefono);
        if(cli!=null){
            FacesMessage msj = new FacesMessage("Creado correctamente!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }
        else{
            FacesMessage msj = new FacesMessage("NO se ha podido crear!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }
        
    }
    
    public void actionListenerEliminarCliente(){
        //llamar ws
        if(modelCliente.eliminarCliente(cedula)){
            FacesMessage msj = new FacesMessage("Eliminacion realizada!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }else{
            FacesMessage msj = new FacesMessage("No eliminado!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }
    }
    
    public void actionListenerActualizarCliente(){
        //llamar ws
        if(modelCliente.actualizarCliente(cedula, nombre, apellido, contrasenia, direccion, telefono)){
            FacesMessage msj = new FacesMessage("Actualizado correctamente!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }else{
            FacesMessage msj = new FacesMessage("NO actualizado!!");
            FacesContext.getCurrentInstance().addMessage(cedula, msj);
        }
    }
    
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
}
