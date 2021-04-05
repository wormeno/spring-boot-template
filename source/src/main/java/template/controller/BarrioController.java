package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import template.generic.controller.RestGenericController;
import template.model.Barrio;
import template.service.BarrioService;
import template.generic.service.GenericService;

import javax.validation.Valid;

@RestController
@RequestMapping("/template/barrios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class BarrioController extends RestGenericController<Barrio,Long> {

    @Autowired
    private BarrioService barrioService;

    @Override
    protected GenericService<Barrio, Long> getService() {
        return barrioService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody Barrio barrio) {
        System.out.println("estamos aca");
        return super.save(barrio);


    }


}
