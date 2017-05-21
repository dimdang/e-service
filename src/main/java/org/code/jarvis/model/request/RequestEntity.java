package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimChheng on 5/14/2017.
 */
public final class RequestEntity<T> {

    @JsonProperty("MESSAGE")
    private String message;
    @JsonProperty("DATA")
    private List<T> data = new ArrayList<T>();

    public RequestEntity() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addAll(List<T> list) {
        if (list != null)
            data.addAll(list);
    }

    public void addBody(T body) {
        if (body != null)
            data.add(body);
    }
}
