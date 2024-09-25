package model;

public class SinhVien {
    private String maso;
    private String hoten;
    private boolean gioitinh;
    private double diemTB;

    public SinhVien() {
    }

    public SinhVien(String maso, String hoten, boolean gioitinh, double diemTB) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.diemTB = diemTB;
    }

    public SinhVien(String maso) {
        this.maso = maso;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    public String getHocLuc()
    {
       String kq="";
       if(diemTB<5)
           kq="Yếu";
       else if(diemTB<6.5)
           kq="Trung bình";
       else if(diemTB<7.5)
           kq="Khá";
       else if(diemTB<9)
           kq="Giỏi";
       else
           kq="Xuất sắc";
       return kq;
    }  
   
}
