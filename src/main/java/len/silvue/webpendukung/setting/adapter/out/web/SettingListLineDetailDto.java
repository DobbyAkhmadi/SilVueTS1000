package len.silvue.webpendukung.setting.adapter.out.web;

public class SettingListLineDetailDto {
    private String stations;
    private int idLine;
    private String namaLine;

    public int getIdLine() {
        return idLine;
    }

    public void setIdLine(int idLine) {
        this.idLine = idLine;
    }

    public String getNamaLine() {
        return namaLine;
    }

    public void setNamaLine(String namaLine) {
        this.namaLine = namaLine;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }
}
