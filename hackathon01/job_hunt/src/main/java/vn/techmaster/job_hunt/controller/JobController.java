package vn.techmaster.job_hunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.repository.EmployerRepo;
import vn.techmaster.job_hunt.repository.JobRepo;
import vn.techmaster.job_hunt.request.JobRequest;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepo listJob;
    @Autowired
    private EmployerRepo employerRepo;

    @GetMapping()
    public String getAllJob(Model model) {
        model.addAttribute("jobs", listJob.getAll());
        return "jobs";
    }

    @GetMapping(value = "/add")
    public String addJobForm(Model model) {
        model.addAttribute("job", new Job("", "", "", "", ""));
        model.addAttribute("employer", new String(""));
        model.addAttribute("employers", employerRepo.getAll());
        return "add_job";
    }

    @PostMapping(value = "/add")
    public String addJob(@ModelAttribute("job") JobRequest request, BindingResult bindingResult, Model model) {
        Job newJob = listJob.addJob(Job.builder()
                .title(request.title())
                .company(request.company())
                .description(request.description())
                .city(request.city()).build());
        if (!bindingResult.hasErrors()) {

            return "redirect:/job/list";
        }
        return "add_job";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable String id) {
        return "redirect:/job/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployerByID(@PathVariable String id) {
        Job job = listJob.deleteById(id);
        return "redirect:/job/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateJobForm(Model model) {
        model.addAttribute("job", new Job("", "", "", "", ""));
        model.addAttribute("employer", new String(""));
        model.addAttribute("employers", employerRepo.getAll());
        return "update_job";
    }

    @PutMapping(value = "/update/{id}")
    public String updateJob(@ModelAttribute Job request, BindingResult bindingResult, Model model, @PathVariable String id) {
        Job job = listJob.findJobById(id);
        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setDescription(request.getDescription());
        job.setCity(request.getCity());
        job = listJob.update(job);
        if (!bindingResult.hasErrors()){

            return "redirect:/job/list";
        }
        return "update_job";
    }
}
