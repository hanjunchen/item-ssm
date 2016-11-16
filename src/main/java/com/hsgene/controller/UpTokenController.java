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
    @RequestMapping(value = "uptoken", method = RequestMethod.GET)
    @ResponseBody
    public UpToken makeUpToken(String key) {
        Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
        String upToken = auth.uploadToken(Constant.BUCKET_NAME, key, 600l, null);
        return UpToken.builder().upToken(upToken).build();
    }

    @RequestMapping(value = "uptoken2", method = RequestMethod.GET)
    @ResponseBody   //  当直接返回数据(对象、字符串等非逻辑视图名的url)时，必须要加上该注解，否则要用response.getWriter().write()来返回页面
    public String createUpToken() {
//        Auth auth = Auth.create(Constant.ACCESS_KEY, Constant.SECRET_KEY);
//        String upToken = auth.uploadToken(Constant.BUCKET_NAME, key, 600l, null);
        return "KoXqNaWfEEQ6pXcBxFxH38XPUtS3cnQS0sRffSMy:d7DO9CibZ559xIkbYiXgKj6pOzg=:eyJzY29wZSI6ImhqYzcyOTpnZW5lMTEtMTYtMTYtMjYtMjgucG5nIiwiZGVhZGxpbmUiOjE0NzkyODUzODh9";
    }
}
