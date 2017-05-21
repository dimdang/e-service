package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by KimChheng on 5/18/2017.
 */
@JsonPropertyOrder({"PRODUCT_ID", "APPLICANTS"})
public class RequestApplicant {

    @JsonProperty("PRODUCT_ID")
    private int productId;
    @JsonProperty("APPLICANTS")
    List<RequestPerson> persons;


    public RequestApplicant() {
    }

    public RequestApplicant(List<RequestPerson> persons) {
        this.persons = persons;
    }

    public List<RequestPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<RequestPerson> persons) {
        this.persons = persons;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
