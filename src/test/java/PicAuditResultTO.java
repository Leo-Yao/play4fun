
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PicAuditResultTO {


    public Long auditContentId;


    public String url;


    public Integer auditStatus;


    public String bizUniqId;


    public String extraInfo;

}
