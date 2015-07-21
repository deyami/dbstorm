package com.reform.dbstorm.xml;

/**
 * 数据服务配置信息异常.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-8 下午5:44:19
 */
public class StromXmlConfigException extends Exception {

    private static final long serialVersionUID = 1L;

    private String            xml              = "";

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public StromXmlConfigException(final String msg) {
        super(msg);
    }

    public StromXmlConfigException(final Throwable cause) {
        initCause(cause);
    }

    public StromXmlConfigException(final String msg, final Throwable cause) {
        super(msg);
        initCause(cause);
    }
}
