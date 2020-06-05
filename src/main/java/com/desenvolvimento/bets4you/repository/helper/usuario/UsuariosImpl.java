package com.desenvolvimento.bets4you.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.desenvolvimento.bets4you.model.Usuario;
import org.springframework.transaction.annotation.Transactional;

public class UsuariosImpl implements UsuariosQueries{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Optional<Usuario> porEmail(String email) {
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email)", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}


	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

	@Override
	@Transactional
	public void atualizarEmailValidation(Long idUsuario) {
		manager.createQuery("UPDATE Usuario SET email_validation = true WHERE id = :id")
				.setParameter("id", idUsuario)
				.executeUpdate();

	}

	@Override
	@Transactional
	public void atualizarAcessoVip(Long idUsuario) {
		manager.createQuery("UPDATE Usuario SET acesso_vip = true WHERE id = :id")
				.setParameter("id", idUsuario)
				.executeUpdate();
	}

	@Override
	@Transactional
	public void retirarAcessoVip(Long idUsuario) {
		manager.createQuery("UPDATE Usuario SET acesso_vip = false WHERE id = :id")
				.setParameter("id", idUsuario)
				.executeUpdate();
	}

}
