package group.whiterabbit.employeedirectory.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import group.whiterabbit.employeedirectory.R;
import group.whiterabbit.employeedirectory.db.DBHelper;
import group.whiterabbit.employeedirectory.model.Employee;

public class EmployeeDetailsPage extends AppCompatActivity {

    int employeeId = 0;
    DBHelper dbHelper;
    Employee employee;
    TextView nameLabel, emailLabel, phoneLabel, websiteLabel, nameValue, emailValue, phoneValue, websiteValue;
    TextView companyLabel, companyValue, catchPhraseLabel, catchPhraseValue, bsLabel, bsValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details_page);
        Intent intent = getIntent();
        employeeId = intent.getExtras().getInt("employeeId");
        init();

    }

    private void init() {
        dbHelper = new DBHelper(getApplicationContext());
        employee = dbHelper.getEmployeeById(employeeId);
        setBaseDataUi();
        setBaseData();
        setCompanyDataUi();
        setCompanyData();
    }

    private void setCompanyData() {
        companyLabel.setText("Company name");
        catchPhraseLabel.setText("Catch Phrase");
        bsLabel.setText("BS");
        companyValue.setText(employee.getCompany().getName());
        catchPhraseValue.setText(employee.getCompany().getCatchPhrase());
        bsValue.setText(employee.getCompany().getBs());
    }

    private void setCompanyDataUi() {

        companyLabel = findViewById(R.id.company_data_layout).findViewById(R.id.filed1).findViewById(R.id.label);
        companyValue = findViewById(R.id.company_data_layout).findViewById(R.id.filed1).findViewById(R.id.value);

        catchPhraseLabel = findViewById(R.id.company_data_layout).findViewById(R.id.filed2).findViewById(R.id.label);
        catchPhraseValue = findViewById(R.id.company_data_layout).findViewById(R.id.filed2).findViewById(R.id.value);

        bsLabel = findViewById(R.id.company_data_layout).findViewById(R.id.filed3).findViewById(R.id.label);
        bsValue = findViewById(R.id.company_data_layout).findViewById(R.id.filed3).findViewById(R.id.value);

        findViewById(R.id.company_data_layout).findViewById(R.id.filed4).findViewById(R.id.value).setVisibility(View.GONE);
        findViewById(R.id.company_data_layout).findViewById(R.id.filed5).findViewById(R.id.value).setVisibility(View.GONE);
        findViewById(R.id.company_data_layout).findViewById(R.id.filed4).findViewById(R.id.label).setVisibility(View.GONE);
        findViewById(R.id.company_data_layout).findViewById(R.id.filed5).findViewById(R.id.label).setVisibility(View.GONE);
    }

    private void setBaseDataUi() {
        nameLabel = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed1).findViewById(R.id.label);
        emailLabel = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed2).findViewById(R.id.label);
        phoneLabel = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed3).findViewById(R.id.label);
        websiteLabel = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed4).findViewById(R.id.label);
        findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed5).findViewById(R.id.label).setVisibility(View.GONE);
        nameValue = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed1).findViewById(R.id.value);
        emailValue = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed2).findViewById(R.id.value);
        phoneValue = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed3).findViewById(R.id.value);
        websiteValue = findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed4).findViewById(R.id.value);
        findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed5).findViewById(R.id.value).setVisibility(View.GONE);


    }

    private void setBaseData() {

        Glide.with(getApplicationContext()).load(employee.getProfileImage()).into((ImageView) findViewById(R.id.employee_profile));

        nameLabel.setText("Name");
        emailLabel.setText("Email");
        phoneLabel.setText("Phone");
        websiteLabel.setText("Website");


        nameValue.setText(employee.getName());
        emailValue.setText(employee.getEmail());
        phoneValue.setText(employee.getPhone());
        websiteValue.setText(employee.getWebsite());

    }
}