package com.estudos.restfullwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
static filtering
Para ignorar podemos utiliza a anotação:
* @JsonIgnore sob o atributo ou:
* @JsonIgnoreProperties("field1") para apenas um
* @JsonIgnoreProperties({"field1", "field3"}) para vários*/

//@JsonIgnoreProperties({"field1", "field3"}) mais fácil um por vez, caso um nome mude, teriamos que trocar em dois lugares

@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;

   /* @JsonIgnore*/
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
