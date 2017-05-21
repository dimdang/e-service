package org.code.jarvis.util;

import flexjson.JSONDeserializer;
import org.apache.commons.beanutils.BeanUtils;
import org.code.jarvis.model.core.AbstractEntity;
import org.code.jarvis.model.core.Color;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.ProductContact;
import org.code.jarvis.service.ServiceHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KimChheng on 5/13/2017.
 */
public final class JsonUtil implements ServiceHelper {


    public static Product deserializeProduct(String json) {
        Product product = null;
        /*Object object;
        if (json != null && !json.isEmpty()) {
            JSONDeserializer<Map<String, Object>> deserializer = new JSONDeserializer<>();
            Map<String, Object> jsonObject = deserializer.deserialize(json);
            if (jsonObject != null && !jsonObject.isEmpty()) {
                if ((object = jsonObject.get("ID")) != null)
                    product = ENTITY_SERVICE.getEntityById(NumberUtil.getLong(object, 0), Product.class);
                else {
                    product = new Product();
                    product.setContact(new ProductContact());
                }
                product.setCode(jsonObject.get("CODE").toString());
                product.setColor(ENTITY_SERVICE.loadEntityById(NumberUtil.getLong(jsonObject.get("COLOR_ID"), 0), Color.class));
                product.setSize(NumberUtil.getInteger(jsonObject.get("SIZE"), 0));
                product.setPrice(jsonObject.get("PRICE").toString());
                if (product.getContact() != null) {
                    product.getContact().setPhone1(jsonObject.get("PHONE"));
                }
            }
        }*/
        return product;
    }

    public static <T extends AbstractEntity> T deserializeEntity(String json, JsonProperty jsonProperty, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            if (json != null && !json.isEmpty() && jsonProperty != null && clazz != null) {
                JSONDeserializer<Map<String, Object>> deserializer = new JSONDeserializer<>();
                Map<String, Object> jsonObject = deserializer.deserialize(json);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    for (Map.Entry<String, String> entry : jsonProperty.getMap().entrySet()) {
                        Object object = jsonObject.get(entry.getKey());
                        if (entry.getKey().contains("_ID")) {

                        }
                        BeanUtils.setProperty(bean, entry.getValue(), object);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static class JsonProperty {

        Map<String, String> map = new HashMap<>();

        public void put(String key, String field) {
            map.put(key, field);
        }

        public Map<String, String> getMap() {
            return map;
        }
    }


    public static void main(String[] args) {

        //Product product = JsonUtil.deserializeEntity("", Product.class);
        //product.getId();
    }
}
