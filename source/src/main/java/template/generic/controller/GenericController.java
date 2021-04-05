package template.generic.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import template.generic.response.ResponseModel;

import javax.validation.Valid;

public interface GenericController<E> {

    public ResponseEntity<?> findAllPageable(@PathVariable Integer pageIndex, @PathVariable Integer pageSize);
    public ResponseEntity<?> findAll();
    public ResponseEntity<?> save(@Valid @RequestBody E entity);
    public ResponseEntity<?> update(@Valid @RequestBody E entity);
    public ResponseEntity<?> find(@PathVariable Long id);
    public @ResponseBody
    ResponseModel<Void> remove(@PathVariable Long id);
}
