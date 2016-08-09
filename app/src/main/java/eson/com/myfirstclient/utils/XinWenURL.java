package eson.com.myfirstclient.utils;

/**
 * Created by HP on 2016/8/6.
 */
public class XinWenURL {
    private int startPage = 0;
    //热点
    String redian = "http://c.3g.163.com/nc/article/list/T1429173762551/0-20.html";
    //头条
    String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + startPage + "-" + (startPage + 20) + ".html";

    public String getToutiao() {
        return null;
    }

    public void setStartPage(int startPage){
        this.startPage = startPage;
    }

    public int getStartPage() {
        return startPage;
    }
}
