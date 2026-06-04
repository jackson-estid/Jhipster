import React, { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import { TextFormat, byteSize, getSortState, openFile } from 'react-jhipster';
import { Link, useLocation, useNavigate } from 'react-router';

import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { DurationFormat } from 'app/shared/DurationFormat';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ASC, DESC } from 'app/shared/util/pagination.constants';

import { getEntities } from './tabla.reducer';

export const Tabla = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const tablaList = useAppSelector(state => state.tabla.entities);
  const loading = useAppSelector(state => state.tabla.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const { order } = sortState;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="tabla-heading" data-cy="TablaHeading">
        Tablas
        <div className="d-flex justify-content-end">
          <Button className="me-2" variant="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refrescar lista
          </Button>
          <Link to="/tabla/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Crear nuevo Tabla
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {tablaList?.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('campo01')}>
                  Campo 01 <FontAwesomeIcon icon={getSortIconByFieldName('campo01')} />
                </th>
                <th className="hand" onClick={sort('campo02')}>
                  Campo 02 <FontAwesomeIcon icon={getSortIconByFieldName('campo02')} />
                </th>
                <th className="hand" onClick={sort('campo03')}>
                  Campo 03 <FontAwesomeIcon icon={getSortIconByFieldName('campo03')} />
                </th>
                <th className="hand" onClick={sort('campo04')}>
                  Campo 04 <FontAwesomeIcon icon={getSortIconByFieldName('campo04')} />
                </th>
                <th className="hand" onClick={sort('campo05')}>
                  Campo 05 <FontAwesomeIcon icon={getSortIconByFieldName('campo05')} />
                </th>
                <th className="hand" onClick={sort('campo06')}>
                  Campo 06 <FontAwesomeIcon icon={getSortIconByFieldName('campo06')} />
                </th>
                <th className="hand" onClick={sort('campo07')}>
                  Campo 07 <FontAwesomeIcon icon={getSortIconByFieldName('campo07')} />
                </th>
                <th className="hand" onClick={sort('campo08')}>
                  Campo 08 <FontAwesomeIcon icon={getSortIconByFieldName('campo08')} />
                </th>
                <th className="hand" onClick={sort('campo09')}>
                  Campo 09 <FontAwesomeIcon icon={getSortIconByFieldName('campo09')} />
                </th>
                <th className="hand" onClick={sort('campo10')}>
                  Campo 10 <FontAwesomeIcon icon={getSortIconByFieldName('campo10')} />
                </th>
                <th className="hand" onClick={sort('campo11')}>
                  Campo 11 <FontAwesomeIcon icon={getSortIconByFieldName('campo11')} />
                </th>
                <th className="hand" onClick={sort('campo12')}>
                  Campo 12 <FontAwesomeIcon icon={getSortIconByFieldName('campo12')} />
                </th>
                <th className="hand" onClick={sort('campo13')}>
                  Campo 13 <FontAwesomeIcon icon={getSortIconByFieldName('campo13')} />
                </th>
                <th className="hand" onClick={sort('campo14')}>
                  Campo 14 <FontAwesomeIcon icon={getSortIconByFieldName('campo14')} />
                </th>
                <th className="hand" onClick={sort('campo15')}>
                  Campo 15 <FontAwesomeIcon icon={getSortIconByFieldName('campo15')} />
                </th>
                <th className="hand" onClick={sort('campo16')}>
                  Campo 16 <FontAwesomeIcon icon={getSortIconByFieldName('campo16')} />
                </th>
                <th className="hand" onClick={sort('campo17')}>
                  Campo 17 <FontAwesomeIcon icon={getSortIconByFieldName('campo17')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tablaList.map(tabla => (
                <tr key={`entity-${tabla.id}`} data-cy="entityTable">
                  <td>
                    <Button as={Link as any} to={`/tabla/${tabla.id}`} variant="link" size="sm">
                      {tabla.id}
                    </Button>
                  </td>
                  <td>{tabla.campo01}</td>
                  <td>{tabla.campo02}</td>
                  <td>{tabla.campo03}</td>
                  <td>{tabla.campo04}</td>
                  <td>{tabla.campo05}</td>
                  <td>{tabla.campo06}</td>
                  <td>{tabla.campo07 ? 'true' : 'false'}</td>
                  <td>{tabla.campo08 ? <TextFormat type="date" value={tabla.campo08} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{tabla.campo09 ? <TextFormat type="date" value={tabla.campo09} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tabla.campo10 ? <TextFormat type="date" value={tabla.campo10} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{tabla.campo11 ? <DurationFormat value={tabla.campo11} /> : null}</td>
                  <td>{tabla.campo12}</td>
                  <td>{tabla.campo13}</td>
                  <td>
                    {tabla.campo14 ? (
                      <div>
                        {tabla.campo14ContentType ? <a onClick={openFile(tabla.campo14ContentType, tabla.campo14)}>Abrir &nbsp;</a> : null}
                        <span>
                          {tabla.campo14ContentType}, {byteSize(tabla.campo14)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {tabla.campo15 ? (
                      <div>
                        {tabla.campo15ContentType ? <a onClick={openFile(tabla.campo15ContentType, tabla.campo15)}>Abrir &nbsp;</a> : null}
                        <span>
                          {tabla.campo15ContentType}, {byteSize(tabla.campo15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {tabla.campo16 ? (
                      <div>
                        {tabla.campo16ContentType ? (
                          <a onClick={openFile(tabla.campo16ContentType, tabla.campo16)}>
                            <img src={`data:${tabla.campo16ContentType};base64,${tabla.campo16}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {tabla.campo16ContentType}, {byteSize(tabla.campo16)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{tabla.campo17}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button as={Link as any} to={`/tabla/${tabla.id}`} variant="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">Vista</span>
                      </Button>
                      <Button as={Link as any} to={`/tabla/${tabla.id}/edit`} variant="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editar</span>
                      </Button>
                      <Button
                        onClick={() => (globalThis.location.href = `/tabla/${tabla.id}/delete`)}
                        variant="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Eliminar</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">Ningún Tablas encontrado</div>
        )}
      </div>
    </div>
  );
};

export default Tabla;
