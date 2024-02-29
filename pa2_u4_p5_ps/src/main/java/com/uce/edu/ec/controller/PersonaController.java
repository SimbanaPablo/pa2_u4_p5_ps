package com.uce.edu.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.ec.repository.modelo.Persona;
import com.uce.edu.ec.service.IPersonaService;

//http://localhost:8080/personas/buscarTodos
@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	public IPersonaService personaService;

	// GET
	@GetMapping("/buscarTodos")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.personaService.consultarTodos();
		modelo.addAttribute("personas", lista);
		return "vistaListaPersonas";
	}

	// Cuando viaja el el modelo en el response
	// buscarporCedula/1752245041
	@GetMapping("/buscarPorCedula/{cedula}")
	public String buscarPorCedula(@PathVariable("cedula") String cedula, Model modelo) {
		Persona persona = this.personaService.consultarPorCedula(cedula);
		modelo.addAttribute("persona", persona);
		return "vistaPersona";
	}

	// cuando viaja el modelo en el request
	@PutMapping("/actualizar/{cedula}")
	public String actualizar(@PathVariable("cedula") String cedula, Persona persona) {
		Persona aux = this.personaService.consultarPorCedula(cedula);
		aux.setApellido(persona.getApellido());
		aux.setCedula(persona.getCedula());
		aux.setGenero(persona.getGenero());
		aux.setNombre(persona.getNombre());
		// como se consulta de la base ya tiene id
		this.personaService.actualizar(aux);
		return "redirect:/personas/buscarTodos";
	}

	@DeleteMapping("/borrar/{cedula}")
	public String borrar(@PathVariable String cedula) {
		this.personaService.borrarPorCedula(cedula);
		return "redirect:/personas/buscarTodos";
	}

	@PostMapping("/guardar")
	public String guardar(Persona persona) {
		this.personaService.guardar(persona);
		return "redirect:/personas/buscarTodos";
	}

	@GetMapping("/nuevaPersona")
	public String mostrarNuevaPersona(Model modelo) {
		modelo.addAttribute("persona", new Persona());
		return "vistaNuevaPersona";
	}

}
