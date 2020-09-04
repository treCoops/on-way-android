package com.trecoops.onway;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trecoops.onway.Adapters.ServiceCategoryAdapter;
import com.trecoops.onway.Adapters.VehicleCategoryAdapter;
import com.trecoops.onway.Controller.UserController;
import com.trecoops.onway.Model.LiveLocation;
import com.trecoops.onway.Model.Service;
import com.trecoops.onway.Model.User;
import com.trecoops.onway.Model.Vehicle;
import com.trecoops.onway.Model.VehicleDAO;
import com.trecoops.onway.Model.Work;
import com.trecoops.onway.Util.AlertBar;
import com.trecoops.onway.Util.ProgressBar;
import com.trecoops.onway.Validator.Validator;

import java.util.ArrayList;

public class Activity_sign_up extends AppCompatActivity implements VehicleCategoryAdapter.OnItemClickListener {

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtVehicleNo;
    private EditText txtVehicleModel;
    private Spinner spinner_manufacturer;
    private RecyclerView listVehicleCategories;
    private RecyclerView listTypeCategories;

    private ArrayList<Vehicle> vehicleArrayList;
    private VehicleCategoryAdapter vehicleCategoryAdapter;

    private ArrayList<Service> serviceArrayList;
    private ServiceCategoryAdapter serviceCategoryAdapter;

    Vibrator vibrate;
    Animation shake;
    ProgressBar progressBar;

    private int[] vehicleDrawables = {R.drawable.mini, R.drawable.micro, R.drawable.threewheeler, R.drawable.motorcycle};
    private String[] vehicleTypes = {"Mini", "Micro", "Tuk", "Scooter"};

    private int[] serviceDrawables = {R.drawable.taxi, R.drawable.food, R.drawable.pharmacy, R.drawable.courier, R.drawable.grocery};
    private String[] serviceTypes = {"Taxi", "Food", "Pharmacy", "Courier", "Grocery"};

    private ArrayList<Service> selectedTypes;

    private String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        shake = AnimationUtils.loadAnimation(this, R.anim.anim_shake_edit_text);
        progressBar = new ProgressBar();

        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtVehicleNo = findViewById(R.id.txtVehicleNo);
        txtVehicleModel = findViewById(R.id.txtVehicleModel);
        spinner_manufacturer = findViewById(R.id.spinner_manufacturer);

        listVehicleCategories = findViewById(R.id.listVehicleCategories);
        listTypeCategories = findViewById(R.id.listTypeCategories);

        selectedTypes = new ArrayList<>();
        listVehicleCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        vehicleArrayList = new ArrayList<>();
        createVehicleCategoryList();

        listTypeCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        serviceArrayList = new ArrayList<>();
        createServiceCategoryList();

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Validator.checkEmpty(txtFirstName.getText().toString())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "First name required.!");
                    txtFirstName.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(Validator.checkEmpty(txtLastName.getText().toString())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Last name required.!");
                    txtLastName.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(Validator.checkEmpty(txtVehicleNo.getText().toString())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Vehicle number required.!");
                    txtVehicleNo.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(!Validator.validateVehicleNo(txtVehicleNo.getText().toString().trim())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Invalid vehicle number.!");
                    txtVehicleNo.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(Validator.checkTwoSame("Select manufacturer", spinner_manufacturer.getSelectedItem().toString())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Please select your vehicle manufacturer.!");
                    spinner_manufacturer.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(Validator.checkEmpty(txtVehicleModel.getText().toString())){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Vehicle model required.!");
                    txtVehicleModel.startAnimation(shake);
                    vibrate.vibrate(20);
                    return;
                }

                if(serviceCategoryAdapter.getSelected().isEmpty()){
                    AlertBar.notifyDanger(Activity_sign_up.this, "Please select your service categories.!");
                    vibrate.vibrate(20);
                    return;
                }

                progressBar.showProgressBar(Activity_sign_up.this);

                selectedTypes = serviceCategoryAdapter.getSelected();
                Work selectedWork = new Work(false, false, false, false, false);
                for (Service s : selectedTypes) {
                    switch (s.getServiceName()) {
                        case "Taxi":
                            selectedWork.setTaxi(true);
                            break;
                        case "Food":
                            selectedWork.setFood(true);
                            break;
                        case "Pharmacy":
                            selectedWork.setPharmacy(true);
                            break;
                        case "Courier":
                            selectedWork.setCourier(true);
                            break;
                        case "Grocery":
                            selectedWork.setGrocery(true);
                            break;
                    }
                }

                VehicleDAO vehicle = new VehicleDAO();

                switch (vehicleCategoryAdapter.getSelected().getCat_name()) {
                    case "Mini":
                        vehicle.setCat_name("Mini");
                        vehicle.setCategory("cat04");
                        vehicle.setVehicle_man(spinner_manufacturer.getSelectedItem().toString());
                        vehicle.setVehicle_model(txtVehicleModel.getText().toString());
                        vehicle.setVehicle_no(txtVehicleNo.getText().toString().toUpperCase().trim());
                        break;
                    case "Micro":
                        vehicle.setCat_name("Micro");
                        vehicle.setCategory("cat03");
                        vehicle.setVehicle_man(spinner_manufacturer.getSelectedItem().toString());
                        vehicle.setVehicle_model(txtVehicleModel.getText().toString());
                        vehicle.setVehicle_no(txtVehicleNo.getText().toString().toUpperCase().trim());
                        break;
                    case "Tuk":
                        vehicle.setCat_name("Tuk");
                        vehicle.setCategory("cat02");
                        vehicle.setVehicle_man(spinner_manufacturer.getSelectedItem().toString());
                        vehicle.setVehicle_model(txtVehicleModel.getText().toString());
                        vehicle.setVehicle_no(txtVehicleNo.getText().toString().toUpperCase().trim());
                        break;
                    case "Scooter":
                        vehicle.setCat_name("Scooter");
                        vehicle.setCategory("cat01");
                        vehicle.setVehicle_man(spinner_manufacturer.getSelectedItem().toString());
                        vehicle.setVehicle_model(txtVehicleModel.getText().toString());
                        vehicle.setVehicle_no(txtVehicleNo.getText().toString().toUpperCase().trim());
                        break;
                }

                phoneNo = getIntent().getStringExtra("PHONE_NUMBER");

                User user = new User(
                        "DRIVER",
                        0,
                        phoneNo,
                        "",
                        txtFirstName.getText().toString(),
                        txtLastName.getText().toString(),
                        new LiveLocation(10.2014144, 0.2511452),
                        0,
                        "PRO_PIC",
                        "PRO_URL",
                        null,
                        vehicle,
                        selectedWork);

                UserController userController = new UserController();
                userController.registerUser(user, Activity_sign_up.this, new UserController.OnUserRegistrationListener() {
                    @Override
                    public void onUserAddingCompleted() {
                        Toast.makeText(Activity_sign_up.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        AlertBar.notifySuccess(Activity_sign_up.this, "Registration Successful");
                        progressBar.dismissProgressBar();
                    }

                    @Override
                    public void onUserAddingInProgress(int progress) {

                    }

                    @Override
                    public void onUserAddingCancelled() {
                        Toast.makeText(Activity_sign_up.this, "Registration Cancelled", Toast.LENGTH_LONG).show();
                        progressBar.dismissProgressBar();
                        AlertBar.notifyWarning(Activity_sign_up.this, "Registration Cancelled");
                    }

                    @Override
                    public void onUserAddingFailed(String message) {
                        Toast.makeText(Activity_sign_up.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        Log.i("ONWAY_ERROR", message);
                        progressBar.dismissProgressBar();
                        AlertBar.notifyDanger(Activity_sign_up.this, "Registration Failed");
                    }
                });
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void createVehicleCategoryList() {
        vehicleArrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            vehicleArrayList.add(new Vehicle(vehicleTypes[i], vehicleDrawables[i]));
        }
        vehicleCategoryAdapter = new VehicleCategoryAdapter(this, vehicleArrayList, this);
        listVehicleCategories.setAdapter(vehicleCategoryAdapter);
    }

    private void createServiceCategoryList() {
        serviceArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            serviceArrayList.add(new Service(serviceTypes[i], serviceDrawables[i]));
        }
        serviceCategoryAdapter = new ServiceCategoryAdapter(this, serviceArrayList);
        listTypeCategories.setAdapter(serviceCategoryAdapter);
    }

    @Override
    public void onItemClick(int position) {
        switch (vehicleTypes[position]) {
            case "Mini":
                spinner_manufacturer.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner, getResources().getStringArray(R.array.brand_vehicles_micro_mini)));
                break;
            case "Micro":
                spinner_manufacturer.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner, getResources().getStringArray(R.array.brand_vehicles_micro_mini)));
                break;
            case "Tuk":
                spinner_manufacturer.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner, getResources().getStringArray(R.array.vehicle_models_tuk)));
                break;
            case "Scooter":
                spinner_manufacturer.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner, getResources().getStringArray(R.array.vehicle_models_bike)));
                break;
        }
    }
}