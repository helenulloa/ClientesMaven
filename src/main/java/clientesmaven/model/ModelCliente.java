/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesmaven.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import clientesmaven.model.entities.Cliente;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Roxiis Ulloa
 */
@Stateless
public class ModelCliente {

    @PersistenceContext(unitName="cliente_ds")
    private EntityManager em;
  
    public List<Cliente> listarClientes(){
        Query q = em.createNamedQuery("Cliente.findAll", Cliente.class);
        return q.getResultList();
    }
    
    public Cliente insertarCliente(String cedula, String nombre, String apellido, String contrasenia, String direccion, String telefono){
        
        Cliente cli =  new Cliente(); // em.find(Cliente.class, cedula);     
        cli.setCedula(cedula);
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setContrasenia(contrasenia);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        em.persist(cli);
        return cli;
    }
    
    public boolean actualizarCliente(String cedula, String nombre, String apellido, String contrasenia, String direccion, String telefono){
       
        Cliente cli =  (Cliente) em.find(Cliente.class, cedula);
        if (cli == null)
            return false;
        cli.setCedula(cedula);
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setContrasenia(contrasenia);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        em.merge(cli);
        return true;
    }
    
    public boolean eliminarCliente(String cedula){
        Cliente cli = (Cliente)em.find(Cliente.class, cedula) ;
        if(cli == null)
            return false;
        em.remove(cli);
        return true;
    }
    
}
