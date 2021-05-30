package data;

public class Article {
    private String sharedUser;//作者名
    private String link;//链接
    private String title;
    private String superName;
    private String chapterName;
    private String super_ChapterName;

    public Article(){

    }

    public String getShareUser() {
        return sharedUser;
    }

    public void setSharedUser(String sharedUser) {
        this.sharedUser = sharedUser;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getSuper_ChapterName() {
        return super_ChapterName;
    }

    public void setSuper_ChapterName() {
        this.super_ChapterName = superName+"-"+chapterName;
    }
}
