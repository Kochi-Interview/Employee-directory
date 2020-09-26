package group.whiterabbit.employeedirectory.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import group.whiterabbit.employeedirectory.R;
import group.whiterabbit.employeedirectory.callback.EmployeeCallBack;
import group.whiterabbit.employeedirectory.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    List<Employee> employeeList= new ArrayList<>();
    Context mContext;
    View view;
    EmployeeCallBack employeeCallBack;
    public  EmployeeAdapter(EmployeeCallBack employeeCallBack){
        this.employeeCallBack=employeeCallBack;
    }
    public EmployeeAdapter upDateData( List<Employee> employeeList){
        this.employeeList=employeeList;
        notifyDataSetChanged();
        return this;
    }
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext().getApplicationContext();
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        view=layoutInflater.inflate(R.layout.employee,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {
        Glide.with(mContext).load(employeeList.get(position).getProfileImage()).into((ImageView) view.findViewById(R.id.employeeImage));
        TextView nameLabel=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed1).findViewById(R.id.label);
        TextView emailLabel=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed2).findViewById(R.id.label);
        TextView phoneLabel=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed3).findViewById(R.id.label);
        TextView websiteLabel=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed4).findViewById(R.id.label);
        TextView companyLabel=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed5).findViewById(R.id.label);
        TextView nameValue=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed1).findViewById(R.id.value);
        TextView emailValue=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed2).findViewById(R.id.value);
        TextView phoneValue=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed3).findViewById(R.id.value);
        TextView websiteValue=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed4).findViewById(R.id.value);
        TextView companyValue=view.findViewById(R.id.employ_basic_details_layout).findViewById(R.id.filed5).findViewById(R.id.value);

        nameLabel.setText("Name");
        emailLabel.setText("Email");
        phoneLabel.setText("Phone");
        websiteLabel.setText("Website");
        companyLabel.setText("Company");

        nameValue.setText(employeeList.get(position).getName());
        emailValue.setText(employeeList.get(position).getEmail());
        phoneValue.setText(employeeList.get(position).getPhone());
        websiteValue.setText(employeeList.get(position).getWebsite());
        companyValue.setText(employeeList.get(position).getCompany().getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              employeeCallBack.onClick(employeeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class  EmployeeViewHolder extends RecyclerView.ViewHolder{

        public EmployeeViewHolder(@NonNull View itemView) {


            super(itemView);
        }
    }

}
