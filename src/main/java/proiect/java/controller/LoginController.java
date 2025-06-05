package proiect.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("error", "Numele de utilizator sau parola sunt incorecte!");
        }

        if (logout != null) {
            model.addAttribute("logout", "Ați fost deconectat cu succes!");
        }

        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/filme";
    }
}
