package org.code.jarvis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.code.jarvis.model.core.Customer;
import org.code.jarvis.model.core.EProductType;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.response.JResponseEntity;
import org.code.jarvis.service.CustomerEntityService;
import org.code.jarvis.service.ProductEntityService;
import org.code.jarvis.util.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/18/2017.
 */
@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    private final Logger log = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private ProductEntityService productEntityService;
    @Autowired
    private CustomerEntityService customerEntityService;
    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(
            httpMethod = "POST",
            value = "Fetch product with pagination",
            notes = "This url does fetch products with pagination. type{WED,CER,DES}",
            response = JResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            protocols = "http")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/product/fetch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity<Object> fetchProducts(@RequestParam(value = "offset", defaultValue = "1") int offset,
                                                 @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                 @RequestParam(value = "type", required = false, defaultValue = "WED") String type) {
        List<Product> response = null;
        try {
            EProductType productType = null;
            switch (type) {
                case "WED":
                    productType = EProductType.WED;
                    break;
                case "CER":
                    productType = EProductType.CER;
                    break;
                case "DES":
                    productType = EProductType.DES;
                    break;
                default:
                    productType = EProductType.WED;
                    break;
            }
            response = productEntityService.fetchProducts(offset, limit, productType);
            if (response == null) response = new ArrayList<>();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseFactory.build("Success", HttpStatus.OK, response);
    }

    @ApiOperation(
            httpMethod = "POST",
            value = "Submit customer and images",
            notes = "The client have to submit json customer and images using form data",
            response = JResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            protocols = "http")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/customer/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JResponseEntity<Object> submitCustomer(@RequestPart(required = false) MultipartFile[] files,
                                                  @RequestPart String json) throws IOException {
        try {
            Customer customer = objectMapper.readValue(json, Customer.class);
            if (customer != null) {
                Product product = customerEntityService.getEntityById(customer.getProductId(), Product.class);
                if (product != null) {
                    customer.setProduct(product);
                    customerEntityService.saveOrUpdateCustomer(files, customer);
                } else
                    return ResponseFactory.build("Submit failed there is no product with id " + customer.getProductId(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseFactory.build("Submit customer successful", HttpStatus.OK, objectMapper.readValue(json, Map.class));
    }

    @ApiOperation(
            httpMethod = "GET",
            value = "View image from server",
            notes = "This url request to server for view image",
            response = HttpEntity.class,
            protocols = "http")
    @GetMapping(value = "/image/view/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public HttpEntity<byte[]> viewImage(@PathVariable(name = "id", required = true) long id) throws IOException {
        log.info("Client Requested picture Id:" + id);
        Image image = productEntityService.getEntityById(id, Image.class);
        if (image != null) {
            byte[] bytes = image.getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");
            headers.set("Content-Type", image.getType());
            headers.setContentLength(bytes.length);
            return new HttpEntity<>(bytes, headers);
        }
        return null;
    }

    @ApiOperation(
            httpMethod = "GET",
            value = "Download image from server",
            notes = "This url request to server for download image",
            response = HttpEntity.class,
            protocols = "http")
    @GetMapping(value = "/image/download/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public HttpEntity<byte[]> downloadImage(@PathVariable(name = "id", required = true) long id) throws IOException {
        log.info("Client Requested picture Id:" + id);
        Image image = productEntityService.getEntityById(id, Image.class);
        if (image != null) {
            byte[] bytes = image.getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"");
            headers.set("Content-Type", image.getType());
            headers.setContentLength(bytes.length);
            return new HttpEntity<>(bytes, headers);
        }
        return null;
    }

    @ApiOperation(
            httpMethod = "GET",
            value = "Sample customer json",
            notes = "Try out to get sample customer json for submit")
    @GetMapping(value = "/sample-customer-json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> customer() {
        Map<String, String> map = new HashMap<>();
        map.put("GROOM_NAME", "Channy");
        map.put("GROOM_DAD_NAME", "Kevin");
        map.put("GROOM_MOM_NAME", "Lina");
        map.put("BRIDE_NAME", "Mey Mey");
        map.put("BRIDE_DAD_NAME", "Jhon");
        map.put("BRIDE_MOM_NAME", "Marina");
        map.put("ADDRESS", "Stree 15,House No 15,New York,USA");
        map.put("DATE", "19-Aug-2017");
        map.put("HOME", "Bride's home");
        map.put("PHONE", "023 34 34 567");
        map.put("OTHER", "Other information");
        map.put("PRODUCT_ID", "1");
        return map;
    }
}
