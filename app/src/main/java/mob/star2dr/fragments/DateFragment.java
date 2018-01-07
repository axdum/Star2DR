package mob.star2dr.fragments;

import android.app.DatePickerDialog;
import android.app.ListFragment;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mob.star2dr.R;
import mob.star2dr.custom.LigneAdaptater;
import mob.star2dr.model.BusRoutes;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView date;
    private TextView hour;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private OnFragmentInteractionListener mListener;

    private ListView lignesLV;

    public DateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = (ViewGroup) inflater.inflate(R.layout.fragment_date, container, false);
        lignesLV = (ListView) mView.findViewById(R.id.LignesLV);
        date = (TextView) mView.findViewById(R.id.dateTxt);
        hour = (TextView) mView.findViewById(R.id.heureTxt);


        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        // Get Current Time
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        date.setText(mDay + "/" + mMonth + "/" + mYear);
        hour.setText(mHour+":"+mMinute);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth +"/" + (monthOfYear+1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });
        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                hour.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        loadLignes();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void loadLignes(){
        // Ici on récupère nos données
        //ContentResolver contentResolver = getActivity().getContentResolver();
        //Cursor c = contentResolver.query(Uri.parse("content://fr.istic.starproviderDR.busroute"), null, null, null, null);

        ArrayList<BusRoutes> listLignes = new ArrayList<BusRoutes>();
        listLignes.add(new BusRoutes("C1","Cesson-Sévigné (Champs Blancs) <> Rennes  <> Chantepie (Rosa Parks)","CHRONOSTAR","3","95C11E","1A171B"));
        listLignes.add(new BusRoutes("C2","Saint-Grégoire (Champ Daguet) <> Rennes (Haut Sancé)","CHRONOSTAR","3","008BD2","FFFFFF"));
        listLignes.add(new BusRoutes("C3","Rennes (Saint-Laurent) <> Rennes (Henri Fréville)","CHRONOSTAR","3","00893E","FFFFFF"));
        listLignes.add(new BusRoutes("C4","Rennes (ZA Saint-Sulpice) <> Saint-Grégoire (Grand Quartier)","CHRONOSTAR","3","6F2282","FFFFFF"));
        listLignes.add(new BusRoutes("C5","Rennes (Patton) <> Rennes (Lycée Bréquigny)","CHRONOSTAR","3","F39200","1A171B"));
        listLignes.add(new BusRoutes("C6","Cesson-Sévigné (Rigourdière) <> Rennes <> Saint-Jacques-de-la-Lande (Morinais)","CHRONOSTAR","3","61C3D9","1A171B"));
        listLignes.add(new BusRoutes("9","Rennes (Saint-Laurent) <> Rennes (Cleunay)","Urbaine","3","004F9E","FFFFFF"));
        listLignes.add(new BusRoutes("11","Rennes (La Poterie) <> Vezin-le-Coquet (ZI Ouest)","Urbaine","3","EF859D","1A171B"));
        listLignes.add(new BusRoutes("12","Saint-Grégoire (Grand Quartier) <> Rennes (La Poterie)","Urbaine","3","BA65A5","FFFFFF"));
        listLignes.add(new BusRoutes("13","Chantepie (Cucé) <> Saint-Jacques-de-la-Lande (Gautrais) / Rennes (Cleunay)","Urbaine","3","6F2282","FFFFFF"));
        listLignes.add(new BusRoutes("14","Rennes (Clos Courtel) <> Rennes (Roazhon Park)","Urbaine","3","A96F23","FFFFFF"));
        listLignes.add(new BusRoutes("31","Cesson-Sévigné (Base de Loisirs) <> Rennes (Villejean-Churchill)","Inter-quartiers","3","FFF164","E2001A"));
        listLignes.add(new BusRoutes( "34","Rennes (ZA Saint-Sulpice) <> Cesson-Sévigné <> Chantepie (Rosa Parks)","Inter-quartiers","3","FFF164","E2001A"));
        listLignes.add(new BusRoutes("35","Cesson-Sévigné (Champs Blancs) <> Cesson-Sévigné (Cesson Gare)","Inter-quartiers","3","FFF164","E2001A"));
        LigneAdaptater ligneAdaptater = new LigneAdaptater(getActivity(),R.layout.ligne_bus_row, listLignes);
        lignesLV.setAdapter(ligneAdaptater);
    }
}


