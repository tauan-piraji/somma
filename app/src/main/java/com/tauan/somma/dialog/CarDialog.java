package com.tauan.somma.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tauan.somma.R;

public class CarDialog {

    private Activity activity;
    private String titulo;

    private TextView dialogTextTitulo, dialogEditCarro, dialogEditKml;
    private Button dialogBtnConfirmar;

    private AlertDialog dialog;

    public CarDialog(final Activity activity, final String titulo) {
        this.activity = activity;
        this.titulo = titulo;
    }

    public void show(View.OnClickListener actionConfirmarItens) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_car_edit, null);

        builder.setView(view);


        dialogTextTitulo = view.findViewById(R.id.dialogTextTitulo);
        dialogTextTitulo.setText(titulo);

        dialogEditCarro = view.findViewById(R.id.DialogEditCarro);
        dialogEditKml = view.findViewById(R.id.DialogEditKml);

        dialogBtnConfirmar = view.findViewById(R.id.DialogBtnConfirmar);
        dialogBtnConfirmar.setOnClickListener(actionConfirmarItens);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public String getCarro() {
        return dialogEditCarro.getText().toString();
    }

    public int getKml() {
        return Integer.parseInt(dialogEditKml.getText().toString());
    }

    public void Cancel() {
        dialog.cancel();
    }
}