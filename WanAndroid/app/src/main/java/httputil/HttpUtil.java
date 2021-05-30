package httputil;

import android.app.Activity;
import android.net.IpSecManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import config.ApiConfig;
import data.Article;
import data.Child;
import json.ParseJson;

public class HttpUtil {

    //发送首页文章网络请求
    public static void sendHomePageRequest(final String address, final int page, ArrayList<Article> articles,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                String response="";
                String newAddress="";
                try{
                    for(int i=page;i<page+10;i++){
                        newAddress=UrlHomeConnect(address,i);
                        URL url=new URL(newAddress);
                        connection=(HttpURLConnection)url.openConnection();
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        connection.connect();
                        InputStream in=connection.getInputStream();
                        response=StreamToString(in);
                        ParseJson.parseHomeArticle(response,articles);
                        System.out.println(articles.get(i).getShareUser());
                    }
                    System.out.println(articles.size());
                    if (listener!=null){
                        listener.onFinish(response);
                    }
                }catch (Exception e){
                    listener.onError(e);
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    //发送搜索网络请求
    public static void sendSearchRequest(final String address,ArrayList<Article> articles,final String author,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                String response="";
                String newAddress=null;
                try{
                    for (int page=0;page<15;page++){
                        newAddress= UrlSearchConnect(address,page,author);
                        URL url=new URL(newAddress);
                        connection=(HttpURLConnection) url.openConnection();
                        connection.setReadTimeout(8000);
                        connection.setConnectTimeout(8000);
                        connection.connect();
                        InputStream in=connection.getInputStream();
                        response=StreamToString(in);
                        ParseJson.parseSearch(response,articles);
                    }

                    if(listener!=null){
                        listener.onFinish(response);
                    }
                }catch(Exception e){
                    listener.onError(e);
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //发送获取体系数据请求
    public static void sendSystemRequest(final String address, ArrayList<Child> childArrayList,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                String response="";
                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in=connection.getInputStream();
                    response=StreamToString(in);
                    ParseJson.parseTree(response,childArrayList);
                    if(listener!=null){
                        listener.onFinish(response);
                    }
                }catch (Exception e){
                    listener.onError(e);
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //发送体系下文章的请求
    public static void sendKindRequest(final String address,ArrayList<Article> articles,int id,HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                String response="";
                String newAdderss="";
                try{
                    for(int i=0;i<5;i++){
                        newAdderss=UrlKindConnect(address,i,id);
                        URL url=new URL(newAdderss);
                        connection=(HttpURLConnection)url.openConnection();
                        connection.setReadTimeout(8000);
                        connection.setConnectTimeout(8000);
                        connection.connect();
                        InputStream in=connection.getInputStream();
                        response=StreamToString(in);
                        ParseJson.parseHomeArticle(response,articles);
                    }
                    if (listener!=null){
                        listener.onFinish(response);
                    }
                }catch (Exception e){
                    listener.onError(e);
                }finally {
                    if(connection!=null)
                    {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    //发送登录网络请求
    public static void sendLogRequest(final String address,final HttpCallbackListener listener,final String...userinfo){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                String response=null;
                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setUseCaches(false);
                    connection.setDoOutput(true);
                    connection.connect();
                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    String data=UrlLoginconnect(userinfo);
                    out.writeBytes(data);
                    out.flush();
                    out.close();
                    int responsecode=connection.getResponseCode();
                    if(responsecode==200){
                        InputStream in=connection.getInputStream();
                        response=StreamToString(in);
                    }
                    if(listener!=null){
                        listener.onFinish(response);
                    }
                }catch(Exception e){
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    //把网络请求返回的信息转为字符串
    public static String StreamToString(InputStream in) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder response=new StringBuilder();
        String line;
        while((line=reader.readLine())!=null){
            response.append(line);
        }
        in.close();
        reader.close();
        return response.toString();
    }


    //对请求链接进行拼接
    public static String UrlLoginconnect(String ...userinfo){
        String data="username="+userinfo[0]+"&"+"password="+userinfo[1];
        return data;
    }

    //对搜索信息进行拼接
    public static String UrlSearchConnect(String address,int page,String author){
        String newAddress=address+"/"+page+"/"+"json"+"?"+"author="+author;
        return newAddress;
    }

    //对首页文章进行拼接
    public static String UrlHomeConnect(String address,int page){
        String newAddress=address+"/"+page+"/"+"json";
        return newAddress;
    }

    public static String UrlKindConnect(String address,int page,int id){
        String newAddress=address+"/"+page+"/"+"json"+"?"+"cid="+id;
        return newAddress;
    }
}
