package edu.huflit.shopDT.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.huflit.shopDT.Database.DBHelper;

import edu.huflit.shopDT.R;

public class LoginActivity extends AppCompatActivity {
    TextView btnDangKy;
    EditText Username , Password;
    Button btnLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DB = new DBHelper(this, "DBTrungTamTheThao.sqlite", null, 1);
        Username = findViewById(R.id.Username1);
        Password = findViewById(R.id.Password1);
        btnLogin = findViewById(R.id.btDangNhap1);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = Username.getText().toString();
                String matkhau = Password.getText().toString();
                //Kiểm tra nhập tài khoản chưa
                if (taikhoan.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập tài khoản", Toast.LENGTH_LONG).show();
                    Username.requestFocus();
                    return;
                }
                //Kiểm tra nhập mật khẩu chưa
                if (matkhau.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập mật khẩu", Toast.LENGTH_LONG).show();
                    Password.requestFocus();
                    return;
                }
                //Lấy dữ liệu account
                Cursor cursor = DB.GetData(
                        "Select*" + "From ACCOUNT");
                boolean check = false;
                String user = ""; //Lưu tài khi đăng nhập thành công
                while(cursor.moveToNext()){
                    if (cursor.getString(0).equals(taikhoan) && cursor.getString(1).equals(matkhau)){
                        check = true;
                        user = cursor.getString(0);
                        break;
                    }
                }
                if (check) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(v -> openDangKy());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Username.setText(bundle.getString("user"));
            Password.setText(bundle.getString("pass"));
        }
    }
    public void openDangKy() {
        Intent intent = new Intent(this, DangKyActivity.class);
        startActivity(intent);
    }
}