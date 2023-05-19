package qloots.project.devapologiesrandomizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qloots.project.devapologiesrandomizer.entity.Apology;
import qloots.project.devapologiesrandomizer.service.ApologyService;

import java.util.List;

@RestController
@RequestMapping("/api/apologies")
public class ApologyController {

    @Autowired
    private ApologyService apologyService;

    @GetMapping
    public List<Apology> getAllApologies() {
        return apologyService.getAllApologies();
    }

    @GetMapping("/{httpCode}")
    public Apology getApologyByHttpCode(@PathVariable int httpCode) {
        return apologyService.getApologyByHttpCode(httpCode);
    }

    @GetMapping("/random")
    public Apology getRandomApology() {
        return apologyService.getRandomApology();
    }

    @PostMapping
    public Apology createApology(@RequestBody Apology apology) {
        return apologyService.createApology(apology);
    }
}
