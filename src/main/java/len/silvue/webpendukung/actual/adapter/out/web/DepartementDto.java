package len.silvue.webpendukung.actual.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DepartementDto {
    private int idDepartement;
    private String nameDepartement;
}
