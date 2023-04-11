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
import mundial.app.Entity.Partido;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import mundial.app.repository.PartidoRepository;

@Controller
@RequestMapping("/partidos")
public class PartidoTemplateController {
	@Autowired
	public PartidoRepository partidoRepository;
	
	@Autowired
	public EquipoRepository equipoRepository;
	
	@GetMapping("/")
	public String partidoListTemplate(Model model)
	{
		model.addAttribute("partidos", partidoRepository.findAll());
		return "partidos-list";
	}
	
	@GetMapping("/new")
	public String partidoNewTemplate(Model model)
	{
		model.addAttribute("partido", new Equipo());
		model.addAttribute("equipos", equipoRepository.findAll());
		return "partidos-form";
	}
	
	@GetMapping("/edit/{id}")
	public String partidoEditTemplate(@PathVariable("id") String id, Model model)
	{
		Partido partido = partidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
		model.addAttribute("partido", partido);
		model.addAttribute("equipos", equipoRepository.findAll());
		return "partidos-form";
	}
	
	@GetMapping("/delete/{id}")
	public String partidoDeleteTemplate(@PathVariable("id") String id)
	{
		partidoRepository.deleteById(id);
		return "redirect:/partido/";
	}
	
	@RequestMapping(value={"/save"},method = RequestMethod.POST)
	public String partidoguardar(@ModelAttribute("partido")Partido partido)
	{
	if (partido.getId().isEmpty())
	{
		partido.setId(null);
	}
		
		partidoRepository.save(partido);
		return "redirect:/partidos/";
	}
	
	

}
