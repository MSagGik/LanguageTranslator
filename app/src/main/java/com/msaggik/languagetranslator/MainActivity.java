package com.msaggik.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // новые переменные
    private EditText textIn; // переводимое слово или фраза
    private TextView textOut; // переведённое слово или фраза

    // создание контейнеров для словарей
    private static HashMap<String, String> ruEn = new HashMap<>();
    private static HashMap<String, String> enRu = new HashMap<>();

    // значение радиокнопки
    String radioValue = "";


    // создание жизненного цикла активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // присваивание жизненному циклу активити представления activity_main

        // присваивание переменным активити элементов представления activity_main
        textIn = findViewById(R.id.textIn); // ввод
        textOut = findViewById(R.id.textOut); // вывод
        RadioButton btnRuEn = findViewById(R.id.radioButtonEnRu); // кнопка англо-русского словаря
        RadioButton btnEnRu = findViewById(R.id.radioButtonRuEn); // кнопка русско-английского словаря
        Button btn = findViewById(R.id.button); // кнопка перевода

        // инициализация (заполнение) словарей
        dataInfo();

        // выполнение действий при нажании кнопки
        btnRuEn.setOnClickListener(listenerRadio); // обработка нажатия радио-кнопки
        btnEnRu.setOnClickListener(listenerRadio); // обработка нажатия радио-кнопки
        btn.setOnClickListener(listener); // обработка нажатия кнопки "перевести"
    }

    // метод обработки нажатия радио-кнопок
    private View.OnClickListener listenerRadio = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton radioBtn = (RadioButton) view; // присваивание view выбранного значения радио-книпки

            switch (radioBtn.getId()) {
                case R.id.radioButtonRuEn: // если radioBtn.getId() равно значению включённой кнопки R.id.radioButtonRuEn
                    radioValue = "ru"; // то выполняется эта и следующая строка
                    break; // данная строка обрывает switch()
                case R.id.radioButtonEnRu:
                    radioValue = "en";
                    break;
                default: // если ни одна кнопка не нажата
                    radioValue = "empty";
                    break;
            }
        }
    };
    // метод обработки нажатия кнопки "перевести"
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String dataIn = ""; // начальное поле ввода пустое

            // запись в поле dataIn написанного пользователем текста
            dataIn = textIn.getText().toString();

            // используется значение нажатой/не нажатой радио-кнопки
            switch (radioValue) {
                case "ru":
                    textOut.setText(ruEn.get(dataIn)); // textOut.setText() - запись значения () в поле textOut;
                    if(ruEn.get(dataIn) == null) {
                        textOut.setText("В словаре такого слова не нашлось");
                    }
                    // ruEn.get(dataIn) - считывание из словаря значения по значению ключа dataIn
                    break;
                case "en":
                    textOut.setText(enRu.get(dataIn));
                    if(enRu.get(dataIn) == null) {
                        textOut.setText("В словаре такого слова не нашлось");
                    }
                    break;
                default:
                    textOut.setText("Вы не выбрали настройки переводчика");
            }
        }
    };

    // метод заполнения словарей
    public static void dataInfo() {
        // добавление данных для русско-английского словаря
        ruEn.put("я","i");
        ruEn.put("ты","you");
        ruEn.put("он","he");
        ruEn.put("она","she");
        ruEn.put("мы","we");
        ruEn.put("они","they");
        ruEn.put("оно","it");

        // добавление данных для англо-русского словаря
        enRu.put("i","я");
        enRu.put("you","ты");
        enRu.put("he","он");
        enRu.put("she","она");
        enRu.put("we","мы");
        enRu.put("they","они");
        enRu.put("it","оно");
    }
}