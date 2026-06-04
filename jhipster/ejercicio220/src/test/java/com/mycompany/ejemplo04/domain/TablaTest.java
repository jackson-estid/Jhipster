package com.mycompany.ejemplo04.domain;

import static com.mycompany.ejemplo04.domain.TablaTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.ejemplo04.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TablaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tabla.class);
        Tabla tabla1 = getTablaSample1();
        Tabla tabla2 = new Tabla();
        assertThat(tabla1).isNotEqualTo(tabla2);

        tabla2.setId(tabla1.getId());
        assertThat(tabla1).isEqualTo(tabla2);

        tabla2 = getTablaSample2();
        assertThat(tabla1).isNotEqualTo(tabla2);
    }
}
