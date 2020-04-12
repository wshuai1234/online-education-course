package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.config.WeChatConfig;
import net.xdclass.xdvideo.domain.JsonData;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.utils.JwtUtils;
import net.xdclass.xdvideo.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;


    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * generating wechat scan login url
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true)String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //get open platform redirect addr

        String callbackUrl = URLEncoder.encode(redirectUrl,"GBK"); //encoding

        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppid(),callbackUrl,accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }


    /**
     * wechat scan login, callback addr
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {


        User user = userService.saveWeChatUser(code);
        if(user != null){
            //generate jwt
            String token = JwtUtils.geneJsonWebToken(user);
            // state current user page url，need  http://  to avoid in-site redirect

            response.sendRedirect(state+"?token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(),"UTF-8"));
        }


    }


    /**
     * wechat pay callback
     */
    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request,HttpServletResponse response) throws Exception {

        InputStream inputStream =  request.getInputStream();

        //BufferedReader design pattern improve performance
         BufferedReader in =  new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
         StringBuffer sb = new StringBuffer();
         String line ;
         while ((line = in.readLine()) != null){
             sb.append(line);
         }
         in.close();
         inputStream.close();
         Map<String,String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
         System.out.println(callbackMap.toString());

         SortedMap<String,String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

         //check signature
        if(WXPayUtil.isCorrectSign(sortedMap,weChatConfig.getKey())){

            if("SUCCESS".equals(sortedMap.get("result_code"))){

                String outTradeNo = sortedMap.get("out_trade_no");

                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);

                if(dbVideoOrder != null && dbVideoOrder.getState()==0){  //check login
                        VideoOrder videoOrder = new VideoOrder();
                        videoOrder.setOpenid(sortedMap.get("openid"));
                        videoOrder.setOutTradeNo(outTradeNo);
                        videoOrder.setNotifyTime(new Date());
                        videoOrder.setState(1);
                        int rows = videoOrderService.updateVideoOderByOutTradeNo(videoOrder);
                        if(rows == 1){ //notify wechat order success
                            response.setContentType("text/xml");
                            response.getWriter().println("success");
                            return;
                        }
                }
            }
        }
        //process failed
        response.setContentType("text/xml");
        response.getWriter().println("fail");

    }




}
