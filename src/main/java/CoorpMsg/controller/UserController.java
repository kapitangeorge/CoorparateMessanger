package CoorpMsg.controller;

import CoorpMsg.domain.User;
import CoorpMsg.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/allUsers")
    public String allUser (Map<String, Object> model){

        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "allUsers";
    }

    @GetMapping ("/user/{id}")
    public String getById (@PathVariable("id") User user, Map<String, Object> model ){

        if (user.getFirst_name() == null) user.setFirst_name("<none>");
        if (user.getSecond_name() == null) user.setSecond_name("<none>");
        if (user.getLast_name() == null) user.setLast_name("<none>");
        if (user.getPosition() == null) user.setPosition( "<none>");
        model.put ("user", user);
        return "profile";
    }

    @GetMapping("/profile")
    public String profile (@AuthenticationPrincipal User user, Map<String, Object> model){


        if (user.getFirst_name() == null) user.setFirst_name("<none>");
        if (user.getSecond_name() == null) user.setSecond_name("<none>");
        if (user.getLast_name() == null) user.setLast_name("<none>");
        if (user.getPosition() == null) user.setPosition( "<none>");
        model.put ("user", user);
        return "profile";
    }

    @GetMapping("/edit")
    public String editor(@AuthenticationPrincipal User user, Map<String, Object> model){
        model.put("user", user);
        return"/edit";
    }

    @PostMapping ("/edit")
    public String userEdit (
            @RequestParam String position,
            @RequestParam String second_name,
            @RequestParam String last_name,
            @RequestParam String first_name,
            @RequestParam String username,
            @AuthenticationPrincipal User user){
            if (user.getUsername() != username) user.setUsername(username);
            if (user.getFirst_name() != first_name) user.setFirst_name(first_name);
            if (user.getSecond_name() != second_name) user.setSecond_name(second_name);
            if (user.getLast_name() != last_name) user.setLast_name(last_name);
            if (user.getPosition()!= position) user.setPosition(position);
            userRepo.save(user);
        return"redirect:/profile";
    }


    @GetMapping("/delete")
    public String userDelete(@AuthenticationPrincipal User user, Map<String, Object> model){
        model.put("user", user);
        return"/delete";
    }

    @PostMapping ("/delete")
    public String userDelete ( @AuthenticationPrincipal User user){
        userRepo.delete(user);
        return "redirect:/login";
    }
}
