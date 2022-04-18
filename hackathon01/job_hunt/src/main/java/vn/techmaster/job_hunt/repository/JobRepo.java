package vn.techmaster.job_hunt.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.model.City;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

@Repository
public class JobRepo {
    private ConcurrentHashMap<String, Job> listJob = new ConcurrentHashMap<>();

    public JobRepo(){

    }

    public Collection<Job> getAll(){
        return listJob.values();
    }

    public Job addJob(Job job){
        String id = UUID.randomUUID().toString();
        job.setId(id);
        listJob.put(id,job);
        return job;
    }

    public Job findJobById(String id){
        return listJob.get(id);
    }

    public Job deleteById(String id){
        return listJob.remove(id);
    }

    public Job update(Job job){     
        return listJob.put(job.getId(), job);
    }

    @PostConstruct
    public void addSomeData(){
        this.addJob(Job.builder().title("Front-end").company("FPT").description("Làm 8 tiếng").city("HaNoi").build());
        this.addJob(Job.builder().title("Back-end").company("Amazon").description("Làm 8 tiếng").city("HaiPhong").build());
        this.addJob(Job.builder().title("Devops").company("Google").description("Làm 8 tiếng").city("HaNoi").build());
    }
}
