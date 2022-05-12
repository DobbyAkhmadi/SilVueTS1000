package len.silvue.webpendukung.actual.application.service;

import len.silvue.webpendukung.actual.application.port.in.ImportActualUseCase;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.actual.application.port.out.SaveProblemPort;
import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.domains.Problem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportActualService implements ImportActualUseCase {
    private MultipartFile file;
    private String fileStrPath;
    private List<ActualPlan> actualPlanList;
    private ActualPlan actualPlan;
    private String[] columns = null;
    private final SimpleDateFormat actualCodeDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");

    private final SaveActualSchedulePort saveActualSchedulePort;
    private final SaveProblemPort saveProblemPort;

    @Override
    public void setMultipartFile(MultipartFile file) throws Exception {
        this.file = file;
    }

    @Override
    public void importData() throws Exception {
        try {
            saveToTemporaryFile();
            fetchDataActuals();
            deleteTemporaryFile();
        } catch(Exception e) {
            throw new Exception("Gagal import data", e);
        }
    }

    private void saveToTemporaryFile() throws Exception {
        try {
            createFileNamePath();
            saveFile();
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan file ke temporary", e);
        }
    }
    private void createFileNamePath() {
        UUID uuid = UUID.randomUUID();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileStrPath = uuid.toString() + "-" + fileName;
    }
    private void saveFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }

    private void fetchDataActuals() throws Exception {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileStrPath))) {
            actualPlanList = new ArrayList<>();
            String line = null;
            int cntLine = 0;
            while((line = bufferedReader.readLine()) != null) {
                if(cntLine > 0) {
                    columns = line.split(",");
                    insertColumnsToActualPlanDomain();
                    addActualPlanToActualPlanList();
                }
                cntLine++;
            }
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data actual", e);
        }
    }

    private void insertColumnsToActualPlanDomain() throws Exception {
        if(columns == null || columns.length < 15)
            return;

        Date initYear = getInitialDayOfYear();

        String train = trimText(columns[0]);
        String route = trimText(columns[1]);
        String type = trimText(columns[2]);
        String actualCodeStr = trimText(columns[3]);
        Date actualCode = (!actualCodeStr.isBlank() ? actualCodeDateFormat.parse(actualCodeStr) : null);
        String indexStr = trimText(columns[4]);
        int index = (!indexStr.isBlank() ? Integer.parseInt(indexStr) : 0);
        String station = trimText(columns[5]);
        String arrivalStr = trimText(columns[6]);
        Date arrival = (!arrivalStr.isBlank() ? dateFormat.parse(arrivalStr) : null);
        String acArrivalStr = trimText(columns[7]);
        Date acArrival = (!acArrivalStr.isBlank() ? dateFormat.parse(acArrivalStr) : null);
        String departStr = trimText(columns[8]);
        Date depart = (!departStr.isBlank() ? dateFormat.parse(departStr) : null);
        String acDepartStr = trimText(columns[9]);
        Date acDepart = (!acDepartStr.isBlank() ? dateFormat.parse(acDepartStr) : null);
        String platformScheduleStr = trimText(columns[10]);
        int platformSchedule = (!platformScheduleStr.isBlank() ? Integer.parseInt(platformScheduleStr) : 0);
        String platformActualStr = trimText(columns[11]);
        int platformActual = (!platformActualStr.isBlank() ? Integer.parseInt(platformActualStr) : 0);
        String department = trimText(columns[12]);
        String flagNextStr = trimText(columns[13]);
        boolean flagNext = (!flagNextStr.isBlank() && (flagNextStr.compareToIgnoreCase("1") == 0));
        String comment = trimText(columns[14]);
        Optional<Problem> optionalProblem = Optional.empty();
        if(columns.length == 16) {
            String problemStr = trimText(columns[15]);

            Problem problem = Problem.builder()
                    .problemName(problemStr)
                    .build();

            optionalProblem = saveProblemPort.storeProblem(problem);
        }

        Date arriveActualDateToInsert = (isDateValid(acArrival) ? acArrival : null);
        Date departActualDateToInsert = (isDateValid(acDepart) ? acDepart : null);

        actualPlan = ActualPlan.builder()
                .actualCode(actualCode)
                .arriveActualPlan(arriveActualDateToInsert)
                .arriveSchedule(arrival)
                .departActualPlan(departActualDateToInsert)
                .departSchedule(depart)
                .statiunActualPlan(station)
                .typePlan(type)
                .timeData(new Date())
                .comments(comment)
                .departmentActual(department)
                .indexActual(index)
                .platformSchedulePlan(platformSchedule)
                .platformActualPlan(platformActual)
                .flagActualPlan(flagNext)
                .ruteRoleActualPlan(route)
                .trainActualPlan(train)
                .problem(optionalProblem.orElse(null))
                .build();
    }

    private String trimText(String text) {
        return (text != null ? text.replaceAll("^\"+|\"+$", "") : "");
    }

    private boolean isDateValid(Date date) throws Exception {
        if(date == null)
            return false;
        Date initDate = getInitialDayOfYear();
        return date.compareTo(initDate) > 0;
    }

    private Date getInitialDayOfYear() throws Exception {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        Calendar cal = new GregorianCalendar(year-1, month, 1);
        return cal.getTime();
    }

    private void addActualPlanToActualPlanList() throws Exception {
        if(actualPlan != null) {
            actualPlanList.add(actualPlan);
        }
    }

    public void deleteTemporaryFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        Files.delete(path);
    }

    @Override
    public void saveToDatabase() throws Exception {
        saveActualSchedulePort.storeActualScheduleList(actualPlanList);
    }
}
