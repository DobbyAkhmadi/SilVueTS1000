package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.out.web.ParamActualPlanDto;

public interface FindParamActualPlanUseCase {
    ParamActualPlanDto getParamByActualCode(String actualCode) throws Exception;
    ParamActualPlanDto getAllParam() throws Exception;
}
