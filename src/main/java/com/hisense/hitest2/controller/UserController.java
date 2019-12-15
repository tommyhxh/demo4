package com.hisense.hitest2.controller;
/**
 * @cmptId 1320538766611316984
 * @dmId 1320538828888342776
 * @author saasp-de
 * @date today
 */
import com.hisense.hitest2.service.UserService;
import com.hisense.orm.resource.BaseController;
import com.hisense.hitest2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User>{

    @Autowired
    UserService userService;

}
