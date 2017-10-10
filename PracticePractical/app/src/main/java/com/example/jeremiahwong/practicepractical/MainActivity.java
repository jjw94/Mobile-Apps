package com.example.jeremiahwong.practicepractical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitText(View view) {
        CheckBox chkMember = (CheckBox) findViewById(R.id.checkbox_member);
        boolean isMember = chkMember.isChecked();

        CheckBox chkCart = (CheckBox) findViewById(R.id.checkbox_cart);
        boolean hasCart = chkCart.isChecked();

        CheckBox chkAfternoon = (CheckBox) findViewById(R.id.checkbox_afternoon);
        boolean isAfternoon = chkAfternoon.isChecked();

        RadioButton nineHole = (RadioButton) findViewById(R.id.radio_9holes);
        boolean isNineHole = nineHole.isChecked();

        RadioButton eighteenHole = (RadioButton) findViewById(R.id.radio_18holes);
        boolean isEighteenHole = eighteenHole.isChecked();

        int price = calculatePrice(isMember, hasCart, isAfternoon, isNineHole, isEighteenHole);

        String message = createSummary(price, isMember, hasCart, isAfternoon, isNineHole, isEighteenHole);
        //Create TextView object
        TextView textinfo = (TextView) findViewById(R.id.textSum);

        textinfo.setText(message);
    }

    private int calculatePrice(boolean addMember, boolean addCart, boolean addAfternoon, boolean addNine, boolean addEighteen) {
        int basePrice = 50;

        if (addMember) {
            basePrice = basePrice - 10;
        }

        if (addCart) {
            basePrice = basePrice + 5;
        }

        if (addAfternoon) {
            basePrice = basePrice - 7;
        }

        if (addNine) {
            basePrice = basePrice + 5;
        }

        if (addEighteen) {
            basePrice = basePrice + 10;
        }

        return basePrice;
    }

    private String createSummary(int price, boolean addMember, boolean addCart, boolean addAfternoon, boolean addNine, boolean addEighteen){
        String member = "Member: ";
        String cart = "Cart: ";
        String afternoon = "Afternoon: ";
        String hole = "Holes: ";
        CheckBox memberChecked = (CheckBox)findViewById(R.id.checkbox_member);
        CheckBox cartChecked = (CheckBox)findViewById(R.id.checkbox_cart);
        CheckBox afterChecked = (CheckBox)findViewById(R.id.checkbox_afternoon);
        RadioButton nineChecked = (RadioButton)findViewById(R.id.radio_9holes);
        RadioButton eighteenChecked = (RadioButton)findViewById(R.id.radio_18holes);

        if (memberChecked.isChecked())
            member += "  You are a club member!";
        else
            member += "  You are not a club member.";

        if (cartChecked.isChecked())
            cart += "Cart Needed";
        else
            cart += "No Cart Needed";

        if (afterChecked.isChecked())
            afternoon += "Afternoons are OK";
        else
            afternoon += "Afternoons are not OK";

        if (nineChecked.isChecked())
            hole += "9 Holes of Play";


        if (eighteenChecked.isChecked())
            hole += "18 Holes of Play";

        String sumMessage = "";
        if(!(nineChecked.isChecked() || eighteenChecked.isChecked())) {
            sumMessage += "Please select how many holes to play!";
        }
        else {
            sumMessage = getString(R.string.summary_header);
            sumMessage += "\n" + hole;
            sumMessage += "\n" + member;
            sumMessage += "\n" + cart;
            sumMessage += "\n" + afternoon;
            sumMessage += "\n" + getString(R.string.summary_price, NumberFormat.getCurrencyInstance().format(price));
        }
        return sumMessage;
    }
}
