package len.silvue.webpendukung.todays.adapter.in;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddTodayCommand {
    private String idTypeMasterPlan;
    private String nameTypeMasterPlan;

}
