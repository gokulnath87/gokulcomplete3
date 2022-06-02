package com.example.common;


import com.example.common.model.Person;
import com.example.common.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/userdetss")
public class CommonController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public Person getUserDetials(@PathVariable("id") String id){
        StopWatch s1 = new StopWatch();
        s1.start();
        Person p = new Person();
        CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> restTemplate.exchange("http://localhost:9009/person/name/"+id, HttpMethod.GET,null,String.class))
                .thenAccept(x -> p.setName(x.getBody())),
        CompletableFuture.supplyAsync(() -> restTemplate.exchange("http://localhost:9001/person/namee/"+id,HttpMethod.GET,null,String.class))
                .thenAccept(x ->p.setSalary(x.getBody()))
                        ).join();
          //  p.setName(p.getName()));
           // p.setSalary(p.getSalary());
            s1.stop();
        System.out.println("total"+s1.getTotalTimeMillis());
            return p;
    }
  @PostMapping("/salaryy")
   public CompletableFuture <Salary> insertSalary(@RequestBody Salary salary){
//        Salary salary1 = new Salary();
//        salary1.setSalaryId(salary.getSalaryId());
//        salary1.setAmount(salary.getAmount());
      return CompletableFuture.supplyAsync(()-> restTemplate.postForObject("http://localhost:9001/person/salary",salary,Salary.class));

  }
   @GetMapping("/salary")
    public CompletableFuture<Salary> getSalaryBy(){
        return CompletableFuture.supplyAsync(()-> restTemplate.getForObject("http://localhost:9001/person/userdata",Salary.class));
   }
}
