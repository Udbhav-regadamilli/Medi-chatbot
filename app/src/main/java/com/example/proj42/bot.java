package com.example.proj42;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class bot extends AppCompatActivity {

    Button send, predict_disease, drug_details;

    EditText inputMsg;

    LinearLayout linearLayout;
    InputStream inputStream;

    ArrayList<String> wishes,symptoms,drug,thanku,wishes_response,symptoms_response,drug_response,thanku_response, drug_name, symptomsList, diseases;

    int age = -1, gender = -1;
    boolean flag = false;
    private boolean drug_flag = false, disease_flag = false;

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        try {
            send = (Button) findViewById(R.id.button);
            inputMsg = (EditText) findViewById(R.id.editText);

            linearLayout  = findViewById(R.id.linear_layout);

            wishes = new ArrayList<>(Arrays.asList("hi", "hello","good morning","good evening","good afternoon"));

            wishes_response = new ArrayList<>(Arrays.asList("Hello, How can I help you"));

            symptoms = new ArrayList<>(Arrays.asList("symptom list","provide symptoms","please give symptom list","give me symptom list","how do i get symptom list"));

            symptoms_response = new ArrayList<>(Arrays.asList("You can select symptoms from the below list"));

            drug = new ArrayList<>(Arrays.asList("drug description","how can i get drug description","drug information","provide drug description","can you provide drug details"));

            drug_response = new ArrayList<>(Arrays.asList("Please enter Drug name"));

            thanku = new ArrayList<>(Arrays.asList("thank you","thanks for the information","thanku", "thanks"));

            thanku_response = new ArrayList<>(Arrays.asList("You're welcome,have a nice day"));

            drug_name = new ArrayList<>(Arrays.asList(
                    "acetaminophen", "acetylcysteine", "adderall", "albuterol", "allegra", "alprazolam",
                    "ascorbic acid", "aspirin", "azathioprine", "baclofen", "banzel", "bentyl", "betadine",
                    "biotin", "botox", "brimonidine", "bupropion", "benzonatate", "bacitracin", "calcitriol",
                    "calcium carbonate", "candesartan", "carbamazepine", "cetirizine", "cholecalciferol",
                    "ciprofloxacin", "clarithromycin", "cyanocobalamin", "codeine", "dapagliflozin", "delzicol",
                    "detrol", "dextrose", "diclofenac", "diflucan", "dulcolax", "dopamine", "diphenhydramine",
                    "doxycycline", "ecotrin", "elavil", "enhertu", "epidiolex", "ergocalciferol", "erythromycin",
                    "estradiol", "ethambutol", "etomidate", "exelon", "famotidine", "felodipine", "ferrous gluconate",
                    "firmagon", "flomax", "fluconazole", "fluphenazine", "fluvoxamine", "folic acid", "forteo",
                    "gabapentin", "garamycin", "garlic", "gaviscon", "gemfibrozil", "glucagon", "glycerin",
                    "glucotrol", "gocovri", "guanfacine", "haldol", "heparin", "herceptin", "histrelin", "humulin n",
                    "hydralazine", "hydrochlorothiazide", "hydromorphone", "hydroxyzine", "hyoscyamine", "ibuprofen",
                    "ilumya", "imipramine", "imodium", "inlyta", "ipratropium", "isoniazid", "isotretinoin", "ivabradine",
                    "imbruvica", "jadenu", "jantoven", "juluca", "jencycla", "jardiance", "kadian", "kanamycin", "ketoconazole",
                    "ketoprofen", "ketotifen", "lamictal", "lansoprazole", "leflunomide", "letrozole", "levofloxacin", "liothyronine",
                    "lithium", "lorazepam", "lupron", "lyrica", "magnesium oxide", "medrol", "methadone", "methylprednisolone",
                    "metoprolol", "midazolam", "morphine", "misoprostol", "mupirocin", "mesalamine", "naloxone", "naproxen",
                    "neosporin", "neurontin", "niacin", "nicotine", "norethindrone", "novolog", "nystatin", "neomycin", "ofloxacin",
                    "olmesartan", "omeprazole", "omnicef", "onureg", "orphenadrine", "oxandrolone", "oxycodone", "oxygen", "oxytocin",
                    "paracetamol", "pantoprazole", "pentasa", "pradaxa", "prednisolone", "prednisone", "progesterone", "probuphine",
                    "protonix", "phenytoin", "quinine", "qualaquin", "quinidine", "qvar", "rabeprazole", "ranitidine", "relugolix",
                    "restoril", "rifaximin", "risperidone", "rituximab", "scopolamine", "salbutamol", "sodium bicarbonate", "sotalol",
                    "somavert", "telmisartan", "temazepam", "tetracycline", "timolol", "topiramate", "uceris", "unithroid", "urecholine",
                    "uroxatral", "urofollitropin", "valproic acid", "vancomycin", "vasopressin", "victoza", "vitamin a", "vitamin c",
                    "vibramycin", "warfarin", "wegovy", "wellbutrin", "westcort", "wormwood", "xcopri", "xenical", "xiaflex", "xopenex",
                    "xylocaine", "yasmin", "yescarta", "yohimbine", "yonsa", "yuvafem", "zanaflex", "zantac", "zeposia", "zinc gluconate",
                    "zithromax", "zolpidem", "zyloprim", "zyvox", "zyprexa"
            ));

            symptomsList = new ArrayList<>();

            symptomsList.add("itching");
            symptomsList.add("skin_rash");
            symptomsList.add("nodal_skin_eruptions");
            symptomsList.add("continuous_sneezing");
            symptomsList.add("shivering");
            symptomsList.add("chills");
            symptomsList.add("joint_pain");
            symptomsList.add("stomach_pain");
            symptomsList.add("acidity");
            symptomsList.add("ulcers_on_tongue");
            symptomsList.add("muscle_wasting");
            symptomsList.add("vomiting");
            symptomsList.add("burning_micturition");
            symptomsList.add("spotting_ urination");
            symptomsList.add("fatigue");
            symptomsList.add("weight_gain");
            symptomsList.add("anxiety");
            symptomsList.add("cold_hands_and_feets");
            symptomsList.add("mood_swings");
            symptomsList.add("weight_loss");
            symptomsList.add("restlessness");
            symptomsList.add("lethargy");
            symptomsList.add("patches_in_throat");
            symptomsList.add("irregular_sugar_level");
            symptomsList.add("cough");
            symptomsList.add("high_fever");
            symptomsList.add("sunken_eyes");
            symptomsList.add("breathlessness");
            symptomsList.add("sweating");
            symptomsList.add("dehydration");
            symptomsList.add("indigestion");
            symptomsList.add("headache");
            symptomsList.add("yellowish_skin");
            symptomsList.add("dark_urine");
            symptomsList.add("nausea");
            symptomsList.add("loss_of_appetite");
            symptomsList.add("pain_behind_the_eyes");
            symptomsList.add("back_pain");
            symptomsList.add("constipation");
            symptomsList.add("abdominal_pain");
            symptomsList.add("diarrhoea");
            symptomsList.add("mild_fever");
            symptomsList.add("yellow_urine");
            symptomsList.add("yellowing_of_eyes");
            symptomsList.add("acute_liver_failure");
            symptomsList.add("fluid_overload");
            symptomsList.add("swelling_of_stomach");
            symptomsList.add("swelled_lymph_nodes");
            symptomsList.add("malaise");
            symptomsList.add("blurred_and_distorted_vision");
            symptomsList.add("phlegm");
            symptomsList.add("throat_irritation");
            symptomsList.add("redness_of_eyes");
            symptomsList.add("sinus_pressure");
            symptomsList.add("runny_nose");
            symptomsList.add("congestion");
            symptomsList.add("chest_pain");
            symptomsList.add("weakness_in_limbs");
            symptomsList.add("fast_heart_rate");
            symptomsList.add("pain_during_bowel_movements");
            symptomsList.add("pain_in_anal_region");
            symptomsList.add("bloody_stool");
            symptomsList.add("irritation_in_anus");
            symptomsList.add("neck_pain");
            symptomsList.add("dizziness");
            symptomsList.add("cramps");
            symptomsList.add("bruising");
            symptomsList.add("obesity");
            symptomsList.add("swollen_legs");
            symptomsList.add("swollen_blood_vessels");
            symptomsList.add("puffy_face_and_eyes");
            symptomsList.add("enlarged_thyroid");
            symptomsList.add("brittle_nails");
            symptomsList.add("swollen_extremeties");
            symptomsList.add("excessive_hunger");
            symptomsList.add("extra_marital_contacts");
            symptomsList.add("drying_and_tingling_lips");
            symptomsList.add("slurred_speech");
            symptomsList.add("knee_pain");
            symptomsList.add("hip_joint_pain");
            symptomsList.add("muscle_weakness");
            symptomsList.add("stiff_neck");
            symptomsList.add("swelling_joints");
            symptomsList.add("movement_stiffness");
            symptomsList.add("spinning_movements");
            symptomsList.add("loss_of_balance");
            symptomsList.add("unsteadiness");
            symptomsList.add("weakness_of_one_body_side");
            symptomsList.add("loss_of_smell");
            symptomsList.add("bladder_discomfort");
            symptomsList.add("foul_smell_of urine");
            symptomsList.add("continuous_feel_of_urine");
            symptomsList.add("passage_of_gases");
            symptomsList.add("internal_itching");
            symptomsList.add("toxic_look_(typhos)");
            symptomsList.add("depression");
            symptomsList.add("irritability");
            symptomsList.add("muscle_pain");
            symptomsList.add("altered_sensorium");
            symptomsList.add("red_spots_over_body");
            symptomsList.add("belly_pain");
            symptomsList.add("abnormal_menstruation");
            symptomsList.add("dischromic _patches");
            symptomsList.add("watering_from_eyes");
            symptomsList.add("increased_appetite");
            symptomsList.add("polyuria");
            symptomsList.add("family_history");
            symptomsList.add("mucoid_sputum");
            symptomsList.add("rusty_sputum");
            symptomsList.add("lack_of_concentration");
            symptomsList.add("visual_disturbances");
            symptomsList.add("receiving_blood_transfusion");
            symptomsList.add("receiving_unsterile_injections");
            symptomsList.add("coma");
            symptomsList.add("stomach_bleeding");
            symptomsList.add("distention_of_abdomen");
            symptomsList.add("history_of_alcohol_consumption");
            symptomsList.add("fluid_overload");
            symptomsList.add("blood_in_sputum");
            symptomsList.add("prominent_veins_on_calf");
            symptomsList.add("palpitations");
            symptomsList.add("painful_walking");
            symptomsList.add("pus_filled_pimples");
            symptomsList.add("blackheads");
            symptomsList.add("scurring");
            symptomsList.add("skin_peeling");
            symptomsList.add("silver_like_dusting");
            symptomsList.add("small_dents_in_nails");
            symptomsList.add("inflammatory_nails");
            symptomsList.add("blister");
            symptomsList.add("red_sore_around_nose");
            symptomsList.add("yellow_crust_ooze");

            diseases = new ArrayList<>();

            diseases.add("Fungal infection");
            diseases.add("Allergy");
            diseases.add("GERD");
            diseases.add("Cervical spondylosis");
            diseases.add("Pneumonia");

            scrollView = findViewById(R.id.scrollView);

            botChat("");

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textView = new TextView(bot.this);

                    textView.setTextSize(18); // Set text size
                    textView.setTextColor(getResources().getColor(R.color.black));
                    textView.setBackgroundResource(R.drawable.msgbg);
                    textView.setText(inputMsg.getText().toString().trim());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

                    params.gravity = Gravity.END;
                    params.setMargins(10, 10, 10, 10);

                    textView.setLayoutParams(params);
                    textView.setPadding(20, 20, 20, 20);

                    linearLayout.addView(textView);

                    if(flag && age == -1){
                        try {
                            int temp = Integer.parseInt(inputMsg.getText().toString().trim());
                            age = temp;
                            inputMsg.setText("");
                            flag = true;
                        }catch (Exception e){
                            Toast.makeText(bot.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                        }
                    }else if (flag && gender == -1){
                        if(inputMsg.getText().toString().trim().toLowerCase().equals("male") ||
                                inputMsg.getText().toString().trim().toLowerCase().equals("m")){
                            gender = 0;
                        }else if(inputMsg.getText().toString().trim().toLowerCase().equals("female") ||
                                inputMsg.getText().toString().trim().toLowerCase().equals("f") ||
                                inputMsg.getText().toString().toLowerCase().equals("fe")){
                            gender = 1;
                        }
                    }

                    botChat(inputMsg.getText().toString().trim().toLowerCase());

                    inputMsg.setText("");
                    scrollToBottom();
                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void scrollToBottom() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void botChat(String msg){

        TextView textView = new TextView(bot.this);

        textView.setTextSize(18); // Set text size
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setBackgroundResource(R.drawable.msgbg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(30, 10, 30, 10);

        textView.setLayoutParams(params);
        textView.setPadding(20, 20, 20, 20);

        if (flag) {
            if (age == -1){
                ViewGroup originalParent = (ViewGroup) textView.getParent();
                if (originalParent != null) {
                    originalParent.removeView(textView);
                }
                textView.setText("Please tell me your age");
                linearLayout.addView(textView);
            }else if (gender == -1){
                ViewGroup originalParent = (ViewGroup) textView.getParent();
                if (originalParent != null) {
                    originalParent.removeView(textView);
                }
                textView.setText("Please tell me your gender");
                linearLayout.addView(textView);
            } else if (age > 0 && gender > -1) {
                if (disease_flag && msg.split(",").length > 0){
                    int rnd = new Random().nextInt(diseases.size());
                    TextView disease_name = new TextView(bot.this);
                    disease_name.setTextSize(18); // Set text size
                    disease_name.setTextColor(getResources().getColor(R.color.white));
                    disease_name.setBackgroundResource(R.drawable.msgbg);
                    disease_name.setLayoutParams(params);
                    disease_name.setPadding(20, 20, 20, 20);
                    disease_name.setText("you may have " + diseases.get(rnd) + "\nplease consult a doctor if symptoms are serious");
                    linearLayout.addView(disease_name);
                    displayWish(textView);
                } else {
                    EditText et = new EditText(bot.this);
                    ListView lv = new ListView(bot.this);

                    et.setBackgroundResource(R.drawable.msgbg);
                    lv.setBackgroundResource(R.drawable.msgbg);

                    ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);

                    lv.setLayoutParams(params1);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, symptomsList);
                    lv.setAdapter(adapter);

                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            adapter.getFilter().filter(charSequence);
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            adapter.getFilter().filter(charSequence);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String temp = inputMsg.getText().toString().trim();
                            inputMsg.setText((temp == "") ? "" : temp  + "," + lv.getItemAtPosition(i).toString());
                            disease_flag = true;
                        }
                    });

                    linearLayout.addView(lv);
                    linearLayout.addView(et);
                }
            }
        } else if (drug_flag) {
            String[] message = displayDrugDetails(msg);
            if(message[0] != "-1"){
                String[] fields = new String[]{"Usage","Side Effects","Dosage","Contraindications"};
                for(int i=1; i<message.length; i++){
                    TextView tv = new TextView(bot.this);
                    tv.setLayoutParams(params);
                    tv.setText(fields[i-1] + ":\n\n" + message[i]);
                    tv.setPadding(20, 20, 20, 20);
                    tv.setBackgroundResource(R.drawable.msgbg);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    linearLayout.addView(tv);
                }
                displayWish(textView);
            }else{
                textView.setText("Sorry to inform I have no valid details about this drug " + msg);
                linearLayout.addView(textView);
            }
        } else if(wishes.contains(msg) || msg.equals("")){
            displayWish(textView);
        } else if (thanku.contains(msg)) {
            ViewGroup originalParent = (ViewGroup) textView.getParent();
            if (originalParent != null) {
                originalParent.removeView(textView);
            }
            textView.setText(thanku_response.get(0));
            linearLayout.addView(textView);
        } else if (msg.equals("drug details")) {
            // Display the first row in the TextView
            textView.setText("Enter the drug name");
            drug_flag = true;
            linearLayout.addView(textView);
        } else if (msg.equals("predict disease")) {
            flag = true;
            ViewGroup originalParent = (ViewGroup) textView.getParent();
            if (originalParent != null) {
                originalParent.removeView(textView);
            }
            if (age == -1){
                textView.setText("Please tell me your age");
                linearLayout.addView(textView);
            }else if (gender == -1){
                textView.setText("Please tell me your gender");
                linearLayout.addView(textView);
            }
        } else {
            ViewGroup originalParent = (ViewGroup) textView.getParent();
            if (originalParent != null) {
                originalParent.removeView(textView);
            }
            textView.setText("Please enter a valid message");
            linearLayout.addView(textView);
        }
        scrollToBottom();
    }

    // Helper method to convert InputStream to String
    private List<String[]> readCsv(InputStream inputStream) {
        List<String[]> data = new ArrayList<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);

            // Read all rows into a List
            data = csvReader.readAll();

            // Close the CSVReader
            csvReader.close();

        } catch (IOException | CsvException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return data;
    }

    private String[] displayDrugDetails(String drugName){
        if(drug_name.contains(drugName)){
            try {
                inputStream = getAssets().open("drug_info.csv");
                List<String[]> csvData = readCsv(inputStream);
                if (!csvData.isEmpty()) {
                    String[] firstRow = csvData.get(drug_name.indexOf(drugName)+1);
                    drug_flag = false;
                    return firstRow;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new String[]{"-1"};
    }

    private void displayWish(TextView textView){
        textView.setText(wishes_response.get(0));

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params1.setMargins(10, 10, 10, 10);

        predict_disease = new Button(bot.this);
        predict_disease.setText("Predict Disease");
        predict_disease.setLayoutParams(params1);

        drug_details = new Button(bot.this);
        drug_details.setText("Drug details");
        drug_details.setLayoutParams(params1);

        predict_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMsg.setText("Predict Disease");
            }
        });

        drug_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMsg.setText("Drug Details");
            }
        });

        ViewGroup originalParent = (ViewGroup) textView.getParent();
        if (originalParent != null) {
            originalParent.removeView(textView);
        }

        linearLayout.addView(textView);
        linearLayout.addView(predict_disease);
        linearLayout.addView(drug_details);
    }
}