package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.technologies.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.DeleteTechnologyRequest;
import kodlama.io.devs.business.requests.technologies.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.technologies.GetAllTechnologiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


public class TechnologiesController {
    TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping("/getall")
    public List<GetAllTechnologiesResponse> getAll() {
        return this.technologyService.getAll();
    }

    @PostMapping("/add")
    public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
        this.technologyService.add(createTechnologyRequest);
    }

    @PutMapping("/update")
    public void update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        this.technologyService.update(updateTechnologyRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(DeleteTechnologyRequest deleteTechnologyRequest) throws Exception {
        this.technologyService.delete(deleteTechnologyRequest);
    }
}
