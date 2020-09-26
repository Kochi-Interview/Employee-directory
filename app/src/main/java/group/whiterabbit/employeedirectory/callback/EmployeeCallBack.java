package group.whiterabbit.employeedirectory.callback;

import java.util.List;

import group.whiterabbit.employeedirectory.model.Employee;

public interface EmployeeCallBack {
      void onSuccess(List<Employee> employeeList);
      void onFailed();
      void onClick(int employeeId);
}
