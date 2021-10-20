package template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import template.dto.SnicDto;
import template.model.Snic;
import template.service.GenericDtoService;
import template.service.SnicDtoService;

@RestController
@RequestMapping("/snic")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
public class SnicController extends GenericDtoController<SnicDto,Snic, Long> {

    @Autowired
    private SnicDtoService snicService;

    @Override
    protected GenericDtoService<SnicDto,Snic, Long> getService() {
        return snicService;
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
