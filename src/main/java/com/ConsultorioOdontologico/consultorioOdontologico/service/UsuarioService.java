
package com.ConsultorioOdontologico.consultorioOdontologico.service;

import com.ConsultorioOdontologico.consultorioOdontologico.dto.LoginDto;
import com.ConsultorioOdontologico.consultorioOdontologico.model.Usuario;
import com.ConsultorioOdontologico.consultorioOdontologico.repository.IUsuarioRepository;
import com.ingeneo.app.utils.hash.BCrypt;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private IUsuarioRepository usuRepo;

    @Override
    public List<Usuario> getUsuario() {
        return usuRepo.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario u) {
        
        u.setContrasenia(BCrypt.hashpw(u.getContrasenia(), BCrypt.gensalt()));
        Usuario usu = usuRepo.save(u);
        
        return usu;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuRepo.deleteById(id);
    }

    @Override
    public Usuario findUsuario(Long id) {
        return usuRepo.findById(id).orElse(null);
    }

    @Override
    public void editUsuario(Usuario u) {
        this.saveUsuario(u);
    }

    @Override
    public int validarUsuario(LoginDto l) {
        int valido = 0;
        
        List<Usuario> usuarios = this.getUsuario();
        
        for (Usuario usu : usuarios) {
            String pass = usu.getContrasenia();
            
            if(usu.getUsuario().equals(l.getUsername()) && BCrypt.checkpw(l.getContrasenia(), pass)) {
                valido = 1;
            }
        }
        
        return valido;
    }
    
}
