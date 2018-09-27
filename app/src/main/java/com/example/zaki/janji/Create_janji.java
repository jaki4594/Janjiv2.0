package com.example.zaki.janji;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;


public class Create_janji extends android.app.Fragment {

    public EditText pilihMasa;
    public EditText penjanjiKedua;
    public EditText tajukJanji;
    public CalendarView tarikh;
    //public EditText masa;
    public Button ciptaJanji;
    HashMap<String, String> params = new HashMap<String, String>();
    HashMap<String, String> params2 = new HashMap<String, String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_create_janji, container, false);
        pilihMasa = (EditText) view.findViewById(R.id.masa);
        penjanjiKedua = (EditText) view.findViewById(R.id.penjanji_kedua);
        tajukJanji = (EditText) view.findViewById(R.id.tajuk);
        tarikh = (CalendarView) view.findViewById(R.id.calendarView);
        ciptaJanji = (Button) view.findViewById(R.id.but_cipta);

        pilihMasa.requestFocus();
        pilihMasa.setShowSoftInputOnFocus(false);

        final Home_page user = (Home_page)getActivity();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String tarikhStr = sdf.format(new Date(tarikh.getDate()));

        pilihMasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        pilihMasa.setText(String.format("%02d:%02d ", hourOfDay, minutes));
                        //pilihMasa.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        ciptaJanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params.put("Penjanji_pertama",user.email);
                params2.put("email", user.email);
                params.put("Validasi_p1","1");
                params.put("Penjanji_kedua",penjanjiKedua.getText().toString());
                params.put("Validasi_p2", "0");
                params.put("Tajuk",tajukJanji.getText().toString());
                params.put("Tarikh",tarikhStr);
                params.put("Masa",pilihMasa.getText().toString());
                params.put("Tempat","Johor");

                Singleton.getInstance().mailtosupport(params, getActivity(),"CiptaJanji.php");

                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, new List_janji() ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }
}
