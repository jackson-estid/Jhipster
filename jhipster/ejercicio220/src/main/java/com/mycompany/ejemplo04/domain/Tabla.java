package com.mycompany.ejemplo04.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Tabla.
 */
@Document(collection = "tabla")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Tabla implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("campo_01")
    private String campo01;

    @Field("campo_02")
    private Integer campo02;

    @Field("campo_03")
    private Long campo03;

    @Field("campo_04")
    private BigDecimal campo04;

    @Field("campo_05")
    private Float campo05;

    @Field("campo_06")
    private Double campo06;

    @Field("campo_07")
    private Boolean campo07;

    @Field("campo_08")
    private LocalDate campo08;

    @Field("campo_09")
    private ZonedDateTime campo09;

    @Field("campo_10")
    private Instant campo10;

    @Field("campo_11")
    private Duration campo11;

    @Field("campo_12")
    private LocalTime campo12;

    @Field("campo_13")
    private UUID campo13;

    @Field("campo_14")
    private byte[] campo14;

    @Field("campo_14_content_type")
    private String campo14ContentType;

    @Field("campo_15")
    private byte[] campo15;

    @Field("campo_15_content_type")
    private String campo15ContentType;

    @Field("campo_16")
    private byte[] campo16;

    @Field("campo_16_content_type")
    private String campo16ContentType;

    @Field("campo_17")
    private String campo17;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Tabla id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampo01() {
        return this.campo01;
    }

    public Tabla campo01(String campo01) {
        this.setCampo01(campo01);
        return this;
    }

    public void setCampo01(String campo01) {
        this.campo01 = campo01;
    }

    public Integer getCampo02() {
        return this.campo02;
    }

    public Tabla campo02(Integer campo02) {
        this.setCampo02(campo02);
        return this;
    }

    public void setCampo02(Integer campo02) {
        this.campo02 = campo02;
    }

    public Long getCampo03() {
        return this.campo03;
    }

    public Tabla campo03(Long campo03) {
        this.setCampo03(campo03);
        return this;
    }

    public void setCampo03(Long campo03) {
        this.campo03 = campo03;
    }

    public BigDecimal getCampo04() {
        return this.campo04;
    }

    public Tabla campo04(BigDecimal campo04) {
        this.setCampo04(campo04);
        return this;
    }

    public void setCampo04(BigDecimal campo04) {
        this.campo04 = campo04;
    }

    public Float getCampo05() {
        return this.campo05;
    }

    public Tabla campo05(Float campo05) {
        this.setCampo05(campo05);
        return this;
    }

    public void setCampo05(Float campo05) {
        this.campo05 = campo05;
    }

    public Double getCampo06() {
        return this.campo06;
    }

    public Tabla campo06(Double campo06) {
        this.setCampo06(campo06);
        return this;
    }

    public void setCampo06(Double campo06) {
        this.campo06 = campo06;
    }

    public Boolean getCampo07() {
        return this.campo07;
    }

    public Tabla campo07(Boolean campo07) {
        this.setCampo07(campo07);
        return this;
    }

    public void setCampo07(Boolean campo07) {
        this.campo07 = campo07;
    }

    public LocalDate getCampo08() {
        return this.campo08;
    }

    public Tabla campo08(LocalDate campo08) {
        this.setCampo08(campo08);
        return this;
    }

    public void setCampo08(LocalDate campo08) {
        this.campo08 = campo08;
    }

    public ZonedDateTime getCampo09() {
        return this.campo09;
    }

    public Tabla campo09(ZonedDateTime campo09) {
        this.setCampo09(campo09);
        return this;
    }

    public void setCampo09(ZonedDateTime campo09) {
        this.campo09 = campo09;
    }

    public Instant getCampo10() {
        return this.campo10;
    }

    public Tabla campo10(Instant campo10) {
        this.setCampo10(campo10);
        return this;
    }

    public void setCampo10(Instant campo10) {
        this.campo10 = campo10;
    }

    public Duration getCampo11() {
        return this.campo11;
    }

    public Tabla campo11(Duration campo11) {
        this.setCampo11(campo11);
        return this;
    }

    public void setCampo11(Duration campo11) {
        this.campo11 = campo11;
    }

    public LocalTime getCampo12() {
        return this.campo12;
    }

    public Tabla campo12(LocalTime campo12) {
        this.setCampo12(campo12);
        return this;
    }

    public void setCampo12(LocalTime campo12) {
        this.campo12 = campo12;
    }

    public UUID getCampo13() {
        return this.campo13;
    }

    public Tabla campo13(UUID campo13) {
        this.setCampo13(campo13);
        return this;
    }

    public void setCampo13(UUID campo13) {
        this.campo13 = campo13;
    }

    public byte[] getCampo14() {
        return this.campo14;
    }

    public Tabla campo14(byte[] campo14) {
        this.setCampo14(campo14);
        return this;
    }

    public void setCampo14(byte[] campo14) {
        this.campo14 = campo14;
    }

    public String getCampo14ContentType() {
        return this.campo14ContentType;
    }

    public Tabla campo14ContentType(String campo14ContentType) {
        this.campo14ContentType = campo14ContentType;
        return this;
    }

    public void setCampo14ContentType(String campo14ContentType) {
        this.campo14ContentType = campo14ContentType;
    }

    public byte[] getCampo15() {
        return this.campo15;
    }

    public Tabla campo15(byte[] campo15) {
        this.setCampo15(campo15);
        return this;
    }

    public void setCampo15(byte[] campo15) {
        this.campo15 = campo15;
    }

    public String getCampo15ContentType() {
        return this.campo15ContentType;
    }

    public Tabla campo15ContentType(String campo15ContentType) {
        this.campo15ContentType = campo15ContentType;
        return this;
    }

    public void setCampo15ContentType(String campo15ContentType) {
        this.campo15ContentType = campo15ContentType;
    }

    public byte[] getCampo16() {
        return this.campo16;
    }

    public Tabla campo16(byte[] campo16) {
        this.setCampo16(campo16);
        return this;
    }

    public void setCampo16(byte[] campo16) {
        this.campo16 = campo16;
    }

    public String getCampo16ContentType() {
        return this.campo16ContentType;
    }

    public Tabla campo16ContentType(String campo16ContentType) {
        this.campo16ContentType = campo16ContentType;
        return this;
    }

    public void setCampo16ContentType(String campo16ContentType) {
        this.campo16ContentType = campo16ContentType;
    }

    public String getCampo17() {
        return this.campo17;
    }

    public Tabla campo17(String campo17) {
        this.setCampo17(campo17);
        return this;
    }

    public void setCampo17(String campo17) {
        this.campo17 = campo17;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tabla)) {
            return false;
        }
        return getId() != null && getId().equals(((Tabla) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tabla{" +
            "id=" + getId() +
            ", campo01='" + getCampo01() + "'" +
            ", campo02=" + getCampo02() +
            ", campo03=" + getCampo03() +
            ", campo04=" + getCampo04() +
            ", campo05=" + getCampo05() +
            ", campo06=" + getCampo06() +
            ", campo07='" + getCampo07() + "'" +
            ", campo08='" + getCampo08() + "'" +
            ", campo09='" + getCampo09() + "'" +
            ", campo10='" + getCampo10() + "'" +
            ", campo11='" + getCampo11() + "'" +
            ", campo12='" + getCampo12() + "'" +
            ", campo13='" + getCampo13() + "'" +
            ", campo14='" + getCampo14() + "'" +
            ", campo14ContentType='" + getCampo14ContentType() + "'" +
            ", campo15='" + getCampo15() + "'" +
            ", campo15ContentType='" + getCampo15ContentType() + "'" +
            ", campo16='" + getCampo16() + "'" +
            ", campo16ContentType='" + getCampo16ContentType() + "'" +
            ", campo17='" + getCampo17() + "'" +
            "}";
    }
}
