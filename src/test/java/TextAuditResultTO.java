

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextAuditResultTO {
    public String fieldName;

    public Integer auditStatus;

    public String verifyDesc;

    public String hitKey;

    public String value;

}
