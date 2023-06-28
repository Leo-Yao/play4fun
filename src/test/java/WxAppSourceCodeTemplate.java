import java.io.Serializable;
import java.util.Date;

public class WxAppSourceCodeTemplate implements Serializable {
    private static final long serialVersionUID = -7992313712305684392L;

    private Integer templateId;

    private String userDesc;

    private String userVersion;

    private Date createTime;

    public WxAppSourceCodeTemplate() {
    }

    public Integer getTemplateId() {
        return this.templateId;
    }

    public WxAppSourceCodeTemplate setTemplateId(Integer templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getUserDesc() {
        return this.userDesc;
    }

    public WxAppSourceCodeTemplate setUserDesc(String userDesc) {
        this.userDesc = userDesc;
        return this;
    }

    public String getUserVersion() {
        return this.userVersion;
    }

    public WxAppSourceCodeTemplate setUserVersion(String userVersion) {
        this.userVersion = userVersion;
        return this;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public WxAppSourceCodeTemplate setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String toString() {
        return "WxAppSourceCodeTemplate{templateId=" + this.templateId + ", userDesc='" + this.userDesc + '\'' + ", userVersion='" + this.userVersion + '\'' + ", createTime=" + this.createTime + '}';
    }
}
