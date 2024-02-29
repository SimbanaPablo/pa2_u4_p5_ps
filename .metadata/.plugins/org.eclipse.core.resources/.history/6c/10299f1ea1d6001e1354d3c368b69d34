package com.uce.edu.ec.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.ec.repository.modelo.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.persist(persona);
	}

	@Override
	public void actualizar(Persona persona) {
		// TODO Auto-generated method stub
		this.entityManager.merge(persona);
	}

	@Override
	public Persona consultarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Persona> mt = this.entityManager.createQuery("SELECT p FROM Persona p WHERE p.cedula =:cedula",
				Persona.class);
		mt.setParameter("cedula", cedula);
		return mt.getSingleResult();
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.consultarPorCedula(cedula));

	}

	@Override
	public List<Persona> consultarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Persona> mt = this.entityManager.createQuery("SELECT p FROM Persona p", Persona.class);
		return mt.getResultList();
	}

}
