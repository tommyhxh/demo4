package com.hisense.hitest2.controller;
/**
 * @cmptId 1320538766611316984
 * @dmId 1320636827157135608
 * @author saasp-de
 * @date today
 */
import com.hisense.hitest2.pojo.Plus;
import com.hisense.hitest2.pojo.CommonInput;
import com.hisense.hitest2.pojo.UpLoadDataInput;
import com.hisense.hitest2.service.PlusService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plus")
public class PlusController {

    @Autowired
    public PlusService plusService;


    @ApiOperation(notes = "模型商品信息表增加接口",value ="模型商品信息表增加接口")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(@RequestBody CommonInput clz) {
        return plusService.addUsedByBase(clz);
    }

    @ApiOperation(notes = "模型商品信息表修改接口",value ="模型商品信息表修改接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody UpLoadDataInput clz) {
        return plusService.updateUsedByBase(clz);
    }
}
