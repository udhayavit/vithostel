package com.example.udhaya.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomActivity extends AppCompatActivity {


    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        listView=(ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData(){


        listDataHeader=new ArrayList<>();
        listHash=new HashMap<>();

        listDataHeader.add("A block");

        listDataHeader.add("B block");

        listDataHeader.add("B Annex block");

        listDataHeader.add("C block");

        listDataHeader.add("D block");

        listDataHeader.add("D Annex block");

        listDataHeader.add("E block");

        listDataHeader.add("F block");

        listDataHeader.add("G block");

        listDataHeader.add("H block");

        listDataHeader.add("I block");

        listDataHeader.add("J block");

        listDataHeader.add("K block");

        listDataHeader.add("L block");

        listDataHeader.add("M block");

        listDataHeader.add("M Annex block");

        listDataHeader.add("N block");

        listDataHeader.add("P block");

        listDataHeader.add("Q block");






        List<String> A_block =new ArrayList<>();
        A_block.add("6 bed - 600 rooms");
        A_block.add("4 bed- 400 rooms");
        A_block.add("2 bed - 200 rooms");

        List<String> B_block =new ArrayList<>();
        B_block.add("6 bed - 600 rooms");
        B_block.add("4 bed- 400 rooms");
        B_block.add("2 bed - 200 rooms");

        List<String> B_Annex_block =new ArrayList<>();
        B_Annex_block.add("6 bed - 600 rooms");
        B_Annex_block.add("4 bed- 400 rooms");
        B_Annex_block.add("2 bed - 200 rooms");

        List<String> C_block =new ArrayList<>();
        C_block.add("6 bed - 600 rooms");
        C_block.add("4 bed- 400 rooms");
        C_block.add("2 bed - 200 rooms");

        List<String> D_block =new ArrayList<>();
        D_block.add("6 bed - 600 rooms");
        D_block.add("4 bed- 400 rooms");
        D_block.add("2 bed - 200 rooms");

        List<String> D_Annex_block =new ArrayList<>();
        D_Annex_block.add("6 bed - 600 rooms");
        D_Annex_block.add("4 bed- 400 rooms");
        D_Annex_block.add("2 bed - 200 rooms");

        List<String> E_block =new ArrayList<>();
        E_block.add("6 bed - 600 rooms");
        E_block.add("4 bed- 400 rooms");
        E_block.add("2 bed - 200 rooms");

        List<String> F_block =new ArrayList<>();
        F_block.add("6 bed - 600 rooms");
        F_block.add("4 bed- 400 rooms");
        F_block.add("2 bed - 200 rooms");

        List<String> G_block =new ArrayList<>();
        G_block.add("6 bed - 600 rooms");
        G_block.add("4 bed- 400 rooms");
        G_block.add("2 bed - 200 rooms");

        List<String> H_block =new ArrayList<>();
        H_block.add("6 bed - 600 rooms");
        H_block.add("4 bed- 400 rooms");
        H_block.add("2 bed - 200 rooms");

        List<String> I_block =new ArrayList<>();
        I_block.add("6 bed - 600 rooms");
        I_block.add("4 bed- 400 rooms");
        I_block.add("2 bed - 200 rooms");

        List<String> J_block =new ArrayList<>();
        J_block.add("6 bed - 600 rooms");
        J_block.add("4 bed- 400 rooms");
        J_block.add("2 bed - 200 rooms");

        List<String> K_block =new ArrayList<>();
        K_block.add("6 bed - 600 rooms");
        K_block.add("4 bed- 400 rooms");
        K_block.add("2 bed - 200 rooms");

        List<String> L_block =new ArrayList<>();
        L_block.add("6 bed - 600 rooms");
        L_block.add("4 bed- 400 rooms");
        L_block.add("2 bed - 200 rooms");

        List<String> M_block =new ArrayList<>();
        M_block.add("6 bed - 600 rooms");
        M_block.add("4 bed- 400 rooms");
        M_block.add("2 bed - 200 rooms");

        List<String> M_Annex_block =new ArrayList<>();
        M_Annex_block.add("6 bed - 600 rooms");
        M_Annex_block.add("4 bed- 400 rooms");
        M_Annex_block.add("2 bed - 200 rooms");

        List<String> N_block =new ArrayList<>();
        N_block.add("6 bed - 600 rooms");
        N_block.add("4 bed- 400 rooms");
        N_block.add("2 bed - 200 rooms");

        List<String> P_block =new ArrayList<>();
        P_block.add("6 bed - 600 rooms");
        P_block.add("4 bed- 400 rooms");
        P_block.add("2 bed - 200 rooms");

        List<String> Q_block =new ArrayList<>();
        Q_block.add("6 bed - 600 rooms");
        Q_block.add("4 bed- 400 rooms");
        Q_block.add("2 bed - 200 rooms");



        listHash.put(listDataHeader.get(0),A_block);
        listHash.put(listDataHeader.get(1),B_block);
        listHash.put(listDataHeader.get(2),B_Annex_block);
        listHash.put(listDataHeader.get(3),C_block);
        listHash.put(listDataHeader.get(4),D_block);
        listHash.put(listDataHeader.get(5),D_Annex_block);
        listHash.put(listDataHeader.get(6),E_block);
        listHash.put(listDataHeader.get(7),F_block);
        listHash.put(listDataHeader.get(8),G_block);
        listHash.put(listDataHeader.get(9),H_block);
        listHash.put(listDataHeader.get(10),I_block);
        listHash.put(listDataHeader.get(11),J_block);
        listHash.put(listDataHeader.get(12),K_block);
        listHash.put(listDataHeader.get(13),L_block);
        listHash.put(listDataHeader.get(14),M_block);
        listHash.put(listDataHeader.get(15),M_Annex_block);
        listHash.put(listDataHeader.get(16),N_block);
        listHash.put(listDataHeader.get(17),P_block);
        listHash.put(listDataHeader.get(18),Q_block);
    }
}
