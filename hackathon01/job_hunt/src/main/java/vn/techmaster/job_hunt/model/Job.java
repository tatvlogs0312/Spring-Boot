package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    private String id;
    private String title;
    private String company;
    private String description;
    private String city;
}
