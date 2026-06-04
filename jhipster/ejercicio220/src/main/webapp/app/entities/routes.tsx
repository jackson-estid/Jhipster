import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

<<<<<<< Updated upstream:jhipster/ejercicio220/src/main/webapp/app/entities/routes.tsx
import Tabla from './tabla';
=======
import TipoDocumento from './tipo-documento';
>>>>>>> Stashed changes:jhipster/ejemplo03/src/main/webapp/app/entities/routes.tsx
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
<<<<<<< Updated upstream:jhipster/ejercicio220/src/main/webapp/app/entities/routes.tsx
        <Route path="/tabla/*" element={<Tabla />} />
=======
        <Route path="/tipo-documento/*" element={<TipoDocumento />} />
>>>>>>> Stashed changes:jhipster/ejemplo03/src/main/webapp/app/entities/routes.tsx
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
