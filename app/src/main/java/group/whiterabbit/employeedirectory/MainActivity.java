package group.whiterabbit.employeedirectory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group.whiterabbit.employeedirectory.adapter.EmployeeAdapter;
import group.whiterabbit.employeedirectory.callback.EmployeeCallBack;
import group.whiterabbit.employeedirectory.db.DBHelper;
import group.whiterabbit.employeedirectory.model.Employee;
import group.whiterabbit.employeedirectory.repo.EmployeeRepo;
import group.whiterabbit.employeedirectory.screen.EmployeeDetailsPage;

public class MainActivity extends AppCompatActivity implements EmployeeCallBack{
    RecyclerView mRecyclerView;
    EmployeeAdapter mAdapter;
    EmployeeRepo employeeRepo;
    SearchView searchView;
    DBHelper searchEmployee;
    List<Employee> employeeList=new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.employeeList);
        searchView=findViewById(R.id.searchView);
        init();
        setSearch();
    }

    private void setSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>0){
                    List<Employee> newEmployeeList=new ArrayList<>();
                    Employee employee= searchEmployee.getEmployee(query);
                     if(employee!=null){
                         newEmployeeList.clear();
                         newEmployeeList.add(employee);
                         mAdapter.upDateData(newEmployeeList);


                     }else{
                         Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                     }

                } else{
                    mAdapter.upDateData(employeeList);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                if(query.length()>0){
                    List<Employee> newEmployeeList=new ArrayList<>();
                    Employee employee= searchEmployee.getEmployee(query);
                    if(employee!=null){
                        newEmployeeList.add(employee);
                        mAdapter.upDateData(newEmployeeList);
                    }else{
                        Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                    }

                } else{
                    mAdapter.upDateData(employeeList);
                }

                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        searchEmployee=new DBHelper(getApplicationContext());
        mAdapter=new EmployeeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        employeeRepo=new EmployeeRepo(this,getApplicationContext());
        employeeRepo.getData();
    }

    @Override
    public void onSuccess(List<Employee> employeeList) {
        this.employeeList=employeeList;
        mAdapter.upDateData(employeeList);
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onClick(int employeeId) {
        Intent myIntent = new Intent(MainActivity.this, EmployeeDetailsPage.class);
        myIntent.putExtra("employeeId",employeeId); //Optional parameters
        this.startActivity(myIntent);
    }

    
}