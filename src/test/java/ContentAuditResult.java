//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.Serializable;
import java.util.List;


public class ContentAuditResult implements Serializable {
    private static final long serialVersionUID = -9140408017181567324L;

    public Integer textVerifyStatus;

    public List<PicAuditResultTO> picAuditResults;

    public List<TextAuditResultTO> textAuditResults;

    public static ContentAuditResult.ContentAuditResultBuilder builder() {
        return new ContentAuditResult.ContentAuditResultBuilder();
    }

    public Integer getTextVerifyStatus() {
        return this.textVerifyStatus;
    }

    public List<PicAuditResultTO> getPicAuditResults() {
        return this.picAuditResults;
    }

    public List<TextAuditResultTO> getTextAuditResults() {
        return this.textAuditResults;
    }

    public void setTextVerifyStatus(Integer textVerifyStatus) {
        this.textVerifyStatus = textVerifyStatus;
    }

    public void setPicAuditResults(List<PicAuditResultTO> picAuditResults) {
        this.picAuditResults = picAuditResults;
    }

    public void setTextAuditResults(List<TextAuditResultTO> textAuditResults) {
        this.textAuditResults = textAuditResults;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ContentAuditResult)) {
            return false;
        } else {
            ContentAuditResult other = (ContentAuditResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$textVerifyStatus = this.getTextVerifyStatus();
                    Object other$textVerifyStatus = other.getTextVerifyStatus();
                    if (this$textVerifyStatus == null) {
                        if (other$textVerifyStatus == null) {
                            break label47;
                        }
                    } else if (this$textVerifyStatus.equals(other$textVerifyStatus)) {
                        break label47;
                    }

                    return false;
                }

                Object this$picAuditResults = this.getPicAuditResults();
                Object other$picAuditResults = other.getPicAuditResults();
                if (this$picAuditResults == null) {
                    if (other$picAuditResults != null) {
                        return false;
                    }
                } else if (!this$picAuditResults.equals(other$picAuditResults)) {
                    return false;
                }

                Object this$textAuditResults = this.getTextAuditResults();
                Object other$textAuditResults = other.getTextAuditResults();
                if (this$textAuditResults == null) {
                    if (other$textAuditResults != null) {
                        return false;
                    }
                } else if (!this$textAuditResults.equals(other$textAuditResults)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ContentAuditResult;
    }


    public String toString() {
        return "ContentAuditResult(textVerifyStatus=" + this.getTextVerifyStatus() + ", picAuditResults=" + this.getPicAuditResults() + ", textAuditResults=" + this.getTextAuditResults() + ")";
    }

    public ContentAuditResult() {
    }

    public ContentAuditResult(Integer textVerifyStatus, List<PicAuditResultTO> picAuditResults, List<TextAuditResultTO> textAuditResults) {
        this.textVerifyStatus = textVerifyStatus;
        this.picAuditResults = picAuditResults;
        this.textAuditResults = textAuditResults;
    }

    public static class ContentAuditResultBuilder {
        private Integer textVerifyStatus;
        private List<PicAuditResultTO> picAuditResults;
        private List<TextAuditResultTO> textAuditResults;

        ContentAuditResultBuilder() {
        }

        public ContentAuditResult.ContentAuditResultBuilder textVerifyStatus(Integer textVerifyStatus) {
            this.textVerifyStatus = textVerifyStatus;
            return this;
        }

        public ContentAuditResult.ContentAuditResultBuilder picAuditResults(List<PicAuditResultTO> picAuditResults) {
            this.picAuditResults = picAuditResults;
            return this;
        }

        public ContentAuditResult.ContentAuditResultBuilder textAuditResults(List<TextAuditResultTO> textAuditResults) {
            this.textAuditResults = textAuditResults;
            return this;
        }

        public ContentAuditResult build() {
            return new ContentAuditResult(this.textVerifyStatus, this.picAuditResults, this.textAuditResults);
        }

        public String toString() {
            return "ContentAuditResult.ContentAuditResultBuilder(textVerifyStatus=" + this.textVerifyStatus + ", picAuditResults=" + this.picAuditResults + ", textAuditResults=" + this.textAuditResults + ")";
        }
    }
}
