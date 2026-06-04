package com.mycompany.ejemplo04.service;

import com.mycompany.ejemplo04.domain.Tabla;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.ejemplo04.domain.Tabla}.
 */
public interface TablaService {
    /**
     * Save a tabla.
     *
     * @param tabla the entity to save.
     * @return the persisted entity.
     */
    Tabla save(Tabla tabla);

    /**
     * Updates a tabla.
     *
     * @param tabla the entity to update.
     * @return the persisted entity.
     */
    Tabla update(Tabla tabla);

    /**
     * Partially updates a tabla.
     *
     * @param tabla the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Tabla> partialUpdate(Tabla tabla);

    /**
     * Get all the tablas.
     *
     * @return the list of entities.
     */
    List<Tabla> findAll();

    /**
     * Get the "id" tabla.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Tabla> findOne(String id);

    /**
     * Delete the "id" tabla.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
