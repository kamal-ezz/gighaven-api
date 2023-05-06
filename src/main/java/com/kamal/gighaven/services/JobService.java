package com.kamal.gighaven.services;

import com.kamal.gighaven.models.Job;
import com.kamal.gighaven.models.User;
import com.kamal.gighaven.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Optional<Job> get(long id){
        return jobRepository.findById(id);
    }

    public Job add(Job job){

        job.setType( job.getType().toLowerCase() );
        job.setExpertizeLevel( job.getExpertizeLevel().toLowerCase() );

        return jobRepository.save(job);
    }

    public List<Job> list(){

        List<Job> result = jobRepository.findAll();

        result.sort( (j1,j2) -> {
            return j1.getId() > j2.getId() ? -1 : 0;
        } );

        return result;
    }

    public List<Job> list(Map<String,Object> filter){

        List<Job> result = null; //jobRepository.findAll();
        User user = (User) filter.get("user");

        if(user != null) {
            result = jobRepository.findByAuthor(user);
        }

        if(result != null) {
            result.sort( (j1,j2) -> {
                return j1.getId() > j2.getId() ? -1 : 0;
            } );
        }

        return result;
    }

    public List<Job> findByAuthor(User user){
        return jobRepository.findByAuthor(user);
    }

    public List<Job> findHiredJobsByAuthor(User user){
        return jobRepository.findByAuthorAndHired(user);
    }

    public Page<Job> findAllPaged(Map<String, Object> filter, Integer pageNumber, int pageSize) {

        PageRequest request = PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.DESC, "id");

        User user = (User) filter.get("user");
        if(user != null) {
            return jobRepository.findByAuthor(user, request);
        } else {
            return jobRepository.findAll(request);
        }

    }

}
