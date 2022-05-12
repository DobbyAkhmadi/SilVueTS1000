package len.silvue.webpendukung.web.controllers;

import com.opencsv.CSVWriter;
import len.silvue.webpendukung.eventlog.adapter.in.ExportAlarmCommand;
import len.silvue.webpendukung.eventlog.adapter.in.DeleteEventCommand;
import len.silvue.webpendukung.eventlog.adapter.out.web.LogMessageDto;
import len.silvue.webpendukung.eventlog.application.port.in.LoadLogMessageUseCase;
import len.silvue.webpendukung.eventlog.application.port.in.DeleteLogMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/alarm")
@RequiredArgsConstructor
@Slf4j
public class AlarmControl {
    private final LoadLogMessageUseCase loadLogMessageUseCase;
    private final DeleteLogMessageUseCase deleteLogMessageUseCase;
    @GetMapping
    public ModelAndView viewAlarmSchedule() {
        try {
            ModelAndView mav = new ModelAndView("alarm/alarm");
            List<LogMessageDto> EventLog = loadLogMessageUseCase.getAllLogMessage();
            mav.addObject("EventLog", EventLog);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Gagal view today schedule");
        }
    }

    @GetMapping("/view-export-event")
    private ModelAndView viewExportAlarm() {
        try {
            ModelAndView mav = new ModelAndView("alarm/view-export-event");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get export actual",e);
        }
    }

    @PostMapping("/exportAlarm")
    public ModelAndView exportAlarmCSV(HttpServletResponse response,
                                       @ModelAttribute ExportAlarmCommand exportAlarmCommand, BindingResult bindingResult) throws Exception {
        response.setContentType("text/csv");
        // format Write ALl CSV
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS1000-Export-Alarm "+
                exportAlarmCommand.getTimeMessage()+".csv";
        response.setHeader(headerKey, headerValue);
        CSVWriter csvWriter = new CSVWriter(response.getWriter());
//        // HEADER
        csvWriter.writeNext(new String[]{"Time","Message","User"});
//        // DETAIL
        List<LogMessageDto> eventLogDtoList = loadLogMessageUseCase.getAllLogMessageByDate(new Date());
        String[] dataNextLine = new String[2+eventLogDtoList.size()*4];
        for(LogMessageDto eventLogDelatiledDto: eventLogDtoList)
        {
            int incVar=0;
            dataNextLine[incVar++] = String.valueOf(eventLogDelatiledDto.getTimeLogMessage());
            dataNextLine[incVar++] = eventLogDelatiledDto.getMessage();
            dataNextLine[incVar++] = eventLogDelatiledDto.getUserLogin();
            csvWriter.writeNext(dataNextLine);
        }
        return null;
    }

    //==================================================================================================================
    // DELETE ALL EVENTLOG
    //==================================================================================================================
    @GetMapping("/delete-all-event")
    public ModelAndView deleteAllLogger(@ModelAttribute DeleteEventCommand deleteEventCommand) {
        try {
            deleteLogMessageUseCase.deleteAllEvent();
            viewAlarmSchedule();
            return new ModelAndView("redirect:/alarm" );
        }catch (Exception e) {
            throw new RuntimeException("failed to delete all problem",e);
        }
    }


}
