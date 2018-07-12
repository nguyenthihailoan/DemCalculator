package com.lx.ltuddd.democaculator.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lx.ltuddd.democaculator.R;
import com.lx.ltuddd.democaculator.utils.Contants;
import com.lx.ltuddd.democaculator.utils.InfixPostfix;
import com.lx.ltuddd.democaculator.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextViewOne, mTextViewTwo, mTextViewThree, mTextViewFour, mTextViewFive, mTextViewSix, mTextViewSeven, mTextViewEight, mTextViewNine, mTextViewDot, mTextViewZero, mTextViewType;
    private TextView mTextViewAC, mTextView, mTextViewPerCent, mTextViewEqual, mTextViewDiv, mTextViewMul, mTextViewSub, mTextViewAdd, mTextViewResult;
    private EditText mEditTextInput;
    private String mInputNumber, mResult;
    private ArrayList<String> mArrValue;
    private boolean mIsOpenBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewNumber();
        setUpViewOperand();
        setUpViewOther();
    }

    public void setUpViewNumber() {
        mTextViewZero = (TextView) findViewById(R.id.text_zero);
        mTextViewOne = (TextView) findViewById(R.id.text_one);
        mTextViewTwo = (TextView) findViewById(R.id.text_two);
        mTextViewThree = (TextView) findViewById(R.id.text_three);
        mTextViewFour = (TextView) findViewById(R.id.text_four);
        mTextViewFive = (TextView) findViewById(R.id.text_five);
        mTextViewSix = (TextView) findViewById(R.id.text_six);
        mTextViewSeven = (TextView) findViewById(R.id.text_seven);
        mTextViewEight = (TextView) findViewById(R.id.text_eight);
        mTextViewNine = (TextView) findViewById(R.id.text_nine);
        mTextViewZero.setOnClickListener(this);
        mTextViewOne.setOnClickListener(this);
        mTextViewTwo.setOnClickListener(this);
        mTextViewThree.setOnClickListener(this);
        mTextViewFour.setOnClickListener(this);
        mTextViewFive.setOnClickListener(this);
        mTextViewSix.setOnClickListener(this);
        mTextViewSeven.setOnClickListener(this);
        mTextViewEight.setOnClickListener(this);
        mTextViewNine.setOnClickListener(this);
    }

    public void setUpViewOperand() {
        mTextViewDiv = (TextView) findViewById(R.id.text_div);
        mTextViewMul = (TextView) findViewById(R.id.text_mul);
        mTextViewSub = (TextView) findViewById(R.id.text_sub);
        mTextViewAdd = (TextView) findViewById(R.id.text_add);
        mTextViewDiv.setOnClickListener(this);
        mTextViewMul.setOnClickListener(this);
        mTextViewSub.setOnClickListener(this);
        mTextViewAdd.setOnClickListener(this);
    }

    public void setUpViewOther() {
        mTextViewDot = (TextView) findViewById(R.id.text_dot);
        mTextViewType = (TextView) findViewById(R.id.text_type);
        mTextViewAC = (TextView) findViewById(R.id.text_ac);
        mTextView = (TextView) findViewById(R.id.text_);
        mTextViewPerCent = (TextView) findViewById(R.id.text_per_cent);
        mTextViewEqual = (TextView) findViewById(R.id.text_equal);
        mTextViewDot.setOnClickListener(this);
        mTextViewType.setOnClickListener(this);
        mTextViewAC.setOnClickListener(this);
        mTextView.setOnClickListener(this);
        mTextViewPerCent.setOnClickListener(this);
        mTextViewEqual.setOnClickListener(this);
        mTextViewResult = (TextView) findViewById(R.id.text_result);
        mEditTextInput = (EditText) findViewById(R.id.edit_input);
    }

    @Override
    public void onClick(View view) {
        if (mArrValue == null) {
            mArrValue = new ArrayList<>();
        }
        switch (view.getId()) {
            case R.id.text_div:
                addOperation(Contants.OPERATION_DIV);
                break;
            case R.id.text_mul:
                addOperation(Contants.OPERATION_MUL);
                break;
            case R.id.text_sub:
                addOperation(Contants.OPERATION_SUB);
                break;
            case R.id.text_add:
                addOperation(Contants.OPERATION_ADD);
                break;
            case R.id.text_ac:
                mArrValue.clear();
                mInputNumber = "";
                mResult = "";
                break;
            case R.id.text_:
                addBracket();
                break;
            case R.id.text_per_cent:
                break;
            case R.id.text_equal:
                mArrValue.add(mInputNumber);
                mResult = InfixPostfix.caculatorPostfix(InfixPostfix.infixToPostfix(mArrValue)) + "";
                mInputNumber = mResult;
                mResult = "";
                mArrValue.clear();
                break;
            case R.id.text_type:
                mInputNumber = (Utils.stringToLong(mInputNumber) * -1) + "";
                break;
            case R.id.text_dot:
                mInputNumber += ".";
                break;
            default:
                TextView mTextViewInput_ = (TextView) view;
                addNumber(mTextViewInput_);
                break;
        }
        mEditTextInput.setText(fomatInput());
    }

    public String fomatInput() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.convetArrToString(mArrValue));
        stringBuilder.append(mInputNumber);
        return stringBuilder.toString();
    }

    public void addOperation(String operand) {

        if (!mInputNumber.equals("")) {
            mArrValue.add(mInputNumber);
        }
        mArrValue.add(operand);
        mInputNumber = "";
        mResult = "";
        mTextViewResult.setText(mResult);
    }

    public void addNumber(TextView mTextView) {
        if (mInputNumber == null) {
            mInputNumber = "";
        }
        if (mTextView.getText().toString() != null) {
            mInputNumber += mTextView.getText().toString();
        } else {
            mInputNumber = mTextView.getText().toString();
        }
    }

    public void addBracket() {
        mIsOpenBracket = !mIsOpenBracket;
        if (mIsOpenBracket) {
            mArrValue.add("(");
        } else {
            mArrValue.add(mInputNumber);
            mArrValue.add(")");
            mInputNumber = "";
        }
    }
}
