package ru.geekbrains.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String KEY = "Counter";

    private int counter;                    // Счетчик

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        String instanceState;
        if (savedInstanceState == null)
            instanceState = "Первый запуск!";
        else
            instanceState = "Повторный запуск!";

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        // Получить Presenter
        final LifeCyclePresenter presenter = LifeCyclePresenter.getInstance();

        final TextView textCounter = findViewById(R.id.textCounter);     // Текст
        // Выводим счетчик в поле
        counter = presenter.getCounter();
        textCounter.setText(String.valueOf(counter));

        Button button = findViewById(R.id.button);         // Кнопка
        button.setOnClickListener(new View.OnClickListener() {      // Обработка нажатий
            @Override
            public void onClick(View v) {
                presenter.incrementCounter();   // Увеличиваем счетчик на единицу
                // Выводим счетчик в поле
                counter = presenter.getCounter();
                textCounter.setText(String.valueOf(counter));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putInt(KEY, counter);               // Сохраняем счетчик
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        counter = saveInstanceState.getInt(KEY);              // Восстанавливаем счетчик
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
