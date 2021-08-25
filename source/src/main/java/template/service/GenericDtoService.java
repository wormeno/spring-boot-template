package template.service;

import org.modelmapper.ModelMapper;
import template.model.EntidadPersistente;
import java.util.Optional;

public abstract class GenericDtoService <D,E extends EntidadPersistente ,ID> extends GenericEntityService<E,ID> {

 /*   @Autowired
    private GenericEntityService genericService;
*/
    protected ModelMapper modelMapper = new ModelMapper() ;

    public abstract E mapperDtoToEntity(D dtoEntity);

    public abstract D mapperEntityToDto(E entity);

    //@Override
    public D save(D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        super.save(entity);
       // dtoEntity.setId(entity.getId());
        D newDtoEntity = mapperEntityToDto(entity);
        return newDtoEntity;
     //   return (D) super.save(entity);
    }


    public D update(ID id, D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        super.update(id, entity);
        D dtoEntity2 = mapperEntityToDto(entity);
        return dtoEntity2;
    }

    public D partialUpdate(ID id, D dtoEntity){
        E entity = mapperDtoToEntity(dtoEntity);
        super.partialUpdate(id, entity);
        D dtoEntity2 = mapperEntityToDto(entity);
        return dtoEntity2;
    }

    public Optional<D> findById(ID aLong) {
        return Optional.empty();
    }

    public void delete(Long aLong) {

    }
/*
    public List<D> find() {
        return null;
    }*/



}
