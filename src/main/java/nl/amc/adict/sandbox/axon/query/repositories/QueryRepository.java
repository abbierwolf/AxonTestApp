package nl.amc.adict.sandbox.axon.query.repositories;

import java.io.Serializable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface QueryRepository<T, I extends Serializable> extends Repository<T, I> {

    /**
     * Retrieves an entity by its id.
     * 
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    T findOne(I id);

    /**
     * Returns whether an entity with the given id exists.
     * 
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(I id);

    /**
     * Returns all instances of the type.
     * 
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     * 
     * @param ids An iterable collection of identifiers
     * @return All instances of the type with the given IDs
     */
    Iterable<T> findAll(Iterable<I> ids);

    /**
     * Returns all instances of the type.
     *
     * @param sort Sorting information
     * @return all entities
     */
    Iterable<T> findAll(Sort sort);

    /**
     * Returns the number of entities available.
     * 
     * @return the number of entities
     */
    long count();

}
