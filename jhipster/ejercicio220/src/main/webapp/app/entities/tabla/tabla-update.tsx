import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { ValidatedBlobField, ValidatedField, ValidatedForm } from 'react-jhipster';
import { Link, useNavigate, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';

import { createEntity, getEntity, reset, updateEntity } from './tabla.reducer';

export const TablaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const tablaEntity = useAppSelector(state => state.tabla.entity);
  const loading = useAppSelector(state => state.tabla.loading);
  const updating = useAppSelector(state => state.tabla.updating);
  const updateSuccess = useAppSelector(state => state.tabla.updateSuccess);

  const handleClose = () => {
    navigate('/tabla');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.campo02 !== undefined && typeof values.campo02 !== 'number') {
      values.campo02 = Number(values.campo02);
    }
    if (values.campo03 !== undefined && typeof values.campo03 !== 'number') {
      values.campo03 = Number(values.campo03);
    }
    if (values.campo04 !== undefined && typeof values.campo04 !== 'number') {
      values.campo04 = Number(values.campo04);
    }
    if (values.campo05 !== undefined && typeof values.campo05 !== 'number') {
      values.campo05 = Number(values.campo05);
    }
    if (values.campo06 !== undefined && typeof values.campo06 !== 'number') {
      values.campo06 = Number(values.campo06);
    }
    values.campo09 = convertDateTimeToServer(values.campo09);
    values.campo10 = convertDateTimeToServer(values.campo10);

    const entity = {
      ...tablaEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          campo09: displayDefaultDateTime(),
          campo10: displayDefaultDateTime(),
        }
      : {
          ...tablaEntity,
          campo09: convertDateTimeFromServer(tablaEntity.campo09),
          campo10: convertDateTimeFromServer(tablaEntity.campo10),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ejemplo04App.tabla.home.createOrEditLabel" data-cy="TablaCreateUpdateHeading">
            Crear o editar Tabla
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew && <ValidatedField name="id" required readOnly id="tabla-id" label="ID" validate={{ required: true }} />}
              <ValidatedField label="Campo 01" id="tabla-campo01" name="campo01" data-cy="campo01" type="text" />
              <ValidatedField label="Campo 02" id="tabla-campo02" name="campo02" data-cy="campo02" type="text" />
              <ValidatedField label="Campo 03" id="tabla-campo03" name="campo03" data-cy="campo03" type="text" />
              <ValidatedField label="Campo 04" id="tabla-campo04" name="campo04" data-cy="campo04" type="text" />
              <ValidatedField label="Campo 05" id="tabla-campo05" name="campo05" data-cy="campo05" type="text" />
              <ValidatedField label="Campo 06" id="tabla-campo06" name="campo06" data-cy="campo06" type="text" />
              <ValidatedField label="Campo 07" id="tabla-campo07" name="campo07" data-cy="campo07" check type="checkbox" />
              <ValidatedField label="Campo 08" id="tabla-campo08" name="campo08" data-cy="campo08" type="date" />
              <ValidatedField
                label="Campo 09"
                id="tabla-campo09"
                name="campo09"
                data-cy="campo09"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label="Campo 10"
                id="tabla-campo10"
                name="campo10"
                data-cy="campo10"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Campo 11" id="tabla-campo11" name="campo11" data-cy="campo11" type="text" />
              <ValidatedField label="Campo 12" id="tabla-campo12" name="campo12" data-cy="campo12" type="time" placeholder="HH:mm" />
              <ValidatedField label="Campo 13" id="tabla-campo13" name="campo13" data-cy="campo13" type="text" />
              <ValidatedBlobField label="Campo 14" id="tabla-campo14" name="campo14" data-cy="campo14" openActionLabel="Abrir" />
              <ValidatedBlobField label="Campo 15" id="tabla-campo15" name="campo15" data-cy="campo15" openActionLabel="Abrir" />
              <ValidatedBlobField label="Campo 16" id="tabla-campo16" name="campo16" data-cy="campo16" isImage accept="image/*" />
              <ValidatedField label="Campo 17" id="tabla-campo17" name="campo17" data-cy="campo17" type="textarea" />
              <Button as={Link as any} id="cancel-save" data-cy="entityCreateCancelButton" to="/tabla" replace variant="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Volver</span>
              </Button>
              &nbsp;
              <Button variant="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Guardar
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default TablaUpdate;
