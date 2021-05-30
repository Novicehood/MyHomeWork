package json;

import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import data.Article;
import data.Child;

public class ParseJson {
    //解析登录的用户信息
    public static int parseUserPassword(String Data) throws JSONException {
        int errorcode;
        if(Data!=null){
            JSONObject jsonObject=new JSONObject(Data);
            errorcode=jsonObject.getInt("errorCode");
        }else {
            errorcode=-1;
        }
        return errorcode;
    }

    //解析首页的文章信息
    public static void parseHomeArticle(String jsonData, ArrayList<Article> articles) throws JSONException {
        JSONObject object=new JSONObject(jsonData);
        JSONObject data=object.getJSONObject("data");
        JSONArray datas=data.getJSONArray("datas");
        for(int i=0;i<datas.length();i++){
            JSONObject information=datas.getJSONObject(i);
            Article article=new Article();
            article.setSharedUser(information.getString("shareUser"));
            article.setLink(information.getString("link"));
            article.setTitle(information.getString("title"));
            article.setSuperName(information.getString("superChapterName"));
            article.setChapterName(information.getString("chapterName"));
            article.setSuper_ChapterName();
            articles.add(article);
        }
    }

    public static void parseSearch(String jsonData,ArrayList<Article> articles) throws JSONException {
        JSONObject object=new JSONObject(jsonData);
        JSONObject data=object.getJSONObject("data");
        JSONArray datas=data.getJSONArray("datas");
        for(int i=0;i<datas.length();i++){
            JSONObject information=datas.getJSONObject(i);
            Article article=new Article();
            article.setSharedUser(information.getString("author"));
            article.setLink(information.getString("link"));
            article.setTitle(information.getString("title"));
            article.setSuperName(information.getString("superChapterName"));
            article.setChapterName(information.getString("chapterName"));
            article.setSuper_ChapterName();
            articles.add(article);
        }
    }

    //解析体系树信息
    public static void parseTree(String jsonData, ArrayList<Child> childArrayList)throws JSONException{
        JSONObject object=new JSONObject(jsonData);
        JSONArray data=object.getJSONArray("data");
        for(int i=0;i<data.length();i++){
            JSONObject a=data.getJSONObject(i);
            Child child=new Child();
            child.setName(a.getString("name"));
            JSONArray children=a.getJSONArray("children");
            for(int j=0;j<children.length();j++){
                JSONObject b=children.getJSONObject(j);
                child.children.add(b.getString("name"));
                child.ids.add(b.getInt("id"));
            }
            childArrayList.add(child);
            System.out.println(childArrayList.size()+childArrayList.get(i).getName());
        }
    }

}
