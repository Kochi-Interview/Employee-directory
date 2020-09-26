package group.whiterabbit.employeedirectory.repo;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.StrictMode;
import android.util.Log;

import group.whiterabbit.employeedirectory.callback.EmployeeCallBack;
import group.whiterabbit.employeedirectory.db.DBHelper;
import group.whiterabbit.employeedirectory.model.Employee;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EmployeeRepo {
    DBHelper employeeDb;
    String EMPLOYEE_URL="http://www.mocky.io/v2/5d565297300000680030a986";
    List<Employee> employeeList=new ArrayList<>();
    EmployeeCallBack employeeCallBack;
    OkHttpClient client;
    public EmployeeRepo(EmployeeCallBack employeeCallBack, Context context){
         employeeDb=new DBHelper(context);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.employeeCallBack=employeeCallBack;
        client = new OkHttpClient();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public EmployeeRepo getData() {



        if (!checkFromLocalDb()) {


        Request request = new Request.Builder()
                .url(EMPLOYEE_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee().setData(jsonArray.getJSONObject(i));
                employeeList.add(employee);

                employeeDb.insertEmployee(employee.getID(), employee.getName(), employee.getPhone(), employee.getEmail(), jsonArray.getJSONObject(i).toString(), employee.getUsername());
            }
            employeeCallBack.onSuccess(employeeList);


        } catch (IOException e) {
            e.printStackTrace();
            employeeCallBack.onFailed();
        } catch (JSONException e) {
            e.printStackTrace();
            employeeCallBack.onFailed();
        }
    }
        return this;
    }

    private boolean checkFromLocalDb() {
       List<String> list=  employeeDb.getAllEmployee();
       if(list!=null?list.size()>0:false){


           for(int i=0;i<list.size();i++){
               Employee employee= null;
               try {
                   employee = new Employee().setData(new JSONObject(list.get(i)));
                   employeeList.add(employee);

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
           employeeCallBack.onSuccess(employeeList);
           return  true;
       }else{
           //not found in local db
           return  false;

       }

    }
}
