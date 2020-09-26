package group.whiterabbit.employeedirectory.db;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import group.whiterabbit.employeedirectory.model.Employee;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EmployeeDB1.db";
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String EMPLOYEE_COLUMN_ID = "id";
    public static final String EMPLOYEE_COLUMN_NAME = "name";
    public static final String EMPLOYEE_COLUMN_EMAIL = "email";
    public static final String EMPLOYEE_COLUMN_USER_NAME = "user_name";
    public static final String EMPLOYEE_COLUMN_PHONE = "phone";
    public static final String EMPLOYEE_COLUMN_MORE = "more";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+EMPLOYEE_TABLE_NAME+" " +
                        "(id integer primary key, name text,phone text,email text, user_name text,more text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ EMPLOYEE_TABLE_NAME +"");
        onCreate(db);
    }

    public boolean insertEmployee(int id, String name, String phone, String email, String more, String user_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_COLUMN_NAME, name);
        contentValues.put(EMPLOYEE_COLUMN_ID, id);
        contentValues.put(EMPLOYEE_COLUMN_PHONE, phone);
        contentValues.put(EMPLOYEE_COLUMN_EMAIL, email);
        contentValues.put(EMPLOYEE_COLUMN_MORE, more);
        contentValues.put(EMPLOYEE_COLUMN_USER_NAME, user_name);


        String nameString=null;
         try {
             Cursor rs =getData(id);

             rs.moveToFirst();
             nameString = rs.getString(rs.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_NAME));
             if(nameString==null){
                 if (!rs.isClosed())  {
                     rs.close();
                 }
                 db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
             }
         }catch (Exception e){
             db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
         }



        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ EMPLOYEE_TABLE_NAME +" where id="+id+"", null );
        return res;
    }

    public Cursor searchEmployee(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ EMPLOYEE_TABLE_NAME +" where "+EMPLOYEE_COLUMN_NAME+" = '"
                +query+"' OR "+EMPLOYEE_COLUMN_EMAIL+" = '"+query+"'", null );
        return res;
    }
    public Employee getEmployee(String query){
        Employee employee=new Employee();
        Cursor rs = searchEmployee(query);

        rs.moveToFirst();

        try {
            employee.setData(new JSONObject(rs.getString(rs.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_MORE))));
            return  employee;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  employee;

    }
    public Employee getEmployeeById(int employeeId){
        Employee employee=new Employee();
        Cursor rs = getData(employeeId);

        rs.moveToFirst();

        try {
            employee.setData(new JSONObject(rs.getString(rs.getColumnIndex(DBHelper.EMPLOYEE_COLUMN_MORE))));
            return  employee;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  employee;

    }





    public ArrayList<String> getAllEmployee() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ EMPLOYEE_TABLE_NAME +"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(EMPLOYEE_COLUMN_MORE)));
            res.moveToNext();
        }
        return array_list;
    }
}