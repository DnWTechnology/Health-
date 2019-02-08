package com.example.nikhil.vihaan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class InfoActivity extends AppCompatActivity {

    ListView lv;
    InfoAdapter mAdapter;
    TextView ttl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);


        lv = findViewById(R.id.info);
        ttl = findViewById(R.id.title);
        ttl.setText(getIntent().getStringExtra("title"));

        ArrayList<Info> data = getInfo();
        mAdapter = new InfoAdapter(this,data);

        lv.setAdapter(mAdapter);

    }

    ArrayList<Info> getInfo(){
        ArrayList<Info> data = new ArrayList<>();

        data.add(new Info("","Tachypnea is defined as an elevated respiratory rate, or more simply, breathing that is more rapid than normal. A normal respiratory rate can vary depending on age and activity but is usually between 12 and 20 breaths per minute for a resting adult. In contrast to the term hyperpnea which refers to rapid deep breathing, tachypnea refers to rapid, shallow breathing. Let's look at the potential causes of tachypnea, " +
                "as well as medical conditions in which it may occur."));

        data.add(new Info("Conditions That May Result in Tachypnea:\n",
                "A wide range of medical conditions can result in tachypnea. By categories these may include:\n" +
                        "\n" +
                        "\n1. Lung related:\n\nLung diseases that result in a low level of oxygen or an elevated level of carbon dioxide in the body may include chronic obstructive pulmonary disease (COPD), asthma, pneumonia, pulmonary fibrosis, a pneumothorax (collapsed lung) or a pulmonary embolism, among others. The rapid breathing rate is the body's way of trying to increase oxygen or lower carbon dioxide levels in the blood. In people who are hospitalized, tachypnea can be a sign that pneumonia is developing, and often occurs before other obvious signs of pneumonia are present.\n" +
                        "\n2. Heart-related:\n\nConditions such as heart failure, anemia, or a low thyroid can result in cardiovascular changes which in turn cause tachypnea.\n" +
                        "\n3. Hyperventilation:\n\nThis may occur due to pain, anxiety, or other conditions.\n" +
                        "\n4. Metabolic acidosis:\n\nWhen the acid level is too high in the blood, breathing rate increases to blow off carbon dioxide. Some causes of this include diabetic ketoacidosis, lactic acidosis, and hepatic encephalopathy.\n" +
                        "\n5. Central nervous system-related:\n\nTachypnea may be directly caused by brain abnormalities such as brain tumors.\n"
        ));

        data.add(new Info("Medications:\n"
                ,"Drugs such as aspirin, stimulants, and marijuana can cause a rapid shallow breathing rate."));

        data.add(new Info("Symptoms:\n",
                "Tachypnea may be accompanied by the sensation of shortness of breath and an inability to get enough air (dyspnea), " +
                        "blue-tinged fingers and lips (cyanosis) and sucking in of the chest muscles with breathing (retracting). " +
                        "Tachypnea may also occur without any obvious symptoms, especially when it is related to conditions such as metabolic imbalances or central nervous system conditions."));

        data.add(new Info("Diagnosis:\n",
                "The diagnosis of tachypnea will vary depending on a person's age, other medical problems, current medications, and other symptoms, but may include:\n" +
                        "\n" +
                        "\n1. Oximetry:\n\nA \"clip\" may be placed on your finger to estimate the amount of oxygen in your blood.\n" +
                        "\n2. Arterial blood gases (ABGs):\n\nBlood gases can give a more accurate estimate of your oxygen level as well as the carbon dioxide content of your blood. They will also tell your doctor the pH of your blood, which can be helpful in evaluating metabolic abnormalities. If the pH of the blood is low (acidosis), tests may be done to look for causes such as diabetic ketoacidosis, lactic acidosis, and liver problems.\n" +
                        "\n3. Chest x-ray:\n\nA chest x-ray can quickly determine some causes of tachypnea, such as a collapsed lung.\n" +
                        "\n4. Chest CT:\n\nA chest CT may be done to look for lung diseases or tumors.\n" +
                        "\n5. Pulmonary function tests:\n\nPulmonary function tests are very helpful when looking for conditions such as COPD and asthma.\n" +
                        "\n6. Glucose:\n\nA blood sugar is often done to rule out (or confirm) diabetic ketoacidosis.\n" +
                        "\n7. Electrolytes:\n\nSodium and potassium levels are helpful in evaluating some of the causes of tachypnea.\n" +
                        "\n8. Hemoglobin:\n\nA complete blood count and hemoglobin may be done to look for evidence of anemia as well as infections.\n" +
                        "\n9. EKG:\n\nAn EKG can look for evidence of a heart attack or abnormal heart rhythms.\n" +
                        "\n10. VQ scan:\n\nA VQ scan is often done if there is a possibility of a pulmonary embolus.\n" +
                        "\n11. Brain MRI:\n\nIf no obvious causes of tachypnea are found, a brain MRI may be helpful in ruling out brain abnormalities (such as tumors) as a cause.\n" +
                        "\n12. Toxicology screen:\n\nThere are many drugs, both prescription, over-the-counter, and illegal that can cause tachypnea. A toxicology screen is often done in the emergency settings if the cause of tachypnea is unknown.\n"
        ));

        data.add(new Info("Treatments:\n",
                "The treatment of tachypnea depends primarily on determining and correcting the underlying cause.\n" +
                        "Consult your doctor for a better understanding"));
        return data;


    }

}

