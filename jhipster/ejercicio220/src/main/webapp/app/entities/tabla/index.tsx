import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Tabla from './tabla';
import TablaDeleteDialog from './tabla-delete-dialog';
import TablaDetail from './tabla-detail';
import TablaUpdate from './tabla-update';

const TablaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Tabla />} />
    <Route path="new" element={<TablaUpdate />} />
    <Route path=":id">
      <Route index element={<TablaDetail />} />
      <Route path="edit" element={<TablaUpdate />} />
      <Route path="delete" element={<TablaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TablaRoutes;
