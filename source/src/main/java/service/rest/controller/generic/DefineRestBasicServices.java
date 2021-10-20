package service.rest.controller.generic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.rest.response.ResponseModel;
import javax.validation.Valid;

@Validated
public interface DefineRestBasicServices<E,ID> {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@Valid @RequestBody E entity);

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@Valid @PathVariable ID id,@RequestBody E entity);

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> partialUpdate(@Valid  @PathVariable ID id, @RequestBody E entity );

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> find();

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody ResponseModel<Void> delete(@Valid @PathVariable ID id);

}
