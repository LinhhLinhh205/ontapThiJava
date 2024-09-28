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
        btDocFile.addActionListener((e) -> {
            qlsv.DocDanhSachSinhVien(FILE_NAME);
            loadDataToJTable();
        });
        btThem.addActionListener((e) -> {
            String error = "";
            if (txtMaSo.getText().length() == 0) {
                error = "Chưa nhập mã sinh viên";
            } else {
                String maso = txtMaSo.getText();
                for (char c : maso.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        error += "Mã sinh viên không được chứa chữ";
                        break;
                    }
                }
            }
            if (txtHoTen.getText().length() == 0) {
                error += "\nChưa nhập họ tên sinh viên";
            } else {
                String hoten = txtHoTen.getText();
                for (char c : hoten.toCharArray()) {
                    if (Character.isDigit(c)) {
                        error += "Họ tên sinh viên không được chứa số\n";
                        break;
                    }
                }
            }
            if (txtDTB.getText().length() == 0) {
                error += "\n Chưa nhập diểm trung bình";
            } else {
                String maso = txtDTB.getText();
                for (char c : maso.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        error += "Điểm trung bình không được chứa chữ";
                        break;
                    }
                }
            }
            if (error.length() > 0) {
                JOptionPane.showMessageDialog(this, error, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String maso = txtMaSo.getText();
            String hoten = txtHoTen.getText();
            boolean gioitinh = rdNam.isSelected();
            double diemtb = Double.parseDouble(txtDTB.getText());

            SinhVien sv = new SinhVien(maso, hoten, gioitinh, diemtb);

            if (qlsv.themSV(sv)) {

                loadDataToJTable();
                JOptionPane.showMessageDialog(this, "Đã thêm sinh viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Thao tác thêm thất bại\nDo trùng mã sinh viên", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        });
        btXoa.addActionListener((e) -> {
            String maso = "";
            int selectedRowIndex = tblSinhVien.getSelectedRow();
            if (selectedRowIndex < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sinh viên cần xoá", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            maso = tblSinhVien.getValueAt(selectedRowIndex, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá sinh viên với mã số: " + maso + "?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (qlsv.xoaSV(maso)) {
                    loadDataToJTable();
                    JOptionPane.showMessageDialog(this, "Đã xoá sinh viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Thao tác xoá thất bại\nDo tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btGhiFile.addActionListener((e) -> {

            if (qlsv.GhiDanhSachSinhVien(FILE_NAME)) {
                JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Đã ghi dữ liệu thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        chkSapXep.addItemListener((e) -> {

            if (chkSapXep.isSelected()) {
                qlsv.sapXepTheoHocLuc();
                loadDataToJTable();
            }

        });
    }

    private void loadDataToJTable() {
        model.setRowCount(0);
        for (SinhVien sv : qlsv.getDsSinhVien()) {
            model.addRow(new Object[]{sv.getMaso(), sv.getHoten(), sv.isGioitinh(), sv.getDiemTB(), sv.getHocLuc()});
        }
    }

}
