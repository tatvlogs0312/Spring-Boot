package vn.techmasterr.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmasterr.jobhunt.model.Employer;

@Repository
@RequestMapping("/employer")
public class EmployerRepository {
    ConcurrentHashMap<String, Employer> list = new ConcurrentHashMap<>();

    @GetMapping("/add")
    public String getEmployerForm(Model model) {
        model.addAttribute("employerRequest", new Employer("", "", "", ""));
        return "addEmployer";
    }

    @PostMapping("/add")
    public String addEmployer(@ModelAttribute Employer request, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Employer employer = new Employer(request.name(), request.website(), request.email(), request.address());
            list.put(employer.name(), employer);
        }
        return "redirect:/employer/list";
    }

    @GetMapping("/list")
    public String listEmployer(Model model) {
        List<Employer> employers = list.values().stream().toList();
        model.addAttribute("employers", employers);
        return "employer";
    }
}
