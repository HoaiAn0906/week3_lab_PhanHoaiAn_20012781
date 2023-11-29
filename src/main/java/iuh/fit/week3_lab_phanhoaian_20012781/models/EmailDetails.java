package iuh.fit.week3_lab_phanhoaian_20012781.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
}
