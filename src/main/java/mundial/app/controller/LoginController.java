package mundial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mundial.app.Entity.Usuario;
import mundial.app.repository.UsuarioRepository;

@Controller

public class LoginController {
	@Autowired
	public UsuarioRepository IUser;
	
	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model, @ModelAttribute Usuario usuarios)
    {
        model.addAttribute("user", usuarios);
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuarios) {
        
        for(Usuario item :IUser.findAll()) {
            if(item.getUsername().equals(usuarios.getUsername())) {
                if(item.getPassword().equals(usuarios.getPassword())) {
                    return "redirect:/home";
                }
            }
        }
        return "redirect:/login";
    }
    
    @GetMapping("/register")
    public String createuser(Model model, @ModelAttribute Usuario usuarios)
    {
        model.addAttribute("user", usuarios);
        return "register";
    }
    
    @PostMapping("/register")
    public String createuser(@ModelAttribute Usuario usuarios) {
        
        IUser.save(usuarios);
        
        return "redirect:/login";
    }

}
