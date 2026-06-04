import React, { useEffect } from 'react';
import { Button, Col, Row } from 'react-bootstrap';
import { TextFormat, byteSize, openFile } from 'react-jhipster';
import { Link, useParams } from 'react-router';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { DurationFormat } from 'app/shared/DurationFormat';

import { getEntity } from './tabla.reducer';

export const TablaDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id!));
  }, []);

  const tablaEntity = useAppSelector(state => state.tabla.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tablaDetailsHeading">Tabla</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tablaEntity.id}</dd>
          <dt>
            <span id="campo01">Campo 01</span>
          </dt>
          <dd>{tablaEntity.campo01}</dd>
          <dt>
            <span id="campo02">Campo 02</span>
          </dt>
          <dd>{tablaEntity.campo02}</dd>
          <dt>
            <span id="campo03">Campo 03</span>
          </dt>
          <dd>{tablaEntity.campo03}</dd>
          <dt>
            <span id="campo04">Campo 04</span>
          </dt>
          <dd>{tablaEntity.campo04}</dd>
          <dt>
            <span id="campo05">Campo 05</span>
          </dt>
          <dd>{tablaEntity.campo05}</dd>
          <dt>
            <span id="campo06">Campo 06</span>
          </dt>
          <dd>{tablaEntity.campo06}</dd>
          <dt>
            <span id="campo07">Campo 07</span>
          </dt>
          <dd>{tablaEntity.campo07 ? 'true' : 'false'}</dd>
          <dt>
            <span id="campo08">Campo 08</span>
          </dt>
          <dd>{tablaEntity.campo08 ? <TextFormat value={tablaEntity.campo08} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="campo09">Campo 09</span>
          </dt>
          <dd>{tablaEntity.campo09 ? <TextFormat value={tablaEntity.campo09} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="campo10">Campo 10</span>
          </dt>
          <dd>{tablaEntity.campo10 ? <TextFormat value={tablaEntity.campo10} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="campo11">Campo 11</span>
          </dt>
          <dd>
            {tablaEntity.campo11 ? <DurationFormat value={tablaEntity.campo11} /> : null} ({tablaEntity.campo11})
          </dd>
          <dt>
            <span id="campo12">Campo 12</span>
          </dt>
          <dd>{tablaEntity.campo12}</dd>
          <dt>
            <span id="campo13">Campo 13</span>
          </dt>
          <dd>{tablaEntity.campo13}</dd>
          <dt>
            <span id="campo14">Campo 14</span>
          </dt>
          <dd>
            {tablaEntity.campo14 ? (
              <div>
                {tablaEntity.campo14ContentType ? (
                  <a onClick={openFile(tablaEntity.campo14ContentType, tablaEntity.campo14)}>Abrir&nbsp;</a>
                ) : null}
                <span>
                  {tablaEntity.campo14ContentType}, {byteSize(tablaEntity.campo14)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="campo15">Campo 15</span>
          </dt>
          <dd>
            {tablaEntity.campo15 ? (
              <div>
                {tablaEntity.campo15ContentType ? (
                  <a onClick={openFile(tablaEntity.campo15ContentType, tablaEntity.campo15)}>Abrir&nbsp;</a>
                ) : null}
                <span>
                  {tablaEntity.campo15ContentType}, {byteSize(tablaEntity.campo15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="campo16">Campo 16</span>
          </dt>
          <dd>
            {tablaEntity.campo16 ? (
              <div>
                {tablaEntity.campo16ContentType ? (
                  <a onClick={openFile(tablaEntity.campo16ContentType, tablaEntity.campo16)}>
                    <img src={`data:${tablaEntity.campo16ContentType};base64,${tablaEntity.campo16}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {tablaEntity.campo16ContentType}, {byteSize(tablaEntity.campo16)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="campo17">Campo 17</span>
          </dt>
          <dd>{tablaEntity.campo17}</dd>
        </dl>
        <Button as={Link as any} to="/tabla" replace variant="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Volver</span>
        </Button>
        &nbsp;
        <Button as={Link as any} to={`/tabla/${tablaEntity.id}/edit`} replace variant="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TablaDetail;
