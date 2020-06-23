package com.byfan.photos.controller;

import com.byfan.photos.entity.UserPc;
import com.byfan.photos.entity.UserWx;
import com.byfan.photos.service.UserPcService;
import com.byfan.photos.service.UserWxService;
import com.byfan.photos.utils.AesCbcUtil;
import com.byfan.photos.utils.HttpRequestUtil;
import com.byfan.photos.utils.MyUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:范秉洋
 * @Date:2019/8/25 17:00
 */
@RestController
@RequestMapping("/userwx")
public class UserController {

    @Autowired
    private UserWxService userWxService;

    @Autowired
    private UserPcService userPcService;

    /**
     * 注册微信用户
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @RequestMapping(value = "/loginwx.do")
    public Map decodeUserInfo(String encryptedData,String iv,String code){
        Map map = new HashMap();

        //登录凭证不能为空
        if(code == null || code.length() == 0)
        {
            map.put("status",0);
            map.put("msg","code不能为空");
            return map;
        }

        //小程序唯一标识(在微信小程序管理后台获取)
        String wxspAppid = "wx10895ab09906f51e";
        //小程序的app secret(在微信小程序管理后台获取)
        String wxspSecret = "5482b3abb083750bb6ec327a7e98188b";
        //授权(必填)
        String grant_type = "authorization_code";

        //*********1.向微信服务器使用登录凭证code获取session_key和openid*******************************//
        //拼接请求参数
        String params = "appid="+wxspAppid + "&secret="+wxspSecret + "&js_code="+code + "&grant_type="+grant_type;
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        //发送请求
        String sr = HttpRequestUtil.sendGet(url,params);
//        System.out.println("发送请求："+url+"?"+params+"\n请求结果："+sr);

        //解析相应内容(转换成json对象)
        JSONObject json = new JSONObject(sr);

        //获取会话密钥(session_key)
        String session_key = json.get("session_key").toString();

        //用户的唯一标识(openid)
        String openid = (String)json.get("openid");

        //*********2.对encryptedData加密数据进行AES解密**************************************************//
        try{
            String result = AesCbcUtil.decrypt(encryptedData,session_key,iv,"UTF-8");
//            System.out.println("result==:"+result);

            if(null != result && result.length() > 0)
            {
                map.put("status",1);
                map.put("msg","解密成功");

                JSONObject userInfoJSON = new JSONObject(result);
                //创建返回数据
                Map userInfo = new HashMap();
                userInfo.put("openId",userInfoJSON.get("openId"));
                userInfo.put("nickName",userInfoJSON.get("nickName"));
                userInfo.put("gender",userInfoJSON.get("gender"));
                userInfo.put("city",userInfoJSON.get("city"));
                userInfo.put("province",userInfoJSON.get("province"));
                userInfo.put("country",userInfoJSON.get("country"));
                userInfo.put("avatarUrl",userInfoJSON.get("avatarUrl"));

                //注册验证，如果是首次登录，将信息写入数据库，如果不是，则进行信息更新
                //创建写入数据库的对象
                UserWx userWx = new UserWx();
                userWx.setOpenId((String)userInfoJSON.get("openId"));
                userWx.setNickName((String)userInfoJSON.get("nickName"));
                userWx.setGender((Integer) userInfoJSON.get("gender"));
                userWx.setAvatarUrl((String)userInfoJSON.get("avatarUrl"));
                //进行地址拼接  China/Henan/Zhengzhou
                String address = userInfoJSON.get("country")+"/"+userInfoJSON.get("province")+"/"+userInfoJSON.get("city");
                userWx.setAddress(address);
                //写入数据库
                userWxService.save(userWx);

                //解密unionId & openId
                //这个信息是只给符合条件的用户下发,如不符合,则没有这个数据,在调用时需要做相应的判断,否则直接取值会报错,
                if(!userInfoJSON.isNull("unionId")){
                    userInfo.put("unionID",userInfoJSON.get("unionId"));
                }
                map.put("userInfo",userInfo);

//                System.out.println("userInfo="+userInfo+"请求登录成功");
            }else{
                map.put("status",0);
                map.put("msg","解密失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 注册/修改pc用户
     * @param userPc
     * @return
     */
    @RequestMapping("/loginpc.do")
    public Map loginPc(UserPc userPc){
        Map map = new HashMap();
        List<UserPc> list = userPcService.findAll();
        for (UserPc upc:list){
            if (userPc.getUsername().equals(upc.getUsername())){
                map.put("status","error");
                map.put("msg","用户名已存在");
                return map;
            }
        }
        UserWx userWx = userWxService.findById(userPc.getOpenId());
        userPc.setAvatarUrl(userWx.getAvatarUrl());
        UserPc upc = userPcService.findById(userPc.getId());
        MyUtil.copyPropertiesIgnoreNull(userPc,upc);
        userPcService.save(upc);
        map.put("status","ok");
        map.put("userPc",upc);
        return map;
    }

    /**
     * 根据微信用户id查找pc用户信息
     * @param openId
     * @return
     */
    @RequestMapping("/finduserpc.do")
    public Map findUserPc(String openId){
        Map map = new HashMap();
        UserPc upc = new UserPc();
        upc.setOpenId(openId);
        List<UserPc> list = userPcService.findByExample(upc);
        if(list!=null && !list.isEmpty()){
            UserPc userPc = list.get(0);
            map.put("msg","ok");
            map.put("status",1);
            map.put("userPc",userPc);
        }else {
            map.put("status",0);
            map.put("msg","error");
        }
        return map;
    }

    /**
     * pc用户登录客户端
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/register.do")
    public Map register(String username, String password, HttpServletRequest httpServletRequest){
//        HttpSession session = httpServletRequest.getSession(true);
//        System.out.println("username=="+username+"***password=="+password);
        Map map = new HashMap();
        UserPc upc = new UserPc();
        upc.setUsername(username);
        List<UserPc> list = userPcService.findByExample(upc);
        if(list!=null && !list.isEmpty()){
            UserPc userPc = list.get(0);
            if (userPc.getPassword().equals(password)){
                map.put("status","ok");
                map.put("msg",userPc);
//                session.setAttribute("userpc",userPc);
            }else {
                map.put("status","error");
                map.put("msg","密码错误");
            }
        }else {
            map.put("status","error");
            map.put("msg","用户不存在");
        }
        return map;
    }


}
