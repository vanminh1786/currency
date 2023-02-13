package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DecimalFormat numberFormat = new DecimalFormat("#0.0000");
    DecimalFormat numberFormat2 = new DecimalFormat("#0.00");


    boolean isTextOneActive = true;
    boolean isChangedActive = false;
    boolean isTextOneHasDot = false;
    boolean isTextTwoHasDot = false;
    boolean isTextOneFull = false;
    boolean isTextTwoFull = false;


    TextView signOne, signTwo, textViewOne, textViewTwo, textViewThree;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9,
         button_dot, button_delete_one, button_clear;
    String currentText;
    int indexOne = 7;
    int indexTwo = 9;

    Spinner spinnerLineOne, spinnerLineTwo;
    ArrayList<String> items;
    String [] shortNameItems;
    double [] weightItems;
    String [] signItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.v("TAG", formatNumberCurrency(2000.1204019284));

//        Log.v("TAG", String.valueOf(getNumberFromString("3099.")));
        items = new ArrayList<>();
        items.add(0, "Australia - Dollar");
        items.add(1, "China - Yuan");
        items.add(2, "Europe - Euro");
        items.add(3, "India - Rupee");
        items.add(4, "Japan - Yen");
        items.add(5, "Korea - Won");
        items.add(6, "Russia - Rouble");
        items.add(7, "United States - Dollar");
        items.add(8, "United Kingdom - Pound");
        items.add(9, "Vietnam - Dong");

        shortNameItems = new String[] {"AUD", "CNY", "EUR", "INR", "JPY", "KRW", "RUB", "USD", "GBP", "VND"};
        weightItems = new double [] {16347.1576, 3455.0767, 24378.8787, 296.7013, 172.4223, 18.1167, 415.7309, 23177.00, 28550.1355, 1.0};
        signItems = new String[] {"$", "CNY", "EUR", "INR", "JPY", "KRW", "RUB", "$", "GBP", "đ"};

        spinnerLineOne = findViewById(R.id.spinner_one);
        spinnerLineTwo = findViewById(R.id.spinner_two);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        spinnerLineOne.setAdapter(adapter);
        spinnerLineTwo.setAdapter(adapter);
        spinnerLineOne.setSelection(indexOne);
        spinnerLineTwo.setSelection(indexTwo);

        spinnerLineOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indexOne = i;
                signOne.setText("From " + signItems[i]);
                textViewThree.setText("1 " + shortNameItems[indexOne] + " = " + (numberFormat.format(weightItems[indexOne] / weightItems[indexTwo])) + " " + shortNameItems[indexTwo]);
                if (isTextOneActive)
                    setTextTwo();
                else
                    setTextOne();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerLineTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indexTwo = i;
                signTwo.setText("To " + signItems[i]);
                textViewThree.setText("1 " + shortNameItems[indexOne] + " = " + (numberFormat.format(weightItems[indexOne] / weightItems[indexTwo])) + " " + shortNameItems[indexTwo]);

                if (isTextOneActive)
                    setTextTwo();
                else
                    setTextOne();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signOne = findViewById(R.id.sign_one);
        textViewOne = findViewById(R.id.textview_one);
        signTwo = findViewById(R.id.sign_two);
        textViewTwo = findViewById(R.id.textview_two);
        textViewThree = findViewById(R.id.textview_three);

        signOne.setText(signItems[indexOne]);
        signTwo.setText(signItems[indexTwo]);
        textViewThree.setText("1 " + shortNameItems[indexOne] + " = " + (numberFormat.format(weightItems[indexOne] / weightItems[indexTwo])) + " " + shortNameItems[indexTwo]);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_dot = findViewById(R.id.button_dot);
        button_delete_one = findViewById(R.id.button_x);
        button_clear = findViewById(R.id.button_ce);




        textViewOne.setTextColor(Color.parseColor("#000000"));
        textViewOne.setTypeface(textViewOne.getTypeface(), Typeface.BOLD);
        textViewTwo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        textViewTwo.setTextColor(Color.parseColor("#FF888787"));

        textViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewOne.setTextColor(Color.parseColor("#000000"));
                textViewOne.setTypeface(textViewOne.getTypeface(), Typeface.BOLD);
                textViewTwo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                textViewTwo.setTextColor(Color.parseColor("#FF888787"));

                if (!isTextOneActive)
                    isChangedActive = true;
                isTextOneActive = true;


            }
        });

        textViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTwo.setTextColor(Color.parseColor("#000000"));
                textViewTwo.setTypeface(textViewTwo.getTypeface(), Typeface.BOLD);
                textViewOne.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                textViewOne.setTextColor(Color.parseColor("#FF888787"));

                if (isTextOneActive)
                    isChangedActive = true;
                isTextOneActive = false;


            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTwo.setText("0");
                textViewOne.setText("0");
                isChangedActive = false;
                isTextOneActive = true;
                isTextOneHasDot = false;
                isTextTwoHasDot = false;
                isTextOneFull = false;
                isTextTwoFull = false;

                textViewOne.setTextColor(Color.parseColor("#000000"));
                textViewOne.setTypeface(textViewOne.getTypeface(), Typeface.BOLD);

                textViewTwo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                textViewTwo.setTextColor(Color.parseColor("#FF888787"));
            }
        });

        button_delete_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTextOneActive) {
                    currentText = textViewOne.getText().toString();
                    if (currentText.length() == 1)
                        currentText = "0";
                    else
                        currentText = currentText.substring(0, currentText.length() - 1);
                    textViewOne.setText((currentText));
                    if (currentText.contains("."))
                        isTextOneHasDot = true;
                    else
                        isTextOneHasDot = false;

                    isTextOneFull = false;

                    //setTextTwo
                    setTextTwo();

                } else {
                    currentText = textViewTwo.getText().toString();
                    if (currentText.length() == 1)
                        currentText = "0";
                    else
                        currentText = currentText.substring(0, currentText.length() - 1);
                    textViewTwo.setText((currentText));
                    if (currentText.contains("."))
                        isTextTwoHasDot = true;
                    else
                        isTextTwoHasDot = false;

                    isTextTwoFull = false;

                    //setTextOne
                    setTextOne();
                }

            }
        });

        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTextOneActive) {
                    currentText = textViewOne.getText().toString();
                    if (!isTextOneHasDot) {
                        textViewOne.setText(currentText + ".");
                        isTextOneHasDot = true;
                    }
                } else {
                    currentText = textViewTwo.getText().toString();
                    if (!isTextTwoHasDot) {
                        textViewTwo.setText(currentText + ".");
                        isTextTwoHasDot = true;
                    }
                }
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("0");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "0");
//                        currentText = textViewOne.getText().toString();
//
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
////                            Log.v("TAG", subString);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("0");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "0");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }

                //Dung ham
                clickNumberButtonHandler("0");
            }

        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("1");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "1");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
////                            Log.v("TAG", subString);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("1");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "1");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//
//                    }
//                }
                clickNumberButtonHandler("1");
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("2");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "2");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("2");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "2");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("2");
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("3");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "3");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("3");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "3");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("3");
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("4");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "4");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("4");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "4");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("4");
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("5");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "5");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("5");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "5");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("5");
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("6");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "6");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("6");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "6");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("6");
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("7");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "7");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("7");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "7");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("7");
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("8");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "8");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("8");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "8");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("8");
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isTextOneActive) {
//                    currentText = textViewOne.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewOne.setText("9");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextOneFull) {
//                        textViewOne.setText(currentText + "9");
//                        currentText = textViewOne.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextOneFull = false;
//                            else
//                                isTextOneFull = true;
//                        }
//                        else {
//                            isTextOneFull = false;
//                        }
//                        //set Text Two
//                    }
//                } else {
//                    currentText = textViewTwo.getText().toString();
//                    if (currentText.equals(String.valueOf("0")) || isChangedActive) {
//                        textViewTwo.setText("9");
//                        if (isChangedActive)
//                            isChangedActive = false;
//                    } else if (!isTextTwoFull) {
//                        textViewTwo.setText(currentText + "9");
//                        currentText = textViewTwo.getText().toString();
//                        if (currentText.contains(".")) {
//                            String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            if (subString.length() < 2)
//                                isTextTwoFull = false;
//                            else
//                                isTextTwoFull = true;
//                        }
//                        else {
//                            isTextTwoFull = false;
//                        }
//                        //set Text One
//                    }
//                }
                clickNumberButtonHandler("9");
            }
        });





    }


    public void clickNumberButtonHandler(String number) {
        if (isTextOneActive) {
            currentText = textViewOne.getText().toString();
            if (currentText.equals(String.valueOf("0")) || isChangedActive) {
                textViewOne.setText(number);
                if (isChangedActive)
                    isChangedActive = false;
            } else if (!isTextOneFull) {
                textViewOne.setText(currentText + number);
                currentText = textViewOne.getText().toString();
                if (currentText.contains(".")) {
                    String subString = currentText.substring(currentText.indexOf('.') + 1);
//                            Log.v("TAG", subString);
                    if (subString.length() < 2)
                        isTextOneFull = false;
                    else
                        isTextOneFull = true;
                }
                else {
                    isTextOneFull = false;
                }
            }
            //set Text Two
            setTextTwo();
//            String tempText = textViewOne.getText().toString();
////            double number_line_1 = getNumberFromString(tempText);
//            double number_line_1 = Double.parseDouble(tempText);
//            Log.v("TAG", "Number on line 1: " + number_line_1);
//            double number_line_2 = (number_line_1 * 1.0 * weightItems[indexOne] / weightItems[indexTwo]);
//            textViewTwo.setText(numberFormat2.format(number_line_2));
        } else {
            currentText = textViewTwo.getText().toString();
            if (currentText.equals(String.valueOf("0")) || isChangedActive) {
                textViewTwo.setText(number);
                if (isChangedActive)
                    isChangedActive = false;
            } else if (!isTextTwoFull) {
                textViewTwo.setText(currentText + number);
                currentText = textViewTwo.getText().toString();
                if (currentText.contains(".")) {
                    String subString = currentText.substring(currentText.indexOf('.') + 1);
                    if (subString.length() < 2)
                        isTextTwoFull = false;
                    else
                        isTextTwoFull = true;
                }
                else {
                    isTextTwoFull = false;
                }

            }
            //set Text One
            setTextOne();
//            String tempText = textViewTwo.getText().toString();
////            double number_line_2 = getNumberFromString(tempText);
//            double number_line_2 = Double.parseDouble(tempText);
//            Log.v("TAG", "Number on line 2: " + number_line_2);
//            double number_line_1 = (number_line_2 * 1.0 * weightItems[indexTwo] / weightItems[indexOne]);
//            textViewOne.setText(numberFormat2.format(number_line_1));
        }
    }

    // Lấy dữ liệu số từ text
    public double getNumberFromString(String str) {
        double res = 0;
        if (str.contains(".")) {
            String subString1 = str.substring(0, str.indexOf('.'));
//            Log.v("TAG", subString1);
            String subString2 = str.substring(str.indexOf('.') + 1);
//            Log.v("TAG", subString2);

            for (int i = 0; i < subString1.length(); i++) {
                int ai = Integer.parseInt(String.valueOf(subString1.charAt(i)));
                res += (double) ai * Math.pow(10, subString1.length() - i - 1);
            }


            for (int i = 0; i < subString2.length(); i++) {
                int ai = Integer.parseInt(String.valueOf(subString2.charAt(i)));
                res += (double) ai * Math.pow(10, - i - 1);
            }

        }
        else {
            for (int i = 0; i < str.length(); i++) {
                int ai = Integer.parseInt(String.valueOf(str.charAt(i)));
                res += (double) ai * Math.pow(10, str.length() - i - 1);
            }
        }
        return res;
    }

    //setText Two when have text one
    public void setTextTwo() {
        String tempText = textViewOne.getText().toString();
        double number_line_1 = getNumberFromString(tempText);
        Log.v("TAG", "Number on line 1: " + number_line_1);
        double number_line_2 = (number_line_1 * 1.0 * weightItems[indexOne] / weightItems[indexTwo]);
        textViewTwo.setText(numberFormat2.format(number_line_2));
    }

    public void setTextOne() {
        String tempText = textViewTwo.getText().toString();
        double number_line_2 = getNumberFromString(tempText);
        Log.v("TAG", "Number on line 2: " + number_line_2);
        double number_line_1 = (number_line_2 * 1.0 * weightItems[indexTwo] / weightItems[indexOne]);
        textViewOne.setText(numberFormat2.format(number_line_1));
    }


}