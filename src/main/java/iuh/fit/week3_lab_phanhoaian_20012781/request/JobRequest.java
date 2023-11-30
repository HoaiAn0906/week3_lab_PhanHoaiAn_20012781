package iuh.fit.week3_lab_phanhoaian_20012781.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private String jobName;
    private String jobDesc;
    private long company;
    private long[] skills;
}
