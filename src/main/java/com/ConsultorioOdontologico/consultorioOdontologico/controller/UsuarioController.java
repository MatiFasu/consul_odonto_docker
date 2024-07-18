
package com.ConsultorioOdontologico.consultorioOdontologico.controller;

import com.ConsultorioOdontologico.consultorioOdontologico.dto.LoginDto;
import com.ConsultorioOdontologico.consultorioOdontologico.model.Usuario;
import com.ConsultorioOdontologico.consultorioOdontologico.service.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*@CrossOrigin("http://localhost:3000")*/
public class UsuarioController {
   
    @Autowired
    private IUsuarioService usuServ;
    
    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/usuario/traer")
    public List<Usuario> getUsuarios() {
        return usuServ.getUsuario();
    }
    
    @GetMapping("/usuario/traer/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuServ.findUsuario(id);
    }
    
    @PostMapping("/usuario/crear")
    public Long saveUsuario(@RequestBody Usuario u) {
        // Guardar el nuevo usuario en la base de datos
        Usuario usuarioGuardado = usuServ.saveUsuario(u);
        
        // Obtener el ID del usuario guardado
        Long idUsuario = usuarioGuardado.getId_usuario();
        
        // Devolver el ID en la respuesta
        return idUsuario;
    }
    
    // Validar usuario
    @PostMapping("/usuario/login")
    public int login(@RequestBody LoginDto l) {
        int validar = usuServ.validarUsuario(l);
        return validar;
    }
    
    @DeleteMapping("/usuario/borrar/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        usuServ.deleteUsuario(id);
        return "Usuario borrado correctamente!";
    }
    
    @PutMapping("/usuario/editar")
    public String editUsuario(@RequestBody Usuario u) {
        usuServ.editUsuario(u);
        return "Usuario editado correctamente!";
    }
    
    
}
