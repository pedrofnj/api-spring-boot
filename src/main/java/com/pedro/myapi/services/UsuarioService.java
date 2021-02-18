package com.pedro.myapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.myapi.domain.Usuario;
import com.pedro.myapi.exceptions.ObjectNotFoundException;
import com.pedro.myapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	// buscando por id e tratamennto exceptions
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	//listando usuarios
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	//updated de usuarios
	public Usuario update(Integer id, Usuario obj) {
		Usuario newObj = findById(id);
		newObj.setNome(obj.getNome());
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
		return repository.save(newObj);
	}
	
	// crianndo usuario
	public Usuario create(Usuario obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	//deletando usuario
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
		
	}

}
