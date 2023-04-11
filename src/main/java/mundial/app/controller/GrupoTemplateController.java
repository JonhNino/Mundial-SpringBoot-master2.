package mundial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mundial.app.Entity.Equipo;
import mundial.app.Entity.Grupo;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import mundial.app.repository.GrupoRepository;

@Controller
@RequestMapping("/grupos")
public class GrupoTemplateController {

	@Autowired
public GrupoRepository grupoRepository;
	
	@Autowired
public EquipoRepository equipoRepository;
	
	@GetMapping("/")
	public String gruposListTemplate(Model model)
	{
		model.addAttribute("grupos", grupoRepository.findAll());
		model.addAttribute("equipos", equipoRepository.findAll());
		return "grupos-list";
	}
	
	@GetMapping("/new")
	public String gruposNewTemplate(Model model)
	{
		model.addAttribute("equipo", new Equipo());
		model.addAttribute("grupos", grupoRepository.findAll());
		return "grupos-form";
	}
	
	@GetMapping("/edit/{id}")
	public String gruposEditTemplate(@PathVariable("id") String id, Model model)
	{
		Grupo grupo = grupoRepository.findById(id).orElseThrow(() -> new NotFoundException("Grupo no encontrado"));
		model.addAttribute("grupo", grupo);
		model.addAttribute("equipos", equipoRepository.findAll());
		return "grupos-form";
	}
	
	@GetMapping("/delete/{id}")
	public String gruposDeleteTemplate(@PathVariable("id") String id)
	{
		grupoRepository.deleteById(id);
		return "redirect:/grupos/";
	}
	
	@RequestMapping(value={"/save"},method = RequestMethod.POST)
	public String gruposguardar(@ModelAttribute("grupo")Grupo grupo)
	{
	if (grupo.getId().isEmpty())
	{
		grupo.setId(null);
	}
		
		grupoRepository.save(grupo);
		return "redirect:/grupos/";
	}
	
	
}
