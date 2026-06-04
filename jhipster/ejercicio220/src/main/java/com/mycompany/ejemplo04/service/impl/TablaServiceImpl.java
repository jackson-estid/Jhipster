package com.mycompany.ejemplo04.service.impl;

import com.mycompany.ejemplo04.domain.Tabla;
import com.mycompany.ejemplo04.repository.TablaRepository;
import com.mycompany.ejemplo04.service.TablaService;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.mycompany.ejemplo04.domain.Tabla}.
 */
@Service
public class TablaServiceImpl implements TablaService {

    private static final Logger LOG = LoggerFactory.getLogger(TablaServiceImpl.class);

    private final TablaRepository tablaRepository;

    public TablaServiceImpl(TablaRepository tablaRepository) {
        this.tablaRepository = tablaRepository;
    }

    @Override
    public Tabla save(Tabla tabla) {
        LOG.debug("Request to save Tabla : {}", tabla);
        return tablaRepository.save(tabla);
    }

    @Override
    public Tabla update(Tabla tabla) {
        LOG.debug("Request to update Tabla : {}", tabla);
        return tablaRepository.save(tabla);
    }

    @Override
    public Optional<Tabla> partialUpdate(Tabla tabla) {
        LOG.debug("Request to partially update Tabla : {}", tabla);

        return tablaRepository
            .findById(tabla.getId())
            .map(existingTabla -> {
                updateIfPresent(existingTabla::setCampo01, tabla.getCampo01());
                updateIfPresent(existingTabla::setCampo02, tabla.getCampo02());
                updateIfPresent(existingTabla::setCampo03, tabla.getCampo03());
                updateIfPresent(existingTabla::setCampo04, tabla.getCampo04());
                updateIfPresent(existingTabla::setCampo05, tabla.getCampo05());
                updateIfPresent(existingTabla::setCampo06, tabla.getCampo06());
                updateIfPresent(existingTabla::setCampo07, tabla.getCampo07());
                updateIfPresent(existingTabla::setCampo08, tabla.getCampo08());
                updateIfPresent(existingTabla::setCampo09, tabla.getCampo09());
                updateIfPresent(existingTabla::setCampo10, tabla.getCampo10());
                updateIfPresent(existingTabla::setCampo11, tabla.getCampo11());
                updateIfPresent(existingTabla::setCampo12, tabla.getCampo12());
                updateIfPresent(existingTabla::setCampo13, tabla.getCampo13());
                updateIfPresent(existingTabla::setCampo14, tabla.getCampo14());
                updateIfPresent(existingTabla::setCampo14ContentType, tabla.getCampo14ContentType());
                updateIfPresent(existingTabla::setCampo15, tabla.getCampo15());
                updateIfPresent(existingTabla::setCampo15ContentType, tabla.getCampo15ContentType());
                updateIfPresent(existingTabla::setCampo16, tabla.getCampo16());
                updateIfPresent(existingTabla::setCampo16ContentType, tabla.getCampo16ContentType());
                updateIfPresent(existingTabla::setCampo17, tabla.getCampo17());

                return existingTabla;
            })
            .map(tablaRepository::save);
    }

    @Override
    public List<Tabla> findAll() {
        LOG.debug("Request to get all Tablas");
        return tablaRepository.findAll();
    }

    @Override
    public Optional<Tabla> findOne(String id) {
        LOG.debug("Request to get Tabla : {}", id);
        return tablaRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete Tabla : {}", id);
        tablaRepository.deleteById(id);
    }

    private <T> void updateIfPresent(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
