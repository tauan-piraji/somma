package com.tauan.somma.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tauan.somma.R;

public class NotaDialog {

    private Activity activity;

    private TextView dialogEditNota;
    private Button dialogBtnConfirmar;

    private AlertDialog dialog;

    public NotaDialog(final Activity activity) {
        this.activity = activity;
    }

    public void show(View.OnClickListener actionConfirmarItens) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nota_edit, null);

        builder.setView(view);


        dialogEditNota = view.findViewById(R.id.dialogEditNota);

        dialogBtnConfirmar = view.findViewById(R.id.DialogBtnConfirmar);
        dialogBtnConfirmar.setOnClickListener(actionConfirmarItens);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public float getNota() {
        return Float.parseFloat(dialogEditNota.getText().toString());
    }

    public void Cancel() {
        dialog.cancel();
    }
}