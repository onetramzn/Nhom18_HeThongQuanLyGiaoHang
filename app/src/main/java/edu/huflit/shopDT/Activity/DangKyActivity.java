package edu.huflit.shopDT.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.huflit.shopDT.Database.DBHelper;
import edu.huflit.shopDT.R;
import edu.huflit.shopDT.Activity.LoginActivity;


public class DangKyActivity extends AppCompatActivity {
    EditText TenDangNhap,MatKhau,TenDayDu,SoDieNThoai,Gmail,DiaChi;
    Button DangKy,QuayLaiDangNhap;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        DB = new DBHelper(this, "DBTrungTamTheThao.sqlite", null, 1);
        TenDangNhap = findViewById(R.id.TenDangNhap);
        MatKhau = findViewById(R.id.MatKhau);
        TenDayDu = findViewById(R.id.TenDayDu);
        SoDieNThoai =findViewById(R.id.SoDienThoai);
        Gmail = findViewById(R.id.Gmail);
        DiaChi = findViewById(R.id.DiaChi);
        DangKy = findViewById(R.id.DangKy);
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = TenDangNhap.getText().toString();
                String matkhau = MatKhau.getText().toString();
                String hoten = TenDayDu.getText().toString();
                String sdt = SoDieNThoai.getText().toString();
                String gmail = Gmail.getText().toString();
                String diachi = DiaChi.getText().toString();

                //region Kiểm tra chuỗi sdt có phải số không
                String[] token = sdt.split("\\.");
                int checkLength = 0;
                for (int i = 0; i < token.length; i++){
                    checkLength += token[i].length();
                }
                if (checkLength != 10){
                    Toast.makeText(getApplicationContext(), "Phone number must have 10 numbers.", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean is_Number = true;
                for (int i = 0; i < token.length; i++){
                    if (!isNumber(token[i])){
                        is_Number = isNumber(sdt);
                        break;
                    }
                }
                if (sdt.length() == 0 || is_Number == false){
                    Toast.makeText(getApplicationContext(), "Phone number is invalid. Please try again!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    SoDieNThoai.setText(toPhoneNumber(token));
                }
                //endregion
                //Kiểm tra gmail
                if (!gmail.equals("")){
                    boolean check = false;
                    for (int i = 0; i < gmail.length(); i++){
                        if (gmail.charAt(i) == '@'){
                            check = true;
                        }
                    }
                    if (!check){
                        Toast.makeText(getApplicationContext(), "Gmail phải có '@', sai định dạng!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                //Kiểm tra đã nhập đủ thông tin cần thiết chưa
                if (taikhoan.length() == 0 || matkhau.length() == 0 || hoten.length() == 0){
                    Toast.makeText(DangKyActivity.this, "Username, Password, Name are required", Toast.LENGTH_LONG).show();
                    return;
                }

                //Thông báo đăng kí thành công
                if (DB.AddAccount(taikhoan, matkhau, hoten, sdt, gmail, diachi) == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DangKyActivity.this);
                    builder.setMessage("Create Account Success.");
                    builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(DangKyActivity.this, LoginActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("user", TenDangNhap.getText().toString());
                            bundle.putString("pass", MatKhau.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    // Create the AlertDialog object
                    builder.create().show();
                    return;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DangKyActivity.this);
                    builder.setMessage("Username already exists.");
                    builder.setPositiveButton("Forgot Password?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(DangKyActivity.this, QuenMatKhauActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("New Username", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    // Create the AlertDialog object
                    builder.create().show();
                    return;
                }
            }
        });
        QuayLaiDangNhap = findViewById(R.id.QuayLaiDangNhap);
        QuayLaiDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private boolean isNumber(String x){
        return x.chars().allMatch( Character::isDigit );
    }
    private String toPhoneNumber(String[] x){
        String originalString = "";
        for (int i = 0 ; i < x.length; i++)
            originalString += x[i];
        String phoneNumber = new String();
        for (int i = 0; i < originalString.length(); i++){
            phoneNumber += originalString.charAt(i);
            if (i == 1 || i == 5)
                phoneNumber += ".";
        }
        return phoneNumber;
    }
}