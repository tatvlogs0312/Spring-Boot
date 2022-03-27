package vn.techmaster.btbuoi2.model;

public enum Location {
    HaNoi("Ha Noi") , 
    HaiPhong("Hai Phong"),
    DaNang("Da Nang"),
    HoChiMinh("Ho Chi Minh");

    private String value;
 
    private Location(String value) {
        this.value = value;
    }

    public String des(){
        return this.value;
    }
}
