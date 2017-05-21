package org.code.jarvis.controller;

import io.swagger.annotations.*;
import org.code.jarvis.component.ApplicationContextProvider;
import org.code.jarvis.model.core.Student;
import org.code.jarvis.repository.EntityDao;
import org.code.jarvis.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KimChheng on 5/8/2017.
 */

@Controller
@RequestMapping("/test")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ViewController {


    @Autowired
    @Qualifier("entityDao")
    private EntityDao entityDao;

    @ApiOperation(value = "wtf", nickname = "getGreeting")
    //@RequestMapping(method = RequestMethod.GET, path = "/greeting", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue = "Niklas")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping("/data")
    public void test() {
        EntityService service = ApplicationContextProvider.getContext().getBean(EntityService.class);
        entityDao.getEntityById(1l, Student.class);

    }
}
