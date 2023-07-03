import cn.hutool.core.io.FileTypeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractTest {

//    @Autowired
//    private AliPayAuthClientService aliPayAuthClientService;
//
//    @Autowired
//    private AlipayUserRemoteService alipayUserRemoteService;
//
//
//    @Test
//    public void testFetchOpenAuthToken() {
//        AlipayOpenAuthTokenAppModel model = new AlipayOpenAuthTokenAppModel();
//        model.setGrantType("authorization_code");
//        model.setCode("1cc19911172e4f8aaa509c8fb5d12F56");
//        AlipayConnectModelRequest<AlipayOpenAuthTokenAppModel> request = AlipayConnectModelRequest.<AlipayOpenAuthTokenAppModel>builder()
//                .thirdPartyAppId("2021003120687097")
//                .data(model)
//                .build();
//        System.out.println(aliPayAuthClientService.getOpenAuthTokenApp(request));
//    }
//
//    @Test
//    public void testGetPhone() {
//        WxGetPhoneNumRequest request = new WxGetPhoneNumRequest();
//        request.setSourceType(SourceType.ALIPAY_RMS);
//        request.setAuthorizerAppId("2021002174690869");
//        request.setCode("{\"response\":\"bFlU7LQkP2ZKitweVUdcuxn8kpcIhWkMpAZl3rRRU0Taext6SLzAMpVMp6f3YaJbRvDRrBhTBuxAHRmCiZ9UjA==\",\"sign\":\"DDeAbw9RHmfWcypLco/LIbeUnuC+9rZ7Fk02+mxdqazmzRIi7okmOTdeGwzPd7JJGvw58R9p57NTzxKcmhW5zQmLlOSj3Bp1mniOxLPIniYDSXSn7GUJ+A91UJOwPHPEduDC6hH97veRwMKsznIngyC3jL+kAg4nJbAlqluguR1l7cmIyTED8I1oqfSCTg0KP0MzpVHY63HPBe6to+oRaPisZ1uAM72MgNBPcOQPI2YQ54I/aH1vyx8ryQDrjKY6z1rg2Szj48gj+vaJNM7IR4S3dg645RLhD4oLYaLO9veFlCGz+4OYiGaQ2hQ7V9BaeosI/oHbRQ9HE6LceFrZdw==\"}");
//        System.out.println(JSON.toJSONString(alipayUserRemoteService.getPhoneNum(request)));
//    }
    @Test
    public void testFileType() {
        /*String url = new String("aliyun.com/ssdafa/sdasada/mount.jpg");
        String subfix = url.substring(url.lastIndexOf(".")+1);
        System.out.println(subfix);*/
        File file = new File("/Users/leoyao/Desktop/潮点网_55011_720p.mp4");
        System.out.println(FileTypeUtil.getType(file));
    }

    @Test
    public void testList() {
        ArrayList<WxAppSourceCodeTemplate> wxAppSourceCodeTemplates = new ArrayList<>();
        wxAppSourceCodeTemplates.add(new WxAppSourceCodeTemplate().setTemplateId(10)) ;
        wxAppSourceCodeTemplates.add(new WxAppSourceCodeTemplate().setTemplateId(20)) ;
        wxAppSourceCodeTemplates.add(new WxAppSourceCodeTemplate().setTemplateId(5)) ;
        wxAppSourceCodeTemplates.add(new WxAppSourceCodeTemplate().setTemplateId(100)) ;
        List<WxAppSourceCodeTemplate> codeTemplates = wxAppSourceCodeTemplates.stream().sorted((o1, o2) -> o2.getTemplateId() - o1.getTemplateId()).collect(Collectors.toList());
        System.out.println(codeTemplates);
    }

    @Test
    public void testObj() {
        ArrayList<String> data = new ArrayList<>();
        data.add(new String("https://lf3-csp.bytetos.com/xxx.html"));
        System.out.println(data.getClass());
    }

   /* @Test
    public void testCus() {
        QueryCustomerServiceConfigRequest request = new QueryCustomerServiceConfigRequest();
        request.setOpenId("123");
        request.setType("1128");
        request.setScene("1");
        request.setAuthorizerAppId("wx3628ea0fb9ac2fb1");
        System.out.println(JSON.toJSON(request));
    }*/

    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        String actionData = "actionData\":\"{\\\"showData\\\":\\\"修改了自定义菜单\\\",\\\"requestToLogInfo\\\":{\\\"ip\\\":\\\"101.236.11.4\\\",\\\"url\\\":\\\"/api/v1/wechat/menu/save\\\",\\\"headers\\\":{\\\"$upstream_name\\\":\\\"com.sankuai.apigw.canyin.default\\\",\\\"accept\\\":\\\"application/json\\\",\\\"accept-encoding\\\":\\\"gzip, deflate, br\\\",\\\"accept-language\\\":\\\"zh-CN,zh;q=0.9\\\",\\\"appcode\\\":\\\"49\\\",\\\"Content-Length\\\":\\\"6426\\\",\\\"content-type\\\":\\\"application/json;charset=UTF-8\\\",\\\"cookie\\\":\\\"_lxsdk_cuid=17aafe10fa9c8-0fa9505359f492-171f4b58-1ea000-17aafe10fa9c8; uuid=cd99fcc06457238fc911.1630582737.1.0.0; _lxsdk=17aafe10fa9c8-0fa9505359f492-171f4b58-1ea000-17aafe10fa9c8; uu=29748710-4b8c-11ec-af30-cbc5ba44fc66; cid=1; ai=1; s_u_745896=MrSLZUoDMWY5aiVII2qi5w==; _ga=GA1.2.603538736.1649836730; moa_deviceId=2FAFF368C0FE5C468CCFA87D76E43A85; u=1274961150; _lx_utm=utm_source%3Dxm; e_u_id_3299326472=5bff1e8d1b074da86f443836e3caa891; loginToken=TZTNumwfq0fzrUcsvQ7c6MPQ4ZKlEpTPLTOEcy6jrNlyS8sGe3qy3r975xnSrxeTAs9j0m7FXjy5GHECLU3z5Q; merchantNo=81190963; arender_union_id=b3ae4697-0ec4-45ad-8beb-e1fe453357b7; s_m_id_3299326472=AwMAAAA5AgAAAAIAAAE9AAAALDVyel4EYxiV3rsR0GRYULYBb//esjRtsF8mo2GY0tjFUCCz/66P+uI7oZMXAAAAI0T5o53ti7NZTX/yTbOv+Sr3E68f5TkwRGgDiXAnJR8+FRQh; _lxsdk_s=184cdc8f340-92c-ad5-24a%7C%7C594\\\",\\\"Host\\\":\\\"rms.sjst.test.meituan.com\\\",\\\"model\\\":\\\"chrome\\\",\\\"mtsi-checked\\\":\\\"true\\\",\\\"mtsi-flag\\\":\\\"0\\\",\\\"mtsi-flow-strategy\\\":\\\"PASS\\\",\\\"mtsi-remote-ip\\\":\\\"172.23.52.255\\\",\\\"mtsi-request-code\\\":\\\"1669907659-173252892-7751458-1\\\",\\\"mtsi-score\\\":\\\"0\\\",\\\"origin\\\":\\\"https://1960-stavs-sl-rms.sjst.test.sankuai.com\\\",\\\"referer\\\":\\\"https://1960-stavs-sl-rms.sjst.test.sankuai.com/web/base-frame/operation/rms-wechat-management/home?hidemenu=1\\\",\\\"sec-ch-ua\\\":\\\"\\\\\\\"Google Chrome\\\\\\\";v=\\\\\\\"107\\\\\\\", \\\\\\\"Chromium\\\\\\\";v=\\\\\\\"107\\\\\\\", \\\\\\\"Not=A?Brand\\\\\\\";v=\\\\\\\"24\\\\\\\"\\\",\\\"sec-ch-ua-mobile\\\":\\\"?0\\\",\\\"sec-ch-ua-platform\\\":\\\"\\\\\\\"macOS\\\\\\\"\\\",\\\"sec-fetch-dest\\\":\\\"empty\\\",\\\"sec-fetch-mode\\\":\\\"cors\\\",\\\"sec-fetch-site\\\":\\\"same-origin\\\",\\\"strategy\\\":\\\"\\\",\\\"swimlane\\\":\\\"1960-stavs\\\",\\\"unionid\\\":\\\"17aafe10fa9c8-0fa9505359f492-171f4b58-1ea000-17aafe10fa9c8\\\",\\\"user-agent\\\":\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36\\\",\\\"versioncode\\\":\\\"\\\",\\\"X-Forwarded-For\\\":\\\"101.236.11.4\\\",\\\"X-Forwarded-Proto\\\":\\\"http\\\",\\\"X-Real-IP\\\":\\\"101.236.11.4\\\",\\\"x-real-url\\\":\\\"http://rms.sjst.test.meituan.com:80\\\",\\\"x-shepherd-swimlane\\\":\\\"1960-stavs\\\"},\\\"bodyListMap\\\":{},\\\"cookies\\\":[{\\\"name\\\":\\\"_ga\\\",\\\"value\\\":\\\"GA1.2.603538736.1649836730\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"_lx_utm\\\",\\\"value\\\":\\\"utm_source%3Dxm\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"_lxsdk\\\",\\\"value\\\":\\\"17aafe10fa9c8-0fa9505359f492-171f4b58-1ea000-17aafe10fa9c8\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"_lxsdk_cuid\\\",\\\"value\\\":\\\"17aafe10fa9c8-0fa9505359f492-171f4b58-1ea000-17aafe10fa9c8\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"_lxsdk_s\\\",\\\"value\\\":\\\"184cdc8f340-92c-ad5-24a%7C%7C594\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"ai\\\",\\\"value\\\":\\\"1\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"arender_union_id\\\",\\\"value\\\":\\\"b3ae4697-0ec4-45ad-8beb-e1fe453357b7\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"cid\\\",\\\"value\\\":\\\"1\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"e_u_id_3299326472\\\",\\\"value\\\":\\\"5bff1e8d1b074da86f443836e3caa891\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"loginToken\\\",\\\"value\\\":\\\"TZTNumwfq0fzrUcsvQ7c6MPQ4ZKlEpTPLTOEcy6jrNlyS8sGe3qy3r975xnSrxeTAs9j0m7FXjy5GHECLU3z5Q\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"merchantNo\\\",\\\"value\\\":\\\"81190963\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"moa_deviceId\\\",\\\"value\\\":\\\"2FAFF368C0FE5C468CCFA87D76E43A85\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"s_m_id_3299326472\\\",\\\"value\\\":\\\"AwMAAAA5AgAAAAIAAAE9AAAALDVyel4EYxiV3rsR0GRYULYBb//esjRtsF8mo2GY0tjFUCCz/66P+uI7oZMXAAAAI0T5o53ti7NZTX/yTbOv+Sr3E68f5TkwRGgDiXAnJR8+FRQh\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"s_u_745896\\\",\\\"value\\\":\\\"MrSLZUoDMWY5aiVII2qi5w==\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"u\\\",\\\"value\\\":\\\"1274961150\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"uu\\\",\\\"value\\\":\\\"29748710-4b8c-11ec-af30-cbc5ba44fc66\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false},{\\\"name\\\":\\\"uuid\\\",\\\"value\\\":\\\"cd99fcc06457238fc911.1630582737.1.0.0\\\",\\\"maxAge\\\":0,\\\"version\\\":-1,\\\"httpOnly\\\":false,\\\"sameSite\\\":\\\"NULL\\\",\\\"secure\\\":false}],\\\"parameterListMap\\\":{}}}";
        System.out.println(actionData.getBytes().length);
//        actionData = actionData.substring(0,10);
        System.out.println(actionData.getBytes().length);
        System.out.println(actionData.substring(0,4096));
        String s = "{\"showData\":\"修改了自定义菜单\",\"requestToLogInfo\":{\"ip\":\"101.236.11.2\",\"url\":\"/api/v1/wechat/menu/save\",\"headers\":{\"$upstream_name\":\"com.sankuai.apigw.canyin.default\",\"accept\":\"application/json\",\"accept-encoding\":\"gzip, deflate\",\"accept-language\":\"zh-CN,zh;q=0.9\",\"appcode\":\"49\",\"Content-Length\":\"6439\",\"content-type\":\"application/json;charset=UTF-8\",\"cookie\":\"_lxsdk_cuid=1841735b4e2c8-0e335e342344b7-307a6e07-1ea000-1841735b4e2c8; _lxsdk=1841735b4e2c8-0e335e342344b7-307a6e07-1ea000-1841735b4e2c8; moa_deviceId=2DF27E7DB7B851F4864143383176753B; s_u_745896=cMYIdnwDfI9+IP61Hk5OrQ==; uuid=8473bd7fa2206ad635a4.1669863028.1.0.0; e_u_id_3299326472=5bff1e8d1b074da86f443836e3caa891; loginToken=dtGIr9y5zvVzrOW5Zb_F3dgvs1GylhJF4FBMx7Ogc3oY8gCMNYrgtOUlPyYyA9Qc-_f6tpdaKWlgPYrB0Yuxpw; merchantNo=81190963; arender_union_id=3862509f-9702-4154-a14f-1ffaa5bd727b; s_m_id_3299326472=AwMAAAA5AgAAAAIAAAE9AAAALDVyel4EYxiV3rsR0GRYULYBb//esjRtsF8mo2GY0tjFUCCz/66P+uI7oZMXAAAAI9ajKmX+PaiyV1zKDwf0JzA+C4wUK94RaKiqoe8oClW6zh8J; _lxsdk_s=184d1a48f57-eb1-643-570%7C%7C345\",\"Host\":\"rms.sjst.test.meituan.com\",\"model\":\"chrome\",\"mtsi-checked\":\"true\",\"mtsi-flag\":\"0\",\"mtsi-flow-strategy\":\"PASS\",\"mtsi-remote-ip\":\"172.23.7.197\",\"mtsi-request-code\":\"1669967237-173252892-9418305-1\",\"mtsi-score\":\"0\",\"origin\":\"http://1960-ejkbj-sl-rms.sjst.test.sankuai.com\",\"referer\":\"http://1960-ejkbj-sl-rms.sjst.test.sankuai.com/web/base-frame/operation/rms-wechat-management/home?hidemenu=1\",\"strategy\":\"\",\"swimlane\":\"1960-ejkbj\",\"unionid\":\"1841735b4e2c8-0e335e342344b7-307a6e07-1ea000-1841735b4e2c8\",\"user-agent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36\",\"versioncode\":\"5300200\",\"X-Forwarded-For\":\"101.236.11.2\",\"X-Forwarded-Proto\":\"http\",\"X-Real-IP\":\"101.236.11.2\",\"x-real-url\":\"http://rms.sjst.test.meituan.com:80\",\"x-shepherd-swimlane\":\"1960-ejkbj\"},\"bodyListMap\":{},\"cookies\":[{\"name\":\"_lxsdk\",\"value\":\"1841735b4e2c8-0e335e342344b7-307a6e07-1ea000-1841735b4e2c8\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"_lxsdk_cuid\",\"value\":\"1841735b4e2c8-0e335e342344b7-307a6e07-1ea000-1841735b4e2c8\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"_lxsdk_s\",\"value\":\"184d1a48f57-eb1-643-570%7C%7C345\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"arender_union_id\",\"value\":\"3862509f-9702-4154-a14f-1ffaa5bd727b\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"e_u_id_3299326472\",\"value\":\"5bff1e8d1b074da86f443836e3caa891\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"loginToken\",\"value\":\"dtGIr9y5zvVzrOW5Zb_F3dgvs1GylhJF4FBMx7Ogc3oY8gCMNYrgtOUlPyYyA9Qc-_f6tpdaKWlgPYrB0Yuxpw\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"merchantNo\",\"value\":\"81190963\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"moa_deviceId\",\"value\":\"2DF27E7DB7B851F4864143383176753B\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"s_m_id_3299326472\",\"value\":\"AwMAAAA5AgAAAAIAAAE9AAAALDVyel4EYxiV3rsR0GRYULYBb//esjRtsF8mo2GY0tjFUCCz/66P+uI7oZMXAAAAI9ajKmX+PaiyV1zKDwf0JzA+C4wUK94RaKiqoe8oClW6zh8J\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"s_u_745896\",\"value\":\"cMYIdnwDfI9+IP61Hk5OrQ==\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false},{\"name\":\"uuid\",\"value\":\"8473bd7fa2206ad635a4.1669863028.1.0.0\",\"maxAge\":0,\"version\":-1,\"httpOnly\":false,\"sameSite\":\"NULL\",\"secure\":false}],\"parameterListMap\":{}}}";
        System.out.println(s.getBytes().length);
        System.out.println(s.length());
    }

    @Test
    public void testParseJson() {
        String json = "{ \"code\" : 200, \"msg\" : \"success\", \"data\" : { \"textVerifyStatus\" : 3, \"picAuditResults\" : [ { \"auditContentId\" : 1607357272873242667, \"url\" : \"http://p0.meituan.net/rmscashier/85b584af5541dd0c926f0cea555d6681618432.png\", \"auditStatus\" : 1, \"bizUniqId\" : null, \"extraInfo\" : null }, { \"auditContentId\" : 1574677153650315294, \"url\" : \"https://img.meituan.net/flagshipicon/c49e6f6b000632e0643c08d850a14e7a22486.png\", \"auditStatus\" : 1, \"bizUniqId\" : null, \"extraInfo\" : null }, { \"auditContentId\" : 1574677538066665482, \"url\" : \"https://qcloud.dpfile.com/pc/i2lCqDL7ogh2DDf4BJrNWo6qtcjxSLzmIZvatSQCZ4IHlfOLalPE2l37H557X3I1aLZ23ABw4IThDHW_mlzUpw.jpg\", \"auditStatus\" : 1, \"bizUniqId\" : null, \"extraInfo\" : null } ], \"textAuditResults\" : [ { \"fieldName\" : \"test0\", \"auditStatus\" : 6, \"verifyDesc\" : \"涉黄\", \"hitKey\" : \"色情\", \"value\" : \"回国人员证明0\" } ] }, \"success\" : true}" ;

        JSONObject jsonObject = JSON.parseObject(json);
        String code = String.valueOf(jsonObject.get("code"));
        System.out.println(code);
        System.out.println(jsonObject.get("msg"));
        JSONObject data = jsonObject.getJSONObject("data");
        ContentAuditResult result = JSON.toJavaObject(data, ContentAuditResult.class);
        System.out.println(result);
    }

    @Test
    public void test() {
        String a = "0.0.1" ;
        String b = "0.0.2" ;
        String s = a.replaceAll("\\.", "");
        String x = b.replaceAll("\\.", "");
        System.out.println(Integer.parseInt(s));
        System.out.println(Integer.parseInt(x));
        System.out.println(s);
    }

    @Test
    public void testTimeStamp() {

        String str = new String("be better");
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        System.out.println(new String(encodedBytes));
        System.out.println(new String(decodedBytes, Charset.forName("UTF-8")));
    }

    @Test
    public void testIO() {
        System.out.println(System.getProperty("user.dir"));
    }
}
