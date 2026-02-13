package com.system.unipar.service;

import com.system.unipar.model.Usuario;
import com.system.unipar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        // Gerar login: CPF
        String login = usuario.getCpf();
        
        // Gerar senha: primeira palavra do nome + últimos 3 dígitos do CPF
        String primeiraPalavra = usuario.getNome().split(" ")[0];
        String ultimos3Digitos = usuario.getCpf().substring(usuario.getCpf().length() - 3);
        String senha = primeiraPalavra + ultimos3Digitos;
        
        usuario.setSenha(senha);
        
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorLogin(String login, String senha) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getCpf().equals(login) && u.getSenha().equals(senha))
                .findFirst();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
