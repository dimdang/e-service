package org.code.jarvis.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.code.jarvis.model.core.Applicant;
import org.code.jarvis.model.core.Image;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.request.RequestApplicant;
import org.code.jarvis.model.response.JProduct;
import org.code.jarvis.model.response.JResponseEntity;
import org.code.jarvis.service.ApplicantEntityService;
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
import java.util.List;

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
    private ApplicantEntityService applicantEntityService;

    @ApiOperation(
            httpMethod = "POST",
            value = "Fetch product with pagination",
            notes = "This url does fetch products with pagination",
            response = JResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            protocols = "http")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/product/fetch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity<Object> fetchProducts(@RequestParam(value = "offset", required = true, defaultValue = "1") int offset,
                                                 @RequestParam(value = "limit", required = true, defaultValue = "10") int limit) {
        List<JProduct> response = new ArrayList<>();
        try {
            List<Product> list = productEntityService.fetchProducts(offset, limit);
            if (list != null) {
                response = new ArrayList<>(list.size());
                for (Product product : list)
                    response.add(new JProduct(product));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseFactory.build("Success", HttpStatus.OK, response);
    }

    @ApiOperation(
            httpMethod = "POST",
            value = "Submit applicant and images",
            notes = "The client have to submit json applicant and images using form data",
            response = JResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            protocols = "http")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/applicant/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JResponseEntity<Object> submitApplicant(@RequestPart(required = false) MultipartFile[] files,
                                                   @RequestPart(required = false) String json) {
        try {
            Applicant applicant = applicantEntityService.saveOrUpdateApplicant(files, json);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseFactory.build("Submit success", HttpStatus.ACCEPTED);
    }

    @ApiOperation(
            httpMethod = "GET",
            value = "View product's image",
            notes = "This url request to server for view image",
            response = HttpEntity.class,
            protocols = "http")
    @GetMapping(value = "/product/view/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public HttpEntity<byte[]> viewImage(@PathVariable(value = "id", required = true) long id) throws IOException {
        log.info("Client Requested picture Id:" + id);
        Image image = productEntityService.getImage(id);
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
            value = "Download product's image",
            notes = "This url request to server for download image",
            response = HttpEntity.class,
            protocols = "http")
    @GetMapping(value = "/product/download/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public HttpEntity<byte[]> downloadImage(@PathVariable(value = "id", required = true) long id) throws IOException {
        log.info("Client Requested picture Id:" + id);
        Image image = productEntityService.getImage(id);
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


    @PostMapping(value = "/test",produces = MediaType.TEXT_PLAIN_VALUE)
    public String test(@RequestBody RequestApplicant applicant){

        return "Hello world";
    }
}
