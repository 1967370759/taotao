package taotao.pojo;
//处理图片
public class PictureResult {
    private int prroe;
    private String url;
    private String massage;

//失败时调用的方法
    public static PictureResult error(String massage) {
        return new PictureResult(1,null,massage);
    }
    //成功时调用的方法
    public static PictureResult ok(String url) {
        return new PictureResult(0,url,null);
    }

    public int getPrroe() {
        return prroe;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public PictureResult(int prroe, String url, String massage) {
        this.prroe = prroe;
        this.url = url;
        this.massage = massage;
    }
}
