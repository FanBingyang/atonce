package com.byfan.photos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @Author:范秉洋
 * @Date:2019/8/25 17:31
 */
public class HttpRequestUtil {

    /**
     * 向指定url发送GTE方法的请求
     * @param url   发送请求的url
     * @param param   请求参数，参数形式是name1=value1&name2=value2
     * @return URL  所代表远程资源的响应结果
     */
    public static String sendGet(String url,String param){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            //打开和URL之间的链接
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            //建立实际的链接
            connection.connect();
            //获取所用响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //遍历所有的响应头字段
//            for(String key:map.keySet()){
//                System.out.println(key + "--->" + map.get(key));
//            }
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输入流
        finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String sendPost(String url,String param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try{
            URL realURl = new URL(url);
            //打开和URL之间的链接
            URLConnection conn = realURl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输入流
            out = new PrintWriter((conn.getOutputStream()));
            //发送请求参数
            out.print(param);
            //flush输出流的缓冲
            out.flush();
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}

