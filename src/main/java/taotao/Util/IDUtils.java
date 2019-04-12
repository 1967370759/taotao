package taotao.Util;

import java.util.Random;

public class IDUtils {
    //图片名生成
    public static String genlmageName() {
        //获取当前时间长整形包含毫米
        long millis = System.currentTimeMillis();
        //加上三为随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三为前面补o
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * 商品id生成器
     *
     * @return id
     */
    public static long getltemId() {
        //获取当前时间的长整形包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getltemId());
        }
    }
}
