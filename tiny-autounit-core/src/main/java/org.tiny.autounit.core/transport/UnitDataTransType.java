package org.tiny.autounit.core.transport;

/**
 * @author shichaoyang
 * @Description: mock数据传输方式
 * @date 2021-08-02 15:56
 */
public enum UnitDataTransType {

    //netty传输
    BY_NETTY("by_netty", 100),

    //http传输，需要起tomcat
    BY_HTTP("by_http", 101);

    UnitDataTransType(String type, int code) {
        this.type = type;
        this.code = code;
    }

    //类型
    private String type;

    //编码
    private int code;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
