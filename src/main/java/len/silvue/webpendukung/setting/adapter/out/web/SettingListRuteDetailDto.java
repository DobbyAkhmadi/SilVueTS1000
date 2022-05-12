package len.silvue.webpendukung.setting.adapter.out.web;

public class SettingListRuteDetailDto {
    private String stations;
    private int idRuteRole;
    private String namaRuteRole;

    public int getIdRuteRole() {
        return idRuteRole;
    }

    public void setIdRuteRole(int idRuteRole) {
        this.idRuteRole = idRuteRole;
    }

    public String getNamaRuteRole() {
        return namaRuteRole;
    }

    public void setNamaRuteRole(String namaRuteRole) {
        this.namaRuteRole = namaRuteRole;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }
}
