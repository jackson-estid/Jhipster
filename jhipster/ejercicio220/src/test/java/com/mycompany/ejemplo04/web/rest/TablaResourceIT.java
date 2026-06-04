package com.mycompany.ejemplo04.web.rest;

import static com.mycompany.ejemplo04.domain.TablaAsserts.*;
import static com.mycompany.ejemplo04.web.rest.TestUtil.createUpdateProxyForBean;
import static com.mycompany.ejemplo04.web.rest.TestUtil.sameInstant;
import static com.mycompany.ejemplo04.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.ejemplo04.IntegrationTest;
import com.mycompany.ejemplo04.domain.Tabla;
import com.mycompany.ejemplo04.repository.TablaRepository;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link TablaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TablaResourceIT {

    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String DEFAULT_CAMPO_01 = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_01 = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAMPO_02 = 1;
    private static final Integer UPDATED_CAMPO_02 = 2;

    private static final Long DEFAULT_CAMPO_03 = 1L;
    private static final Long UPDATED_CAMPO_03 = 2L;

    private static final BigDecimal DEFAULT_CAMPO_04 = new BigDecimal(1);
    private static final BigDecimal UPDATED_CAMPO_04 = new BigDecimal(2);

    private static final Float DEFAULT_CAMPO_05 = 1F;
    private static final Float UPDATED_CAMPO_05 = 2F;

    private static final Double DEFAULT_CAMPO_06 = 1D;
    private static final Double UPDATED_CAMPO_06 = 2D;

    private static final Boolean DEFAULT_CAMPO_07 = false;
    private static final Boolean UPDATED_CAMPO_07 = true;

    private static final LocalDate DEFAULT_CAMPO_08 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CAMPO_08 = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_CAMPO_09 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CAMPO_09 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Instant DEFAULT_CAMPO_10 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CAMPO_10 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Duration DEFAULT_CAMPO_11 = Duration.ofHours(6);
    private static final Duration UPDATED_CAMPO_11 = Duration.ofHours(12);

    private static final LocalTime DEFAULT_CAMPO_12 = LocalTime.NOON;
    private static final LocalTime UPDATED_CAMPO_12 = LocalTime.MAX.withNano(0);

    private static final UUID DEFAULT_CAMPO_13 = UUID.randomUUID();
    private static final UUID UPDATED_CAMPO_13 = UUID.randomUUID();

    private static final byte[] DEFAULT_CAMPO_14 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPO_14 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPO_14_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPO_14_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CAMPO_15 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPO_15 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPO_15_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPO_15_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CAMPO_16 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CAMPO_16 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CAMPO_16_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CAMPO_16_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CAMPO_17 = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_17 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tablas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TablaRepository tablaRepository;

    @Autowired
    private MockMvc restTablaMockMvc;

    private Tabla tabla;

    private Tabla insertedTabla;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tabla createEntity() {
        return new Tabla()
            .campo01(DEFAULT_CAMPO_01)
            .campo02(DEFAULT_CAMPO_02)
            .campo03(DEFAULT_CAMPO_03)
            .campo04(DEFAULT_CAMPO_04)
            .campo05(DEFAULT_CAMPO_05)
            .campo06(DEFAULT_CAMPO_06)
            .campo07(DEFAULT_CAMPO_07)
            .campo08(DEFAULT_CAMPO_08)
            .campo09(DEFAULT_CAMPO_09)
            .campo10(DEFAULT_CAMPO_10)
            .campo11(DEFAULT_CAMPO_11)
            .campo12(DEFAULT_CAMPO_12)
            .campo13(DEFAULT_CAMPO_13)
            .campo14(DEFAULT_CAMPO_14)
            .campo14ContentType(DEFAULT_CAMPO_14_CONTENT_TYPE)
            .campo15(DEFAULT_CAMPO_15)
            .campo15ContentType(DEFAULT_CAMPO_15_CONTENT_TYPE)
            .campo16(DEFAULT_CAMPO_16)
            .campo16ContentType(DEFAULT_CAMPO_16_CONTENT_TYPE)
            .campo17(DEFAULT_CAMPO_17);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tabla createUpdatedEntity() {
        return new Tabla()
            .campo01(UPDATED_CAMPO_01)
            .campo02(UPDATED_CAMPO_02)
            .campo03(UPDATED_CAMPO_03)
            .campo04(UPDATED_CAMPO_04)
            .campo05(UPDATED_CAMPO_05)
            .campo06(UPDATED_CAMPO_06)
            .campo07(UPDATED_CAMPO_07)
            .campo08(UPDATED_CAMPO_08)
            .campo09(UPDATED_CAMPO_09)
            .campo10(UPDATED_CAMPO_10)
            .campo11(UPDATED_CAMPO_11)
            .campo12(UPDATED_CAMPO_12)
            .campo13(UPDATED_CAMPO_13)
            .campo14(UPDATED_CAMPO_14)
            .campo14ContentType(UPDATED_CAMPO_14_CONTENT_TYPE)
            .campo15(UPDATED_CAMPO_15)
            .campo15ContentType(UPDATED_CAMPO_15_CONTENT_TYPE)
            .campo16(UPDATED_CAMPO_16)
            .campo16ContentType(UPDATED_CAMPO_16_CONTENT_TYPE)
            .campo17(UPDATED_CAMPO_17);
    }

    @BeforeEach
    void initTest() {
        tabla = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedTabla != null) {
            tablaRepository.delete(insertedTabla);
            insertedTabla = null;
        }
    }

    @Test
    void createTabla() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Tabla
        var returnedTabla = om.readValue(
            restTablaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tabla)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Tabla.class
        );

        // Validate the Tabla in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTablaUpdatableFieldsEquals(returnedTabla, getPersistedTabla(returnedTabla));

        insertedTabla = returnedTabla;
    }

    @Test
    void createTablaWithExistingId() throws Exception {
        // Create the Tabla with an existing ID
        tabla.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTablaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tabla)))
            .andExpect(status().isBadRequest());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTablas() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        // Get all the tablaList
        restTablaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabla.getId())))
            .andExpect(jsonPath("$.[*].campo01").value(hasItem(DEFAULT_CAMPO_01)))
            .andExpect(jsonPath("$.[*].campo02").value(hasItem(DEFAULT_CAMPO_02)))
            .andExpect(jsonPath("$.[*].campo03").value(hasItem(DEFAULT_CAMPO_03.intValue())))
            .andExpect(jsonPath("$.[*].campo04").value(hasItem(sameNumber(DEFAULT_CAMPO_04))))
            .andExpect(jsonPath("$.[*].campo05").value(hasItem(DEFAULT_CAMPO_05.doubleValue())))
            .andExpect(jsonPath("$.[*].campo06").value(hasItem(DEFAULT_CAMPO_06)))
            .andExpect(jsonPath("$.[*].campo07").value(hasItem(DEFAULT_CAMPO_07)))
            .andExpect(jsonPath("$.[*].campo08").value(hasItem(DEFAULT_CAMPO_08.toString())))
            .andExpect(jsonPath("$.[*].campo09").value(hasItem(sameInstant(DEFAULT_CAMPO_09))))
            .andExpect(jsonPath("$.[*].campo10").value(hasItem(DEFAULT_CAMPO_10.toString())))
            .andExpect(jsonPath("$.[*].campo11").value(hasItem(DEFAULT_CAMPO_11.toString())))
            .andExpect(jsonPath("$.[*].campo12").value(hasItem(DEFAULT_CAMPO_12.format(LOCAL_DATE_TIME_FORMAT))))
            .andExpect(jsonPath("$.[*].campo13").value(hasItem(DEFAULT_CAMPO_13.toString())))
            .andExpect(jsonPath("$.[*].campo14ContentType").value(hasItem(DEFAULT_CAMPO_14_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campo14").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_14))))
            .andExpect(jsonPath("$.[*].campo15ContentType").value(hasItem(DEFAULT_CAMPO_15_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campo15").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_15))))
            .andExpect(jsonPath("$.[*].campo16ContentType").value(hasItem(DEFAULT_CAMPO_16_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campo16").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_16))))
            .andExpect(jsonPath("$.[*].campo17").value(hasItem(DEFAULT_CAMPO_17)));
    }

    @Test
    void getTabla() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        // Get the tabla
        restTablaMockMvc
            .perform(get(ENTITY_API_URL_ID, tabla.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabla.getId()))
            .andExpect(jsonPath("$.campo01").value(DEFAULT_CAMPO_01))
            .andExpect(jsonPath("$.campo02").value(DEFAULT_CAMPO_02))
            .andExpect(jsonPath("$.campo03").value(DEFAULT_CAMPO_03.intValue()))
            .andExpect(jsonPath("$.campo04").value(sameNumber(DEFAULT_CAMPO_04)))
            .andExpect(jsonPath("$.campo05").value(DEFAULT_CAMPO_05.doubleValue()))
            .andExpect(jsonPath("$.campo06").value(DEFAULT_CAMPO_06))
            .andExpect(jsonPath("$.campo07").value(DEFAULT_CAMPO_07))
            .andExpect(jsonPath("$.campo08").value(DEFAULT_CAMPO_08.toString()))
            .andExpect(jsonPath("$.campo09").value(sameInstant(DEFAULT_CAMPO_09)))
            .andExpect(jsonPath("$.campo10").value(DEFAULT_CAMPO_10.toString()))
            .andExpect(jsonPath("$.campo11").value(DEFAULT_CAMPO_11.toString()))
            .andExpect(jsonPath("$.campo12").value(DEFAULT_CAMPO_12.format(LOCAL_DATE_TIME_FORMAT)))
            .andExpect(jsonPath("$.campo13").value(DEFAULT_CAMPO_13.toString()))
            .andExpect(jsonPath("$.campo14ContentType").value(DEFAULT_CAMPO_14_CONTENT_TYPE))
            .andExpect(jsonPath("$.campo14").value(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_14)))
            .andExpect(jsonPath("$.campo15ContentType").value(DEFAULT_CAMPO_15_CONTENT_TYPE))
            .andExpect(jsonPath("$.campo15").value(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_15)))
            .andExpect(jsonPath("$.campo16ContentType").value(DEFAULT_CAMPO_16_CONTENT_TYPE))
            .andExpect(jsonPath("$.campo16").value(Base64.getEncoder().encodeToString(DEFAULT_CAMPO_16)))
            .andExpect(jsonPath("$.campo17").value(DEFAULT_CAMPO_17));
    }

    @Test
    void getNonExistingTabla() throws Exception {
        // Get the tabla
        restTablaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingTabla() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tabla
        Tabla updatedTabla = tablaRepository.findById(tabla.getId()).orElseThrow();
        updatedTabla
            .campo01(UPDATED_CAMPO_01)
            .campo02(UPDATED_CAMPO_02)
            .campo03(UPDATED_CAMPO_03)
            .campo04(UPDATED_CAMPO_04)
            .campo05(UPDATED_CAMPO_05)
            .campo06(UPDATED_CAMPO_06)
            .campo07(UPDATED_CAMPO_07)
            .campo08(UPDATED_CAMPO_08)
            .campo09(UPDATED_CAMPO_09)
            .campo10(UPDATED_CAMPO_10)
            .campo11(UPDATED_CAMPO_11)
            .campo12(UPDATED_CAMPO_12)
            .campo13(UPDATED_CAMPO_13)
            .campo14(UPDATED_CAMPO_14)
            .campo14ContentType(UPDATED_CAMPO_14_CONTENT_TYPE)
            .campo15(UPDATED_CAMPO_15)
            .campo15ContentType(UPDATED_CAMPO_15_CONTENT_TYPE)
            .campo16(UPDATED_CAMPO_16)
            .campo16ContentType(UPDATED_CAMPO_16_CONTENT_TYPE)
            .campo17(UPDATED_CAMPO_17);

        restTablaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTabla.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTabla))
            )
            .andExpect(status().isOk());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTablaToMatchAllProperties(updatedTabla);
    }

    @Test
    void putNonExistingTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(put(ENTITY_API_URL_ID, tabla.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tabla)))
            .andExpect(status().isBadRequest());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tabla))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tabla)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTablaWithPatch() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tabla using partial update
        Tabla partialUpdatedTabla = new Tabla();
        partialUpdatedTabla.setId(tabla.getId());

        partialUpdatedTabla
            .campo01(UPDATED_CAMPO_01)
            .campo02(UPDATED_CAMPO_02)
            .campo04(UPDATED_CAMPO_04)
            .campo05(UPDATED_CAMPO_05)
            .campo09(UPDATED_CAMPO_09)
            .campo10(UPDATED_CAMPO_10)
            .campo12(UPDATED_CAMPO_12)
            .campo13(UPDATED_CAMPO_13)
            .campo14(UPDATED_CAMPO_14)
            .campo14ContentType(UPDATED_CAMPO_14_CONTENT_TYPE);

        restTablaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTabla.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTabla))
            )
            .andExpect(status().isOk());

        // Validate the Tabla in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTablaUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedTabla, tabla), getPersistedTabla(tabla));
    }

    @Test
    void fullUpdateTablaWithPatch() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tabla using partial update
        Tabla partialUpdatedTabla = new Tabla();
        partialUpdatedTabla.setId(tabla.getId());

        partialUpdatedTabla
            .campo01(UPDATED_CAMPO_01)
            .campo02(UPDATED_CAMPO_02)
            .campo03(UPDATED_CAMPO_03)
            .campo04(UPDATED_CAMPO_04)
            .campo05(UPDATED_CAMPO_05)
            .campo06(UPDATED_CAMPO_06)
            .campo07(UPDATED_CAMPO_07)
            .campo08(UPDATED_CAMPO_08)
            .campo09(UPDATED_CAMPO_09)
            .campo10(UPDATED_CAMPO_10)
            .campo11(UPDATED_CAMPO_11)
            .campo12(UPDATED_CAMPO_12)
            .campo13(UPDATED_CAMPO_13)
            .campo14(UPDATED_CAMPO_14)
            .campo14ContentType(UPDATED_CAMPO_14_CONTENT_TYPE)
            .campo15(UPDATED_CAMPO_15)
            .campo15ContentType(UPDATED_CAMPO_15_CONTENT_TYPE)
            .campo16(UPDATED_CAMPO_16)
            .campo16ContentType(UPDATED_CAMPO_16_CONTENT_TYPE)
            .campo17(UPDATED_CAMPO_17);

        restTablaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTabla.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTabla))
            )
            .andExpect(status().isOk());

        // Validate the Tabla in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTablaUpdatableFieldsEquals(partialUpdatedTabla, getPersistedTabla(partialUpdatedTabla));
    }

    @Test
    void patchNonExistingTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tabla.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tabla))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tabla))
            )
            .andExpect(status().isBadRequest());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTabla() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tabla.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTablaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tabla)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Tabla in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTabla() throws Exception {
        // Initialize the database
        insertedTabla = tablaRepository.save(tabla);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the tabla
        restTablaMockMvc
            .perform(delete(ENTITY_API_URL_ID, tabla.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tablaRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Tabla getPersistedTabla(Tabla tabla) {
        return tablaRepository.findById(tabla.getId()).orElseThrow();
    }

    protected void assertPersistedTablaToMatchAllProperties(Tabla expectedTabla) {
        assertTablaAllPropertiesEquals(expectedTabla, getPersistedTabla(expectedTabla));
    }

    protected void assertPersistedTablaToMatchUpdatableProperties(Tabla expectedTabla) {
        assertTablaAllUpdatablePropertiesEquals(expectedTabla, getPersistedTabla(expectedTabla));
    }
}
