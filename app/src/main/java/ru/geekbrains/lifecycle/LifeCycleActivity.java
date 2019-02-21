package ru.geekbrains.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
        запустили
        2019-02-21 14:58:59.613 25035-25035/? D/LifeCycleActivity: Первый запуск! - onCreate()
        2019-02-21 14:58:59.634 25035-25035/? D/LifeCycleActivity: onStart:
        2019-02-21 14:58:59.657 25035-25035/? D/LifeCycleActivity: onResume:
        перевернули
        2019-02-21 14:59:10.168 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onPause:
        2019-02-21 14:59:10.187 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onSaveInstanceState:
        2019-02-21 14:59:10.205 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStop:
        2019-02-21 14:59:10.223 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onDestroy:
        2019-02-21 14:59:10.414 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: Повторный запуск! - onCreate()
        2019-02-21 14:59:10.432 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStart:
        2019-02-21 14:59:10.449 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: Повторный запуск!! - onRestoreInstanceState()
        2019-02-21 14:59:10.469 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onResume:
        клавиша "назад"
        2019-02-21 14:59:22.948 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onPause:
        2019-02-21 14:59:23.329 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStop:
        2019-02-21 14:59:23.352 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onDestroy:
        опыть вызвали из памяти
        2019-02-21 14:59:49.385 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: Первый запуск! - onCreate()
        2019-02-21 14:59:49.404 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStart:
        2019-02-21 14:59:49.424 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onResume:
        клавиша "домой"
        2019-02-21 15:00:01.804 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onPause:
        2019-02-21 15:00:01.841 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onSaveInstanceState:
        2019-02-21 15:00:01.864 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStop:
        опыть вызвали из памяти
        2019-02-21 15:00:12.466 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onRestart:
        2019-02-21 15:00:12.482 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStart:
        2019-02-21 15:00:12.505 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onResume:
        клавиша "назад"
        2019-02-21 15:00:21.620 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onPause:
        2019-02-21 15:00:22.192 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onStop:
        2019-02-21 15:00:22.213 25035-25035/ru.geekbrains.lifecycle D/LifeCycleActivity: onDestroy:
*/

public class LifeCycleActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycleActivity";
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
        Log.d(TAG, instanceState + " - onCreate()");

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
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        counter = saveInstanceState.getInt(KEY);              // Восстанавливаем счетчик
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()",
                Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy: ");
    }
}
