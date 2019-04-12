package taotao.text;

import org.apache.log4j.Logger;

public class log4j {
    private static Logger logger = Logger.getLogger(log4j.class);

    public static void main(String[] args) {

        //BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境。

        logger.info("aaaa");

        logger.debug("bbbb");

        logger.error("cccc");


    }
}
