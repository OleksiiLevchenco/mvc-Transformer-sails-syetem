package com.levchenko.transformerShop.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @author Alexey Levchenko
 */

@Entity
@Table(name = "transformers")
public class Transformer {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;


    //    @NotEmpty
//    @Pattern(regexp="[0-9]+", message = "must be a integer")
//    @Range(min = 0, max = 100)
    @NotNull
    @Min(0)
    @Max(100_000_000)
    @Column(name = "input_voltage")
    private Integer inputVoltage;


    @NotNull

    @Min(0)
    @Max(100_000_000)
    @Column(name = "output_voltage")
    private Integer outputVoltage;

    @NotNull
    @Min(0)
    @Max(100_000_000)
    @Column(name = "output_current")
    private Integer outputCurrent;


    @NotNull
    @Min(0)
    @Max(100_000_000)
    @Column(name = "mass")
    private Integer mass;


    @NotNull
    @DecimalMin("0")
    @Column(name = "price")
    private BigDecimal price;

    public Transformer() {
    }

    public Transformer(Integer inputVoltage, Integer outputVoltage, Integer outputCurrent, Integer mass, BigDecimal price) {
        this.inputVoltage = inputVoltage;
        this.outputVoltage = outputVoltage;
        this.outputCurrent = outputCurrent;
        this.mass = mass;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInputVoltage() {
        return inputVoltage;
    }

    public void setInputVoltage(Integer inputVoltage) {
        this.inputVoltage = inputVoltage;
    }

    public Integer getOutputVoltage() {
        return outputVoltage;
    }

    public void setOutputVoltage(Integer outputVoltage) {
        this.outputVoltage = outputVoltage;
    }

    public Integer getOutputCurrent() {
        return outputCurrent;
    }

    public void setOutputCurrent(Integer outputCurrent) {
        this.outputCurrent = outputCurrent;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transformer{" +
                "id=" + id +
                ", inputVoltage=" + inputVoltage +
                ", outputVoltage=" + outputVoltage +
                ", outputCurrent=" + outputCurrent +
                ", mass=" + mass +
                ", price=" + price + '}' +
                "";
    }
}
