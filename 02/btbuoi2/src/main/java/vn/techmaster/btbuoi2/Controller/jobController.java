package vn.techmaster.btbuoi2.controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.btbuoi2.model.Job;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Job")

public class jobController {

        private ConcurrentHashMap<String, Job> listJob;

        public jobController() {
                listJob = new ConcurrentHashMap<>();
                listJob.put("No.1",
                                new Job("No.1", "Lập trình viên Spring Boot", "Làm 8 tiếng", "Ha Noi", 500, 1000,
                                                "a@gmail.com"));
                listJob.put("No.2",
                                new Job("No.2", "Lập trình viên Front-End", "Làm 8 tiếng", "Ho Chi Minh", 600,
                                                1200, "b@gmail.com"));
                listJob.put("No.3",
                                new Job("No.3", "Lập trình viên Fullstack", "Làm 8 tiếng", "Da Nang", 1000, 1700,
                                                "c@gmail.com"));
                listJob.put("No.4",
                                new Job("No.4", "Lập trình viên Back-End", "Làm 8 tiếng", "Hai Phong", 800, 1300,
                                                "d@gmail.com"));
        }

        @GetMapping
        public List<Job> getList() {
                return listJob.values().stream().toList();
        }

        @GetMapping(value = "/{id}")
        public Job getJobByID(@PathVariable("id") String id) {
                return listJob.get(id);
        }

        @GetMapping(value = "/sortbylocation")
        public List<Job> getListByLocation() {
                return listJob.values().stream().sorted(Comparator.comparing(Job::getLocation))
                                .collect(Collectors.toList());
        }

        @GetMapping(value = "/salary/{salary}")
        public List<Job> getJobBySalary(@PathVariable("salary") int salary) {
                return listJob.values().stream()
                                .filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
                                .collect(Collectors.toList());
        }

        @GetMapping(value = "/keyword/{keyword}")
        public List<Job> getListByKeyword(@PathVariable("keyword") String keyword) {
                return listJob.values().stream()
                                .filter(i -> (i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                                                || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                                .collect(Collectors.toList());
        }

        @GetMapping(value = "/query")
        public List<Job> getListByLocationAndKeyword(@RequestParam("location") String location,
                        @RequestParam("keyword") String keyword) {
                return listJob.values().stream()
                                .filter(i -> ((i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                                                || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                                                && (i.getLocation().toLowerCase()
                                                                .contains(location.toLowerCase())))
                                .collect(Collectors.toList());
        }

}
