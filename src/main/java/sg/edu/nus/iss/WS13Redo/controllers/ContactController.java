package sg.edu.nus.iss.WS13Redo.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.WS13Redo.model.Contact;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired Environment env;
    
    
    @PostMapping
    public String getContact(@RequestBody MultiValueMap <String, String> form, Model model) throws IOException{
        String name = form.getFirst("name");
        // model.addAttribute("name", name);
        String email = form.getFirst("email");
        // model.addAttribute("email", email);
        String phone = form.getFirst("phone");
        // model.addAttribute("phone", phone);

        Contact contact = new Contact();
        String id = contact.getId();
        contact.setEmail(email);
        contact.setName(name);
        contact.setPhone(Integer.parseInt(phone));
        model.addAttribute("contact", contact);

        contact.saveContact(contact, env);


        return "contactlist";
    }

    @GetMapping("/{id}")
    public String showContactById(@PathVariable String id, Model model) throws IOException{
        String contactId = id;
        Contact contact = new Contact();
        String nullMsg = "Not Found!";
        contact = contact.getContactById(contact, contactId, env);
        if(contact != null){
            model.addAttribute("contact", contact);
        }else{
            model.addAttribute("nullMsg", nullMsg);
        }
        return "contactlist";
    }
}
