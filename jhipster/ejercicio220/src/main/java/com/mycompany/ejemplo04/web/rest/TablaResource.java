package com.mycompany.ejemplo04.web.rest;

import com.mycompany.ejemplo04.domain.Tabla;
import com.mycompany.ejemplo04.repository.TablaRepository;
import com.mycompany.ejemplo04.service.TablaService;
import com.mycompany.ejemplo04.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.ejemplo04.domain.Tabla}.
 */
@RestController
@RequestMapping("/api/tablas")
public class TablaResource {

    private static final Logger LOG = LoggerFactory.getLogger(TablaResource.class);

    private static final String ENTITY_NAME = "tabla";

    @Value("${jhipster.clientApp.name:ejemplo04}")
    private String applicationName;

    private final TablaService tablaService;

    private final TablaRepository tablaRepository;

    public TablaResource(TablaService tablaService, TablaRepository tablaRepository) {
        this.tablaService = tablaService;
        this.tablaRepository = tablaRepository;
    }

    /**
     * {@code POST  /tablas} : Create a new tabla.
     *
     * @param tabla the tabla to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabla, or with status {@code 400 (Bad Request)} if the tabla has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Tabla> createTabla(@RequestBody Tabla tabla) throws URISyntaxException {
        LOG.debug("REST request to save Tabla : {}", tabla);
        if (tabla.getId() != null) {
            throw new BadRequestAlertException("A new tabla cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tabla = tablaService.save(tabla);
        return ResponseEntity.created(new URI("/api/tablas/" + tabla.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, tabla.getId()))
            .body(tabla);
    }

    /**
     * {@code PUT  /tablas/:id} : Updates an existing tabla.
     *
     * @param id the id of the tabla to save.
     * @param tabla the tabla to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabla,
     * or with status {@code 400 (Bad Request)} if the tabla is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabla couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tabla> updateTabla(@PathVariable(value = "id", required = false) final String id, @RequestBody Tabla tabla)
        throws URISyntaxException {
        LOG.debug("REST request to update Tabla : {}, {}", id, tabla);
        if (tabla.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tabla.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tablaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tabla = tablaService.update(tabla);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tabla.getId()))
            .body(tabla);
    }

    /**
     * {@code PATCH  /tablas/:id} : Partial updates given fields of an existing tabla, field will ignore if it is null
     *
     * @param id the id of the tabla to save.
     * @param tabla the tabla to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabla,
     * or with status {@code 400 (Bad Request)} if the tabla is not valid,
     * or with status {@code 404 (Not Found)} if the tabla is not found,
     * or with status {@code 500 (Internal Server Error)} if the tabla couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Tabla> partialUpdateTabla(@PathVariable(value = "id", required = false) final String id, @RequestBody Tabla tabla)
        throws URISyntaxException {
        LOG.debug("REST request to partial update Tabla partially : {}, {}", id, tabla);
        if (tabla.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tabla.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tablaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Tabla> result = tablaService.partialUpdate(tabla);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tabla.getId()));
    }

    /**
     * {@code GET  /tablas} : get all the Tablas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Tablas in body.
     */
    @GetMapping("")
    public List<Tabla> getAllTablas() {
        LOG.debug("REST request to get all Tablas");
        return tablaService.findAll();
    }

    /**
     * {@code GET  /tablas/:id} : get the "id" tabla.
     *
     * @param id the id of the tabla to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabla, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tabla> getTabla(@PathVariable("id") String id) {
        LOG.debug("REST request to get Tabla : {}", id);
        Optional<Tabla> tabla = tablaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tabla);
    }

    /**
     * {@code DELETE  /tablas/:id} : delete the "id" tabla.
     *
     * @param id the id of the tabla to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTabla(@PathVariable("id") String id) {
        LOG.debug("REST request to delete Tabla : {}", id);
        tablaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id))
            .build();
    }
}
