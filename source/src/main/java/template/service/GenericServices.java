package template.service;

import java.util.List;
import java.util.Optional;

public interface GenericServices<E,ID> {
    public E save(E entity);
    public Optional<E> findById(ID id);
    public void delete(ID id);
    public List<E> find();
}
