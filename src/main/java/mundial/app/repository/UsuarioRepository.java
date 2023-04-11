package mundial.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mundial.app.Entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
