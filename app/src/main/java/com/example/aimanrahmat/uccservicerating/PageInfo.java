package com.example.aimanrahmat.uccservicerating;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageInfo extends AppCompatActivity {

    private Toolbar mToolbar;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String>listDataHeader;
    private HashMap<String,List<String>>listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_info);

        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        listView = (ExpandableListView)findViewById(R.id.expCounter);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("1. Program Information & Inquiries");
        listDataHeader.add("2. Sponsorship, Career & Alumni");
        listDataHeader.add("3. Student Affairs");
        listDataHeader.add("4. Academic & Enrollment");
        listDataHeader.add("5. International Office");
        listDataHeader.add("6. Finance");
        listDataHeader.add("7. Payment");

        List<String> info = new ArrayList<>();
        info.add("- New Applicant");
        info.add("- Certify Documents");
        info.add("- Inquiries about admission");

        List<String> sponsor = new ArrayList<>();
        sponsor.add("- Inquiries about PTPTN, YTN & Sponsorship");
        sponsor.add("- Submit PTPTN Form");
        sponsor.add("- Witness Signature");

        List<String> welfare = new ArrayList<>();
        welfare.add("- Electricity Top Up");
        welfare.add("- Enquiries for Accommodation");
        welfare.add("- Scorun");
        welfare.add("- Student Activity");
        welfare.add("- Club Activity");
        welfare.add("- Insurance");
        welfare.add("- Siswa Card");

        List<String> enroll = new ArrayList<>();
        enroll.add("- Offer Letter/Confirmation Letter");
        enroll.add("- Updating Student Record/Portal");
        enroll.add("- KWSP/EPF");
        enroll.add("- Progress Report");
        enroll.add("- Result Exam/Certificate/Transcript");
        enroll.add("- Withdrawal from University");
        enroll.add("- Defer Semester");
        enroll.add("- Add & Drop Subject/Timetable");

        List<String> international = new ArrayList<>();
        international.add("- For International Student");
        international.add("- Immigration");
        international.add("- Submit Document");
        international.add("- VISA/Passport");

        List<String> finance = new ArrayList<>();
        finance.add("- Check/Claim Legal Balance");
        finance.add("- Print Result/Invoice");
        finance.add("- Appeal/By pass");
        finance.add("- Unblock Financial Status");

        List<String> pay = new ArrayList<>();
        pay.add("- Pay for Accommodation/Fee");
        pay.add("- Return Advance Money/Petty Cash");
        pay.add("- Pay for Sticker Parking/ID Card");
        pay.add("- Pay for Summons Clamp");
        pay.add("- Library Fine & etc");

        listHash.put(listDataHeader.get(0),info);
        listHash.put(listDataHeader.get(1),sponsor);
        listHash.put(listDataHeader.get(2),welfare);
        listHash.put(listDataHeader.get(3),enroll);
        listHash.put(listDataHeader.get(4),international);
        listHash.put(listDataHeader.get(5),finance);
        listHash.put(listDataHeader.get(6),pay);
    }

}
