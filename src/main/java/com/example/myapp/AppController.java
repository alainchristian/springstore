package com.example.myapp;

import com.example.myapp.entity.Role;
import com.example.myapp.entity.User;
import com.example.myapp.repository.RoleRepository;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public String openHome(Model model){
        model.addAttribute("role",new Role());
        model.addAttribute("user",new User());
        model.addAttribute("roleList",roleRepository.findAll());
        return "redirect:/login";
    }
    @PostMapping("/addrole")
    public String addRole(Role role){
        roleRepository.save(role);
        return "rolelist";
    }
    @PostMapping("/adduser")
    public String addUser(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        Role role=roleRepository.findRoleByName("User");
        user.addRole(role);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "userlist";
    }
    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        // custom logic before showing login page...
        model.addAttribute("role",new Role());
        model.addAttribute("user",new User());
        model.addAttribute("roleList",roleRepository.findAll());

       return "/mypage";
    }
    @GetMapping("/showusers")
    public String showUser(Model model){
        return "userlist";
    }

}
