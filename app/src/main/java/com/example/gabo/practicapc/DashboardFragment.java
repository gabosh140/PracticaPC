package com.example.gabo.practicapc;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabo.practicapc.Entities.Item;
import com.example.gabo.practicapc.Entities.ItemCategory;
import com.example.gabo.practicapc.Parsers.Parsers.Item.IItemsAPI;
import com.example.gabo.practicapc.Parsers.Parsers.Item.ItemsAPI;
import com.example.gabo.practicapc.Spinners.CategoriesSpinnerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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

    //Agregar Aqui las funcionalidades
    @Override
    public void onStart() {
        super.onStart();
            GetTask mytask = new GetTask();
            mytask.execute();

        Button btnSave = getView().findViewById(R.id.dashboard_button_pressme);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User
                int userId = 1;

                //Category
                Spinner spnCategories = getView().
                        findViewById(R.id.dashboard_spinner);
                int categoryId =
                        ((ItemCategory)spnCategories.getSelectedItem()).getId();

                //Name
                TextView txtName = getView().findViewById(R.id.dasboard_editText_name);
                String name = txtName.getText().toString();

                //Description
                String description = "TEST";

                //Referencial Value
                Double referencialValue = 12.5;

                //Tradable
                Boolean tradable = true;

                //Create and fill object
                Item objItem = new Item();
                objItem.setUserId(userId);
                objItem.setCategoryId(categoryId);
                objItem.setName(name);
                objItem.setDescription(description);
                objItem.setReferencialValue(referencialValue);
                objItem.setTradable(tradable);

                PostTask postTask = new PostTask();
                postTask.execute(objItem);
            }
        });


    }
    public void fillSpinner(ArrayList<ItemCategory> categories){

        Spinner spinner = getView().findViewById(R.id.dashboard_spinner);
        CategoriesSpinnerAdapter adapter = new CategoriesSpinnerAdapter(getContext(),android.R.layout.simple_spinner_item,categories);
        spinner.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_dashboard,container,false);
        final Spinner categorySpiner = myView.findViewById(R.id.dashboard_spinner);
        return myView;
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

    class GetTask extends AsyncTask<String,ArrayList<ItemCategory>,ArrayList<ItemCategory>> {

        @Override
        protected ArrayList<ItemCategory> doInBackground(String... strings) {

            IItemsAPI itemsAPI = new ItemsAPI();
            ArrayList<ItemCategory> categories = itemsAPI.GetItems();

            return categories;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemCategory> categories) {
            super.onPostExecute(categories);
            fillSpinner(categories);
        }
    }
    class PostTask extends AsyncTask<Item,Void,Void> {



        @Override
        protected Void doInBackground(Item... items) {
            IItemsAPI itemsAPI = new ItemsAPI();
            itemsAPI.PostItems(items[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getContext(),"EXITO",Toast.LENGTH_LONG);
        }
    }
}


