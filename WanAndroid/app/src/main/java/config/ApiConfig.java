package config;

import com.example.wanandroid.R;

import java.util.Random;

public class ApiConfig {
    public static final String LOGIN="https://wanandroid.com/user/login";
    public static final String HOMEPAGE="https://www.wanandroid.com/article/list";
    public static final String SEARCH="https://wanandroid.com/article/list";
    public static final String TREE="https://www.wanandroid.com/tree/json";
    public static final String KINDS="https://www.wanandroid.com/article/list";

    private static int RandomNum(){
        Random random=new Random();
        return random.nextInt(10);
    }
}
