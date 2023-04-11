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
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;

@Controller
@RequestMapping("/equipos")
public class EquipoTemplateController {
	
	@Autowired
	public EquipoRepository equipoRepository;
	
	@GetMapping("/")
	public String equiposListTemplate(Model model)
	{
		model.addAttribute("equipos", equipoRepository.findAll());
		return "equipos-list";
	}
	
	@GetMapping("/new")
	public String equiposNewTemplate(Model model)
	{
		model.addAttribute("equipo", new Equipo());
		return "equipos-form";
	}
	
	@GetMapping("/edit/{id}")
	public String equiposEditTemplate(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("equipo", equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado")));
		return "equipos-form";
	}
	
	@GetMapping("/delete/{id}")
	public String equiposDeleteTemplate(@PathVariable("id") String id)
	{
		equipoRepository.deleteById(id);
		return "redirect:/equipos/";
	}
	
	@RequestMapping(value={"/save"},method = RequestMethod.POST)
	public String guardar(@ModelAttribute("equipo")Equipo equipo)
	{
	if (equipo.getId().isEmpty())
	{
		equipo.setId(null);
	}
		
		equipoRepository.save(equipo);
		return "redirect:/equipos/";
	}
	
}
