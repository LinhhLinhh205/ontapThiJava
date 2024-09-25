package gui;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.QLSinhVien;
import model.SinhVien;

/**
 *
 * @author ADMIN
 */
public class FrmQLSinhVien extends JFrame {

    private JTable tblSinhVien;
    private JButton btThem, btXoa;
    private JButton btDocFile, btGhiFile;

    private DefaultTableModel model;
    private JTextField txtMaSo, txtHoTen, txtDTB;

    private JRadioButton rdNam, rdNu;
    private JCheckBox chkSapXep;

    private static final String FILE_NAME = "data.txt";

    private QLSinhVien qlsv = new QLSinhVien();

    public FrmQLSinhVien(String title) {
        super(title);
        createGUI();
        createEvent();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên", "Phái", "ĐTB", "Xếp loại"};
        model = new DefaultTableModel(null, columnNames);
        tblSinhVien = new JTable(model);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblSinhVien);

        //tạo các điều khiển nhập liệu  và các nút lệnh
        JPanel p = new JPanel();
        p.add(new JLabel("Mã sinh viên"));
        p.add(txtMaSo = new JTextField(5));
        p.add(new JLabel("Họ tên"));
        p.add(txtHoTen = new JTextField(10));

        p.add(rdNam = new JRadioButton("Nam"));
        p.add(rdNu = new JRadioButton("Nữ"));

        ButtonGroup btgPhai = new ButtonGroup();
        btgPhai.add(rdNam);
        btgPhai.add(rdNu);

        p.add(new JLabel("Điểm TB"));
        p.add(txtDTB = new JTextField(10));

        p.add(btDocFile = new JButton("Đọc File"));
        p.add(btThem = new JButton("Thêm"));
        p.add(btXoa = new JButton("Xoá"));
        p.add(btGhiFile = new JButton("Ghi File"));

        JPanel p2 = new JPanel();
        p2.add(chkSapXep = new JCheckBox("Sắp xếp theo học lực"));

        //add các thành phần vào cửa sổ
        add(p, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
    }

    private void createEvent() {
        btDocFile.addActionListener((e)->{
            qlsv.DocDanhSachSinhVien(FILE_NAME);
            loadData();
        });
        btThem.addActionListener((e)->{
            String error="";
            String ma=txtMaSo.getText();
            String ten=txtHoTen.getText();
            
                    
        });
    }

    private void loadData() {
        model.setRowCount(0);
        for(SinhVien sv:qlsv.getDsSinhVien()){
            model.addRow(new Object[]{sv.getMaso(),sv.getHoten(),sv.isGioitinh(),sv.getDiemTB(),sv.getHocLuc()});
        }
    }

}

