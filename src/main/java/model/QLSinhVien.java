/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import util.FileHelper;

/**
 *
 * @author ADMIN
 */
public class QLSinhVien {
    private ArrayList<SinhVien> dsSinhVien;
    
    public QLSinhVien() {
        dsSinhVien = new ArrayList<>();        
    }

    public QLSinhVien(ArrayList<SinhVien> ds) {
        this.dsSinhVien = ds;
    }
    
    public ArrayList<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }
    
    public void DocDanhSachSinhVien(String filename)
    {
        ArrayList<String> data= FileHelper.readFileText(filename);
        dsSinhVien.clear();
        for(String item:data){
            String [] arr=item.split(";");
            SinhVien sv=new SinhVien();
            sv.setMaso(arr[0]);
            sv.setHoten(arr[1]);
            sv.setGioitinh(Boolean.parseBoolean(arr[2]));
            sv.setDiemTB(Double.parseDouble(arr[3]));
            dsSinhVien.add(sv);
        }
    }

    public boolean GhiDanhSachSinhVien(String filename)
    {      
        
        return true;
    }    
    
    public boolean themSV(SinhVien sv)
    {
       //sinh viên viết code 
        return true;
    }  
    
    public boolean xoaSV(String maso)
    {
        //sinh viên viết code 
        return true;
    }     
   
    public void sapXepTheoHocLuc()
    {
        //sinh viên viết code         
    }   
    
}
