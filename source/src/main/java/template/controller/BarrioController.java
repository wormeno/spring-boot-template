package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import template.model.Barrio;
import template.service.BarrioService;
import template.service.GenericEntityService;

@RestController
@RequestMapping("/template/barrios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class BarrioController extends GenericEntityController<Barrio, Long> {

    @Autowired
    private BarrioService barrioService;

    @Override
    protected GenericEntityService<Barrio, Long> getService() {
        return barrioService;
    }


/*
    @Override
    protected GenericServiceEntity2<Barrio, Long> getService() {
        return barrioService;
    }*/

/*    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody Barrio barrio) {
        return super.save(barrio);


    }*/



}
