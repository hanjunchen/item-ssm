package com.hsgene.controller;

import com.hsgene.common.Constant;
import com.hsgene.entity.UpToken;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hjc on 2016/10/21.
 */
@Controller
public class UpTokenController {

    //  使用七牛API生成上传凭证，凭证是动态的，所以每次上传都要获取一次
    @RequestMapping(value = "/uptoken", method = RequestMethod.GET)
    @ResponseBody
    public UpToken makeUpToken(String key) {
        Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
        String upToken = auth.uploadToken(Constant.BUCKET_NAME, key, 600l, null);
        return UpToken.builder().upToken(upToken).build();
    }

    //  直接返回字符串
    @RequestMapping(value = "/uptoken2", method = RequestMethod.GET)
    @ResponseBody
    public String createUpToken(String key) {
        Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
        return auth.uploadToken(Constant.BUCKET_NAME, key, 600l, null);
    }
}
