package com.example.hammad.examquestion1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class contentday extends Fragment {

    private ImageView iconWheather;

    private TextView temp,desp,windFlow,preception,humidity,Location;

    private TextView hourl_text,hour2_text,hour3_text,hour4_text,hour5_text;
    private TextView hourltime_text,hour2time_text,hour3time_text,hour4time_text,hour5time_text;
    private ImageView hourl_img,hour2_img,hour3_img,hour4_img,hour5_img;

    private String time12hr[] = new String[]{"12","13","14","15","16","17","18","19","20","21","22","23","24"};
    private String time12hrreplace[] = new String[]{"12 PM","1 PM","2 PM","3 PM","4 PM","5 PM","6 PM","7 PM","8 PM","9 PM","10 PM","11 PM","12 PM"};
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_contentday, container, false);

        String uri = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=d5ed1f2030294937baa94416191901&q=karachi&num_of_days=15&tp=3&format=json";

        iconWheather = view.findViewById(R.id.wheather_icon_Day);
        temp = view.findViewById(R.id.Temperature_Day);
        Location = view.findViewById(R.id.Location_Day);
        desp = view.findViewById(R.id.wheather_Day);
        windFlow = view.findViewById(R.id.windflow_Day);
        preception = view.findViewById(R.id.Preception_Day);
        humidity = view.findViewById(R.id.Humdity_Day);

        hourl_text = view.findViewById(R.id.hourly1_Day);
        hour2_text = view.findViewById(R.id.hourly2_Day);
        hour3_text = view.findViewById(R.id.hourly3_Day);
        hour4_text = view.findViewById(R.id.hourly4_Day);
        hour5_text = view.findViewById(R.id.hourly5_Day);

        hourltime_text = view.findViewById(R.id.hourly1time_Day);
        hour2time_text = view.findViewById(R.id.hourly2time_Day);
        hour3time_text = view.findViewById(R.id.hourly3time_Day);
        hour4time_text = view.findViewById(R.id.hourly4time_Day);
        hour5time_text = view.findViewById(R.id.hourly5time_Day);

        hourl_img = view.findViewById(R.id.hourly1img_Day);
        hour2_img = view.findViewById(R.id.hourly2img_Day);
        hour3_img = view.findViewById(R.id.hourly3img_Day);
        hour4_img = view.findViewById(R.id.hourly4img_Day);
        hour5_img = view.findViewById(R.id.hourly5img_Day);

        Ion.with(this).load(uri).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                if(e== null) {
                    JsonObject data = (JsonObject) result.get("data");
                    //Get City Name
                    JsonArray request_Array = (JsonArray) data.get("request");
                    JsonObject request_obj = (JsonObject) request_Array.get(0);
                    Location.setText(request_obj.get("query").getAsString());
                    //Get Current Condtion
                    JsonArray current_conditon_Array = (JsonArray) data.get("current_condition");
                    JsonObject Mainobj = (JsonObject) current_conditon_Array.get(0);

                    //Get Current Wheather ICON
                    JsonArray icon_Array = (JsonArray) Mainobj.get("weatherIconUrl");
                    JsonObject icon_Obj = (JsonObject) icon_Array.get(0);
                    Picasso.get().load(icon_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(iconWheather);
                    ////Get Current Tempearture
                    temp.setText(Mainobj.get("temp_C").getAsString() + "°C");
                    ////Get Current Wheater Descrirptuon
                    JsonArray description_Array = (JsonArray) Mainobj.get("weatherDesc");
                    JsonObject description_Obj = (JsonObject) description_Array.get(0);
                    desp.setText(description_Obj.get("value").getAsString());
                    //Get Current WInd Speed
                    windFlow.setText(Mainobj.get("windspeedKmph").getAsString());
                    preception.setText(Mainobj.get("precipMM").getAsString());
                    humidity.setText(Mainobj.get("humidity").getAsString());


                    //Set Hour Wheather
                    JsonArray weather_Array = (JsonArray) data.get("weather");
                    JsonObject wheather_Obj = (JsonObject) weather_Array.get(0);
                    JsonArray hourly_Array = (JsonArray) wheather_Obj.get("hourly");

                    //SET HOUR 1th Wheather
                    JsonObject hourly_Obj1 = (JsonObject) hourly_Array.get(3);
                    //Wheater ICON
                    JsonArray hourly_Obj_Array = (JsonArray) hourly_Obj1.get("weatherIconUrl");
                    JsonObject houtly_ICON_Obj = (JsonObject) hourly_Obj_Array.get(0);
                    Picasso.get().load(houtly_ICON_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(hourl_img);
                    //Wheater Description
                    hourly_Obj_Array = (JsonArray) hourly_Obj1.get("weatherDesc");
                    JsonObject hourly_Obj1_description_Obj = (JsonObject) hourly_Obj_Array.get(0);
                    hourl_text.setText(hourly_Obj1_description_Obj.get("value").getAsString());
                    //Time
                    String time,newTIME;
                    int j=0;
                    time = hourly_Obj1.get("time").getAsString();
                    for (j = 0; j < time.length(); j++){
                        if ((time.charAt(j) == '0'))
                            break;
                    }
                    newTIME = time.substring(0,j);
                    if(Integer.parseInt(newTIME) >= 12) {
                        for (int i = 0; i < time12hr.length; i++) {
                            if (newTIME.equals(time12hr[i])) {
                                hourltime_text.setText(time12hrreplace[i]);
                                break;
                            }
                        }
                    }
                    else
                        hourltime_text.setText(newTIME + " AM");


                    //SET HOUR 2nd Wheather
                    JsonObject hourly_Obj2 = (JsonObject) hourly_Array.get(4);
                        //Wheather ICON
                    hourly_Obj_Array =(JsonArray) hourly_Obj2.get("weatherIconUrl");
                    houtly_ICON_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    Picasso.get().load(houtly_ICON_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(hour2_img);
                        //Wheather Deskriptiuon
                    hourly_Obj_Array =(JsonArray) hourly_Obj2.get("weatherDesc");
                    hourly_Obj1_description_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    hour2_text.setText(hourly_Obj1_description_Obj.get("value").getAsString());

                        //Time
                    time =hourly_Obj2.get("time").getAsString();
                    for( j=0; j<time.length(); j++)
                        if ((time.charAt(j) == '0'))
                            break;
                    newTIME = time.substring(0,j);
                    if(Integer.parseInt(newTIME) >= 12) {
                        for(int i=0; i<time12hr.length; i++) {
                            if(newTIME.equals(time12hr[i])){
                                newTIME = time12hrreplace[i];
                                hour2time_text.setText(newTIME);
                                break;
                            }
                        }
                    }
                    else
                        hour2time_text.setText(newTIME + " AM");


                    //Set Hour 3rd Wheather
                    JsonObject hourly_Obj3 = (JsonObject) hourly_Array.get(5);
                         //Wheater ICON
                    hourly_Obj_Array =(JsonArray) hourly_Obj3.get("weatherIconUrl");
                    houtly_ICON_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    Picasso.get().load(houtly_ICON_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(hour3_img);
                        //Wheater Description
                    hourly_Obj_Array =(JsonArray) hourly_Obj3.get("weatherDesc");
                    hourly_Obj1_description_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    hour3_text.setText(hourly_Obj1_description_Obj.get("value").getAsString());

                        //Time
                    time =hourly_Obj3.get("time").getAsString();
                    for(j=0; j<time.length(); j++)
                        if ((time.charAt(j) == '0'))
                            break;
                    newTIME = time.substring(0,j);
                    if(Integer.parseInt(newTIME) >= 12) {
                        for(int i=0; i<time12hr.length; i++) {
                            if(newTIME.equals(time12hr[i])){
                                newTIME = time12hrreplace[i];
                                hour3time_text.setText(newTIME);
                                break;
                            }
                        }
                    }
                    else
                        hour3time_text.setText(newTIME + " AM");


                        //SET HOUR 4th Wheather
                    JsonObject hourly_Obj4 = (JsonObject) hourly_Array.get(6);
                        //Wheater ICON
                    hourly_Obj_Array =(JsonArray) hourly_Obj4.get("weatherIconUrl");
                    houtly_ICON_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    Picasso.get().load(houtly_ICON_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(hour4_img);
                        //Wheater Description
                    hourly_Obj_Array =(JsonArray) hourly_Obj4.get("weatherDesc");
                    hourly_Obj1_description_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    hour4_text.setText(hourly_Obj1_description_Obj.get("value").getAsString());
                        //Time
                    time =hourly_Obj4.get("time").getAsString();
                    for( j=0; j<time.length(); j++)
                        if ((time.charAt(j) == '0'))
                            break;
                    newTIME = time.substring(0,j);
                    if(Integer.parseInt(newTIME) >= 12) {
                        for(int i=0; i<time12hr.length; i++) {
                            if(newTIME.equals(time12hr[i])){
                                newTIME = time12hrreplace[i];
                                hour4time_text.setText(newTIME);
                                break;
                            }
                        }
                    }
                    else
                        hour4time_text.setText(newTIME + " AM");


                        //Set HOUR 5th Wheather
                    JsonObject hourly_Obj5 = (JsonObject) hourly_Array.get(7);
                        //ICON
                    hourly_Obj_Array =(JsonArray) hourly_Obj5.get("weatherIconUrl");
                    houtly_ICON_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    Picasso.get().load(houtly_ICON_Obj.get("value").getAsString()).placeholder(R.drawable.rain).into(hour5_img);
                        //Wheater Description
                    hourly_Obj_Array =(JsonArray) hourly_Obj5.get("weatherDesc");
                    hourly_Obj1_description_Obj =(JsonObject) hourly_Obj_Array.get(0);
                    hour5_text.setText(hourly_Obj1_description_Obj.get("value").getAsString());
                        //TIME
                    time =hourly_Obj5.get("time").getAsString();
                    for( j=0; j<time.length(); j++)
                        if ((time.charAt(j) == '0'))
                            break;
                    newTIME = time.substring(0,j);
                    if(Integer.parseInt(newTIME) >= 12) {
                        for(int i=0; i<time12hr.length; i++) {
                            if(newTIME.equals(time12hr[i])){
                                newTIME = time12hrreplace[i];
                                hour5time_text.setText(newTIME);
                                break;
                            }
                        }
                    }
                    else
                        hour5time_text.setText(newTIME + " AM");
                }
                else{
                }
            }
        });
        return view;
    }
}
